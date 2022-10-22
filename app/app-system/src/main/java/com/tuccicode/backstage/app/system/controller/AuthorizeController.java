package com.tuccicode.backstage.app.system.controller;

import com.tuccicode.backstage.app.aspect.Limit;
import com.tuccicode.backstage.app.system.dto.body.ChangePasswordBody;
import com.tuccicode.backstage.app.system.dto.body.LoginBody;
import com.tuccicode.backstage.app.system.service.AuthorizeAppService;
import com.tuccicode.concise.dto.Response;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author tucci.lee
 */
@RestController
public class AuthorizeController {

    private final AuthorizeAppService authorizeAppService;

    public AuthorizeController(AuthorizeAppService authorizeAppService) {
        this.authorizeAppService = authorizeAppService;
    }

    /**
     * 登录
     */
    @Limit(key = "#body.username", rate = 5, rateInterval = 5 * 60)
    @PostMapping("login")
    public Response login(@Validated @RequestBody LoginBody body, HttpSession session) {
        return authorizeAppService.login(body, session.getId());
    }

    /**
     * 退出登录
     */
    @PostMapping("logout")
    public Response logout() {
        return authorizeAppService.logout();
    }

    /**
     * 修改自己的密码
     */
    @PutMapping("change_password")
    public Response changePassword(@Validated @RequestBody ChangePasswordBody body) {
        return authorizeAppService.changePassword(body);
    }
}
