package com.hg.jiagou.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hg.jiagou.util.sys.BaseError;
import com.hg.jiagou.util.sys.BaseException;

public class Filter1  implements Filter
{
  public void destroy()
  {
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException
  {
    HttpServletRequest request2 = (HttpServletRequest)request;
    HttpServletResponse response2 = (HttpServletResponse)response;

    response2.setContentType("text/html;charset=UTF-8");

    String urlString = request2.getServletPath();
    if (urlString.equals("/sys/user/user_index.jsp")) {
      HttpSession session = request2.getSession();
      String uid = (String)session.getAttribute("uid");
      if (uid == null)
        response2.sendRedirect("/RBAC/sys/user/user_login.html");
      else{
	  	String error="";
		try{
			chain.doFilter(request, response);	
		}catch(BaseException err){			
			error = err.getKey();		
		}catch(BaseError err){
			error = err.getKey();
		}catch(Exception err){					//某些还未能考虑到的异常	
			error = "系统异常";
		}catch(Error err){						//某些还未能考虑到的错误
			error = "系统错误";
		}
      }
    }else if (urlString.equals("/sys/dept/dept_index.jsp")) {
      HttpSession session = request2.getSession();
      String uid = (String)session.getAttribute("uid");
      if (uid == null)
        response2.sendRedirect("/RBAC/sys/user/user_login.html");
      else{
  	  	String error="";
  		try{
  			chain.doFilter(request, response);	
  		}catch(BaseException err){			
  			error = err.getKey();		
  		}catch(BaseError err){
  			error = err.getKey();
  		}catch(Exception err){					//某些还未能考虑到的异常	
  			error = "系统异常";
  		}catch(Error err){						//某些还未能考虑到的错误
  			error = "系统错误";
  		}
       }
    }else if (urlString.equals("/sys/dept/role_index.jsp")) {
        HttpSession session = request2.getSession();
        String uid = (String)session.getAttribute("uid");
        if (uid == null)
          response2.sendRedirect("/RBAC/sys/user/user_login.html");
        else{
    	  	String error="";
    		try{
    			chain.doFilter(request, response);	
    		}catch(BaseException err){			
    			error = err.getKey();		
    		}catch(BaseError err){
    			error = err.getKey();
    		}catch(Exception err){					//某些还未能考虑到的异常	
    			error = "系统异常";
    		}catch(Error err){						//某些还未能考虑到的错误
    			error = "系统错误";
    		}
          }
    }else if (urlString.equals("/sys/function/function_index.jsp")) {
        HttpSession session = request2.getSession();
        String uid = (String)session.getAttribute("uid");
        if (uid == null)
          response2.sendRedirect("/RBAC/sys/user/user_login.html");
        else{
    	  	String error="";
    		try{
    			chain.doFilter(request, response);	
    		}catch(BaseException err){			
    			error = err.getKey();		
    		}catch(BaseError err){
    			error = err.getKey();
    		}catch(Exception err){					//某些还未能考虑到的异常	
    			error = "系统异常";
    		}catch(Error err){						//某些还未能考虑到的错误
    			error = "系统错误";
    		}
          }
    }else {
	  	String error="";
		try{
			chain.doFilter(request, response);	
		}catch(BaseException err){			
			error = err.getKey();		
		}catch(BaseError err){
			error = err.getKey();
		}catch(Exception err){					//某些还未能考虑到的异常	
			error = "系统异常";
		}catch(Error err){						//某些还未能考虑到的错误
			error = "系统错误";
		}
    }
  }

@Override
public void init(FilterConfig filterConfig) throws ServletException {
	// TODO Auto-generated method stub
	
}


}