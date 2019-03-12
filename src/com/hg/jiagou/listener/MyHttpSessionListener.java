package com.hg.jiagou.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener
  implements HttpSessionListener
{
  int number;

  public void sessionCreated(HttpSessionEvent se)
  {
    this.number += 1;
    se.getSession().getServletContext().setAttribute("number", Integer.valueOf(this.number));
//    System.out.println("----------------------------------1111111111111");
  }

  public void sessionDestroyed(HttpSessionEvent se)
  {
    this.number -= 1;
    se.getSession().getServletContext().setAttribute("number", Integer.valueOf(this.number));
//    System.out.println("----------------------------------2222222222222222");
  }
}