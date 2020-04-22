package com.jiuqi.cosmos.pojo;

import java.util.List;

import com.jiuqi.cosmos.entity.FoodRecipe;
import com.jiuqi.cosmos.entity.User;

public class UserDTO {
	private User user;
	
	private List<User> idolUserList;
	private Integer idolCount;
	
	private List<User> funUserList;
	private Integer funCount;
	
	private List<FoodRecipe> likeRecipeList;
	private Integer likeCount;
	
	private List<FoodRecipe> collectRecipeList;
	
	private Integer collectCount;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getIdolUserList() {
		return idolUserList;
	}

	public void setIdolUserList(List<User> idolUserList) {
		this.idolUserList = idolUserList;
	}

	public Integer getIdolCount() {
		return idolCount;
	}

	public void setIdolCount(Integer idolCount) {
		this.idolCount = idolCount;
	}

	public List<User> getFunUserList() {
		return funUserList;
	}

	public void setFunUserList(List<User> funUserList) {
		this.funUserList = funUserList;
	}

	public Integer getFunCount() {
		return funCount;
	}

	public void setFunCount(Integer funCount) {
		this.funCount = funCount;
	}

	public List<FoodRecipe> getLikeRecipeList() {
		return likeRecipeList;
	}

	public void setLikeRecipeList(List<FoodRecipe> likeRecipeList) {
		this.likeRecipeList = likeRecipeList;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public List<FoodRecipe> getCollectRecipeList() {
		return collectRecipeList;
	}

	public void setCollectRecipeList(List<FoodRecipe> collectRecipeList) {
		this.collectRecipeList = collectRecipeList;
	}

	public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	@Override
	public String toString() {
		return "UserDTO [user=" + user + ", idolUserList=" + idolUserList + ", idolCount=" + idolCount
				+ ", funUserList=" + funUserList + ", funCount=" + funCount + ", likeRecipeList=" + likeRecipeList
				+ ", likeCount=" + likeCount + ", collectRecipeList=" + collectRecipeList + ", collectCount="
				+ collectCount + "]";
	}
	
	
}
