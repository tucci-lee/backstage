package com.tuccicode.backstage.app.crontab.exe;

import com.tuccicode.backstage.app.crontab.job.JobUtils;
import com.tuccicode.backstage.domain.crontab.entity.Crontab;
import com.tuccicode.backstage.domain.crontab.service.CrontabService;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tucci.lee
 */
@Service
public class CrontabInitExe {

    private final Scheduler scheduler;
    private final CrontabService crontabService;

    public CrontabInitExe(Scheduler scheduler,
                          CrontabService crontabService) {
        this.scheduler = scheduler;
        this.crontabService = crontabService;
    }

    public void execute() {
        List<Crontab> crontabs = crontabService.all();
        for (Crontab crontab : crontabs) {
            JobUtils.createJob(scheduler, crontab);
        }
    }
}
