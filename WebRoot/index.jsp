<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="com.htmlparse.threesixzerobuy.mysql.*" pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.sql.Connection" pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="com.tools.mysql.MySql" pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.sql.ResultSet" pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.sql.SQLException" pageEncoding="ISO-8859-1"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <%@ %>
 <% Connection conn = JingDongDB.mysql.getConnetction("mystore");%>
<%MySql mysql = new MySql(); %>
<%	ResultSet rs = mysql
			.getRes( conn,"select top ItemNumber,ItemName,ItemType,JingDongPrice,JingDongPriceUrl,ItemSalesPromotionInfo from product");
%>
<%
	try {
		while (rs.next()) {
			
			%>
			
			"ItemNumber:"<%out.print(rs.getString(1));%>
			"ItemName:"  <%out.print(rs.getString(2));%> 
 			"ItemType:"  <%out.print(rs.getString(3));%>
 			JingDongPrice:" <%out.print(rs.getString(4));%>
 			JingDongPriceUrl:" <%out.print(rs.getString(5)); %> 
<% 
		}    
	} catch (SQLException e) { 
		e.printStackTrace();
	}
%>
  </body>
</html>
