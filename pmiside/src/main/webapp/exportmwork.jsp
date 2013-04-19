<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link media="screen" rel="stylesheet" type="text/css" href="css/mworkexport.css" />
<%@ include file="gloabl.jsp" %>
<script type="text/javascript" src="script/export.js"></script>

<title><s:text name="ui.exportmwork.title" /></title>
</head>
<body>
	<div>
		<form class="exportmwork-form">
			<div class="user">
				<span class="user-title"><s:text name="ui.exportmwork.user"/></span>
				<span class="user-icon"><s:text name="ui.exportmwork.pmisuserid"/></span>
				<input id="pmisuserid" type="text" name="message.pmisUserid" value="${msg.pmisUserid}"/>
				<span class="password-icon"><s:text name="ui.exportmwork.pmispassword"/></span>
				<input id="pmispassword" type="password" name="message.pmisPassword" value="${msg.pmisPassword}"/>
				<span class="name-icon"><s:text name="ui.exportmwork.pmisname"/></span>
				<input id="pmisname" type="text" name="message.pmisName" value="${msg.pmisName}"/>
				<span class="supplier-icon"><s:text name="ui.exportmwork.supplier"/></span>
				<input id="supplier" type="text" name="message.supplier" value="${msg.supplier}"/>
			</div>
			<div class="project">
				<span class="project-title"><s:text name="ui.exportmwork.project"/></span>
				<span class="projectname-icon"><s:text name="ui.exportmwork.pmisprojectname"/></span>
				<input id="pmisprojectname" type="text" name="message.pmisProjectName" value="${msg.pmisProjectName}"/>
				<span class="projectrole-icon"><s:text name="ui.exportmwork.pmisprojectrole"/></span>
				<input id="pmisprojectrole" type="text" name="message.pmisProjectRole" value="${msg.pmisProjectRole}"/>
				<span class="place-icon"><s:text name="ui.exportmwork.officeplace"/></span>
				<input id="pmisofficeplace" type="text" name="message.pmisOfficePlace" value="${msg.pmisOfficePlace}"/>
			</div>
			<div class="date">
				<span class="date-title"><s:text name="ui.exportmwork.date"/></span>
				<input id="exportdate" type="text" name="exportDate"/>
				<input type="text" name="message.month" />
				<input type="text" name="message.year" />
			</div>
		</form>
		
		<div id="controller" class="account-control">
			<input type="image" id="export" class="export-button" src="images/start.png" />
			<span class="progressBar" id="pb1">0</span>
		</div>
		
		<div id="exported" class="exporter">
			<input  type="image" id="download" src="images/download.png" />
		</div>
	</div>
	
	<div id="option">
		<span class="save-icon"><s:text name="ui.exportmwork.savemsg"/></span>
		<input class="save-checkbox" type="checkbox" name="needsave" value="true"/>
		
		<span class="subscribe-icon"><s:text name="ui.exportmwork.subscribe"/></span>
		<input class="subscribe-checkbox" type="checkbox" name="message.subscribed" value="true"/>
	</div>
	
	<div>
		<input type="image" id="settingsicon" src="images/settings.png" />
	</div>
	
	<div>
		<input type="image" id="logout" src="images/logoutpre.png" />
	</div>
	
	<div class="backgroundmsg">
		<span id="actionerror"><s:property value="#request.actionerror" /></span>
		<span id="errorpmisuserid"><s:text name="ui.exportmwork.error.pmisuserid" /></span>
		<span id="errorpmispassword"><s:text name="ui.exportmwork.error.password" /></span>
		<span id="errorpmisexportdate"><s:text name="ui.exportmwork.error.exportDate" /></span>
		<span id="fatalerrorconactauthor"><s:text name="ui.exportmwork.contactauthor" /></span>
		<span id="logouttips"><s:text name="ui.exportmwork.logout" /></span>
		<span id="error"><s:text name="ui.exportmwork.error" /></span>
		<span id="exporttips"><s:text name="ui.exportmwork.action" /></span>
		<span id="saved"><s:property value="#request.saved"/></span>
		<span id="subscribed"><s:property value="#request.msg.subscribed"/></span>
	</div>
	
	<%@ include file="copyright.jsp" %>
</body>
</html>
