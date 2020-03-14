package com.jiuqi.cosmos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiuqi.cosmos.dao.FocusDao;
import com.jiuqi.cosmos.dao.UserDao;
import com.jiuqi.cosmos.entity.FocusInfo;
import com.jiuqi.cosmos.entity.User;
import com.jiuqi.cosmos.pojo.FocusCountDTO;
import com.jiuqi.cosmos.service.FocusRedisService;
import com.jiuqi.cosmos.service.FocusService;
@Service
public class FocusServiceImpl implements FocusService {
	@Autowired
	FocusDao focusDao;
	@Autowired
	UserDao userDao;
	@Autowired
	FocusRedisService focusRedisService;

	@Override
	public List<FocusInfo> getFocusListByFocusUserId(Integer focusUserId) {// 谁给我点过赞
		return focusDao.findByFocusUserId(focusUserId);
	}

	@Override
	public List<FocusInfo> getFocusListByFocusPostId(Integer focusPostId) {// 我给谁点过赞
		return focusDao.findByFocusPostId(focusPostId);
	}

	@Override
	public FocusInfo getByFocusUserIdAndFocusPostId(Integer focusUserId, Integer focusPostId) {
		return focusDao.findByFocusUserIdAndLikedPostId(focusUserId, focusPostId);
	}

	@Override
	@Transactional
	public void transFocusFromRedis2DB() {
		List<FocusInfo> list = focusRedisService.getFocusDataFromRedis();// redis中的所有点赞或未点赞数据(获取后即删除记录)
		for (FocusInfo focusInfo : list) {
			FocusInfo ul = getByFocusUserIdAndFocusPostId(focusInfo.getFocusUserId(), focusInfo.getFocusPostId());
			if (ul == null) {
				// 没有记录，直接存入
				focusDao.save(focusInfo);
			} else {
				// 有记录，需要更新
				ul.setStatus(focusInfo.getStatus());
				focusDao.update(ul);
			}
		}
	}

	@Override
	@Transactional
	public void transFocusCountFromRedis2DB() {// 获取该用户的所有点赞量，存到数据库中
		List<FocusCountDTO> list = focusRedisService.getFocusCountFromRedis();// redis中所有被点赞用户的点赞数量
		for (FocusCountDTO dto : list) {
			User user = userDao.getById(dto.getUserId());
			if (user == null) {
				return;
			} else {
				user.setFocuscount(user.getFocuscount() + dto.getFocusCount());
				// 更新关注量
				userDao.updateFocusCount(user);
			}
		}
	}

}
