package com.jiuqi.cosmos.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jiuqi.cosmos.task.FocusTask;

@Configuration
public class QuartzConfig {

	private static final String FOCUS_TASK_IDENTITY = "FocusTaskQuartz";

	@Bean
	public JobDetail quartzDetail() {
		return JobBuilder.newJob(FocusTask.class).withIdentity(FOCUS_TASK_IDENTITY).storeDurably().build();
	}

	@Bean
	public Trigger quartzTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)  //设置时间周期单位秒
//				.withIntervalInMinutes(1)// 两个小时执行一次
				.repeatForever();
		return TriggerBuilder.newTrigger().forJob(quartzDetail()).withIdentity(FOCUS_TASK_IDENTITY)
				.withSchedule(scheduleBuilder).build();
	}
}
