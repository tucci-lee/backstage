package com.tuccicode.backstage.domain.system.dataobject;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tucci.lee
 */
@Data
@Accessors(chain = true)
@TableName("sys_res")
public class SysResDO {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private Integer type;
    private String url;
    private Long pid;
    private String resChar;
    private Integer seq;
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Long updateTime;
    private Boolean isDeleted;

}