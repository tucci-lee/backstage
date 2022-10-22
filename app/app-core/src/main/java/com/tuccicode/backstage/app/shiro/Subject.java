package com.tuccicode.backstage.app.shiro;

import com.tuccicode.backstage.domain.system.entity.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * @author tucci.lee
 */
public class Subject {

    public static SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getUid() {
        return getUser().getUid();
    }
}
