<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> --%>
<%@ page isELIgnored="false"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html lang="en">
  <head>
    
    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="this is my page">
    
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<title>用户界面</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css" >
	<link href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/site.css" rel="stylesheet" type="text/css">
	
  </head>
  
  <body>

 
  <%-- <fmt:requestEncoding value="UTF-8"/> --%>
 	<%-- <c:if test="${sessionScope.uid=='null'}">
  		<c:redirect url="/User_login.html"></c:redirect>
  	</c:if> --%>
  
		<div class="container">
			<div class="navbar">
				<div class="navbar-inner">
					<div class="container">
						<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a> <a class="brand" href="#">首页</a>
						<div class="nav-collapse">
							<ul class="nav">
								<li class="active">
									<a href="User_index.jsp">功能管理</a>
								</li>
								<li>
									<a href="settings.htm">这个</a>
								</li>
								<li>
									<a href="help.htm">那个</a>
								</li>
								<li class="dropdown">
									<a href="help.htm" class="dropdown-toggle" data-toggle="dropdown">随意 <b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li>
											<a href="help.htm">Introduction Tour</a>
										</li>
										<li>
											<a href="help.htm">Project Organisation</a>
										</li>
										<li>
											<a href="help.htm">Task Assignment</a>
										</li>
										<li>
											<a href="help.htm">Access Permissions</a>
										</li>
										<li class="divider">
										</li>
										<li class="nav-header">
											Files
										</li>
										<li>
											<a href="help.htm">How to upload multiple files</a>
										</li>
										<li>
											<a href="help.htm">Using file version</a>
										</li>
									</ul>
								</li>
							</ul>
							<form class="navbar-search pull-left" action="">
								<input type="text" class="search-query span2" placeholder="Search" />
							</form>
							<ul class="nav pull-right">
								
								<li>
									<a>在线人数：${number}</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/sys/user/user_login.html">退出登录</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="span3">
					<div class="well" style="padding: 8px 0;">
						<ul class="nav nav-list">
							<li class="nav-header">
								模块选择
							</li>
							<li class="active">
								<a href="${pageContext.request.contextPath}/UserServlet?pages=1&choose=index"><i class="icon-user"></i> 用户模块</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/FunctionServlet?pages=1&choose=function_index"><i class="icon-list-alt"></i> 功能模块</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/RoleServlet?pages=1&choose=role_index"><i class="icon-check"></i> 角色模块</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/DeptServlet?pages=1&choose=dept_index"><i class="icon-envelope"></i> 部门模块</a>
							</li>
							<li class="nav-header">
								Your Account
							</li>
							<li>
								<a href="profile.htm"><i class="icon-user"></i> Profile</a>
							</li>
							<li>
								<a href="settings.htm"><i class="icon-cog"></i> Settings</a>
							</li>
							<li class="divider">
							</li>
							<li>
								<a href="help.htm"><i class="icon-info-sign"></i> Help</a>
							</li>
							<li class="nav-header">
								Bonus Templates
							</li>
							<li>
								<a href="gallery.htm"><i class="icon-picture"></i> Gallery</a>
							</li>
							<li>
								<a href="blank.htm"><i class="icon-stop"></i> Blank Slate</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="span9">
					<h2>
						用户展示
					</h2>
					
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>
									用户图片
								</th>
								<th>
									用户昵称
								</th>
								<th>
									真实姓名
								</th>
								<th>
									电话
								</th>
								<th>
									部门id
								</th>
								<th>
									角色id
								</th>
								<th>
									状态
								</th>
								<th>
									创建时间
								</th>
							</tr>
						</thead>
						<tbody>
						<!-- 数据显示 -->
						<c:forEach var="Vo4User" items="${userList}" >		
							<tr>
								<td>
									<%-- <img src="${Vo4User.logo_url}"  alt="图片"   > --%>
								</td>
								<td>
									${Vo4User['user_name']}
								</td>
								<td>
									${Vo4User['true_name']}
								</td>
								<td>
									${Vo4User['user_phone']}
								</td>
								<td>
									${Vo4User['dept_id']}
								</td>
								<td>
									${Vo4User['role_id']}
								</td>
								<td>
									${Vo4User['data_status']}
								</td>
								<td>
									${Vo4User['create_time']}
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					
					<div style="float:right">
								<span>共${count}条数据</span>&nbsp;&nbsp;
								<span>第${currentPages}页</span>&nbsp;&nbsp;
								<span>共${totalPages}页</span>&nbsp;&nbsp;
						<ul class="pager">
							<li class="next">
								<a href="${pageContext.request.contextPath}/UserServlet?pages=${totalPages}&choose=index">最后一页</a>
								
								<a href="${pageContext.request.contextPath}/UserServlet?pages=${currentPages+1}&choose=index">下一页</a>
								<a href="${pageContext.request.contextPath}/UserServlet?pages=${currentPages-1}&choose=index">上一页</a>
								<a href="${pageContext.request.contextPath}/UserServlet?pages=1&choose=index">首页</a>
							</li>
						</ul>
                    </div>
				</div>
			</div>
		</div>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/site.js"></script>
	</body>
</html>
