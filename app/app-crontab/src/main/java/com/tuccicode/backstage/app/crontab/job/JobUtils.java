package com.tuccicode.backstage.app.crontab.job;

import com.tuccicode.backstage.domain.core.exception.BackstageBizCode;
import com.tuccicode.backstage.domain.crontab.entity.Crontab;
import com.tuccicode.concise.exception.BizException;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

/**
 * @author tucci.lee
 */
public class JobUtils {

    private static final Logger logger = LoggerFactory.getLogger(JobUtils.class);

    /**
     * 添加定时任务到内存
     *
     * @param crontab 定时任务
     */
    public static void createJob(Scheduler scheduler, Crontab crontab) {
        if (ObjectUtils.isEmpty(crontab)) {
            return;
        }
        try {
            String jobName = crontab.getId().toString();
            Class<?> crontabClass = Class.forName(crontab.getClassName());
            if (!AbstractJob.class.isAssignableFrom(crontabClass)) {
                throw new ClassCastException("不是一个正确的定时任务");
            }
            Class<Job> jobClass = (Class<Job>) crontabClass;
            JobKey jobKey = JobKey.jobKey(jobName);
            JobBuilder jobBuilder = JobBuilder.newJob(jobClass)
                    .withIdentity(jobKey);
            JobDetail detail = jobBuilder.build();
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobName)
                    .withSchedule(CronScheduleBuilder.cronSchedule(crontab.getCron()))
                    .build();
            scheduler.scheduleJob(detail, trigger);
            if (crontab.getStatus() == null || !crontab.getStatus()) {
                scheduler.pauseJob(jobKey);
            }
        } catch (Exception e) {
            logger.error("定时任务添加出错: " + crontab.getId(), e);
            throw new BizException(BackstageBizCode.CRONTAB_ADD_ERROR);
        }
    }

    /**
     * 暂停定时任务
     *
     * @param jobName 定时任务key
     */
    public static void pauseJob(Scheduler scheduler, String jobName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName);
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            logger.error("定时任务暂停出错: " + jobName, e);
            throw new BizException(BackstageBizCode.CRONTAB_PAUSE_ERROR);
        }
    }

    /**
     * 恢复定时任务
     *
     * @param jobName 定时任务key
     */
    public static void resumeJob(Scheduler scheduler, String jobName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName);
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            logger.error("定时任务恢复出错: " + jobName, e);
            throw new BizException(BackstageBizCode.CRONTAB_RESUME_ERROR);
        }
    }

    /**
     * 删除内存中的定时任务
     *
     * @param jobName 定时任务key
     */
    public static void deleteJob(Scheduler scheduler, String jobName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName);
            scheduler.pauseJob(jobKey);
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            logger.error("定时任务删除出错: " + jobName, e);
            throw new BizException(BackstageBizCode.CRONTAB_DELETE_ERROR);
        }
    }

    /**
     * 执行一次定时任务
     *
     * @param jobName 定时任务key
     */
    public static void startJob(Scheduler scheduler, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            logger.error("定时任务执行出错: " + jobName, e);
            throw new BizException(BackstageBizCode.CRONTAB_START_ERROR);
        }
    }
}
