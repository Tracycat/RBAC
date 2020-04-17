package com.hg.jiagou.db.dao.sys;

import com.hg.jiagou.db.dto.sys.DeptDto;
import com.hg.jiagou.db.dto.sys.DeptViewDto;
import com.hg.jiagou.util.sys.BaseException;
import com.hg.jiagou.util.sys.C3p0_DataSource;
import com.hg.jiagou.util.sys.DBUtil;
import com.hg.jiagou.vo.sys.DBUtil_VO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeptDao
{
  private static DeptDao s = new DeptDao();


  public static DeptDao getDeptDao()
  {
    return s;
  }

  public List<DeptViewDto> queryView()
  {
    DBUtil_VO dbvo = null;
    List list = new ArrayList();
    try {
      Connection con = C3p0_DataSource.getConnection();
      String sql = "select * from tb_dept";
      dbvo = DBUtil.executeQuery(con, sql);
      while (dbvo.rs.next()) {
        DeptViewDto dto = new DeptViewDto();
        dto.setDept_id(dbvo.rs.getInt("dept_id"));
        dto.setDept_level(dbvo.rs.getString("dept_level"));
        dto.setDept_name(dbvo.rs.getString("dept_name"));
        list.add(dto);
      }
    } catch (Exception ex) {

      throw new BaseException("系统异常1001", ex);
    } finally {
      DBUtil.realseSource(dbvo);
    }
    return list;
  }

  public int update(int user_id, int dept_id){
	  int rs;
    try
    {
      Connection con = C3p0_DataSource.getConnection();
      String sql = "update tb_user set dept_id=" + dept_id + " where user_id=" + user_id;
      rs = DBUtil.executeUpdate(con, sql);
    }
    catch (Exception ex)
    {
     

      throw new BaseException("系统异常1001", ex);
    }
    
    return rs;
  }

  public int queryCount()
  {
    DBUtil_VO dbvo = null;
    int count = 0;
    try {
      Connection con = C3p0_DataSource.getConnection();
      String sql = "select count(*) from tb_user";
      dbvo = DBUtil.executeQuery(con, sql);
      if (dbvo.rs.next())
        count = dbvo.rs.getInt(1);
    }
    catch (Exception ex) {

      throw new BaseException("系统异常1001", ex);
    } finally {
      DBUtil.realseSource(dbvo);
    }
    return count;
  }

  public List<DeptDto> queryIndex(int limits, int currentPages)
  {
    DBUtil_VO dbvo = null;
    List list = new ArrayList();
    try {
      Connection con = C3p0_DataSource.getConnection();
      String sql = "SELECT a.user_id,a.true_name,b.*,c.* FROM tb_user a,tb_dept b,tb_role c WHERE a.dept_id=b.dept_id AND a.role_id=c.role_id ORDER BY a.dept_id DESC limit " + (currentPages - 1) * limits + "," + limits;
      dbvo = DBUtil.executeQuery(con, sql);
      while (dbvo.rs.next()) {
        DeptDto dto = new DeptDto();
        dto.setUser_id(dbvo.rs.getInt("user_id"));
        dto.setTrue_name(dbvo.rs.getString("true_name"));
        dto.setRole_id(dbvo.rs.getInt("role_id"));
        dto.setRole_name(dbvo.rs.getString("role_name"));
        dto.setDept_id(dbvo.rs.getInt("dept_id"));
        dto.setDept_name(dbvo.rs.getString("dept_name"));
        dto.setDept_level(dbvo.rs.getString("dept_level"));
        list.add(dto);
      }
    } catch (Exception ex) {

      throw new BaseException("系统异常1001", ex);
    } finally {
      DBUtil.realseSource(dbvo);
    }
    return list;
  }
}