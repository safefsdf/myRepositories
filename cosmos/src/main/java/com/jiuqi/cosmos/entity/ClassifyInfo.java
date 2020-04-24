package com.jiuqi.cosmos.entity;

import java.util.List;

public class ClassifyInfo {

    private Integer classifyId;

    private String classifyName;

    private Integer classifyPid;

    private List<ClassifyInfo> sonList;
    
    public List<ClassifyInfo> getSonList() {
		return sonList;
	}

	public void setSonList(List<ClassifyInfo> sonList) {
		this.sonList = sonList;
	}

	public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName == null ? null : classifyName.trim();
    }

    public Integer getClassifyPid() {
        return classifyPid;
    }

    public void setClassifyPid(Integer classifyPid) {
        this.classifyPid = classifyPid;
    }
    
    public ClassifyInfo(Integer classifyId, String classifyName, Integer classifyPid) {
		super();
		this.classifyId = classifyId;
		this.classifyName = classifyName;
		this.classifyPid = classifyPid;
	}

	public ClassifyInfo(String classifyName, Integer classifyPid) {
		this(null, classifyName, classifyPid);
	}

	public ClassifyInfo() {
		super();
	}

	@Override
	public String toString() {
		return "ClassifyInfo [classifyId=" + classifyId + ", classifyName=" + classifyName + ", classifyPid="
				+ classifyPid + ", sonList=" + sonList + "]";
	}

}
