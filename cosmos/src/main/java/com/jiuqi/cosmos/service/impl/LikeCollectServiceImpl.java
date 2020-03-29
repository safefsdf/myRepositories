package com.jiuqi.cosmos.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import com.jiuqi.cosmos.constants.LikedStatusEnum;
import com.jiuqi.cosmos.constants.UserConstants;
import com.jiuqi.cosmos.dao.CollectToBlogMapper;
import com.jiuqi.cosmos.dao.LikeToBlogMapper;
import com.jiuqi.cosmos.entity.CollectToBlog;
import com.jiuqi.cosmos.entity.LikeToBlog;
import com.jiuqi.cosmos.pojo.LikeCollectDTO;
import com.jiuqi.cosmos.pojo.UserInfoDTO;
import com.jiuqi.cosmos.service.LikeCollectService;
import com.jiuqi.cosmos.util.RedisKeyUtils;

@Service
public class LikeCollectServiceImpl implements LikeCollectService {

	@Autowired
	public RedisTemplate<String, Object> redisTemplate;

	@Autowired
	public LikeToBlogMapper likeToBlogDao;
	@Autowired
	public CollectToBlogMapper collectToBlogDao;

	/**
	 * MAP_KEY_USER_LIKED MAP_KEY_USER_LIKED_COUNT
	 */
	@Override
	public boolean likeToblogToRedis(LikeCollectDTO likeCollectDTO) {
		boolean flag = false;
		if (likeCollectDTO == null) {
			return flag;
		}
		String like_key = RedisKeyUtils.likeOrCollectKey(likeCollectDTO);
		String like_count_key = RedisKeyUtils.likeOrCollectCountKey(likeCollectDTO);
		try {
			String hashKey = RedisKeyUtils.getLikedKey(likeCollectDTO.getUserId(), likeCollectDTO.getrecipeId());
			redisTemplate.opsForHash().put(like_key, hashKey, LikedStatusEnum.LIKE.getCode());// 点赞
			if (likeCollectDTO.getrecipeId() != null) {// 博客的点赞了+1
				redisTemplate.opsForHash().increment(like_count_key, String.valueOf(likeCollectDTO.getrecipeId()), 1);
			}
			flag = true;
		} catch (Exception e) {
			return flag;
		}
		return flag;
	}

	@Override
	public boolean unlikeToblogToRedis(LikeCollectDTO likeCollectDTO) {
		boolean flag = false;
		if (likeCollectDTO == null) {
			return flag;
		}
		String like_key = RedisKeyUtils.likeOrCollectKey(likeCollectDTO);
		String like_count_key = RedisKeyUtils.likeOrCollectCountKey(likeCollectDTO);
		try {
			String hashKey = RedisKeyUtils.getLikedKey(likeCollectDTO.getUserId(), likeCollectDTO.getrecipeId());
			redisTemplate.opsForHash().put(like_key, hashKey, LikedStatusEnum.UNLIKE.getCode());// 取消点赞
			if (likeCollectDTO.getrecipeId() != null) {// 博客的点赞了+1
				redisTemplate.opsForHash().increment(like_count_key, String.valueOf(likeCollectDTO.getrecipeId()), -1);
			}
			flag = true;
		} catch (Exception e) {
			return flag;
		}
		return flag;
	}

	public List<LikeCollectDTO> getLikedDataFromRedis(String type) {
		String likeOrCollect = "";
		if ("like".equalsIgnoreCase(type)) {
			likeOrCollect = UserConstants.MAP_KEY_USER_LIKED;
		} else if ("collect".equalsIgnoreCase(type)) {
			likeOrCollect = UserConstants.MAP_KEY_USER_COLLECT;
		}
		Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(likeOrCollect, ScanOptions.NONE);
		List<LikeCollectDTO> focusInfoList = new ArrayList<LikeCollectDTO>();
		while (cursor.hasNext()) {
			Map.Entry<Object, Object> entry = cursor.next();
			String key = (String) entry.getKey();
			Integer value = (Integer) entry.getValue();
			// 分离出 likedUserId，likedPostId
			Integer userId;
			Integer recipeId;
			try {
				String[] split = key.split("::");
				userId = Integer.valueOf(split[0]);
				recipeId = Integer.valueOf(split[1]);
				LikeCollectDTO likeOrCol = new LikeCollectDTO();
				likeOrCol.setUserId(userId);
				likeOrCol.setrecipeId(recipeId);
				likeOrCol.setStatus(value);
				likeOrCol.setType(type);
				focusInfoList.add(likeOrCol);
				// 存到 list 后从 Redis 中删除,因为要向mysql中持久化，redis只是暂存的一个地方
				redisTemplate.opsForHash().delete(likeOrCollect, key);
			} catch (NumberFormatException e) {
				System.out.println("LikeCollectServiceImpl.getLikedDataFromRedis");
			}
			
		}
		return focusInfoList;
	}

	@Override
	@Transactional
	public void transLikeFromRedis2DB() {
		String type = "like";
		List<LikeCollectDTO> likeList = getLikedDataFromRedis(type);
		for (LikeCollectDTO dto : likeList) {
			LikeToBlog like = likeToBlogDao.selectByBlogAndUser(dto.getrecipeId(), dto.getUserId());
			if (like == null) {
				like = new LikeToBlog(dto.getrecipeId(), dto.getUserId(), dto.getStatus());
				likeToBlogDao.insert(like);
			} else {
				switch (like.getLikeStatus()) {
				case 1:
					like.setLikeStatus(0);
					break;
				case 0:
					like.setLikeStatus(1);
					break;
				}
				likeToBlogDao.updateByPrimaryKey(like);
			}
		}

	}

	@Override
	@Transactional
	public void transCollectFromRedis2DB() {
		String type = "collect";
		List<LikeCollectDTO> likeList = getLikedDataFromRedis(type);
		for (LikeCollectDTO dto : likeList) {
			CollectToBlog collect = collectToBlogDao.selectByBlogAndUser(dto.getrecipeId(), dto.getUserId());
			if (collect == null) {
				collect = new CollectToBlog(dto.getrecipeId(), dto.getUserId(), dto.getStatus());
				collectToBlogDao.insert(collect);
			} else {
				if (collect.getCollectStatus() == 1) {
					collect.setCollectStatus(0);
				} else {
					collect.setCollectStatus(1);
				}
				collectToBlogDao.updateByblogAndUser(collect);
			}
		}
	}

	@Override
	public List<UserInfoDTO> getLikedByrecipeId(Integer recipeId) {
		return likeToBlogDao.selectUserInfoByBlog(recipeId);
	}

	@Override
	public List<UserInfoDTO> getCollectedByrecipeId(Integer recipeId) {
		return collectToBlogDao.selectUserByrecipeId(recipeId);
	}

}
