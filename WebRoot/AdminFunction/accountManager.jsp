<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags" %>

<s:set value="#session.account" var="account"/>
<s:set var="isAdmin" value="#session.isAdmin"/>
<s:if test="%{#account != null && #isAdmin =='1'.toString()}">

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>帳號管控</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width,initial-scale=0.3">
	<link href="Css/inputText.css"></link>
    <link href="Css/jquery-ui-1.10.4.custom.css" rel="stylesheet" type="text/css"/>
    <link href="Css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
    <link href="Css/metro/jtable_metro_base.css" rel="stylesheet" type="text/css" />
     <link rel="shortcut icon" href="Image/favicon.ico" type="image/x-icon">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
     <script type="text/javascript" src="JS/jquery-1.10.2.js" charset="UTF-8"></script>
  	<script type="text/javascript" src="JS/jquery-ui-1.10.4.custom.js"></script>
  	<script type="text/javascript" src="JS/jquery.jtable.js"></script>
  	<script type="text/javascript" src="JS/jquery.jtable.zh-CN.js"></script>
  	<script type="text/javascript" src="JS/accountManager.js"></script>
  
  </head>
  
  <body>
   <h2 align="center">帳號控管</h2>
  <table   width="1%" align="left">
  <tr><td><s:include value="/MEMU/memu.jsp"></s:include></td></tr>
  </table>
  <div align="center" id="PersonTableContainer"  style="width:60%;margin-left:21%;"></div>
  </body>
</html>
</s:if>
<s:else>
<s:action name="index" executeResult="true"></s:action>
</s:else>