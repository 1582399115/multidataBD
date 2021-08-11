package com.time.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Configuration
@EnableScheduling
public class DefaultSchedulingConfigurer implements SchedulingConfigurer {
    private ScheduledTaskRegistrar taskRegistrar;
    private Set<ScheduledFuture<?>> scheduledFutures = null;
    private final Map<String, ScheduledFuture<?>> taskFutures = new ConcurrentHashMap<>();

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        this.taskRegistrar = scheduledTaskRegistrar;
        System.out.println(inited());
    }
    @SuppressWarnings("unchecked")
    private Set<ScheduledFuture<?>> getScheduledFutures() {
        try {
            if (scheduledFutures == null) {
                scheduledFutures =(Set<ScheduledFuture<?>>) BeanUtils.getProperty(taskRegistrar, "scheduledTasks");
            }
        } catch (NoSuchFieldException ex) {
            throw new SchedulingException("not found scheduledFutures field");

        }
        return scheduledFutures;
    }

    /**
     * 添加任务
     */
    public void addTriggerTask(String taskId, TriggerTask triggerTask) {
        if (taskFutures.containsKey(taskId)) {
            throw new SchedulingException("已经存在这个任务[" + taskId + "]");
        }
        TaskScheduler scheduler = taskRegistrar.getScheduler();
        assert scheduler != null;
        ScheduledFuture<?> future = scheduler.schedule(triggerTask.getRunnable(), triggerTask.getTrigger());
        getScheduledFutures().add(future);
        taskFutures.put(taskId, future);
    }

    /**
     * 取消任务
     */
    public void cancelTriggerTask(String taskId) {
        ScheduledFuture<?> future = taskFutures.get(taskId);
        if (future != null) {
            future.cancel(true);
        }
        taskFutures.remove(taskId);
        getScheduledFutures().remove(future);
    }

    /**
     * 重置任务  ->先取消原来的任务,在添加新的任务
     */
    public void resetTriggerTask(String taskId, TriggerTask triggerTask) {
        cancelTriggerTask(taskId);
        addTriggerTask(taskId, triggerTask);
    }

    /**
     * 任务编号
     */
    public Set<String> taskIds() {
        return taskFutures.keySet();
    }

    /**
     * 是否存在任务
     */
    public boolean hasTask(String taskId) {
        return this.taskFutures.containsKey(taskId);
    }

    /**
     * 任务调度是否已经初始化完成
     */
    public boolean inited() {
        return this.taskRegistrar != null && this.taskRegistrar.getScheduler() != null;
    }
    /***
     *  功能描述：日期转换cron表达式
     * @param date
     * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDateByPattern(Date date, String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }
    /***
     * convert Date to cron ,eg.  "0 07 10 15 1 ? 2016"
     * @param date  : 时间点
     * @return
     */
    public static String getCron(Date date){
        String dateFormat="ss mm HH dd MM ?";
        return formatDateByPattern(date, dateFormat);
    }
}
