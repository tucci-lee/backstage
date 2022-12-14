package com.tuccicode.backstage.app.system.dto.body;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author tucci.lee
 */
@Data
public class SysRoleCreateBody {

    @NotBlank
    @Length(max = 20)
    private String name;

    @Size(max = 50)
    private String roleChar;

    @Size(max = 200)
    private String remarks;

    @NotEmpty
    private List<Long> resIds;
}
