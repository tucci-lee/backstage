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
@TableName("sys_login_log")
public class SysLoginLogDO {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String username;
    private String os;
    private String browser;
    private String ip;
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;
    private Boolean status;
    private String message;

}