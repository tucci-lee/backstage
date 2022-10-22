package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.app.system.dto.body.LoginBody;
import com.tuccicode.backstage.app.util.WebUtils;
import com.tuccicode.backstage.domain.core.exception.BackstageBizCode;
import com.tuccicode.backstage.domain.system.entity.Captcha;
import com.tuccicode.backstage.domain.system.entity.SysLoginLog;
import com.tuccicode.backstage.domain.system.service.ImageCaptchaService;
import com.tuccicode.backstage.domain.system.service.SysLoginLogService;
import com.tuccicode.concise.dto.Response;
import com.tuccicode.concise.exception.BizException;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class LoginExe {

    private final ImageCaptchaService imageCaptchaService;
    private final SysLoginLogService sysLoginLogService;

    public LoginExe(ImageCaptchaService imageCaptchaService,
                    SysLoginLogService sysLoginLogService) {
        this.imageCaptchaService = imageCaptchaService;
        this.sysLoginLogService = sysLoginLogService;
    }

    /**
     * 校验验证码
     * shiro登录
     * 记录日志（无论成功失败）
     *
     * @param body 登录参数
     * @return Response
     */
    public Response execute(LoginBody body, String captchaKey) {
        boolean status = true;
        String message = "";
        try {
            imageCaptchaService.verify(Captcha.Type.LOGIN, captchaKey, body.getCaptcha());

            UsernamePasswordToken token = new UsernamePasswordToken(body.getUsername(), body.getPassword(), body.getRememberMe());
            try {
                SecurityUtils.getSubject().login(token);
            } catch (UnknownAccountException | CredentialsException e) {
                throw new BizException(BackstageBizCode.ACCOUNT_OR_PASSWORD_ERROR);
            } catch (LockedAccountException e) {
                throw new BizException(BackstageBizCode.ACCOUNT_LOCKED);
            }
        } catch (Exception e) {
            status = false;
            message = e.getMessage();
            throw e;
        } finally {
            String userAgentStr = WebUtils.getRequest().getHeader("User-Agent");
            String ip = WebUtils.getIp();
            // 获取浏览器信息（操作系统，浏览器）
            UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
            String os = userAgent.getOperatingSystem().getName();
            String browser = userAgent.getBrowser().getName();
            SysLoginLog log = new SysLoginLog()
                    .setUsername(body.getUsername())
                    .setOs(os)
                    .setBrowser(browser)
                    .setStatus(status)
                    .setMessage(message)
                    .setIp(ip);
            sysLoginLogService.create(log);
        }
        return Response.success();
    }
}
