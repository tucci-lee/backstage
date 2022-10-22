package com.tuccicode.backstage.app.crontab.job;

import com.tuccicode.backstage.domain.crontab.service.CrontabLogService;
import org.quartz.JobExecutionContext;

/**
 * @author tucci.lee
 */
public class TestJob extends LogJob {
    protected TestJob(CrontabLogService crontabLogService) {
        super(crontabLogService);
    }

    @Override
    protected void doExecute(JobExecutionContext context) throws Exception {
        System.out.println(1);
    }
}
