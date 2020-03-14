package com.jiuqi.cosmos.dao;

import com.jiuqi.cosmos.entity.CollectToBlog;
import com.jiuqi.cosmos.pojo.UserInfoDTO;

import java.util.List;

public interface CollectToBlogMapper {
    int deleteByPrimaryKey(Integer collectId);

    int insert(CollectToBlog record);

    CollectToBlog selectByBlogAndUser(Integer blogId, Integer userId);
    List<UserInfoDTO> selectAllByBlogId(Integer blogId);

    int updateByblogAndUser(CollectToBlog record);
}