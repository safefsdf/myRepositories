package com.jiuqi.cosmos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiuqi.cosmos.constants.ResultEnum;
import com.jiuqi.cosmos.pojo.BlogCommentDTO;
import com.jiuqi.cosmos.pojo.R;
import com.jiuqi.cosmos.service.CommentService;

@RestController
@RequestMapping("comment")
public class BlogCommentContoller {

	@Autowired
	public CommentService commentService;
	@RequestMapping("firstLevel")
	public R<BlogCommentDTO> getFirstLevelComment(int recipeId){
		try {
			List<BlogCommentDTO> list = commentService.selectSecondLevel(recipeId);
			 
			return R.success(list, ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
}
