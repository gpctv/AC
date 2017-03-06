<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<s:set value="#session.account" var="account"/>
<s:if test="%{#account != null}">
  <head>
    <base href="<%=basePath%>">
    
    <title>剩餘款項</title>
    <meta name="viewport" content="width=device-width,initial-scale=0.3">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="shortcut icon" href="Image/favicon.ico" type="image/x-icon">
<link href="Css/jquery-ui-1.10.4.custom.css" rel="stylesheet" type="text/css"/>
<link href="Css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
<link href="Css/metro/jtable_metro_base.css" rel="stylesheet" type="text/css" />
  
<script type="text/javascript" src="JS/jquery-1.10.2.js" charset="UTF-8"></script>  	
<script type="text/javascript" src="https://code.highcharts.com/highcharts.js"></script>
<script type="text/javascript" src="https://code.highcharts.com/modules/exporting.js"></script>
 
 <script type="text/javascript" src="JS/profit_Report.js"></script>
  </head>
  
  <body>
      <h2 align="center">剩餘金額狀況</h2>
<table   width="1%" align="left">
  <tr><td><s:include value="/MEMU/memu.jsp"></s:include></td></tr>
  </table>
  <div class="filtering"><s:select list="yearMonth" name="monthSelect" ></s:select><s:submit id="search" theme="simple" key="查詢"></s:submit></div>
  <div id="container" style="width: 60%; height: 400px; margin: 0 auto"></div>
  <table style="width:60%;margin-left:21%;">
  <tr><td>即時剩餘款項:<div id="remain" style="display:inline;"></div>+<div id="sumNum" style="display:inline;"></div>=<div id="result" style="display:inline;"></div>(餘額+當月淨利)</td></tr>
  </table>
  </body>
    </s:if>
  <s:else>
<s:action name="index" executeResult="true"></s:action>
</s:else>
</html>
