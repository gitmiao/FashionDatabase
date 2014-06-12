<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
 
<html> 
	<head>
		<title>Welcome</title>
	</head>
	<body>
	<h1>Welcome to the women apparel information site developed by Rui Chen and Xiner Zhou</h1>
	<h2>Please click the button to begin your search</h2>
		<html:form action="/loadSearchProduct">
			<html:submit value="Search Apparel"/>
		</html:form>
		<html:form action="/showBestAndMostRated">
			<html:submit value="See the hottest and best rated apparel"/>
		</html:form>
		<html:form action="/loadAddReview">
			<html:submit value="Review apparel"/>
		</html:form>
	</body>
</html>

