package com.jiuqi.cosmos.service;

import java.util.List;

import com.jiuqi.cosmos.entity.FocusInfo;
import com.jiuqi.cosmos.pojo.FocusCountDTO;
 

/**
 * 操作redis数据库
 * 点赞+取消点赞+加1条记录+减1条记录+获取redis中存储的所有点赞数据
 * @author hp
 *
 */
public interface FocusRedisService {
	/**
	 * 点赞。状态为1
	 * 
	 * @param likedUserId
	 * @param likedPostId
	 */
	void focusToRedis(Integer likedUserId, Integer likedPostId);

	/**
	 * 取消点赞。将状态改变为0
	 * 
	 * @param likedUserId
	 * @param likedPostId
	 */
	void unFocusToRedis(Integer likedUserId, Integer likedPostId);

	/**
	 * 从Redis中删除一条点赞数据
	 * 
	 * @param likedUserId
	 * @param likedPostId
	 */
	void deleteLikedFromRedis(Integer likedUserId, Integer likedPostId);

	/**
	 * 该用户的点赞数加1
	 * 
	 * @param likedUserId
	 */
	void incrementLikedCount(Integer likedUserId);

	/**
	 * 该用户的点赞数减1
	 * 
	 * @param likedUserId
	 */
	void decrementLikedCount(Integer likedUserId);

	/**
	 * 获取Redis中存储的所有关注的数据（一旦触发那么redis数据库中便不再有该条数据）
	 * 
	 * @return
	 */
	List<FocusInfo> getFocusDataFromRedis();

	/**
	 * 获取Redis中存储的所有用户的粉丝数据
	 * 
	 * @return
	 */
	List<FocusCountDTO> getFocusCountFromRedis(); 
}
