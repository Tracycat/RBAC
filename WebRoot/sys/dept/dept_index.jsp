<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>部门列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/site.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/site.js"></script>
	<script src="${pageContext.request.contextPath}/js/zDialog.js" charset="UTF-8"></script>
	<script src="${pageContext.request.contextPath}/js/zDrag.js" charset="UTF-8"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" charset="UTF-8">
		function open2(a,b){
			var diag = new Dialog();
			diag.Width = 300;
			diag.Height = 120;
			diag.Title = "更改部门";
			diag.URL = "/RBAC/DeptServlet?choose=dept_view&user_id="+a+"&dept_id="+b;
			diag.show();
	}
	</script>
  </head>
  
  <body>
		<div class="container">
			<div class="navbar">
				<div class="navbar-inner">
					<div class="container">
						<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a> <a class="brand" href="#">首页</a>
						<div class="nav-collapse">
							<ul class="nav">
								<li>
									<a href="index.html">功能管理</a>
								</li>
								<li>
									<a href="settings.htm">不要点</a>
								</li>
								<li>
									<a href="help.htm">不要点</a>
								</li>
								<li class="dropdown">
									<a href="help.htm" class="dropdown-toggle" data-toggle="dropdown">下拉 <b class="caret"></b></a>
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
							<li>
								<a href="${pageContext.request.contextPath}/UserServlet?pages=1&choose=index"><i class="icon-user"></i> 用户模块</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/FunctionServlet?pages=1&choose=function_index"><i class="icon-list-alt"></i> 功能模块</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/RoleServlet?pages=1&choose=role_index"><i class="icon-check"></i> 角色模块</a>
							</li>
							<li class="active">
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
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>
									真实姓名
								</th>
								<th>
									所属部门
								</th>
								<th>
									角色职位
								</th>
								<th>
									部门更改
								</th>
							</tr>
						</thead>
						<tbody>
						<!-- 数据显示 -->
						<c:forEach var="Vo4Dept" items="${deptList}" >
							<tr>
								<td>
									${Vo4Dept['true_name']}
								</td>
								<td>
									${Vo4Dept['dept_name']}
								</td>
								<td>
									${Vo4Dept['role_name']}
								</td>
								<td>
									<input type="button" id="b" class="view-link" value="点击更改" onclick="open2('${Vo4Dept['user_id']}','${Vo4Dept['dept_id']}')">
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>				
					<div class="pagination">
						<ul>
							<li >
								<a href="${pageContext.request.contextPath}/DeptServlet?pages=${currentPages-1}&choose=dept_index">&laquo;</a>
							</li>
							<c:forEach var="n" begin="1" end="${totalPages}" step="1">
							<%-- <c:set var="currentPages" scope="request" value="${currentPages}"></c:set>
							<c:choose>
								<c:when test="${currentPages == n} ">
								<li class="disabled">
								</c:when>
								<c:otherwise>
								<li>
								</c:otherwise>
							</c:choose> --%>
							<li>
								<a href="${pageContext.request.contextPath}/DeptServlet?pages=${n}&choose=dept_index">${n}</a>
							</li>
							</c:forEach>
							<li>
								<a href="${pageContext.request.contextPath}/DeptServlet?pages=${currentPages+1}&choose=dept_index">&raquo;</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		
	</body>
</html>
