package com.jiuqi.cosmos.pojo;

import java.util.List;

public class AidInfo {
	//Integer recipeId, Integer [] firstCIdList
	private Integer recipeId;
	private List<Integer> firstCIdList;
	private Integer CrecipeId;
	
	public List<Integer> getFirstCIdList() {
		return firstCIdList;
	}
	public void setFirstCIdList(List<Integer> firstCIdList) {
		this.firstCIdList = firstCIdList;
	}
	public Integer getLrecipeId() {
		return recipeId;
	}
	public void setLrecipeId(Integer lrecipeId) {
		recipeId = lrecipeId;
	}
	public Integer getCrecipeId() {
		return CrecipeId;
	}
	public void setCrecipeId(Integer crecipeId) {
		CrecipeId = crecipeId;
	}
	
}
