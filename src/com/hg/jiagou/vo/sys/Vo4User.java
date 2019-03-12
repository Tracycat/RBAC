package com.hg.jiagou.vo.sys;

public class Vo4User
{
  private int user_id;
  private String user_name;
  private String true_name;
  private String user_pw;
  private String user_phone;
  private int dept_id;
  private int role_id;
  private int data_status;
  private String create_time;
  private String logo_url;

  public int getUser_id()
  {
    return this.user_id;
  }
  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }
  public String getUser_name() {
    return this.user_name;
  }
  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }
  public String getTrue_name() {
    return this.true_name;
  }
  public void setTrue_name(String true_name) {
    this.true_name = true_name;
  }
  public String getUser_pw() {
    return this.user_pw;
  }
  public void setUser_pw(String user_pw) {
    this.user_pw = user_pw;
  }
  public String getUser_phone() {
    return this.user_phone;
  }
  public void setUser_phone(String user_phone) {
    this.user_phone = user_phone;
  }
  public int getDept_id() {
    return this.dept_id;
  }
  public void setDept_id(int dept_id) {
    this.dept_id = dept_id;
  }
  public int getRole_id() {
    return this.role_id;
  }
  public void setRole_id(int role_id) {
    this.role_id = role_id;
  }
  public int getData_status() {
    return this.data_status;
  }
  public void setData_status(int data_status) {
    this.data_status = data_status;
  }
  public String getCreate_time() {
    return this.create_time;
  }
  public void setCreate_time(String create_time) {
    this.create_time = create_time;
  }
  public String getLogo_url() {
    return this.logo_url;
  }
  public void setLogo_url(String logo_url) {
    this.logo_url = logo_url;
  }
}