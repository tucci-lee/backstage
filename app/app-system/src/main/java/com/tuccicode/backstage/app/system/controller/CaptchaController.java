package com.tuccicode.backstage.app.system.controller;

import com.tuccicode.backstage.app.system.service.CaptchaAppService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author tucci.lee
 */
@RestController
@RequestMapping("captcha")
public class CaptchaController {

    private final CaptchaAppService captchaService;

    public CaptchaController(CaptchaAppService captchaService) {
        this.captchaService = captchaService;
    }

    /**
     * 生成图片验证码
     */
    @GetMapping("image")
    public void image(HttpServletResponse response, HttpSession session) throws IOException {
        captchaService.generate(response, session.getId());
    }
}
