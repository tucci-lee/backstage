package com.tuccicode.backstage.app.crontab.job;

import com.tuccicode.backstage.domain.crontab.entity.CrontabLog;
import com.tuccicode.backstage.domain.crontab.service.CrontabLogService;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志纪录任务，继承此类的任务每次执行都会纪录运行信息到数据库
 *
 * @author tucci.lee
 */
public abstract class LogJob extends AbstractJob {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CrontabLogService crontabLogService;

    protected LogJob(CrontabLogService crontabLogService) {
        this.crontabLogService = crontabLogService;
    }


    @Override
    protected void before(JobExecutionContext context) {
        long crontabId = Long.parseLong(context.getJobDetail().getKey().getName());
        long startTime = context.getFireTime().getTime();
        CrontabLog log = new CrontabLog()
                .setCrontabId(crontabId)
                .setStartTime(startTime);
        Long id = crontabLogService.create(log);
        context.setResult(id);
    }

    @Override
    protected void after(JobExecutionContext context, Exception e) {
        Long logId = (Long) context.getResult();
        long startTime = context.getFireTime().getTime();
        long runTime = System.currentTimeMillis() - startTime;
        CrontabLog log = new CrontabLog()
                .setId(logId)
                .setRunTime(runTime);
        if (e == null) {
            log.setStatus(true);
        } else {
            logger.error("任务失败", e);
            log.setStatus(false)
                    .setMessage(e.getMessage());
        }
        crontabLogService.update(log);
    }

}
