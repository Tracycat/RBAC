package com.hg.jiagou.service.sys;

import com.hg.jiagou.db.dao.sys.DeptDao;
import com.hg.jiagou.db.dto.sys.DeptDto;
import com.hg.jiagou.db.dto.sys.DeptViewDto;
import com.hg.jiagou.vo.sys.Vo4Dept;
import com.hg.jiagou.vo.sys.Vo4DeptView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeptService
{
  private static DeptService s = new DeptService();

  public static DeptService getDeptService() { return s; }

  public int getDeptCount()
  {
    DeptDao dao = DeptDao.getDeptDao();
    int count = dao.queryCount();
    return count;
  }

  public List<Vo4Dept> queryIndex(int limits, int currentPages) {
    List listVo = new ArrayList();
    DeptDto dto = new DeptDto();

    DeptDao dao = DeptDao.getDeptDao();
    List list = dao.queryIndex(limits, currentPages);

    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
      Vo4Dept vo = new Vo4Dept();
      dto = (DeptDto)iterator.next();
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

  public List<Vo4DeptView> queryView(int dept_id_now)
  {
    List listVo = new ArrayList();
    DeptViewDto dto = new DeptViewDto();

    DeptDao dao = DeptDao.getDeptDao();
    List list = dao.queryView();

    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
      Vo4DeptView vo = new Vo4DeptView();
      dto = (DeptViewDto)iterator.next();
      if (!dto.getDept_level().equals("0")) {
        vo.setDept_id(dto.getDept_id());
        vo.setDept_name(dto.getDept_name());
        vo.setDept_level(dto.getDept_level());
        if (dto.getDept_id() == dept_id_now) {
          vo.setNow_dept(dto.getDept_name());
        }
        listVo.add(vo);
      }
    }
    return listVo;
  }

  public int update(int user_id, int dept_id)
  {
    DeptDao dao = DeptDao.getDeptDao();
    int rs = dao.update(user_id, dept_id);
    return rs;
  }
}