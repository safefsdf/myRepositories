package com.jiuqi.cosmos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiuqi.cosmos.dao.BlogCommentMapper;
import com.jiuqi.cosmos.entity.BlogComment;
import com.jiuqi.cosmos.service.BlogCommentService;

@Service
public class BlogCommentServiceImpl implements BlogCommentService {

	@Autowired
	public BlogCommentMapper blogCommentDao;

	@Override
	public List<BlogComment> selectAllByrecipeId(Integer recipeId) {
		List<BlogComment> resList = new ArrayList<BlogComment>();
		//查出来一级评论
		List<BlogComment> list = blogCommentDao.selectByCommentPidrecipeId(0, recipeId);
		for(BlogComment levelOne : list) {
			BlogComment com = getComment(levelOne);
			resList.add(com);
		}
		return resList;
	}
	 

	/**
	 * 递归函数，查询某条评论下的所有子回复
	 * @param comment
	 * @return
	 */
	private BlogComment getComment(BlogComment comment) {
		List<BlogComment> levelOne = blogCommentDao.selectByCommentPid(comment.getBlogCommentId()); 
		if(levelOne.size()>0) {
			comment.setChild(levelOne);
			for(BlogComment comlevel:levelOne) {
				getComment(comlevel);
			}
		}else {
			comment.setChild(null);
		}
		return comment;
		 
	}

	@Override
	public int insert(BlogComment record) {
		return blogCommentDao.insert(record);
	}

	@Override
	public int deleteByrecipeId(Integer recipeId) {
		return blogCommentDao.deleteByrecipeId(recipeId);
	}

	@Override
	public int deleteByPrimaryKey(Integer blogCommentId) {
		return blogCommentDao.deleteByPrimaryKey(blogCommentId);
	}

}
