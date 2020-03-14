package com.jiuqi.cosmos.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import com.jiuqi.cosmos.constants.LikedStatusEnum;
import com.jiuqi.cosmos.constants.UserConstants;
import com.jiuqi.cosmos.entity.FocusInfo;
import com.jiuqi.cosmos.pojo.FocusCountDTO;
import com.jiuqi.cosmos.service.FocusRedisService;
import com.jiuqi.cosmos.util.RedisKeyUtils;

@Service
public class FocusRedisServiceImpl implements FocusRedisService {

	@Autowired
	RedisTemplate<String, Object> redisTemplate;
//	Hash 第一个参数是：表名（点赞表状态，点赞数量表），第二个参数：字段名；第三个参数：值
	
	//点赞
	@Override
	public void focusToRedis(Integer likedUserId, Integer likedPostId) {
		String key = RedisKeyUtils.getLikedKey(likedUserId, likedPostId);
		System.out.println("likedUserId  " + key);
		redisTemplate.opsForHash().put(UserConstants.MAP_KEY_USER_FOCUS, key, LikedStatusEnum.LIKE.getCode());
	}
	//取消点赞
	@Override
	public void unFocusToRedis(Integer likedUserId, Integer likedPostId) {
		String key = RedisKeyUtils.getLikedKey(likedUserId, likedPostId);
		redisTemplate.opsForHash().put(UserConstants.MAP_KEY_USER_FOCUS, key, LikedStatusEnum.UNLIKE.getCode());
	}

	// 从Redis---MAP_KEY_USER_LIKED 中删除一条点赞数据
	@Override
	public void deleteLikedFromRedis(Integer likedUserId, Integer likedPostId) {
		String key = RedisKeyUtils.getLikedKey(likedUserId, likedPostId);
		redisTemplate.opsForHash().delete(UserConstants.MAP_KEY_USER_FOCUS, key);
	}

	// hash结构 该用户的关注人数加1
	@Override
	public void incrementLikedCount(Integer likedUserId) {
		if(likedUserId!=null) {
			redisTemplate.opsForHash().increment(UserConstants.MAP_KEY_USER_FOCUS_COUNT, String.valueOf(likedUserId), 1);
		}
	}

	// hash结构 该用户的关注人数减1
	@Override
	public void decrementLikedCount(Integer likedUserId) {
		if(likedUserId!=null) {
			redisTemplate.opsForHash().increment(UserConstants.MAP_KEY_USER_FOCUS_COUNT, likedUserId, -1);
		}
	}

	@Override
	public List<FocusInfo> getFocusDataFromRedis() {
		Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(UserConstants.MAP_KEY_USER_FOCUS,
				ScanOptions.NONE);
		List<FocusInfo> focusInfoList = new ArrayList<FocusInfo>();
		while (cursor.hasNext()) {
			Map.Entry<Object, Object> entry = cursor.next();
			String key = (String) entry.getKey();
			Integer value = (Integer) entry.getValue();
			// 分离出 likedUserId，likedPostId
			String[] split = key.split("::");
			Integer focusUserId = Integer.valueOf(split[0]);
			Integer focusPostId = Integer.valueOf(split[1]);

			FocusInfo focus = new FocusInfo(focusUserId, focusPostId, value);
			focusInfoList.add(focus);

			// 存到 list 后从 Redis 中删除,因为要向mysql中持久化，redis只是暂存的一个地方
			redisTemplate.opsForHash().delete(UserConstants.MAP_KEY_USER_FOCUS, key);
		}

		return focusInfoList;
	}

	@Override
	public List<FocusCountDTO> getFocusCountFromRedis() {
		Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash()
				.scan(UserConstants.MAP_KEY_USER_FOCUS_COUNT, ScanOptions.NONE);
		List<FocusCountDTO> focusCountList = new ArrayList<FocusCountDTO>();
		while (cursor.hasNext()) {
			Map.Entry<Object, Object> map = cursor.next();
			// 将点赞数量存储在 LikedCountDT
			String key = (String) map.getKey();
			Integer value = (Integer) map.getValue();

			FocusCountDTO count = new FocusCountDTO(Integer.valueOf(key), value);
			focusCountList.add(count);
			// 从Redis中删除这条记录
			redisTemplate.opsForHash().delete(UserConstants.MAP_KEY_USER_FOCUS_COUNT, key);
		}
		return focusCountList;
	}

}
