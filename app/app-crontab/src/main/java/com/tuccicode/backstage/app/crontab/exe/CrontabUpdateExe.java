package com.tuccicode.backstage.app.crontab.exe;

import com.tuccicode.backstage.app.crontab.dto.body.CrontabUpdateBody;
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
public class CrontabUpdateExe {

    private final Scheduler scheduler;
    private final CrontabService crontabService;

    public CrontabUpdateExe(Scheduler scheduler,
                            CrontabService crontabService) {
        this.scheduler = scheduler;
        this.crontabService = crontabService;
    }

    public Response execute(CrontabUpdateBody body) {
        Crontab oldCrontab = crontabService.getById(body.getId());

        Crontab crontab = new Crontab();
        BeanUtils.copyProperties(body, crontab);
        crontabService.update(crontab);
        crontab.setStatus(oldCrontab.getStatus());

        JobUtils.deleteJob(scheduler, body.getId().toString());
        JobUtils.createJob(scheduler, crontab);

        return Response.success();
    }
}
