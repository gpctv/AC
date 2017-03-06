<%@ page language="java" import="java.util.*" pageEncoding="BIG5"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testpage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="Css/jquery-ui-1.10.4.custom.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="JS/jquery-1.10.2.js" charset="UTF-8"></script> 
  <script type="text/javascript" src="JS/jquery-ui-1.10.4.custom.js"></script>
  <script type="text/javascript" src="JS/test.js"></script>
  
  </head>
  
  <body>
   <select id="Stext">
   
   </select>
  </body>
</html>
