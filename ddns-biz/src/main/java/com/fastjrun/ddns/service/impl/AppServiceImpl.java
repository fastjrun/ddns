package com.fastjrun.ddns.service.impl;

import com.fastjrun.ddns.config.AppBean;
import com.fastjrun.ddns.schedule.IntervalTaskRegistrar;
import com.fastjrun.ddns.schedule.SchedulingRunnable;
import com.fastjrun.ddns.service.AppService;
import com.fastjrun.ddns.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("appService")
public class AppServiceImpl implements AppService {
    @Resource
    CacheService cacheService;

    @Autowired
    IntervalTaskRegistrar intervalTaskRegistrar;

    @Override
    public void stop() {
        intervalTaskRegistrar.destroy();
    }

    @Override
    public void restart() {
        intervalTaskRegistrar.destroy();
        cacheService.refresh();
        AppBean appBean = cacheService.cache();
        SchedulingRunnable task = new SchedulingRunnable("checkIPTask", "process");
        intervalTaskRegistrar.addIntervalTask(task, 1000 * 60 * appBean.getInterval());

    }
}
