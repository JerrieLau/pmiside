<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link media="screen" rel="stylesheet" type="text/css" href="css/login.css" />
<%@ include file="gloabl.jsp" %>
<script type="text/javascript" src="script/login.js"></script>

<title><s:text name="ui.login.title" /></title>
</head>
<body>
	<div>
		<form id="loginform" action="login" method="post" class="login-form">
			<div class="title">
				<span class="title-icon"><s:text name="ui.login.title" /></span>
			</div>
			<div class="username">
				<span class="user-icon"><s:text name="ui.login.username" /></span><input id="userid" type="text" name="user.userId" /> 
			</div>
			<div class="password">
				<span class="password-icon"><s:text name="ui.login.password" /></span><input id="password" type="password" name="user.password" />
			</div>
			<div class="account-control">
				<button id="login" type="button">
					<s:text name="ui.login.action" />
				</button>
				<a href="regist.jsp" class="linkregist"><s:text name="ui.regist.title" /></a>
			</div>
		</form>
		<div class="backgroundmsg">
			<span id="actionerror"><s:property value="#request.actionerror" /></span>
			<span id="errorusername"><s:text name="ui.login.error.username" /></span>
			<span id="errorpassword"><s:text name="ui.login.error.password" /></span>
		</div>
	</div>
	
	<%@ include file="copyright.jsp" %>
</body>
</html>
