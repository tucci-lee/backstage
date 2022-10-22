package com.tuccicode.backstage.app.system.service;

import com.tuccicode.backstage.app.system.dto.body.SysDeptCreateBody;
import com.tuccicode.backstage.app.system.dto.body.SysDeptUpdateBody;
import com.tuccicode.backstage.app.system.exe.SysDeptCreateExe;
import com.tuccicode.backstage.app.system.exe.SysDeptDeleteExe;
import com.tuccicode.backstage.app.system.exe.SysDeptTreeExe;
import com.tuccicode.backstage.app.system.exe.SysDeptUpdateExe;
import com.tuccicode.concise.dto.Response;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class SysDeptAppService {

    private final SysDeptTreeExe sysDeptTreeExe;
    private final SysDeptCreateExe sysDeptCreateExe;
    private final SysDeptDeleteExe sysDeptDeleteExe;
    private final SysDeptUpdateExe sysDeptUpdateExe;

    public SysDeptAppService(SysDeptTreeExe sysDeptTreeExe,
                             SysDeptCreateExe sysDeptCreateExe,
                             SysDeptDeleteExe sysDeptDeleteExe,
                             SysDeptUpdateExe sysDeptUpdateExe) {
        this.sysDeptTreeExe = sysDeptTreeExe;
        this.sysDeptCreateExe = sysDeptCreateExe;
        this.sysDeptDeleteExe = sysDeptDeleteExe;
        this.sysDeptUpdateExe = sysDeptUpdateExe;
    }

    public Response tree() {
        return sysDeptTreeExe.execute();
    }

    public Response create(SysDeptCreateBody body) {
        return sysDeptCreateExe.execute(body);
    }

    public Response delete(Long id) {
       return sysDeptDeleteExe.execute(id);
    }

    public Response update(SysDeptUpdateBody body) {
        return sysDeptUpdateExe.execute(body);
    }
}
