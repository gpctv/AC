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
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	  <link rel="stylesheet" type="text/css" href="Css/inputText.css">
  <link rel="shortcut icon" href="Image/favicon.ico" type="image/x-icon">
  <script src="JS/Logout.js"></script>
  <script src="https://www.google.com/recaptcha/api.js"></script>
  
  <body>
  <s:div cssClass="w3-display-topmiddle w3-container w3-blue">
   <h1><s:text name="AC System"></s:text></h1>
    </s:div> 
  <s:form action="Login" theme="simple"   >
  	<table class="w3-display-middle w3-border w3-round-xlarge" width="40%" height="50%">
  	<tr><td class="w3-center"><s:text name="帳號:"/></td></tr>
  	<tr><td class="w3-center"><s:textfield id="account" name="account" cssClass="w3-input w3-border w3-round-xlarge w3-xlarge" ></s:textfield></td></tr>
  	<tr><td class="w3-center"><s:text name="密碼:"/></td></tr>
  	<tr><td class="w3-center"><s:property value="密碼:"/><s:password id="password" name="password"  cssClass="w3-input w3-border w3-round-xlarge w3-xlarge"></s:password></td></tr>
     <tr><td class="w3-center"><s:submit value="Login" cssClass="w3-btn  w3-blue w3-round-xxlarge w3-xlarge "></s:submit></td></tr>
     <!-- <tr><td class="w3-center"><div style="margin-left:21%;width:30%;" class="g-recaptcha" data-sitekey="6LeTKggUAAAAAPJu4-aythecSlEAgqLQOThpTkdT"></div></td></tr> -->
   	<tr><td class="w3-center"><s:property value="#request.error"></s:property></td></tr>
 	</table>
   </s:form>
    
  </body>
</html>
