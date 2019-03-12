package com.hg.jiagou.service.sys;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hg.jiagou.db.dao.sys.DeptDao;
import com.hg.jiagou.db.dao.sys.FunctionDao;
import com.hg.jiagou.db.dto.sys.DeptDto;
import com.hg.jiagou.db.dto.sys.FunctionDto;
import com.hg.jiagou.vo.sys.Vo4Dept;
import com.hg.jiagou.vo.sys.Vo4Function;

public class FunctionService {
	//单例模式
	private static FunctionService s = new FunctionService();
	public static FunctionService getFunctionService(){
		return s; 
	}
	
	//查询总数
	public int getFunctionCount(){
		FunctionDao dao = FunctionDao.getFunctionDao();
		int count = dao.queryCount();
		return count;
	}
	
	//查询分页内容
	public List<Vo4Function> queryIndex(int limits, int currentPages){
	    List<Vo4Function> listVo = new ArrayList<Vo4Function>();//装载返回内容的数组
	    FunctionDto dto = new FunctionDto();//解读数据
	    //查询数据
	    FunctionDao dao = FunctionDao.getFunctionDao();
	    List<FunctionDto> list = dao.queryIndex(limits, currentPages);
	    //遍历数据
	    Iterator<FunctionDto> iterator = list.iterator();
	    while (iterator.hasNext()) {
	    	Vo4Function vo = new Vo4Function();
	      dto =iterator.next();
	      vo.setFunction_id(dto.getFunction_id());
	      vo.setFunction_name(dto.getFunction_name());
	      vo.setFunction_url(dto.getFunction_url());
	      vo.setParent_id(dto.getParent_id());
	      vo.setFunction_level(dto.getFunction_level());
	      listVo.add(vo);
	    }
	    return listVo;
	}
	
	//功能修改
	public int update(Vo4Function vo){
		FunctionDao dao = FunctionDao.getFunctionDao();
	    int rs = dao.update(vo);
	    return rs;
	  }
	
}
