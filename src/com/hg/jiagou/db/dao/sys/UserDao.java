package com.hg.jiagou.db.dao.sys;

import com.hg.jiagou.db.dto.sys.UserDto;
import com.hg.jiagou.util.sys.BaseException;
import com.hg.jiagou.util.sys.C3p0_DataSource;
import com.hg.jiagou.util.sys.DBUtil;
import com.hg.jiagou.vo.sys.DBUtil_VO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class UserDao
{
  private static UserDao s = new UserDao();

  static Logger logger = Logger.getLogger(UserDao.class.getName());

  public static UserDao getUserDao()
  {
    return s;
  }

  public List<UserDto> query(String user_name, String password)
  {
    DBUtil_VO dbvo = null;
    UserDto dto = new UserDto();
    List list = new ArrayList();
    try {
      Connection con = C3p0_DataSource.getConnection();
      String sql = "select * from tb_user where user_name='" + user_name + "' and user_pw='" + password + "'";
      dbvo = DBUtil.executeQuery(con, sql);
      if (dbvo.rs.next()) {
        dto.setUser_id(dbvo.rs.getInt("user_id"));
        dto.setUser_name(dbvo.rs.getString("user_name"));
        dto.setUser_phone(dbvo.rs.getString("user_phone"));
        dto.setUser_pw(dbvo.rs.getString("user_pw"));
        dto.setTrue_name(dbvo.rs.getString("true_name"));
        dto.setRole_id(dbvo.rs.getInt("role_id"));
        dto.setLogo_url(dbvo.rs.getString("logo_url"));
        dto.setDept_id(dbvo.rs.getInt("dept_id"));
        dto.setData_status(dbvo.rs.getInt("data_status"));
        dto.setCreate_time(dbvo.rs.getString("create_time"));
        dto.setQue(true);
        list.add(dto);
      } else {
        dto.setQue(false);
        list.add(dto);
      }
    } catch (Exception ex) {
      logger.error("1001--UserDAO.query()数据库sql语句错误", ex);

      throw new BaseException("系统异常1001", ex);
    } finally {
      DBUtil.realseSource(dbvo);
    }
    return list;
  }

  public int queryCount() {
    int count = 0;
    DBUtil_VO dbvo = null;
    try {
      Connection con = C3p0_DataSource.getConnection();
      String sql = "select count(*) from tb_user";
      dbvo = DBUtil.executeQuery(con, sql);
      if (dbvo.rs.next())
        count = dbvo.rs.getInt(1);
    }
    catch (Exception ex) {
      logger.error("1001--UserDAO.queryCount()数据库sql语句错误", ex);

      throw new BaseException("系统异常1001", ex);
    } finally {
      DBUtil.realseSource(dbvo);
    }
    return count;
  }

  public List<UserDto> queryIndex(int limits, int currentPages)
  {
    DBUtil_VO dbvo = null;
    List list = new ArrayList();
    try {
      Connection con = C3p0_DataSource.getConnection();
      String sql = "select * from tb_user limit " + (currentPages - 1) * limits + "," + limits;
      dbvo = DBUtil.executeQuery(con, sql);
      while (dbvo.rs.next()) {
        UserDto dto = new UserDto();
        dto.setUser_id(dbvo.rs.getInt("user_id"));
        dto.setUser_name(dbvo.rs.getString("user_name"));
        dto.setUser_phone(dbvo.rs.getString("user_phone"));
        dto.setUser_pw(dbvo.rs.getString("user_pw"));
        dto.setTrue_name(dbvo.rs.getString("true_name"));
        dto.setRole_id(dbvo.rs.getInt("role_id"));
        dto.setLogo_url(dbvo.rs.getString("logo_url"));
        dto.setDept_id(dbvo.rs.getInt("dept_id"));
        dto.setData_status(dbvo.rs.getInt("data_status"));
        dto.setCreate_time(dbvo.rs.getString("create_time"));
        list.add(dto);
      }
    } catch (Exception ex) {
      logger.error("1001--UserDAO.queryIndex()数据库sql语句错误", ex);

      throw new BaseException("系统异常1001", ex);
    } finally {
      DBUtil.realseSource(dbvo);
    }
    return list;
  }

  public void add(UserDto dto)
  {
    try {
      Connection con = C3p0_DataSource.getConnection();
      String sql = "insert into tb_user (user_name,true_name,user_pw,user_phone,dept_id,role_id,data_status,create_time,logo_url) value ('" + 
        dto.getUser_name() + "','" + dto.getTrue_name() + "','" + dto.getUser_pw() + "','" + 
        dto.getUser_phone() + "','" + String.valueOf(dto.getDept_id()) + "','" + String.valueOf(dto.getRole_id()) + "','" + 
        String.valueOf(dto.getData_status()) + "','" + dto.getCreate_time() + "','" + dto.getLogo_url() + "')";
      DBUtil.executeUpdate(con, sql);
    } catch (Exception ex) {
      logger.error("1001--UserDAO.add()数据库sql语句错误", ex);

      throw new BaseException("系统异常1001", ex);
    }
  }

  public void update(UserDto dto, int pin)
  {
    try {
      Connection con = C3p0_DataSource.getConnection();
      String sql = null;
      if (pin == 1) {
        sql = "update tb_user set user_name='" + dto.getUser_name() + "',true_name='" + dto.getTrue_name() + "',user_pw='" + dto.getUser_pw() + "',user_phone='" + 
          dto.getUser_phone() + "',dept_id='" + String.valueOf(dto.getDept_id()) + "',role_id='" + String.valueOf(dto.getRole_id()) + 
          "',data_status='" + String.valueOf(dto.getData_status()) + "',create_time='" + dto.getCreate_time() + "',logo_url='" + 
          dto.getLogo_url() + "' where user_id='" + String.valueOf(dto.getUser_id()) + "'";
      }
      DBUtil.executeUpdate(con, sql);
    } catch (Exception ex) {
      logger.error("1001--UserDAO.update()数据库sql语句错误", ex);

      throw new BaseException("系统异常1001", ex);
    }
  }

  public void delete(String table, String idname, int id) {
    try {
      Connection con = C3p0_DataSource.getConnection();
      String sql = "delete from " + table + " where " + idname + "='" + id + "'";
      DBUtil.executeUpdate(con, sql);
    } catch (Exception ex) {
      logger.error("1001--UserDAO.delete()数据库sql语句错误", ex);

      throw new BaseException("系统异常1001", ex);
    }
  }
}