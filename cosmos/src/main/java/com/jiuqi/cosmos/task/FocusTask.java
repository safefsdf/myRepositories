package com.jiuqi.cosmos.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.jiuqi.cosmos.service.FocusService;
import com.jiuqi.cosmos.service.LikeCollectService;

/**
 * 关注、点赞、收藏的定时任务
 */
public class FocusTask extends QuartzJobBean {

	@Autowired
	FocusService focusService;
	
	@Autowired
	LikeCollectService likeCollectService;

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

		// 将 Redis 里的点赞信息同步到数据库里
		focusService.transFocusFromRedis2DB();
		//点赞收藏的数量为更新到数据库中
		likeCollectService.transLikeFromRedis2DB();
		likeCollectService.transCollectFromRedis2DB();
	}
}
