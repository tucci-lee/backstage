package com.tuccicode.backstage.app.shiro.realm;

import com.tuccicode.backstage.app.shiro.credential.BCryptCredentialsMatcher;
import com.tuccicode.backstage.domain.system.entity.SysRes;
import com.tuccicode.backstage.domain.system.entity.SysUser;
import com.tuccicode.backstage.domain.system.service.SysResService;
import com.tuccicode.backstage.domain.system.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 处理账号密码登陆的Realm
 *
 * @author tucci.lee
 */
@Component
public class AccountRealm extends AuthorizingRealm {

    private final SysUserService sysUserService;
    private final SysResService sysResService;

    public AccountRealm(SysUserService sysUserService,
                        SysResService sysResService) {
        this.sysUserService = sysUserService;
        this.sysResService = sysResService;
        // 凭证校验器
        this.setCredentialsMatcher(new BCryptCredentialsMatcher());
    }

    /**
     * 授权：获取用户的权限
     *
     * @param principalCollection principal
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        long uid = sysUser.getUid();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        if (SysUser.isAdmin(uid)) {
            info.addStringPermission("*");
        } else {
            List<String> resChars = sysResService.listByUid(uid)
                    .stream()
                    .filter(sysRes -> sysRes.getResChar() != null && !sysRes.getResChar().isEmpty())
                    .map(SysRes::getResChar)
                    .collect(Collectors.toList());
            info.addStringPermissions(resChars);
        }
        return info;
    }

    /**
     * 认证：用户登陆
     *
     * @param token Subject.login传入的token
     * @return AuthenticationInfo
     * @throws AuthenticationException exception
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        SysUser sysUser = sysUserService.getByUsername((String) token.getPrincipal());
        if (sysUser == null) {
            throw new UnknownAccountException();
        }
        if (sysUser.getIsLock()) {
            throw new LockedAccountException();
        }
        String userPassword = sysUser.getPassword();
        sysUser.setPassword(null);
        // 第一个参数可以通过SecurityUtils.getSubject().getPrincipal()获取
        return new SimpleAuthenticationInfo(sysUser, userPassword, getName());
    }
}
