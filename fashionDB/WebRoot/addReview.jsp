<%@page import="org.apache.struts.util.LabelValueBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map.Entry"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
 
<html> 
	<head>
		<title>JSP for AddReviewForm form</title>
	</head>
	<body>
	<a href="index.jsp">Home</a>
	<br/>
	<br/>	
		<html:form action="/addReview">
	<%
			Vector<LabelValueBean> productVector = new Vector<LabelValueBean>();
				Map<Long, String> productDisplayNames = (Map<Long, String>) session
						.getAttribute("allProducts");
				if (productDisplayNames != null) {
					for (final Entry<Long, String> product : productDisplayNames
							.entrySet()) {
						productVector.add(new LabelValueBean(
								product.getValue(), String.valueOf(product
										.getKey())));
					}
				}
				pageContext.setAttribute("productVector", productVector);
	%>		
			User Name : <html:text property="userName"/><html:errors property="userName"/><br/>
			Apparel :
			<html:select property="productId">
			<html:options collection="productVector" property="value"
					labelProperty="label" />
			</html:select>				
			<html:errors property="productId"/><br/>
			rate : 
			<html:select property="rate">
				<html:option value="5">5</html:option>
				<html:option value="4.5">4.5</html:option>
				<html:option value="4">4</html:option>
				<html:option value="3.5">3.5</html:option>
				<html:option value="3">3</html:option>
				<html:option value="2.5">2.5</html:option>
				<html:option value="2">2</html:option>
				<html:option value="1.5">1.5</html:option>
				<html:option value="1">1</html:option>
				<html:option value="0.5">0.5</html:option>
				<html:option value="0">0</html:option>
			</html:select>
			<html:errors property="rate"/><br/>
			<html:submit/>
		<br />
		<html:errors property="addReviewResult" />
		</html:form>
	</body>
</html>

