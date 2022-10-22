package com.tuccicode.backstage.domain.system.entity;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tucci.lee
 */
@Data
@Accessors(chain = true)
public class SysDept {

    public static final Long TOP_ID = 0L;

    private Long id;
    private String name;
    private Long pid;
    private Integer seq;
    private String manager;
    private String managerPhone;
    private Long createTime;
    private Long updateTime;

}
