package com.tuccicode.backstage.app.crontab.dto.body;


import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author tucci.lee
 */
@Data
public class CrontabUpdateStatusBody {

    @NotNull
    private Long id;

    @NotNull
    private Boolean status;
}
