<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link media="screen" rel="stylesheet" type="text/css" href="css/regist.css" />
<%@ include file="gloabl.jsp" %>
<script type="text/javascript" src="script/regist.js"></script>

<title><s:text name="ui.regist.title" /></title>
</head>
<body>
	<div class="content">
		<form id="registform" action="register" method="post" class="regist-form">
			<div class="title">
				<span class="title-icon"><s:text name="ui.regist.title" /></span>
			</div>
			<div class="username">
				<span class="user-icon"><s:text name="ui.regist.username" /></span> <input id="userid" type="text" name="user.userId" /> 
			</div>
			<div class="password">
				<span class="password-icon"><s:text name="ui.regist.password" /></span> <input id="password" type="password" name="user.password" />
			</div>
			<div class="password">
				<span class="password-icon"><s:text name="ui.regist.confirm" /></span> <input id="passwordconfirm" type="password" name="confirm" />
			</div>
			<div class="account-control">
				<button id="regist" type="button"><s:text name="ui.regist.action" /></button>
				<a href="login.jsp" class="linklogin"><s:text name="ui.regist.registed" /></a>
			</div>
		</form>
		
		<div class="backgroundmsg">
			<span id="actionerror"><s:property value="#request.actionerror" /></span>
			<span id="errorusername"><s:text name="ui.regist.error.username" /></span>
			<span id="errorpassword"><s:text name="ui.regist.error.password" /></span>
			<span id="errorpasswordconfirm"><s:text name="ui.regist.error.confirm" /></span>
			<span id="errorpasswordconfirmdifferent"><s:text name="ui.regist.error.confirmdifferent" /></span>
		</div>
	</div>
	
	<%@ include file="copyright.jsp" %>
</body>
</html>
