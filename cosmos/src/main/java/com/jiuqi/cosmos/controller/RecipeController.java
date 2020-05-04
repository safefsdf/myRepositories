package com.jiuqi.cosmos.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jiuqi.cosmos.constants.ResultEnum;
import com.jiuqi.cosmos.entity.ClassifyInfo;
import com.jiuqi.cosmos.entity.FoodRecipe;
import com.jiuqi.cosmos.entity.User;
import com.jiuqi.cosmos.pojo.R;
import com.jiuqi.cosmos.service.RecipeService;
import com.jiuqi.cosmos.service.UserService;
import com.jiuqi.cosmos.util.RecipeUtil;
import com.jiuqi.cosmos.util.RedisUtil;
import com.jiuqi.cosmos.util.picUtil;

import io.netty.util.internal.StringUtil;

@RestController
@RequestMapping("recipe")
@CrossOrigin
public class RecipeController {

	@Autowired
	RecipeService recipeService;

	@Autowired
	private RedisUtil<FoodRecipe> redisRecipeService;
	@Autowired
	private RedisUtil<ClassifyInfo> redisClassifyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	RecipeUtil recipeUtilSer;
	
	/*获取食谱封面图*/
	@PostMapping("/coverImg")
	public R<String> importData(MultipartFile file, HttpServletRequest req) throws IOException {
		try {
			String singleFileUpload = picUtil.singleFileUpload(file,"coverImages");
			System.out.println(singleFileUpload);
			return R.success(singleFileUpload, 200, "suc");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	@RequestMapping("/getre")
	R<FoodRecipe> getRecipe() {
		try {
			
			return new R<FoodRecipe>(200, "stc");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new R<FoodRecipe>(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());

	}
	/**
	 * 获取主键为recipeId的食谱信息
	 * @return
	 */
	@RequestMapping("/getRecipe")
	R<FoodRecipe> getRecipeByRecipeId(Integer recipeId) {
		try {
			FoodRecipe recipe = recipeService.selectByPrimaryKey(recipeId);
			recipe.setUserDto(userService.getById(recipe.getUserId()));
			return R.success(recipe,200, ResultEnum.SUCCESS.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new R<FoodRecipe>(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());

	}
	/**
	 * 获取一级分类
	 * 
	 * @param classifyPid
	 * @return
	 */
	@RequestMapping("/getClassifyByPid")
	public R<ClassifyInfo> getClassifyByPid(int classifyPid) {
		System.out.println(classifyPid);
		try {
			List<ClassifyInfo> selectClassifies = recipeService.selectByClassifyPid(classifyPid);
			R<ClassifyInfo> r = new R<ClassifyInfo>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
			r.setList(selectClassifies);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new R<ClassifyInfo>(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
	}

	/**
	 * 获取一级分类下的子分类
	 * 
	 * @param classifyPid
	 * @return
	 */
	@RequestMapping("/getClassifyByPidST")
	public R<ClassifyInfo> getClassifyST(int classifyPid) {
		System.out.println(classifyPid);
		try {
			List<ClassifyInfo> selectClassifies = recipeService.selectByClassifyPid(classifyPid);
			for (ClassifyInfo info : selectClassifies) {
				info.setSonList(recipeService.selectByClassifyPid(info.getClassifyId()));
			}
			R<ClassifyInfo> r = new R<ClassifyInfo>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
			r.setList(selectClassifies);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new R<ClassifyInfo>(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
	}

	/**
	 * 获取轮播图的食谱内容 默认选用几个好看的。 根据该用户关注的人所发表的食谱。 点赞或关注食谱分类下的其他食谱 涉及主动推送的内容
	 * userId:当前登陆的用户
	 * 
	 * @return 集合部分：该用户关注点赞收藏所涉及的食谱集合 查了三次数据库
	 *         我想：每隔30分钟将内容同步到redis中一次，然后用户访问的时候，直接访问redis，如果没有再？---行不通！
	 *         针对关注的用户发送博客，主动推送给被关注的用户！
	 */
	@RequestMapping("getListByToken")
	public R<FoodRecipe> getFromRedisByToken(String token) {
		List<FoodRecipe> lGet = null;
		try {
			if (StringUtil.isNullOrEmpty(token)) {
				lGet = redisRecipeService.lGet("notoken", 0, 50);
				System.out.println(lGet.size());
				return R.success(lGet, ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
			}
			lGet = redisRecipeService.lGet(token + "1", 0, 100);
			if (lGet == null || lGet.size() <= 0) {
				Object u = redisRecipeService.get(token);
				if (u != null && u instanceof User) {
					User user = (User) u;
					List<FoodRecipe> relateRecipeByUserid = recipeService.getRelateRecipeByUserid(user.getUserId());
					System.out.println(relateRecipeByUserid.size());
					return R.success(relateRecipeByUserid, ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
				} else {
					// 跳转到登陆页面，提示当前登录已失效
					return R.error(ResultEnum.TOKEN_TIMEOUT.getCode(), ResultEnum.TOKEN_TIMEOUT.getMsg());
				}
			}
			return R.success(lGet, ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping("/slide")
	public R<FoodRecipe> getSlideInfo(){
		List<FoodRecipe> list = null;
		try {
			list = new ArrayList<FoodRecipe>();
			//9,547,3598,147
			int [] recipeId = {9,547,3598,147};
			for(int rId:recipeId) {
				list.add(recipeService.selectByPrimaryKey(rId));
			}
			return R.success(list, ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return R.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
		
	}

	/*
	 * @RequestMapping("saveToRedis") public R<?> saveToThirdLevels() {
	 * List<ClassifyInfo> selectThirdAll = recipeService.selectThirdAll();
	 * redisClassifyService.pushToList("classify",selectThirdAll); return
	 * R.success(); }
	 */
	@RequestMapping("thirdLevels")
	public R<ClassifyInfo> getThirdLevels() {
		List<ClassifyInfo> lGet = redisClassifyService.lGet("classify", 0, 30);
		System.out.println(lGet.size());
		return R.success(lGet, ResultEnum.SUCCESS.getCode(),  ResultEnum.SUCCESS.getMsg());
		
	}
	@RequestMapping("saveRecipe")
	public R<FoodRecipe> saveRecipe(@RequestBody FoodRecipe recipe) {
		int insert = recipeService.insert(recipe);
		System.out.println(recipe.getRecipeId());
		System.out.println(insert);
		
		return R.success(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
		
	}
	@RequestMapping("getRecipeByCid")
	public R<FoodRecipe> getRecipeByCid(Integer classifyId) {
		try {
			System.out.println("分类到首页面的跳转： "+classifyId);
			List<FoodRecipe> selectByClassifyId = recipeService.selectByClassifyId(classifyId);
			System.out.println(selectByClassifyId);
			return R.success(selectByClassifyId,ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return R.error();
	}
	@RequestMapping("onSearch")
	public R<FoodRecipe> getRecipeByCid(String keyword) {
		try {
			List<FoodRecipe> queryByRecipeTitleVague = recipeService.queryByRecipeTitleVague(keyword);
			return R.success(queryByRecipeTitleVague, ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return R.error();
	}
	
	
	
	
	
	
}
