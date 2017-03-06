<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>My JSP 'memu.jsp' starting page</title>
    <link rel="stylesheet" type="text/css" href="Css/memucss.css">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="JS/Logout.js"></script>
  </head>
  
  <body>
 
  <ul>
  <s:set var="isAdmin" value="#session.isAdmin"/>
  
  <s:if test="%{#session.isAdmin=='1'.toString()}">
  <li><s:a href="InCome"><s:text name="income"/></s:a> </li>
  <li><s:a href="SpendTypeAction">科目新增</s:a> </li>
  <li><s:a href="Expense" ><s:text name="detail"/></s:a> </li> 
  <li><s:a href="AccountManager"><s:text name="帳號管控"/></s:a> </li>   
  </s:if>
  <li><a href="yearReport" ><s:text name="yearReport"/></a> </li>
  <li><s:a href="monthReport"><s:text name="monthReport"/></s:a> </li>
  <li><s:a href="ProfitReport"><s:text name="profitReport"></s:text></s:a></li>
  <li><s:a href="Logout" ><s:text name="登出"/></s:a> </li>
  
  </ul>
  </body>
</html>
