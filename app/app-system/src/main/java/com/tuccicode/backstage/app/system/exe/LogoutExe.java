package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.concise.dto.Response;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class LogoutExe {


    public Response execute() {
        SecurityUtils.getSubject().logout();
        return Response.success();
    }
}
