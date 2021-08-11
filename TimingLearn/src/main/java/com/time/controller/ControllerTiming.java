package com.time.controller;

import com.time.config.DefaultSchedulingConfigurer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;

@RestController
@Api(tags = "定时器")
public class ControllerTiming {
    @Resource
    private DefaultSchedulingConfigurer defaultSchedulingConfigurer;

    //添加定时器
    @GetMapping("add")
    @ApiOperation(value = "添加定时器")
    public String add(String task, Date date) {
        String cron = DefaultSchedulingConfigurer.getCron(date);
        Set<String> strings = defaultSchedulingConfigurer.taskIds();
        boolean contains = strings.contains(task);
        System.out.println(contains);
        if (contains)
            return "该任务以存在!";
        defaultSchedulingConfigurer.addTriggerTask(task,
                new TriggerTask(
                        () -> System.out.println(task + "时间:" + date),
                        new CronTrigger(cron)));
        return "成功";
    }

    //查询定时任务
    @GetMapping("sel")
    @ApiOperation(value = "查询定时任务")
    public Set<String> sel() {
        return defaultSchedulingConfigurer.taskIds();
    }

    //删除定时器
    @GetMapping("del")
    @ApiOperation(value = "删除定时器")
    public String del(String task) {
        defaultSchedulingConfigurer.cancelTriggerTask(task);
        return "成功";
    }
}
