package com.tuccicode.backstage.app.shiro.credential;

import com.tuccicode.backstage.domain.system.entity.SysUser;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * @author tucci.lee
 */
public class BCryptCredentialsMatcher implements CredentialsMatcher {

    /**
     * 校验密码是否正确
     *
     * @param token 登陆填写的密码
     * @param info  真实的密码
     * @return boolean
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String loginCredential = String.valueOf((char[]) token.getCredentials());
        String userCredential = (String) info.getCredentials();
        return SysUser.verifyPassword(loginCredential, userCredential);
    }
}
