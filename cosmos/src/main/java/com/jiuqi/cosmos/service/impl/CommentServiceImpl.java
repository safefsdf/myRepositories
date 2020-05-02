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
import com.jiuqi.cosmos.pojo.BlogCommentDTO;
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

	public List<Comment> selectFirstLevel(Integer recipeId) {
		return commentDao.selectFirstLevel(recipeId);
	}
	@Override
	public List<BlogCommentDTO> selectSecondLevel(Integer recipeId) {
		try {
			List<Comment> firsts = commentDao.selectFirstLevel(recipeId);
			List<BlogCommentDTO> result = new ArrayList<BlogCommentDTO>();
			for(Comment com : firsts) {
				BlogCommentDTO dto = new BlogCommentDTO();
				dto.setHeadimg(com.getHeadImg());
				dto.setCom(com);
				List<Comment> selectSecondLevel = commentDao.selectSecondLevel(com.getCid(), com.getRecipeId());
				dto.setList(selectSecondLevel);
				result.add(dto);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 

}
