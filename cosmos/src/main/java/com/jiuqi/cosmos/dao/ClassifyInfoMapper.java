package com.jiuqi.cosmos.dao;

import java.util.List;

import com.jiuqi.cosmos.entity.ClassifyInfo;

public interface ClassifyInfoMapper {
	/**
	 * 新建分类信息
	 * @param record
	 * @return
	 */
	int insert(ClassifyInfo record);

	/**
	 * 根据分类的名字查询类别
	 * @param classifyName
	 * @return
	 */
	ClassifyInfo selectByClassifyName(String classifyName);

	/**
	 * 查询二级、三级分类信息
	 * @param classifyPid
	 * @return
	 */
	List<ClassifyInfo> selectByClassifyPid(Integer classifyPid);
	

}