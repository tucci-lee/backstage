package com.tuccicode.backstage.app.system.dto.body;


import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author tucci.lee
 */
@Data
public class SysUserUpdateLockBody {

    @NotNull
    private Long uid;

    @NotNull
    private Boolean isLock;
}
