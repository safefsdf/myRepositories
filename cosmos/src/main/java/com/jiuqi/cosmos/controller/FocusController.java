package com.jiuqi.cosmos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiuqi.cosmos.constants.ResultEnum;
import com.jiuqi.cosmos.entity.FocusInfo;
import com.jiuqi.cosmos.entity.User;
import com.jiuqi.cosmos.pojo.R;
import com.jiuqi.cosmos.service.FocusRedisService;
import com.jiuqi.cosmos.service.FocusService;
import com.jiuqi.cosmos.service.UserService;
@RequestMapping("focus")
@RestController
public class FocusController {

	@Autowired
	public FocusService focusService;
	@Autowired
	public FocusRedisService focusRedisService;
	@Autowired
	public UserService userService;

	/**
	 * 关注 ：用户 focusUserId 被 focusPostId 所关注。（此时登录者为postId,看到了用户userId的信息）
	 * 
	 * @param focusUserId 被关注者
	 * @param focusPostId 关注的操作者
	 * @return
	 */
	@RequestMapping("focusOn")
	@Transactional
	public R<FocusInfo> focusOn(int focusUserId, int focusPostId) {
		try {
			FocusInfo info = new FocusInfo(focusUserId, focusPostId, 1);
			focusRedisService.focusToRedis(focusUserId, focusPostId);
			focusRedisService.incrementLikedCount(focusUserId);// 粉丝量+1
			return R.success(info, 200, "success");
		} catch (Exception e) {
			return R.success(9, "关注失败");
		}
	}
	/**
	 * 取消关注 用户postId不喜欢UserId了
	 * 
	 * @param focusUserId
	 * @param focusPostId
	 * @return
	 */
	@RequestMapping("focusCancel")
	@Transactional
	public R<FocusInfo> focusDown(int focusUserId, int focusPostId) {
		FocusInfo info = new FocusInfo(focusUserId, focusPostId, 0);
		try {
			focusRedisService.unFocusToRedis(focusUserId, focusPostId);
			focusRedisService.decrementLikedCount(focusUserId);
			return R.success(info, 200, "取消关注成功");
		} catch (Exception e) {
			return R.error(9, "关注失败");
		}
	}

	/**
	 * 获取该用户的粉丝
	 * @param focusUserId
	 * @return userList的列表
	 */
	@RequestMapping("/getMyFun")
	public R<User> getFocusfun(int focusUserId) {
		try {
			List<User> userList = new ArrayList<User>();
			List<FocusInfo> focusList = focusService.getFocusListByFocusUserId(focusUserId);
			for (FocusInfo info : focusList) {
				int funId = info.getFocusPostId();
				User funUser = userService.getById(funId);
				if (funUser != null) {
					userList.add(funUser);
				}
			}
			return R.success(userList, ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
		} catch (Exception e) {
			return R.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
		}
	}

	// 获取该用户的粉丝量---user表中有一个字段，count是用来表示粉丝量的。
	// 但是根据登录用户的ID就可以获取该用户的全部信息，包括---头像，封面，昵称，个性签名，以及粉丝量。
	
	
	/**
	 * 查看我关注的博主的信息
	 * @param focusPostId
	 * @return userList 我的偶像的集合
	 */
	@RequestMapping("/getMyIdol")
	public R<User> getMyFocus(int focusPostId) {
		try {
			List<User> userList = new ArrayList<User>();
			List<FocusInfo> focusList = focusService.getFocusListByFocusPostId(focusPostId);
			for (FocusInfo info : focusList) {
				int funId = info.getFocusUserId();
				User idolUser = userService.getById(funId);
				if (idolUser != null) {
					userList.add(idolUser);
				}
			}
			return R.success(userList, ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
		} catch (Exception e) {
			return R.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
		}
	}

	

}
