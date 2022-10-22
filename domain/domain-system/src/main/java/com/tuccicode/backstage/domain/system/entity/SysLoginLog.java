package com.tuccicode.backstage.domain.system.entity;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * @author tucci.lee
 */
@Data
@Accessors(chain = true)
public class SysLoginLog {

    private Long id;
    private String username;
    private String os;
    private String browser;
    private String ip;
    private Boolean status;
    private String message;
    private Long createTime;

}
