package com.hg.jiagou.vo.sys;

public class Vo4Function {
	private int function_id;			//功能ID
	private String function_name;		//功能名称
	private String function_url;		//功能访问地址
	private String function_level;		//等级
	private int parent_id;				//上一级功能id
	public int getFunction_id() {
		return function_id;
	}
	public void setFunction_id(int function_id) {
		this.function_id = function_id;
	}
	public String getFunction_name() {
		return function_name;
	}
	public void setFunction_name(String function_name) {
		this.function_name = function_name;
	}
	public String getFunction_url() {
		return function_url;
	}
	public void setFunction_url(String function_url) {
		this.function_url = function_url;
	}
	public String getFunction_level() {
		return function_level;
	}
	public void setFunction_level(String function_level) {
		this.function_level = function_level;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
}
