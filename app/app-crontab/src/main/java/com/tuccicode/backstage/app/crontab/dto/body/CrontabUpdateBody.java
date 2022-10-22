package com.tuccicode.backstage.app.crontab.dto.body;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author tucci.lee
 */
@Data
public class CrontabUpdateBody {

    @NotNull
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String name;

    @NotBlank
    @Size(max = 200)
    private String className;

    @NotBlank
    @Size(max = 100)
    private String cron;

    @Size(max = 200)
    private String remarks;
}
