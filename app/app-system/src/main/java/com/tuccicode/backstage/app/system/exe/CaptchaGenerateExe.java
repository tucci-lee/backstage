package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.domain.system.entity.Captcha;
import com.tuccicode.backstage.domain.system.service.ImageCaptchaService;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author tucci.lee
 */
@Service
public class CaptchaGenerateExe {

    private final ImageCaptchaService imageCaptchaService;

    public CaptchaGenerateExe(ImageCaptchaService imageCaptchaService) {
        this.imageCaptchaService = imageCaptchaService;
    }

    public void execute(HttpServletResponse response, String key) throws IOException {
        BufferedImage image = imageCaptchaService.generate(Captcha.Type.LOGIN, key);
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(image, "jpg", os);
    }
}
