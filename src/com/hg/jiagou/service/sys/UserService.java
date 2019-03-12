package com.hg.jiagou.service.sys;

import com.hg.jiagou.db.dao.sys.UserDao;
import com.hg.jiagou.db.dto.sys.UserDto;
import com.hg.jiagou.vo.sys.Vo4User;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UserService
{
  private static UserService s = new UserService();

  public static UserService getUserService() { return s; }

  public boolean userLoginService(String user_name, String password)
  {
    UserDao dao = UserDao.getUserDao();
    UserDto dto = new UserDto();
    List list = new ArrayList();
    int pin = 1;
    list = dao.query(user_name, password);

    Iterator it = list.iterator();

    dto = (UserDto)it.next();

    return dto.getQue();
  }

  public int getUserCount()
  {
    UserDao dao = UserDao.getUserDao();
    int count = dao.queryCount();
    return count;
  }

  public List<Vo4User> queryIndex(int limits, int currentPages) {
    List listVo = new ArrayList();
    UserDto dto = new UserDto();

    UserDao dao = UserDao.getUserDao();
    List list = dao.queryIndex(limits, currentPages);

    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
      Vo4User vo = new Vo4User();
      dto = (UserDto)iterator.next();
      vo.setUser_id(dto.getUser_id());
      vo.setUser_name(dto.getUser_name());
      vo.setTrue_name(dto.getTrue_name());
      vo.setUser_pw(dto.getUser_pw());
      vo.setUser_phone(dto.getUser_phone());
      vo.setDept_id(dto.getDept_id());
      vo.setRole_id(dto.getRole_id());
      vo.setData_status(dto.getData_status());
      vo.setCreate_time(dto.getCreate_time());
      vo.setLogo_url(dto.getLogo_url());
      listVo.add(vo);
    }
    return listVo;
  }

  public void userAdd(Vo4User vo) {
    UserDto dto = new UserDto();
    String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    dto.setUser_name(vo.getUser_name());
    dto.setTrue_name(vo.getTrue_name());
    dto.setUser_pw(vo.getUser_pw());
    dto.setUser_phone(vo.getUser_phone());
    dto.setDept_id(vo.getDept_id());
    dto.setRole_id(vo.getRole_id());
    dto.setLogo_url(vo.getLogo_url());
    dto.setCreate_time(now);
    UserDao dao = UserDao.getUserDao();
    dao.add(dto);
  }
}