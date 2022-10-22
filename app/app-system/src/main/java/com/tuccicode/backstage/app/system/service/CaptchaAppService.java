package com.tuccicode.backstage.app.system.service;

import com.tuccicode.backstage.app.system.exe.CaptchaGenerateExe;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tucci.lee
 */
@Service
public class CaptchaAppService {

    private final CaptchaGenerateExe captchaGenerateExe;

    public CaptchaAppService(CaptchaGenerateExe captchaGenerateExe) {
        this.captchaGenerateExe = captchaGenerateExe;
    }


    /**
     * 生成图片验证码
     *
     * @param key 验证码的key
     */
    public void generate(HttpServletResponse response, String key) throws IOException {
        captchaGenerateExe.execute(response, key);
    }
}
