package com.hg.jiagou.db.dto.sys;

public class RoleDto
{
  private int user_id;
  private String true_name;
  private int dept_id;
  private String dept_name;
  private String dept_level;
  private int role_id;
  private String role_name;

  public int getUser_id()
  {
    return this.user_id;
  }
  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }
  public String getTrue_name() {
    return this.true_name;
  }
  public void setTrue_name(String true_name) {
    this.true_name = true_name;
  }
  public int getDept_id() {
    return this.dept_id;
  }
  public void setDept_id(int dept_id) {
    this.dept_id = dept_id;
  }
  public String getDept_name() {
    return this.dept_name;
  }
  public void setDept_name(String dept_name) {
    this.dept_name = dept_name;
  }
  public String getDept_level() {
    return this.dept_level;
  }
  public void setDept_level(String dept_level) {
    this.dept_level = dept_level;
  }
  public int getRole_id() {
    return this.role_id;
  }
  public void setRole_id(int role_id) {
    this.role_id = role_id;
  }
  public String getRole_name() {
    return this.role_name;
  }
  public void setRole_name(String role_name) {
    this.role_name = role_name;
  }
}