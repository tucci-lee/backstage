package com.tuccicode.backstage.domain.crontab.dataobject;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author tucci.lee
 */
@Data
@Accessors(chain = true)
@TableName("crontab_log")
public class CrontabLogDO implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long crontabId;
    private Boolean status;
    private String message;
    private Long startTime;
    private Long runTime;
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

}
