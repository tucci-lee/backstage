package com.tuccicode.backstage.app.crontab.exe;

import com.tuccicode.backstage.app.crontab.dto.body.CrontabCreateBody;
import com.tuccicode.backstage.app.crontab.job.JobUtils;
import com.tuccicode.backstage.domain.crontab.entity.Crontab;
import com.tuccicode.backstage.domain.crontab.service.CrontabService;
import com.tuccicode.concise.dto.Response;
import org.quartz.Scheduler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class CrontabCreateExe {

    private final Scheduler scheduler;
    private final CrontabService crontabService;

    public CrontabCreateExe(Scheduler scheduler,
                            CrontabService crontabService) {
        this.scheduler = scheduler;
        this.crontabService = crontabService;
    }

    public Response execute(CrontabCreateBody body) {
        Crontab crontab = new Crontab()
                .setStatus(false);
        BeanUtils.copyProperties(body, crontab);
        Long id = crontabService.create(crontab);
        crontab.setId(id);

        JobUtils.createJob(scheduler, crontab);

        return Response.success();
    }
}
