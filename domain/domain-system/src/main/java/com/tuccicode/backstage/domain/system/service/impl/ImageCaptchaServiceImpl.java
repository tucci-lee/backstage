package com.tuccicode.backstage.domain.system.service.impl;

import com.google.code.kaptcha.Producer;
import com.tuccicode.backstage.domain.core.cache.CacheOperate;
import com.tuccicode.backstage.domain.core.exception.BackstageBizCode;
import com.tuccicode.backstage.domain.system.entity.Captcha;
import com.tuccicode.backstage.domain.system.service.ImageCaptchaService;
import com.tuccicode.concise.exception.Assert;
import com.tuccicode.concise.exception.BizException;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * @author tucci.lee
 */
@Service
public class ImageCaptchaServiceImpl implements ImageCaptchaService {

    private final Producer producer;
    private final CacheOperate cacheOperate;

    public ImageCaptchaServiceImpl(Producer producer,
                                   CacheOperate cacheOperate) {
        this.producer = producer;
        this.cacheOperate = cacheOperate;
    }

    @Override
    public BufferedImage generate(Integer type, String key) {
        String captcha = producer.createText();
        BufferedImage image = producer.createImage(captcha);
        String cacheKey = this.getKey(type, key);
        cacheOperate.set(cacheKey, captcha, Captcha.CAPTCHA_LOGIN_EXPIRE);
        return image;
    }

    @Override
    public void verify(Integer type, String key, String captcha) {
        Assert.notEmpty(captcha, BackstageBizCode.IMAGE_CAPTCHA_ERROR);
        String cacheKey = this.getKey(type, key);
        String cacheCaptcha = cacheOperate.get(cacheKey);
        Assert.isTrue(Objects.equals(captcha, cacheCaptcha), BackstageBizCode.IMAGE_CAPTCHA_ERROR);
        cacheOperate.delete(key);
    }


    protected String getKey(int type, String key) {
        Assert.notEmpty(key, BackstageBizCode.CAPTCHA_TYPE_ERROR);
        switch (type) {
            case Captcha.Type.LOGIN:
                return Captcha.getCaptchaLoginKey(key);
            default:
                throw new BizException(BackstageBizCode.CAPTCHA_TYPE_ERROR);
        }
    }
}
