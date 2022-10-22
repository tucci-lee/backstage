package com.tuccicode.backstage.domain.crontab.dataobject;

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
@TableName("crontab")
public class CrontabDO {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private String className;
    private String cron;
    private Boolean status;
    private String remarks;
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Long updateTime;
    private Boolean isDeleted;

}
