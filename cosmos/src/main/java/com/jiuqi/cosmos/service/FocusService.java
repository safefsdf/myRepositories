package com.jiuqi.cosmos.service;

import java.util.List;

import com.jiuqi.cosmos.entity.FocusInfo;


public interface FocusService {
	  

    /**mysql
     * 
     * who给我点过赞
     * 
     * 根据被点赞人的id查询点赞列表（即查询都谁给这个人点赞过）
     * @param likedUserId 被点赞人的id
     * @param pageable
     * @return
     */
    List<FocusInfo> getFocusListByFocusUserId(Integer focusUserId);

    /**mysql
     * 
     * 我给who点过赞
     * 
     * 根据点赞人的id查询点赞列表（即查询这个人都给谁点赞过）
     * @param likedPostId
     * @param pageable
     * @return	UserLike- id likedUserId likedPostId status=1
     */
    List<FocusInfo> getFocusListByFocusPostId(Integer focusPostId );

    /**mysql
     * 
     * 通过被点赞人和点赞人id查询是否存在点赞记录
     * @param likedUserId
     * @param likedPostId
     * @return
     */
    FocusInfo getByFocusUserIdAndFocusPostId(Integer focusUserId, Integer focusPostId);

    /**
     * 将Redis里的点赞数据存入数据库中 -- 批量定时存入
     */
    void transFocusFromRedis2DB();

    /**
     * 将Redis中的点赞数量数据存入数据库  -- 批量定时存入
     */
    void transFocusCountFromRedis2DB();
}
