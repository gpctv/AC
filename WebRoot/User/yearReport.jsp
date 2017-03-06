<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:set value="#session.account" var="account"/>
<s:if test="%{#account != null}">
<head>
<meta name="viewport" content="width=device-width,initial-scale=0.3">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="JS/jquery-1.10.2.js" charset="UTF-8"></script>  	
<script type="text/javascript" src="https://code.highcharts.com/highcharts.js"></script>
<script type="text/javascript" src="https://code.highcharts.com/modules/exporting.js"></script>
<script type="text/javascript" src="JS/y_report.js"></script>
<title>年報表</title>
</head>
<body>
<h2 align="center">年費用報表</h2>
<table   width="1%" align="left">
  <tr><td><s:include value="/MEMU/memu.jsp"></s:include></td></tr>
  </table>
  <div class="filtering"><s:select list="years" name="yearSelect" ></s:select><s:submit id="search" theme="simple" key="查詢"></s:submit></div>
  <div id="container" style="width: 60%; height: 400px; margin: 0 auto"></div>
  
</body>
</s:if>
<s:else>
<s:action name="index" executeResult="true"></s:action>
</s:else>
</html>