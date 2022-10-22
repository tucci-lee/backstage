package com.tuccicode.backstage.domain.system.entity;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * @author tucci.lee
 */
@Data
@Accessors(chain = true)
public class SysRes {

    public static final Long TOP_ID = 0L;

    private Long id;
    private String name;
    private Integer type;
    private String url;
    private Long pid;
    private String resChar;
    private Integer seq;
    private Long createTime;
    private Long updateTime;

}