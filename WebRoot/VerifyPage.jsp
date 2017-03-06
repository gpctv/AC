<%@ page language="java" import="java.util.*" pageEncoding="BIG5"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>VerifyTest</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="https://www.google.com/recaptcha/api.js"></script>
  </head>
  
  <body>
  <s:form theme="simple">
   <div class="g-recaptcha" data-sitekey="6LeTKggUAAAAAPJu4-aythecSlEAgqLQOThpTkdT"></div>
		<br> <s:submit action="VerifyPage" theme="simple" ></s:submit>
  <s:property  value="statues"/>
  </body>
  </s:form>
</html>
