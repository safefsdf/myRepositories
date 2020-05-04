package com.jiuqi.cosmos.pojo;

import java.util.List;
import java.util.Map;

import com.jiuqi.cosmos.constants.ResultEnum;

public class R<T> {
	private int code;
	private String msg;
	private T data;
	private List<T> list;
	private Map<String, Integer> map;
	

	public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public R(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public R(int code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

//	成功且带数据
	public static <T> R<T> success(T t, Integer code, String msg) {
		R<T> result = new R<T>();
		result.setData(t);
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
//	成功且带数据--数据集
	public static <T> R<T> success(List<T> list, Integer code, String msg) {
		R<T> result = new R<T>();
		result.setList(list);
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

//	成功但不带数据
	public static <T> R<T> success(Integer code, String msg) {
		R<T> result = new R<T>();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

//	成功但不带数据
	public static <T> R<T> success() {
		return success(null, ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
	}
	
	// 失败
	public static <T> R<T> error(Integer code, String msg) {
		R<T> result = new R<T>();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
	public static <T> R<T> error(){
		return new R<T>(ResultEnum.ERROR.getCode(),ResultEnum.ERROR.getMsg());
	}
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "R [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

	public R() {
		super();
	}

}
