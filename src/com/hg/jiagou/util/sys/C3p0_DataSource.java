package com.hg.jiagou.util.sys;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.apache.log4j.Logger;

public final class C3p0_DataSource
{
  private static ComboPooledDataSource dataSource;
  static Logger logger = Logger.getLogger(C3p0_DataSource.class.getName());

  static {
    dataSource = new ComboPooledDataSource();
    Properties properties = new Properties();
    InputStream is = C3p0_DataSource.class.getClassLoader().getResourceAsStream("jdbc.properties");
    try {
      properties.load(is);
    } catch (Exception e) {
      logger.error("1001读取h_cp.properties配置文件失败", e);

      throw new BaseError("系统异常1001", e);
    }

    dataSource.setUser(properties.getProperty("user"));
    dataSource.setPassword(properties.getProperty("password"));
    dataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
    try {
      dataSource.setDriverClass(properties.getProperty("driverClass"));
    }
    catch (PropertyVetoException e) {
      logger.error("1002无法加载数据库连接驱动类", e);

      throw new BaseError("系统异常1002", e);
    }

    dataSource.setInitialPoolSize(Integer.parseInt(properties.getProperty("initialPoolSize")));
    dataSource.setMinPoolSize(Integer.parseInt(properties.getProperty("minPoolSize")));
    dataSource.setMaxPoolSize(Integer.parseInt(properties.getProperty("maxPoolSize")));
    dataSource.setMaxIdleTime(Integer.parseInt(properties.getProperty("maxIdleTime")));
    dataSource.setIdleConnectionTestPeriod(Integer.parseInt(properties.getProperty("idleConnectionTestPeriod")));
  }

  public static Connection getConnection() {
    try {
      return dataSource.getConnection();
    } catch (SQLException e) {
    	logger.error("1002无法获取数据库连接con", e);
    throw new RuntimeException("数据库异常", e);
    }
  }

  public static void realseSource(ResultSet rs, Statement stmt, Connection con)
  {
    try
    {
      try {
        if (rs != null)
          rs.close();
      } finally {
        try {
          if (stmt != null)
            stmt.close();
        } finally {
          if (con != null)
            con.close();
        }
      }
    } catch (SQLException ex) {
    	
      throw new RuntimeException("数据库异常", ex);
    }
  }
}