package com.tuccicode.backstage.app.system.dto.body;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author tucci.lee
 */
@Data
public class SysUserUpdateBody {

    @NotNull
    private Long uid;

    @Pattern(regexp = "^[1][3-9][0-9]{9}$")
    private String phone;

    @Email
    private String email;

    @Size(max = 20)
    private String nickname;

    @Size(max = 200)
    private String remarks;

    private Long deptId;
}
