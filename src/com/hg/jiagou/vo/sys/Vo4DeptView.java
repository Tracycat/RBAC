package com.hg.jiagou.vo.sys;

public class Vo4DeptView
{
  private int dept_id;
  private String dept_name;
  private String dept_level;
  private String now_dept;

  public String getNow_dept()
  {
    return this.now_dept;
  }
  public void setNow_dept(String now_dept) {
    this.now_dept = now_dept;
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
}