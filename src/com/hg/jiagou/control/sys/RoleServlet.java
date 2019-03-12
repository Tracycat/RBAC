package com.hg.jiagou.control.sys;

import com.hg.jiagou.service.sys.RoleService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RoleServlet extends HttpServlet
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
    if (choose.equals("role_index"))
      index(req, resp);
    else if (choose.equals("role_view"))
      view(req, resp);
    else if (choose.equals("role_update"))
      update(req, resp);
    else
      resp.sendRedirect("/RBAC/sys/user/user_login.html");
  }

  public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    HttpSession session = request.getSession();
    String uid = (String)session.getAttribute("uid");
    if (uid == null) {
      response.sendRedirect("/RBAC/sys/user/user_login.html");
    } else {
      int limits = 10;
      int currentPages = 0;
      RoleService serve = RoleService.getRoleService();
      int count = serve.getRoleCount();
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

      List roleList = serve.queryIndex(limits, currentPages);

      request.setAttribute("roleList", roleList);
      request.setAttribute("count", Integer.valueOf(count));
      request.setAttribute("currentPages", Integer.valueOf(currentPages));
      request.setAttribute("limits", Integer.valueOf(limits));
      request.setAttribute("totalPages", Integer.valueOf(totalPages));
      request.getRequestDispatcher("/sys/role/role_index.jsp").forward(request, response);
    }
  }

  public void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");

    int user_id = Integer.parseInt(request.getParameter("user_id"));
    int role_id = Integer.parseInt(request.getParameter("role_id"));

    RoleService role = RoleService.getRoleService();
    List roleViewsList = role.queryView(role_id);
    request.setAttribute("user_id", Integer.valueOf(user_id));
    request.setAttribute("roleViewsList", roleViewsList);
    request.getRequestDispatcher("/sys/role/role_test.jsp").forward(request, response);
  }

  public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");

    int user_id = Integer.parseInt(request.getParameter("user_id"));
    int role_id = Integer.parseInt(request.getParameter("role_id"));
    PrintWriter out = response.getWriter();

    RoleService role = RoleService.getRoleService();
    int rs = role.update(user_id, role_id);
    if (rs == 1) {
      out.println("<script>alert('成功！！');parentDialog.close();</script>");
    }
    else {
      out.println("<script>alert('错误，无法修改！！');window.location='/RBAC/DeptServlet?pages=1&choose=dept_index'</script>");
    }
    out.flush();
    out.close();
  }
}