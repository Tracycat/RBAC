package com.hg.jiagou.util.sys;

import com.hg.jiagou.vo.sys.DBUtil_VO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import org.apache.log4j.Logger;

public class DBUtil
{
  private static Logger logger = Logger.getLogger(DBUtil.class.getName());

  private static void realseSource(ResultSet _rs, Statement _st, Connection _conn)
  {
    C3p0_DataSource.realseSource(_rs, _st, _conn);
  }

  public static void realseSource(DBUtil_VO _vo)
  {
    if (_vo != null)
      realseSource(_vo.rs, _vo.st, _vo.conn);
  }

  public static DBUtil_VO executeQuery(Connection _conn, String _sql)
  {
    DBUtil_VO vo = new DBUtil_VO();
    vo.conn = _conn;
    try {
      vo.st = vo.conn.createStatement();
    } catch (SQLException e) {
      realseSource(vo);
      String uuid = UUID.randomUUID().toString();
      logger.error("UUID:" + uuid + ", 无法从连接中创建Statement", e);
      throw new BaseException("err.user.dbutil.jdbc", e, new Object[] { uuid });
    }
    try {
      vo.rs = vo.st.executeQuery(_sql);
    } catch (SQLException e) {
      realseSource(vo);
      String uuid = UUID.randomUUID().toString();
      logger.error("UUID:" + uuid + ", SQL语法有误: " + _sql, e);
      throw new BaseException("err.user.dao.jdbc", e, new Object[] { uuid });
    }
    return vo;
  }

  public static int executeUpdate(Connection _conn, String _sql)
  {
    int rs = 0;
    Connection conn = _conn;
    Statement st = null;
    try {
      st = conn.createStatement();
    } catch (SQLException e) {
      realseSource(null, st, conn);
      String uuid = UUID.randomUUID().toString();
      logger.error("UUID:" + uuid + ", 无法从连接中创建Statement", e);
      throw new BaseException("err.user.dbutil.jdbc", e, new Object[] { uuid });
    }
    try {
      rs = st.executeUpdate(_sql);
    } catch (SQLException e) {
      realseSource(null, st, conn);
      String uuid = UUID.randomUUID().toString();
      logger.error("UUID:" + uuid + ", SQL语法有误: " + _sql, e);
      throw new BaseException("err.user.dao.jdbc", e, new Object[] { uuid });
    }
    realseSource(null, st, conn);

    return rs;
  }
}