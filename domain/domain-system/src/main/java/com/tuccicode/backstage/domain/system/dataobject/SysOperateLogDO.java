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
@TableName("sys_operate_log")
public class SysOperateLogDO {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String username;
    private String ip;
    private String url;
    private String method;
    private String params;
    private String result;
    private String description;
    private String errorMessage;
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;
    private Boolean status;

}