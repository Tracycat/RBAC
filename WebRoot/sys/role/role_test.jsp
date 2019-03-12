<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page isELIgnored="false"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>部门更改 </title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		body { background: #ffffff; color: #444;font-size:12px; }
		a { color: #07c; text-decoration: none; border: 0; background-color: transparent; }
		body, div, q, iframe, form, h5 { margin: 0; padding: 0; }
		img, fieldset { border: none 0; }
		body, td, textarea { word-break: break-all; word-wrap: break-word; line-height:1.6; }
		body, input, textarea, select, button { margin: 0; font-size: 12px; font-family: Tahoma, SimSun, sans-serif; }
		div, p, table, th, td { font-size:1em; font-family:inherit; line-height:inherit; }
		h5 { font-size:12px; }
		ol li,ul li{ margin-bottom:0.5em;}
		pre, code { font-family: "Courier New", Courier, monospace; word-wrap:break-word; line-height:1.4; font-size:12px;}
		pre{background:#f6f6f6; border:#eee solid 1px; margin:1em 0.5em; padding:0.5em 1em;}
		#content { padding-left:50px; padding-right:50px; }
		#content h2 { font-size:20px; color:#069; padding-top:8px; margin-bottom:8px; }
		#content h3 { margin:8px 0; font-size:14px; COLOR:#693; }
		#content h4 { margin:8px 0; font-size:16px; COLOR:#690; }
		#content div.item { margin-top:10px; margin-bottom:10px; border:#eee solid 4px; padding:10px; }
		hr { clear:both; margin:7px 0; +margin: 0;
		border:0 none; font-size: 1px; line-height:1px; color: #069; background-color:#069; height: 1px; }
		.infobar { background:#fff9e3; border:1px solid #fadc80; color:#743e04; }
		.buttonStyle{width:64px;height:22px;line-height:22px;color:#369;text-align:center;background:url(img/buticon.gif) no-repeat left top;border:0;font-size:12px;}
		.buttonStyle:hover{background:url(img/buticon.gif) no-repeat left -23px;}
		
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/zDrag.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/zDialog.js" charset="UTF-8"></script>
	<script type="text/javascript" charset="UTF-8">
		function fun2()
		{
			parentDialog.close();
		}
	</script>
  </head>
 <body>
	 <div id="forlogin">
	 <form action="/RBAC/RoleServlet?choose=role_update&user_id=${user_id}"  method="post" >
	      <table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
	        <tr>
	         <c:forEach var="roleViewsListt" items="${roleViewsList}">
		        <c:if test="${roleViewsListt['now_role']!= null}">
	         	 	<td colspan="2" bgcolor="#eeeeee">当前角色：<span id="deptnn">${roleViewsListt['now_role']}</span></td>
	         	</c:if>
		     </c:forEach> 
	        </tr>
	        <tr>
		        <td width="150" align="right">选择要修改的角色：</td>
	         	<td>
	         	<select name="role_id">
	          		<c:forEach var="roleViewsList" items="${roleViewsList}">
  						<option value ="${roleViewsList['role_id']}">${roleViewsList['role_name']}</option>
  					</c:forEach> 
				</select> 
				</td>
	        </tr>
	        <tr>
	          <td colspan="2" align="left" style="padding-left:100px;"><input type="submit" class="buttonStyle" value="确 定" /> <input onClick="fun2()" class="buttonStyle" type="button" value="关闭" /></td>
	        </tr>
	      </table>
	</form>
	</div>
</body>
</html>
