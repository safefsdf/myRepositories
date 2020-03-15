package com.jiuqi.cosmos.dao;

import java.util.List;

import com.jiuqi.cosmos.entity.ClassifyInfo;

public interface ClassifyInfoMapper {
	int insert(ClassifyInfo record);

	List<ClassifyInfo> selectAll();

	ClassifyInfo selectByClassifyName(String classifyName);

	List<ClassifyInfo> selectByClassifyPid(Integer classifyPid);

}