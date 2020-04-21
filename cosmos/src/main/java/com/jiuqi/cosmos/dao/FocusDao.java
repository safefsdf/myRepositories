package com.jiuqi.cosmos.dao;

import java.util.List;

import com.jiuqi.cosmos.entity.FocusInfo;
import com.jiuqi.cosmos.entity.User;

public interface FocusDao {

	/**
	 * 将关注的数据存到mysql中
	 * @param info
	 * @return
	 */
	int save(FocusInfo info);
	/**
	 * 更新facusInfo 的状态记录
	 * @param info
	 * @return
	 */
	int update(FocusInfo info);
	/**
	 * 从mysql中查询（谁关注了focusUserId）
	 * @param focusUserId
	 * @return
	 */
	List<User> findByFocusUserId(Integer focusUserId);

	/**
	 * 从mysql中查询（ focusPostId关注了谁）
	 * @param focusPostId
	 * @return
	 */
	List<User> findByFocusPostId(Integer focusPostId);

	/**
	 * 查询该数据是否存在
	 * @param focusUserId
	 * @param focusPostId
	 * @return
	 */
	FocusInfo findByFocusUserIdAndLikedPostId(Integer focusUserId, Integer focusPostId);
}
