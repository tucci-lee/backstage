package com.tuccicode.backstage.app.system.service;

import com.tuccicode.backstage.app.system.dto.body.ChangePasswordBody;
import com.tuccicode.backstage.app.system.dto.body.LoginBody;
import com.tuccicode.backstage.app.system.exe.ChangePasswordExe;
import com.tuccicode.backstage.app.system.exe.LoginExe;
import com.tuccicode.backstage.app.system.exe.LogoutExe;
import com.tuccicode.concise.dto.Response;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class AuthorizeAppService {

    private final LoginExe loginExe;
    private final LogoutExe logoutExe;
    private final ChangePasswordExe changePasswordExe;

    public AuthorizeAppService(LoginExe loginExe,
                               LogoutExe logoutExe,
                               ChangePasswordExe changePasswordExe) {
        this.loginExe = loginExe;
        this.logoutExe = logoutExe;
        this.changePasswordExe = changePasswordExe;
    }

    public Response login(LoginBody body, String captchaKey) {
        return loginExe.execute(body, captchaKey);
    }

    public Response logout() {
        return logoutExe.execute();
    }

    public Response changePassword(ChangePasswordBody body) {
        return changePasswordExe.execute(body);
    }
}
