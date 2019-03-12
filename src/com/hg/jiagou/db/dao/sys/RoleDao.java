package com.hg.jiagou.db.dao.sys;

import com.hg.jiagou.db.dto.sys.RoleDto;
import com.hg.jiagou.util.sys.BaseException;
import com.hg.jiagou.util.sys.C3p0_DataSource;
import com.hg.jiagou.util.sys.DBUtil;
import com.hg.jiagou.vo.sys.DBUtil_VO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class RoleDao
{
  private static RoleDao s = new RoleDao();

  static Logger logger = Logger.getLogger(RoleDao.class.getName());

  public static RoleDao getRoleDao()
  {
    return s;
  }

  public List<RoleDto> queryView()
  {
    DBUtil_VO dbvo = null;
    List list = new ArrayList();
    try {
      Connection con = C3p0_DataSource.getConnection();
      String sql = "select * from tb_role";
      dbvo = DBUtil.executeQuery(con, sql);
      while (dbvo.rs.next()) {
        RoleDto dto = new RoleDto();
        dto.setRole_id(dbvo.rs.getInt("role_id"));
        dto.setRole_name(dbvo.rs.getString("role_name"));
        list.add(dto);
      }
    } catch (Exception ex) {
      logger.error("1001--DeptDao.queryView()数据库sql语句错误", ex);

      throw new BaseException("系统异常1001", ex);
    } finally {
      DBUtil.realseSource(dbvo);
    }
    return list;
  }

  public int update(int user_id, int role_id)
  {
	  int rs;
    try
    {
      Connection con = C3p0_DataSource.getConnection();
      String sql = "update tb_user set role_id=" + role_id + " where user_id=" + user_id;
      rs = DBUtil.executeUpdate(con, sql);
    }
    catch (Exception ex)
    {
     
      logger.error("1001--DeptDao.update()数据库sql语句错误", ex);

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
      logger.error("1001--RoleDao.queryCount()数据库sql语句错误", ex);

      throw new BaseException("系统异常1001", ex);
    } finally {
      DBUtil.realseSource(dbvo);
    }
    return count;
  }

  public List<RoleDto> queryIndex(int limits, int currentPages)
  {
    List list = new ArrayList();
    DBUtil_VO dbvo = null;
    try {
      Connection con = C3p0_DataSource.getConnection();
      String sql = "SELECT a.user_id,a.true_name,b.*,c.* FROM tb_user a,tb_dept b,tb_role c WHERE a.dept_id=b.dept_id AND a.role_id=c.role_id ORDER BY c.role_id ASC limit " + (currentPages - 1) * limits + "," + limits;
      dbvo = DBUtil.executeQuery(con, sql);
      while (dbvo.rs.next()) {
        RoleDto dto = new RoleDto();
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
      logger.error("1001--RoleDao.queryIndex()数据库sql语句错误", ex);

      throw new BaseException("系统异常1001", ex);
    } finally {
      DBUtil.realseSource(dbvo);
    }
    return list;
  }
}