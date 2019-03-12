package com.hg.jiagou.control.sys;

import com.hg.jiagou.service.sys.DeptService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeptServlet extends HttpServlet
{
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    doPost(req, resp);
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html;charset=UTF-8");
    String choose = req.getParameter("choose");
    if (choose.equals("dept_index"))
      index(req, resp);
    else if (choose.equals("dept_view"))
      view(req, resp);
    else if (choose.equals("dept_update"))
      update(req, resp);
    else
      resp.sendRedirect("/RBAC/sys/user/user_login.html");
  }

  public void update(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");

    int user_id = Integer.parseInt(request.getParameter("user_id"));
    int dept_id = Integer.parseInt(request.getParameter("dept_id"));
    PrintWriter out = response.getWriter();

    DeptService dept = DeptService.getDeptService();
    int rs = dept.update(user_id, dept_id);
    if (rs == 1) {
      out.println("<script>(function a(){alert('成功！！');parentDialog.close();})();</script>");
      response.sendRedirect("/RBAC/DeptServlet?pages=1&choose=dept_index");
    } else {
      out.println("<script>alert('错误，无法修改！！');window.location='/RBAC/DeptServlet?pages=1&choose=dept_index'</script>");
    }
    out.flush();
    out.close();
  }

  public void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");

    int user_id = Integer.parseInt(request.getParameter("user_id"));
    int dept_id = Integer.parseInt(request.getParameter("dept_id"));

    DeptService dept = DeptService.getDeptService();
    List deptViewsList = dept.queryView(dept_id);
    request.setAttribute("user_id", Integer.valueOf(user_id));
    request.setAttribute("deptViewsList", deptViewsList);
    request.getRequestDispatcher("/sys/dept/dept_test.jsp").forward(request, response);
  }

  public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    HttpSession session = request.getSession();
    String uid = (String)session.getAttribute("uid");
    if (uid == null) {
      response.sendRedirect("/RBAC/sys/user/user_login.html");
    } else {
      int limits = 10;
      int currentPages = 0;
      DeptService serve = DeptService.getDeptService();
      int count = serve.getDeptCount();
      int totalPages = 0;
      if (count % limits == 0)
        totalPages = count / limits;
      else {
        totalPages = count / limits + 1;
      }

      String pages = request.getParameter("pages");
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

      List deptList = serve.queryIndex(limits, currentPages);

      request.setAttribute("deptList", deptList);
      request.setAttribute("count", Integer.valueOf(count));
      request.setAttribute("currentPages", Integer.valueOf(currentPages));
      request.setAttribute("limits", Integer.valueOf(limits));
      request.setAttribute("totalPages", Integer.valueOf(totalPages));
      request.getRequestDispatcher("/sys/dept/dept_index.jsp").forward(request, response);
    }
  }
}