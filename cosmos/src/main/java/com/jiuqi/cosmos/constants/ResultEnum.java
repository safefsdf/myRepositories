package com.jiuqi.cosmos.constants;

public enum ResultEnum  {
	UNKNOWN_ERROE(-1,"未知错误"),
	ERROR(0,"失败"),
	SUCCESS(200,"成功"),
	SUC(200,"文件上传成功"),
	USER_NOT_EXIST(1,"用户不存在"),
	USER_IS_EXISTS(2,"用户已存在"),
	DATA_IS_NULL(3,"数据为空"),
	PASSWORD_ERROR(4,"密码错误"),
	TOKEN_TIMEOUT(5,"token过期"),
	TOKEN_ERROR(6,"没有token"),
	FILE_ERROR(7,"文件上传失败"),
	REG_ERROR(8,"注册失败"),
	LIKE_ERROR(9,"操作失败");
	
	private Integer code;
	private String msg;
	ResultEnum(Integer code,String msg){
		this.code=code;
		this.msg=msg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
