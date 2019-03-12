package com.hg.jiagou.control.sys;

import com.hg.jiagou.service.sys.UserService;
import com.hg.jiagou.util.sys.Upload;
import com.hg.jiagou.vo.sys.Vo4Upload;
import com.hg.jiagou.vo.sys.Vo4User;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserServlet extends HttpServlet
{
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");

    String choose = request.getParameter("choose");
    if (choose.equals("login"))
      login(request, response);
    else if (choose.equals("Useradd"))
      userAdd(request, response);
    else if (choose.equals("index"))
      index(request, response);
    else
      response.sendRedirect("/RBAC/sys/user/user_login.html");
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
      UserService serve = UserService.getUserService();
      int count = serve.getUserCount();
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

      List userList = serve.queryIndex(limits, currentPages);

      ServletContext context = request.getServletContext();
//      System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkk1111111111111");
      int number=0;
      if((Integer)context.getAttribute("number")!=null){
    	  number = ((Integer)context.getAttribute("number")).intValue();
      }
      request.setAttribute("number", Integer.valueOf(number));
      request.setAttribute("userList", userList);
      request.setAttribute("count", Integer.valueOf(count));
      request.setAttribute("currentPages", Integer.valueOf(currentPages));
      request.setAttribute("limits", Integer.valueOf(limits));
      request.setAttribute("totalPages", Integer.valueOf(totalPages));
      request.getRequestDispatcher("/sys/user/user_index.jsp").forward(request, response);
    }
  }

  public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    String user_name = request.getParameter("user_name");
    String password = request.getParameter("password");
    String check = request.getParameter("check");
    HttpSession session = request.getSession();
    String check0 = (String)session.getAttribute("KAPTCHA_SESSION_KEY");
    UserService uus = UserService.getUserService();
    if (check.equals(check0)) {
      boolean que = uus.userLoginService(user_name, password);
      if (que) {
        session.setAttribute("uid", "uid");
        out.println("<script>alert('登陆成功！！');window.location.href='/RBAC/UserServlet?pages=1&choose=index'</script>");
      } else {
        out.println("<script>alert('账户或密码错误！！');window.location='/RBAC/sys/user/user_login.html'</script>");
      }
    }
    else {
      out.println("<script>alert('验证码错误！！');window.location='/RBAC/sys/user/user_login.html'</script>");
    }

    out.flush();
    out.close();
  }

  public void userAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    String user_name = null;
    String user_pw = null;
    String true_name = null;
    String user_phone = null;
    int dept_id = 0;
    int role_id = 0;
    String logo_url = null;
    String check = null;
    Upload upload = new Upload();
    List list = upload.upload(request);

    Vo4Upload voUp = new Vo4Upload();
    Iterator it = list.iterator();
    while (it.hasNext()) {
      voUp = (Vo4Upload)it.next();
      if (voUp.getName().equals("user_name")) { user_name = voUp.getValue();
      } else if (voUp.getName().equals("user_pw")) { user_pw = voUp.getValue();
      } else if (voUp.getName().equals("true_name")) { true_name = voUp.getValue();
      } else if (voUp.getName().equals("user_phone")) { user_phone = voUp.getValue();
      } else if (voUp.getName().equals("logo_url")) { logo_url = voUp.getValue();
      } else if (voUp.getName().equals("dept_id")) { dept_id = Integer.parseInt(voUp.getValue());
      } else if (voUp.getName().equals("role_id")) { role_id = Integer.parseInt(voUp.getValue()); } else {
        if (!voUp.getName().equals("check")) continue; check = voUp.getValue();
      }
    }
    Vo4User vo = new Vo4User();
    vo.setUser_name(user_name);
    vo.setUser_phone(user_phone);
    vo.setUser_pw(user_pw);
    vo.setTrue_name(true_name);
    vo.setRole_id(role_id);
    vo.setLogo_url(logo_url);
    vo.setDept_id(dept_id);
    UserService service = UserService.getUserService();
    HttpSession session = request.getSession();
    String check0 = (String)session.getAttribute("KAPTCHA_SESSION_KEY");
    if (check.equals(check0)) {
      service.userAdd(vo);
      out.println("<script>alert('成功！！');window.location='/RBAC/sys/user/user_login.html'</script>");
    } else {
      out.println("<script>alert('验证码错误！！');window.location='/RBAC/sys/user/user_add.html'</script>");
    }

    out.flush();
    out.close();
  }
}