package com.jiuqi.cosmos.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jiuqi.cosmos.entity.Comment;
import com.jiuqi.cosmos.pojo.AidInfo;

public interface CommentMapper {
	/*删除cid及食谱id查询食谱评论信息*/
    int deleteByPrimaryKey(@Param("cid") Integer cid, @Param("recipeId") Integer recipeId);
    /*插入评论信息*/
    int insert(Comment record);

    Comment selectByPrimaryKey(@Param("cid") Integer cid, @Param("recipeId") Integer recipeId);
    /**
     * 查询食谱recipeId的一级评论信息
     * @param recipeId
     * @return
     */
    List<Comment> selectFirstLevel(Integer recipeId);
    
    public List<Comment> selectSecondLevel(Integer commentId, Integer recipeId);
     

    

}