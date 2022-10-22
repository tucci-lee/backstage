package com.tuccicode.backstage.app.crontab.exe;

import com.tuccicode.backstage.app.crontab.dto.body.CrontabUpdateStatusBody;
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
public class CrontabUpdateStatusExe {

    private final Scheduler scheduler;
    private final CrontabService crontabService;

    public CrontabUpdateStatusExe(Scheduler scheduler,
                                  CrontabService crontabService) {
        this.scheduler = scheduler;
        this.crontabService = crontabService;
    }

    public Response execute(CrontabUpdateStatusBody body) {
        Crontab crontab = new Crontab();
        BeanUtils.copyProperties(body, crontab);
        crontabService.updateStatus(crontab);

        if (crontab.getStatus()) {
            JobUtils.resumeJob(scheduler, body.getId().toString());
        } else {
            JobUtils.pauseJob(scheduler, body.getId().toString());
        }
        return Response.success();
    }
}
