package com.tuccicode.backstage.domain.system.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;

/**
 * @author tucci.lee
 */
@Data
@Accessors(chain = true)
public class SysUser {

    private static final Long ADMIN_UID = 1L;

    private Long uid;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String nickname;
    private String remarks;
    private Long deptId;
    private Long version;
    private Boolean isLock;
    private Long createTime;
    private Long updateTime;
    private List<Long> roleIds;

    public static String password(String plaintext){
        return BCrypt.hashpw(plaintext, BCrypt.gensalt());
    }

    public static boolean verifyPassword(String plaintext, String ciphertext) {
        try {
            return BCrypt.checkpw(plaintext, ciphertext);
        } catch (RuntimeException e) {
            return false;
        }
    }

    public static boolean isAdmin(Long uid){
        return ADMIN_UID.equals(uid);
    }
}
