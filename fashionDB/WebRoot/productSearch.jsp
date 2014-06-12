<%@page import="org.apache.struts.util.LabelValueBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>
<head>
<title>JSP for SearchProductForm form</title>
</head>
<body>
	<a href="index.jsp">Home</a>
	<br/>		
	<html:form action="/searchProduct">
	<h2>Search Apparel</h2>
			type : 
			<html:select property="type">
			<html:option value="bag">Bag</html:option>
			<html:option value="shoes">Shoes</html:option>
			<html:option value="clothing">Clothing</html:option>
		</html:select>
		<html:errors property="type" />
		<t/><t/>
		<%
			Vector<LabelValueBean> brandVector = new Vector<LabelValueBean>();
				List<String> brands = (List<String>) session
						.getAttribute("allBrands");
				if (brands != null) {
					for (final String brand : brands) {
						brandVector.add(new LabelValueBean(brand, brand));
					}
				}
				pageContext.setAttribute("brandVector", brandVector);
		%>
			brand : 
			<html:select property="brand">
			<html:option value="ALL"></html:option>
			<html:options collection="brandVector" property="value"
				labelProperty="label" />
		</html:select>
		<html:errors property="brand" />
		<t/><t/>
		<%
			Vector<LabelValueBean> seasonVector = new Vector<LabelValueBean>();
				List<String> seasons = (List<String>) session
						.getAttribute("allSeasons");
				if (seasons != null) {
					for (final String season : seasons) {
						seasonVector.add(new LabelValueBean(season, season));
					}
				}
				pageContext.setAttribute("seasonVector", seasonVector);
		%>
			season : 
			<html:select property="season">
			<html:option value="ALL"></html:option>
			<html:options collection="seasonVector" property="value"
				labelProperty="label" />
		</html:select>
		<html:errors property="season" />
		<br />
			retailFrom : <html:text property="retailFrom" />
		<html:errors property="retailFrom" />
		<t/><t/>
			retailTo : <html:text property="retailTo" />
		<html:errors property="retailTo" />
		<t/><t/>
			Minimum rate : <html:text property="minRate" />
		<html:errors property="minRate" />
		<br />		
		<%
			Vector<LabelValueBean> productVector = new Vector<LabelValueBean>();
				Map<Long, String> allPresses = (Map<Long, String>) session
						.getAttribute("allPresses");
				if (allPresses != null) {
					for (final Entry<Long, String> entry : allPresses
							.entrySet()) {
						productVector.add(new LabelValueBean(
								entry.getValue(), String.valueOf(entry
										.getKey())));
					}
				}
				pageContext.setAttribute("pressVector", productVector);
		%>
			Featured in Press : 
			<html:select property="pressId">
			<html:option value=""></html:option>
			<html:options collection="pressVector" property="value"
				labelProperty="label" />
		</html:select>
		<html:errors property="pressId" />
	    <t/><t/>
		<%
			Vector<LabelValueBean> dateVector = new Vector<LabelValueBean>();
				List<Date> dates = (List<Date>) session
						.getAttribute("allPublishDates");
				if (dates != null) {
					for (final Date date : dates) {
						final String dateString=new SimpleDateFormat("dd-MMM-yyyy")
							.format(date);
						dateVector.add(new LabelValueBean(dateString,dateString));
					}
				}
				pageContext.setAttribute("dateVector", dateVector);
		%>	
			Publish date : 
			<html:select property="publishDate">
			<html:option value=""></html:option>
			<html:options collection="dateVector" property="value"
				labelProperty="label" />
		</html:select>
		<html:errors property="pressId" />	
		<br />
		<html:submit value="Find" />
		<br />
		<html:errors property="searchResult" />
		<br />
		<logic:present name="bag" scope="session">
			<table border=1>
				<tr>
					<td>Type</td>
					<td>Brand Name</td>
					<td>Name</td>
					<td>Season</td>
					<td>Color</td>
					<td>Retail</td>
					<td>Category</td>
					<td>Height</td>
					<td>Width</td>
					<td>Depth</td>
					<td>Average Rate</td>
				</tr>
				<logic:iterate id="aBag" name="bag" scope="session">
					<tr>
						<td>Bag</td>
						<td><bean:write name="aBag" property="brandName" /></td>
						<td><bean:write name="aBag" property="name" /></td>
						<td><bean:write name="aBag" property="season" /></td>
						<td><bean:write name="aBag" property="color" /></td>
						<td><bean:write name="aBag" property="retail" /></td>
						<td><bean:write name="aBag" property="category" /></td>
						<td><bean:write name="aBag" property="height" /></td>
						<td><bean:write name="aBag" property="width" /></td>
						<td><bean:write name="aBag" property="depth" /></td>
						<td><bean:write name="aBag" property="avgRate" /></td>
					</tr>
				</logic:iterate>
			</table>
		</logic:present>
		<logic:present name="shoes" scope="session">
			<table border=1>
				<tr>
					<td>Type</td>
					<td>Brand Name</td>
					<td>Name</td>
					<td>Season</td>
					<td>Color</td>
					<td>Retail</td>
					<td>Category</td>
					<td>Height</td>
					<td>Average Rate</td>					
				</tr>
				<logic:iterate id="oneShoes" name="shoes" scope="session">
					<tr>
						<td>Shoes</td>
						<td><bean:write name="oneShoes" property="brandName" /></td>
						<td><bean:write name="oneShoes" property="name" /></td>
						<td><bean:write name="oneShoes" property="season" /></td>
						<td><bean:write name="oneShoes" property="color" /></td>
						<td><bean:write name="oneShoes" property="retail" /></td>
						<td><bean:write name="oneShoes" property="category" /></td>
						<td><bean:write name="oneShoes" property="height" /></td>
						<td><bean:write name="oneShoes" property="avgRate" /></td>
					</tr>
				</logic:iterate>
			</table>
		</logic:present>
		<logic:present name="clothing" scope="session">
			<table border=1>
				<tr>
					<td>Type</td>
					<td>Brand Name</td>
					<td>Name</td>
					<td>Season</td>
					<td>Color</td>
					<td>Retail</td>
					<td>Category</td>
					<td>Occasion</td>
					<td>Average Rate</td>
				</tr>
				<logic:iterate id="aClothing" name="clothing" scope="session">
					<tr>
						<td>Shoes</td>
						<td><bean:write name="aClothing" property="brandName" /></td>
						<td><bean:write name="aClothing" property="name" /></td>
						<td><bean:write name="aClothing" property="season" /></td>
						<td><bean:write name="aClothing" property="color" /></td>
						<td><bean:write name="aClothing" property="retail" /></td>
						<td><bean:write name="aClothing" property="category" /></td>
						<td><bean:write name="aClothing" property="occasion" /></td>
						<td><bean:write name="aClothing" property="avgRate" /></td>
					</tr>
				</logic:iterate>
			</table>
		</logic:present>
	</html:form>
	
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
	
	<html:form action="/searchAvailability">
		<h2>Search Availability</h2>
		Apparel : 
		<html:select property="productId">
		<html:options collection="productVector" property="value"
				labelProperty="label" />
		</html:select>	
		<html:errors property="productId" />
		<br />
		Size (shoes and clothing): <html:text property="size" />
		<html:errors property="size" />		
		<br />
		<html:submit value="Find" />
		<br />
		<html:errors property="searchAvailResult" />
		<br />
		<logic:present name="availResult" scope="session">
			<table border=1>
				<tr>
					<td>Price</td>
					<td>Discount</td>
					<td>Store Name</td>
					<td>Phone</td>
					<td>Website</td>
					<td>Address</td>
				</tr>
				<logic:iterate id="aResult" name="availResult" scope="session">
					<tr>
						<td><bean:write name="aResult" property="price" /></td>
						<td><bean:write name="aResult" property="discount" /></td>
						<td><bean:write name="aResult" property="storeName" /></td>
						<td><bean:write name="aResult" property="phone" /></td>
						<td><bean:write name="aResult" property="website" /></td>
						<td><bean:write name="aResult" property="address" /></td>
					</tr>
				</logic:iterate>
			</table>
		</logic:present>		
	</html:form>

	<html:form action="/searchOutfit">
		<h2>Search Outfit</h2>
		Apparel : 
		<html:select property="productId">
		<html:option value="ALL"></html:option>		
		<html:options collection="productVector" property="value"
				labelProperty="label" />
		</html:select>	
		<html:errors property="productId" />
		<br />
		<html:submit value="Find" />
		<br />
		<html:errors property="searchOutfitResult" />
		<br />
		<logic:present name="outfitResult" scope="session">
			<table border=1>
				<tr>
					<td>Type</td>
					<td>Brand</td>
					<td>Name</td>
					<td>Season</td>
					<td>Color</td>
					<td>Source</td>
				</tr>
				<logic:iterate id="aOutfit" name="outfitResult" scope="session">
					<tr>
						<td>Bag</td>
						<td><bean:write name="aOutfit" property="bagBrand" /></td>
						<td><bean:write name="aOutfit" property="bagName" /></td>
						<td><bean:write name="aOutfit" property="bagSeason" /></td>
						<td><bean:write name="aOutfit" property="bagColor" /></td>
						<td><bean:write name="aOutfit" property="source" /></td>
					</tr>
					<tr>
						<td>Shoes</td>
						<td><bean:write name="aOutfit" property="shoesBrand" /></td>
						<td><bean:write name="aOutfit" property="shoesName" /></td>
						<td><bean:write name="aOutfit" property="shoesSeason" /></td>
						<td><bean:write name="aOutfit" property="shoesColor" /></td>
						<td><bean:write name="aOutfit" property="source" /></td>
					</tr>
					<tr>
						<td>Clothing</td>
						<td><bean:write name="aOutfit" property="clothingBrand" /></td>
						<td><bean:write name="aOutfit" property="clothingName" /></td>
						<td><bean:write name="aOutfit" property="clothingSeason" /></td>
						<td><bean:write name="aOutfit" property="clothingColor" /></td>
						<td><bean:write name="aOutfit" property="source" /></td>
					</tr>					
					<tr height=10></tr>
				</logic:iterate>
			</table>
		</logic:present>		
	</html:form>	
</body>
</html>

