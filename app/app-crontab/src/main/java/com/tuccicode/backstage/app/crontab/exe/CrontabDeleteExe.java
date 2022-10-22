package com.tuccicode.backstage.app.crontab.exe;

import com.tuccicode.backstage.app.crontab.job.JobUtils;
import com.tuccicode.backstage.domain.crontab.service.CrontabService;
import com.tuccicode.concise.dto.Response;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class CrontabDeleteExe {

    private final Scheduler scheduler;
    private final CrontabService crontabService;

    public CrontabDeleteExe(Scheduler scheduler,
                            CrontabService crontabService) {
        this.scheduler = scheduler;
        this.crontabService = crontabService;
    }

    public Response execute(Long id) {
        crontabService.delete(id);
        JobUtils.deleteJob(scheduler, id.toString());
        return Response.success();
    }
}
