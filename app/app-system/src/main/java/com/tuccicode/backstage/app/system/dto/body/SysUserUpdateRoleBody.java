package com.tuccicode.backstage.app.system.dto.body;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author tucci.lee
 */
@Data
public class SysUserUpdateRoleBody {

    @NotNull
    private Long uid;

    @NotEmpty
    private List<Long> roleIds;
}
