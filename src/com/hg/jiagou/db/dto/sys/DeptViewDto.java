package com.hg.jiagou.db.dto.sys;

public class DeptViewDto
{
  private int dept_id;
  private String dept_name;
  private String dept_level;

  public int getDept_id()
  {
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