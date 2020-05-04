package com.jiuqi.cosmos.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiuqi.cosmos.dao.ClassifyInfoMapper;
import com.jiuqi.cosmos.dao.CollectToBlogMapper;
import com.jiuqi.cosmos.dao.FoodRecipeMapper;
import com.jiuqi.cosmos.dao.FoodStepMapper;
import com.jiuqi.cosmos.dao.LikeToBlogMapper;
import com.jiuqi.cosmos.entity.ClassifyInfo;
import com.jiuqi.cosmos.entity.FoodRecipe;
import com.jiuqi.cosmos.entity.FoodStep;
import com.jiuqi.cosmos.pojo.AidInfo;
import com.jiuqi.cosmos.service.RecipeService;
import com.jiuqi.cosmos.util.ComparatorOuter;
import com.jiuqi.cosmos.util.RecipeUtil;
@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	FoodRecipeMapper recipeDao;

	@Autowired
	FoodStepMapper stepDao;
	
	@Autowired
	ClassifyInfoMapper classifyDao;
	
	@Autowired
	LikeToBlogMapper likeToBlogDao;
	
	@Autowired
	CollectToBlogMapper collectToBlogDao;
	
	@Override
	public int insert(FoodRecipe recipe) {
		return recipeDao.insert(recipe);
	}

	/**
	 * 00：recipe不存在 01: recipeStep未删除成功 02：recipeSteps为空 03： 删除成功
	 * 
	 * 	删除某一条食谱：食谱表，步骤表，点赞表，收藏表。凡是跟食谱Id相关的表中的数据都要删除
	 */
	@Override
	public void delete(Integer recipeId) {
		try {
			recipeDao.deleteByPrimaryKey(recipeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public FoodRecipe selectByPrimaryKey(Integer recipeId) {
		FoodRecipe foodRecipe = recipeDao.selectByPrimaryKey(recipeId);
		List<FoodStep> recipeSteps = stepDao.selectByRecipeId(recipeId);
		foodRecipe.setRecipeSteps(recipeSteps);
		return foodRecipe;

	}
	

	@Override
	public List<FoodRecipe> selectByClassifyId(Integer classifyId) {
		List<FoodRecipe> list = recipeDao.selectByClassifyId(classifyId);
		for (FoodRecipe recipe : list) {
			List<FoodStep> stepsByRecipe = stepDao.selectByRecipeId(recipe.getRecipeId());
			recipe.setRecipeSteps(stepsByRecipe);
		}
		return list;
	}

	@Override
	public List<FoodRecipe> selectByUserId(Integer userId) {
		List<FoodRecipe> list = recipeDao.selectByUserId(userId);
		for (FoodRecipe recipe : list) {
			List<FoodStep> stepsByRecipe = stepDao.selectByRecipeId(recipe.getRecipeId());
			recipe.setRecipeSteps(stepsByRecipe);
		}
		return list;
	}

	@Override
	public List<ClassifyInfo> selectByClassifyPid(Integer classifyPid) {
		return classifyDao.selectByClassifyPid(classifyPid);
	}
	
	/**
	 * 1.点赞表和收藏表,通过用户主键关联,然后根据用户Id获取该用户点赞或收藏的博客主键集合
	 * 2.根据1的博客主键,获取该主键对应类别下的博客信息
	 * @param userId
	 * @return
	 */
	public List<FoodRecipe> getRecipeListByUserId(Integer userId){
		List<FoodRecipe> newList = new ArrayList<FoodRecipe>();
		List<AidInfo> selectReciIdByUid = recipeDao.selectReciIdByUid(userId);
		List<Integer> recipeIdList = new ArrayList<Integer>();
		for (AidInfo info : selectReciIdByUid) {
			if (recipeIdList.contains(info.getCrecipeId()))
				continue;
			recipeIdList.add(info.getCrecipeId());
			if (recipeIdList.contains(info.getLrecipeId()))
				continue;
			recipeIdList.add(info.getLrecipeId());
		}
		//与该用户有关的食谱ID，对应分类下的其他食谱集合
		
		for (Integer i : recipeIdList) {
			List<FoodRecipe> recipeList = recipeDao.selectRecipeListByOtherRecipe(i);
			RecipeUtil.bianli(recipeList, newList);
		}
		return newList;
		
	}
	@Override
	public List<FoodRecipe> getRelateRecipeByUserid(Integer userId) {
		List<FoodRecipe> recipeListByUserId;
		try {
			recipeListByUserId = getRecipeListByUserId(userId);
			//根据该用户关注的用户,获取他发表的博客
			List<FoodRecipe> selectRecipeByPostUserId = recipeDao.selectRecipeByPostUserId(userId);
			RecipeUtil.bianli(selectRecipeByPostUserId, recipeListByUserId);
			List<FoodRecipe> selectAll = recipeDao.selectAll();
			RecipeUtil.bianli(selectAll, recipeListByUserId);
			Collections.sort(recipeListByUserId, new ComparatorOuter());
			System.out.println(recipeListByUserId.size());
			return recipeListByUserId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<FoodRecipe> selectAll() {
		return recipeDao.selectAll();
	}
	
	@Override
	public List<FoodRecipe> queryByRecipeTitleVague(String recipeTitle) {
		return recipeDao.queryByRecipeTitleVague(recipeTitle);
	}
	
	
	
	
	
	
}
