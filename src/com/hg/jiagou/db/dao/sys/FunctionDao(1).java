package com.hg.jiagou.db.dao.sys;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hg.jiagou.db.dto.sys.DeptViewDto;
import com.hg.jiagou.db.dto.sys.FunctionDto;
import com.hg.jiagou.util.sys.BaseException;
import com.hg.jiagou.util.sys.C3p0_DataSource;
import com.hg.jiagou.util.sys.DBUtil;
import com.hg.jiagou.vo.sys.DBUtil_VO;
import com.hg.jiagou.vo.sys.Vo4Function;

public class FunctionDao {
	//单例模式
	private static FunctionDao s = new FunctionDao();
	public static FunctionDao getFunctionDao(){
	    return s;
	}
	//异常日志
	static Logger logger = Logger.getLogger(FunctionDao.class.getName());
	
	//查询总数
	 public int queryCount(){
	    DBUtil_VO dbvo = null;
	    int count = 0;
	    try {
	      Connection con = C3p0_DataSource.getConnection();
	      String sql = "select count(*) from tb_function";
	      dbvo = DBUtil.executeQuery(con, sql);
	      if (dbvo.rs.next())
	        count = dbvo.rs.getInt(1);
	    }
	    catch (Exception ex) {
	      logger.error("1001--FunctionDao.queryCount()数据库sql语句错误", ex);

	      throw new BaseException("系统异常1001", ex);
	    } finally {
	      DBUtil.realseSource(dbvo);
	    }
	    return count;
	  }
	 
	 //分页查询
	 public List<FunctionDto> queryIndex(int limits, int currentPages){
	    DBUtil_VO dbvo = null;
	    List<FunctionDto> list = new ArrayList<FunctionDto>();
	    try {
	      Connection con = C3p0_DataSource.getConnection();
	      String sql = "SELECT * FROM tb_function limit " + (currentPages - 1) * limits + "," + limits;
	      dbvo = DBUtil.executeQuery(con, sql);
	      while (dbvo.rs.next()) {
	    	FunctionDto dto = new FunctionDto();
	        dto.setFunction_id(dbvo.rs.getInt("function_id"));
	        dto.setFunction_name(dbvo.rs.getString("function_name"));
	        dto.setFunction_url(dbvo.rs.getString("function_url"));
	        dto.setParent_id(dbvo.rs.getInt("parent_id"));
	        dto.setFunction_level(dbvo.rs.getString("function_level"));
	        list.add(dto);
	      }
	    } catch (Exception ex) {
	      logger.error("1001--FunctionDao.queryIndex()数据库sql语句错误", ex);
	      throw new BaseException("系统异常1001", ex);
	    } finally {
	      DBUtil.realseSource(dbvo);
	    }
	    return list;
	  }
	 
	 //功能修改
	 public int update(Vo4Function vo){
		int rs;
		
	    try{
	      Connection con = C3p0_DataSource.getConnection();
	      String sql = "update tb_function set function_name='"+vo.getFunction_name()+"',function_url='"+vo.getFunction_url()+"',function_level='"+vo.getFunction_level()+"',parent_id="+vo.getParent_id()+" where function_id=" + vo.getFunction_id();
	      rs = DBUtil.executeUpdate(con, sql);
	    }
	    catch (Exception ex){
	      logger.error("1001--DeptDao.update()数据库sql语句错误", ex);
	      throw new BaseException("系统异常1001", ex);
	    }
	    return rs;
	  }
}
