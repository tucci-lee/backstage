package com.tuccicode.backstage.domain.system.entity;

/**
 * @author tucci.lee
 */
public class Captcha {

    /**
     * 登录验证码key
     */
    static final String CAPTCHA_LOGIN_KEY = "captcha:login:";
    /**
     * 登录验证码过期时间
     */
    public static final int CAPTCHA_LOGIN_EXPIRE = 5 * 60;
    public static String getCaptchaLoginKey(String key) {
        return CAPTCHA_LOGIN_KEY + key;
    }

    /**
     * 验证码类型
     */
    public static class Type {
        /**
         * 登录
         */
        public static final int LOGIN = 101;
    }
}
