package com.jiuqi.cosmos.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jiuqi.cosmos.constants.ResultEnum;
import com.jiuqi.cosmos.dao.FoodStepMapper;
import com.jiuqi.cosmos.entity.FoodRecipe;
import com.jiuqi.cosmos.entity.FoodStep;
import com.jiuqi.cosmos.entity.User;
import com.jiuqi.cosmos.pojo.R;
import com.jiuqi.cosmos.pojo.UserDTO;
import com.jiuqi.cosmos.service.FocusService;
import com.jiuqi.cosmos.service.LikeCollectService;
import com.jiuqi.cosmos.service.RecipeService;
import com.jiuqi.cosmos.service.UserService;
import com.jiuqi.cosmos.util.MD5;
import com.jiuqi.cosmos.util.RedisUtil;
import com.jiuqi.cosmos.util.VerifyUtil;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisUtil redisService;
	
	@Autowired
	private FocusService focusService;
	@Autowired
	private LikeCollectService likeCollectService;
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private FoodStepMapper stepService;
	/**
	 * 使用手机号和密码登录
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	@RequestMapping("login")
	public R<User> login(String phone, String password) {
		System.out.println(phone+" "+password);
		User user = userService.getByPhone(phone); // 根据电话查询用户
		if (user != null) {
			user = userService.quaryByPhoneAndPassword(phone, password); 
			if (user == null) {// 密码错误
				return R.success(ResultEnum.PASSWORD_ERROR.getCode(), ResultEnum.PASSWORD_ERROR.getMsg());
			} else {
				//用户登录后的token信息
				String token = user.getUserid() + user.getPhone() + System.currentTimeMillis();				
				//使用md5算法进行加密
				user.setToken(MD5.md5(token));
				redisService.set(token, user, 60*5);
				return R.success(user, ResultEnum.SUCCESS.getCode(),"登录"+ ResultEnum.SUCCESS.getMsg());
			}
		} else {//用户不存在
			return R.success(ResultEnum.USER_NOT_EXIST.getCode(), ResultEnum.USER_NOT_EXIST.getMsg());
		}
	}
	
	 
	@RequestMapping("/save")
	public R<User> register(@RequestBody User user) {
		System.out.println(user.toString());
		try {
			int res = userService.createUser(user);
			if(res == 1) {
				 return R.success(user, ResultEnum.SUCCESS.getCode(), "注册"+ ResultEnum.SUCCESS.getMsg()) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
			 return R.error(ResultEnum.REG_ERROR.getCode(),  "该用户已存在，"+ResultEnum.REG_ERROR.getMsg());
		}
		return null;
		
	}

	/**
	 * 注销账号
	 * @return
	 */
	@RequestMapping("/del")
	public R<User> cancel(Integer userid){
		R<User> r = new R<User>();
		try {
			if(userService.deleteById(userid)==1) {
				r.setCode(1);
				r.setMsg("delete success");
			}
		} catch (Exception e) {
			r.setCode(0);
			r.setMsg("delete fail");
		}
		return r;
	}
	/**
	 * 	生成图片验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getVerify")
	public void getVerify(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
			response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
			VerifyUtil vu = new VerifyUtil();
			// 在下面的这个方法中，生成了一张图片，并通过流的方式相应给客户端，并将图片的值存到了session中
			vu.getRandcode(request, response);// 输出验证码图片方法
		} catch (Exception e) {

		}
	}
	/**
	 *	验证码校验
	 * @param verifyInput
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/checkVerify", method = RequestMethod.GET)
	public boolean checkVerify(String verifyInput, HttpSession session) {
		try {
			String str=(String) session.getAttribute(VerifyUtil.RANDOMCODEKEY);
			 System.out.println("后端生成： "+str);
			System.out.println("前端输入： " + verifyInput);
			if (str == null) {
				return false;
			}
			if (str.equalsIgnoreCase(verifyInput)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 *	获取用户资料
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/getInfo", method = RequestMethod.GET)
	public R getUserInfo(int userid) {
		System.out.println("userid: "+userid);
		UserDTO userDto = new UserDTO();
		User byId;
		try {
			byId = userService.getById(userid);
			if(byId==null) {
				return R.error(ResultEnum.USER_NOT_EXIST.getCode(), ResultEnum.USER_NOT_EXIST.getMsg());
			}
			userDto.setUser(byId);
			List<User> idolList = focusService.getFocusListByFocusPostId(userid);
			List<User> funList = focusService.getFocusListByFocusUserId(userid);
			userDto.setIdolUserList(idolList);
			userDto.setFunUserList(funList);
			userDto.setIdolCount(idolList.size());
			userDto.setFunCount(funList.size());
			List<FoodRecipe> likeRecipeList = likeCollectService.selectLikeListRecipeByUserId(userid);
			for(FoodRecipe recipe : likeRecipeList) {
				List<FoodStep> stepsByRecipe = stepService.selectByRecipeId(recipe.getRecipeId());
				recipe.setRecipeSteps(stepsByRecipe);
			}
			
			List<FoodRecipe> collectRecipeList = likeCollectService.selectColListRecipeByUserId(userid);
			for(FoodRecipe recipe : collectRecipeList) {
				List<FoodStep> stepsByRecipe = stepService.selectByRecipeId(recipe.getRecipeId());
				recipe.setRecipeSteps(stepsByRecipe);
			}
			userDto.setCollectRecipeList(collectRecipeList);
			userDto.setLikeRecipeList(likeRecipeList);
			userDto.setLikeCount(likeRecipeList.size());
			userDto.setCollectCount(collectRecipeList.size());
			return new R<UserDTO>(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(),userDto);
		} catch (Exception e) {
			return R.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
		}
	}
}
