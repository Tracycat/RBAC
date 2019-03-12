package com.hg.jiagou.service.sys;

import com.hg.jiagou.db.dao.sys.RoleDao;
import com.hg.jiagou.db.dto.sys.RoleDto;
import com.hg.jiagou.vo.sys.Vo4Role;
import com.hg.jiagou.vo.sys.Vo4RoleView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoleService
{
  private static RoleService s = new RoleService();

  public static RoleService getRoleService() { return s;
  }

  public int getRoleCount()
  {
    RoleDao dao = RoleDao.getRoleDao();
    int count = dao.queryCount();
    return count;
  }

  public List<Vo4Role> queryIndex(int limits, int currentPages)
  {
    List listVo = new ArrayList();
    RoleDto dto = new RoleDto();

    RoleDao dao = RoleDao.getRoleDao();
    List list = dao.queryIndex(limits, currentPages);

    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
      Vo4Role vo = new Vo4Role();
      dto = (RoleDto)iterator.next();
      vo.setUser_id(dto.getUser_id());
      vo.setTrue_name(dto.getTrue_name());
      vo.setDept_id(dto.getDept_id());
      vo.setRole_id(dto.getRole_id());
      vo.setRole_name(dto.getRole_name());
      vo.setDept_name(dto.getDept_name());
      vo.setDept_level(dto.getDept_level());
      listVo.add(vo);
    }
    return listVo;
  }

  public List<Vo4RoleView> queryView(int role_id_now)
  {
    List listVo = new ArrayList();
    RoleDto dto = new RoleDto();

    RoleDao dao = RoleDao.getRoleDao();
    List list = dao.queryView();

    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
      Vo4RoleView vo = new Vo4RoleView();
      dto = (RoleDto)iterator.next();
      vo.setRole_id(dto.getRole_id());
      vo.setRole_name(dto.getRole_name());
      if (dto.getRole_id() == role_id_now) {
        vo.setNow_role(dto.getRole_name());
      }
      listVo.add(vo);
    }

    return listVo;
  }

  public int update(int user_id, int role_id)
  {
    RoleDao dao = RoleDao.getRoleDao();
    int rs = dao.update(user_id, role_id);
    return rs;
  }
}