package com.jiuqi.cosmos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiuqi.cosmos.dao.CommentMapper;
import com.jiuqi.cosmos.dao.FoodRecipeMapper;
import com.jiuqi.cosmos.dao.UserDao;
import com.jiuqi.cosmos.entity.Comment;
import com.jiuqi.cosmos.pojo.AidInfo;
import com.jiuqi.cosmos.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentMapper commentDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired 
	FoodRecipeMapper recipeService;
	
	@Override
	public int insert(Comment record) {
		return commentDao.insert(record);
	}

	@Override
	public List<Comment> selectFirstLevel(Integer recipeId) {
		return commentDao.selectFirstLevel(recipeId);
	}

	@Override
	public List<Comment> selectSecondLevel(AidInfo aidInfo) {
		List<Integer> firstId = new ArrayList<Integer>();
		List<Comment> selectFirstLevel = selectFirstLevel(aidInfo.getLrecipeId());
		for(Comment ment:selectFirstLevel) {
			firstId.add(ment.getCid());
		}
		aidInfo.setFirstCIdList(firstId);
		
		return commentDao.selectSecondLevel(aidInfo);
	}

}
