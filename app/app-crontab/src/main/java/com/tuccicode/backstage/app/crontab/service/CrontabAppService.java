package com.tuccicode.backstage.app.crontab.service;

import com.tuccicode.backstage.app.crontab.dto.body.CrontabCreateBody;
import com.tuccicode.backstage.app.crontab.dto.body.CrontabUpdateBody;
import com.tuccicode.backstage.app.crontab.dto.body.CrontabUpdateStatusBody;
import com.tuccicode.backstage.app.crontab.exe.CrontabCreateExe;
import com.tuccicode.backstage.app.crontab.exe.CrontabDeleteExe;
import com.tuccicode.backstage.app.crontab.exe.CrontabInitExe;
import com.tuccicode.backstage.app.crontab.exe.CrontabListExe;
import com.tuccicode.backstage.app.crontab.exe.CrontabStartExe;
import com.tuccicode.backstage.app.crontab.exe.CrontabUpdateExe;
import com.tuccicode.backstage.app.crontab.exe.CrontabUpdateStatusExe;
import com.tuccicode.backstage.domain.crontab.entity.CrontabQuery;
import com.tuccicode.concise.dto.Response;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tucci.lee
 */
@Service
public class CrontabAppService implements InitializingBean {

    private final CrontabInitExe crontabInitExe;
    private final CrontabListExe crontabListExe;
    private final CrontabCreateExe crontabCreateExe;
    private final CrontabUpdateExe crontabUpdateExe;
    private final CrontabDeleteExe crontabDeleteExe;
    private final CrontabUpdateStatusExe crontabUpdateStatusExe;
    private final CrontabStartExe crontabStartExe;

    public CrontabAppService(CrontabInitExe crontabInitExe,
                             CrontabListExe crontabListExe,
                             CrontabCreateExe crontabCreateExe,
                             CrontabUpdateExe crontabUpdateExe,
                             CrontabDeleteExe crontabDeleteExe,
                             CrontabUpdateStatusExe crontabUpdateStatusExe,
                             CrontabStartExe crontabStartExe) {
        this.crontabInitExe = crontabInitExe;
        this.crontabListExe = crontabListExe;
        this.crontabCreateExe = crontabCreateExe;
        this.crontabUpdateExe = crontabUpdateExe;
        this.crontabDeleteExe = crontabDeleteExe;
        this.crontabUpdateStatusExe = crontabUpdateStatusExe;
        this.crontabStartExe = crontabStartExe;
    }

    /**
     * 将所有的定时任务加载到内存中
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        crontabInitExe.execute();
    }

    public Response list(CrontabQuery query) {
        return crontabListExe.execute(query);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Response create(CrontabCreateBody body) {
        return crontabCreateExe.execute(body);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Response update(CrontabUpdateBody body) {
        return crontabUpdateExe.execute(body);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Response delete(Long id) {
        return crontabDeleteExe.execute(id);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Response updateStatus(CrontabUpdateStatusBody body) {
        return crontabUpdateStatusExe.execute(body);
    }

    public Response start(Long id) {
        return crontabStartExe.execute(id);
    }

}
