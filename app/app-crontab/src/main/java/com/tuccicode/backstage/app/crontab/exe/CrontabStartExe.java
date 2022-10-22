package com.tuccicode.backstage.app.crontab.exe;

import com.tuccicode.backstage.app.crontab.job.JobUtils;
import com.tuccicode.backstage.domain.crontab.entity.Crontab;
import com.tuccicode.backstage.domain.crontab.service.CrontabService;
import com.tuccicode.concise.dto.Response;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class CrontabStartExe {

    private final Scheduler scheduler;
    private final CrontabService crontabService;

    public CrontabStartExe(Scheduler scheduler,
                           CrontabService crontabService) {
        this.scheduler = scheduler;
        this.crontabService = crontabService;
    }

    public Response execute(Long id) {
        Crontab crontab = crontabService.getById(id);
        JobUtils.startJob(scheduler, crontab.getId().toString());
        return Response.success();
    }
}
