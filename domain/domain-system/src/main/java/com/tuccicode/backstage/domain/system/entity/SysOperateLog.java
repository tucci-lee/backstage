package com.tuccicode.backstage.domain.system.entity;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * @author tucci.lee
 */
@Data
@Accessors(chain = true)
public class SysOperateLog {

    private Long id;
    private String username;
    private String ip;
    private String url;
    private String method;
    private String params;
    private String result;
    private String description;
    private String errorMessage;
    private Long createTime;
    private Boolean status;

}