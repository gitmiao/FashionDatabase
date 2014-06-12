<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<a href="index.jsp">Home</a>
	<br/>
	<br/>	
	Hottest Apparel
	<br>
	<logic:present name="hottestApparel" scope="session">
		<table border=1>
			<tr>
				<td>Type</td>
				<td>Brand</td>
				<td>Name</td>
				<td>Season</td>
				<td>Color</td>
				<td>Number of reviews</td>
			</tr>
			<logic:iterate id="anApparel" name="hottestApparel" scope="session">
				<tr>
					<td><bean:write name="anApparel" property="type" /></td>
					<td><bean:write name="anApparel" property="brand" /></td>
					<td><bean:write name="anApparel" property="name" /></td>
					<td><bean:write name="anApparel" property="season" /></td>
					<td><bean:write name="anApparel" property="color" /></td>
					<td><bean:write name="anApparel" property="numOfRate" /></td>
				</tr>
			</logic:iterate>
		</table>
	</logic:present>
	<br>
	Best rated Apparel
	<br>
	<logic:present name="bestApparel" scope="session">
		<table border=1>
			<tr>
				<td>Type</td>
				<td>Brand</td>
				<td>Name</td>
				<td>Season</td>
				<td>Color</td>
				<td>Average rate</td>
			</tr>
			<logic:iterate id="anApparel" name="bestApparel" scope="session">
				<tr>
					<td><bean:write name="anApparel" property="type" /></td>
					<td><bean:write name="anApparel" property="brand" /></td>
					<td><bean:write name="anApparel" property="name" /></td>
					<td><bean:write name="anApparel" property="season" /></td>
					<td><bean:write name="anApparel" property="color" /></td>
					<td><bean:write name="anApparel" property="avgRate" /></td>
				</tr>
			</logic:iterate>
		</table>
	</logic:present>	
</body>
</html>
