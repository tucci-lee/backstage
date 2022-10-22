package com.tuccicode.backstage.app.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.tuccicode.backstage.app.shiro.Subject;
import com.tuccicode.backstage.domain.core.exception.BackstageBizCode;
import com.tuccicode.backstage.domain.system.entity.SysUser;
import com.tuccicode.backstage.domain.system.service.SysUserService;
import com.tuccicode.concise.dto.Response;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author tucci.lee
 */
public class UserFilter extends AccessControlFilter {

    private final SysUserService sysUserService;

    public UserFilter(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 判断用户是否登陆
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        // 如果没登录直接返回false
        SysUser user = Subject.getUser();
        if(user == null){
            return false;
        }
        // 如果当前登录用户的版本号不等于数据库的版本号返回false
        SysUser userDb = sysUserService.getByUid(user.getUid());
        return Objects.equals(user.getVersion(), userDb.getVersion());
    }

    /**
     * 未登陆的错误处理
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = (HttpServletResponse) response;
        res.setContentType("application/json;charset=UTF-8");
        BackstageBizCode bizCode = BackstageBizCode.UNAUTHENTICATED;
        String result = JSON.toJSONString(Response.fail(bizCode.getCode(), bizCode.getMessage()));
        res.getWriter().print(result);
        return false;
    }
}
