package com.tuccicode.backstage.app.system.dto.body;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author tucci.lee
 */
@Data
public class SysRoleUpdateResBody {

    @NotNull
    private Long id;

    @NotEmpty
    private List<Long> resIds;
}
