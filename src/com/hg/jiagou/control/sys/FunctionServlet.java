package com.hg.jiagou.control.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hg.jiagou.service.sys.DeptService;
import com.hg.jiagou.service.sys.FunctionService;
import com.hg.jiagou.vo.sys.Vo4Function;

public class FunctionServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html;charset=UTF-8");
	    String choose = req.getParameter("choose");
	    if (choose.equals("function_index"))
	      index(req, resp);
	    else if (choose.equals("function_update"))
	      update(req, resp);
	    else if (choose.equals("function_view"))
	        view(req, resp);
	    else
	      resp.sendRedirect("/RBAC/sys/user/user_login.html");
	}
	public void index(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html;charset=UTF-8");
	    HttpSession session = request.getSession();
	    String uid = (String)session.getAttribute("uid");
	    if (uid == null) {
	      response.sendRedirect("/RBAC/sys/user/user_login.html");
	    } else {
	      int limits = 10;//一页显示的数据量
	      int currentPages = 0;//当前页码
	      FunctionService serve = FunctionService.getFunctionService();
	      int count = serve.getFunctionCount();//总数据量
	      int totalPages = 0;//总页数
	      if (count % limits == 0)
	        totalPages = count / limits;
	      else {
	        totalPages = count / limits + 1;
	      }

	      String pages = request.getParameter("pages");//待显示的页码
	      if (pages == null) {
	        currentPages = 1;
	      }
	      else if (Integer.parseInt(pages) <= 0) {
	        currentPages = 1;
	      }
	      else if (Integer.parseInt(pages) >= totalPages)
	        currentPages = totalPages;
	      else {
	        currentPages = Integer.parseInt(pages);
	      }

	      List<Vo4Function> functionList = serve.queryIndex(limits, currentPages);

	      request.setAttribute("functionList", functionList);
	      request.setAttribute("count", count);
	      request.setAttribute("currentPages", currentPages);
	      request.setAttribute("limits",limits);
	      request.setAttribute("totalPages", totalPages);
	      request.getRequestDispatcher("/sys/function/function_index.jsp").forward(request, response);//内跳转
	    }
	}
	public void view(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html;charset=UTF-8");
	    String function_id = request.getParameter("function_id");
	    String function_name = request.getParameter("function_name");
	    function_name =  java.net.URLDecoder.decode(function_name , "UTF-8");//转码
	    String function_url = request.getParameter("function_url");
	    String function_level = request.getParameter("function_level");
	    String parent_id = request.getParameter("parent_id");
	   

	    request.setAttribute("function_id",function_id);
	    request.setAttribute("function_name",function_name);
	    request.setAttribute("function_url",function_url);
	    request.setAttribute("function_level",function_level);
	    request.setAttribute("parent_id",parent_id);
	    request.getRequestDispatcher("/sys/function/function_test.jsp").forward(request, response);
	}
	public void update(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//获取数据
		int function_id = Integer.parseInt(request.getParameter("function_id"));
	    String function_name = request.getParameter("function_name");
	    String function_url = request.getParameter("function_url");
	    String function_level = request.getParameter("function_level");
	    int parent_id = Integer.parseInt(request.getParameter("parent_id"));
	    PrintWriter out = response.getWriter();
	    //封装数据
	    Vo4Function vo =new Vo4Function();
	    vo.setFunction_id(function_id);
	    vo.setFunction_level(function_level);
	    vo.setFunction_name(function_name);
	    vo.setFunction_url(function_url);
	    vo.setParent_id(parent_id);
	    
	    FunctionService serve = FunctionService.getFunctionService();
	    int rs = serve.update(vo);
	    if (rs == 1) {
	      out.println("<script>(function a(){alert('成功！！');parentDialog.close();})();</script>");
	    } else {
	      out.println("<script>alert('错误，无法修改！！');window.location='/RBAC/DeptServlet?pages=1&choose=dept_index'</script>");
	    }
	    out.flush();
	    out.close();
	}
}
