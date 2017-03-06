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
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>新增種類</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.min.js" charset="UTF-8"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <meta name="viewport" content="width=device-width,initial-scale=0.3">
     <link href="Css/jquery-ui-1.10.4.custom.css" rel="stylesheet" type="text/css"/>
     <link href="Css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
     <link href="Css/metro/jtable_metro_base.css" rel="stylesheet" type="text/css" />
  
	<link rel="stylesheet" type="text/css" href="Css/inputText.css">
    <script type="text/javascript" src="JS/jquery-1.10.2.js" charset="UTF-8"></script>
  	<script type="text/javascript" src="JS/jquery-ui-1.10.4.custom.js"></script>
  	<script type="text/javascript" src="JS/jquery.jtable.js"></script>
  	<script type="text/javascript" src="JS/jquery.jtable.zh-CN.js"></script>
  	<script type="text/javascript" src="JS/addTypeTable.js"></script>
    <script type="text/javascript" src="JS/addType.js" charset="UTF-8"></script>
  </head>
  
  <body>
    <h2 align="center">物品種類新增</h2>
  <table   width="1%" align="left">
  <tr><td><s:include value="/MEMU/memu.jsp"></s:include></td></tr>
  </table>
  <s:form theme="simple" action="AddTypeAction">
  <table align="center" class="w3-table w3-bordered w3-striped w3-border w3-large w3-center" width="70%" >
  <tbody id="table">
  <tr><td>物品種類:</td><td class="w3-center"><s:textfield id="itemType" name="itemType" cssClass="w3-input w3-border w3-round-xlarge w3-xlarge" ></s:textfield></td><td><s:submit onclick="return false;" value="+" cssClass="w3-btn w3-blue" id="add"/></td><td><s:submit value="-" cssClass="w3-btn w3-blue" id="dis" onclick="return false;"/></td></tr>
  </tbody>
  <tfoot><tr><td></td><td></td><td colspan="2"><s:submit value="新增" cssClass="w3-btn  w3-blue w3-round-xxlarge w3-large"/></td></tr></tfoot>
  </table>
  <s:token name="refresh"></s:token>
  </s:form>
  <s:div cssStyle="margin-left:25%;"><s:property value="#request.error"></s:property></s:div>
  <div align="center" id="PersonTableContainer"  style="width:35%;margin-left:40%;"></div>
   
  </body>
</html>
</s:if>
<s:else>

<s:action name="index" executeResult="true"></s:action>
</s:else>