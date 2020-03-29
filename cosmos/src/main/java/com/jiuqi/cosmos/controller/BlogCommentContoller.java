package com.jiuqi.cosmos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiuqi.cosmos.entity.BlogComment;
import com.jiuqi.cosmos.pojo.R;
import com.jiuqi.cosmos.service.BlogCommentService;

@RestController
@RequestMapping("comment")
public class BlogCommentContoller {

	@Autowired
	public BlogCommentService commentService;
	@RequestMapping("commentList")
	public R<BlogComment> getComment(int recipeId){
		List<BlogComment> list = commentService.selectAllByrecipeId(recipeId);
		return R.success(list, 200, "suc");
	}
}
