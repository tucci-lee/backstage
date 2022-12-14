package com.tuccicode.backstage.app.system.dto.body;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author tucci.lee
 */
@Data
public class SysUserCreateBody {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]{3,9}$")
    private String username;

    @NotBlank
    @Length(min = 6, max = 32)
    private String password;

    @Pattern(regexp = "^[1][3-9][0-9]{9}$")
    private String phone;

    @Email
    private String email;

    @Size(max = 20)
    private String nickname;

    @Size(max = 200)
    private String remarks;

    private Long deptId;

    @NotEmpty
    private List<Long> roleIds;
}
