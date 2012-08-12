<%-- page language="java" import="java.util.*" pageEncoding="ISO-8859-1"--%>
<%@ page contentType="text/html; charset=gbk" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.sql.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
<meta http-equiv="Content-Type" content="text/html;charset=utf8">
    <base href="<%=basePath%>"> 
    <title>Ê×Ò³</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page"> 
	 
</head> 

<body id="login">
  <div id="login-wrapper" class="png_bg">
  <div id="login-top"> 
  <div id="login-content">
    <form method="post"  action="SearchResults.jsp">
      <div class="notification information png_bg">
        <div> Just click "Search". </div>
      </div>
      <p>
        <label> 
	        <select name="select"> 
	         	<option value="ItemNumber">ItemNumber</option>
	            <option value="ItemName">ItemName</option> 
	        </select> 
        </label>

        <input class="text-input" type="text" name="key" /> 
        <input class="button" type="submit" value="Search" />
      </p><div class="clear"></div>   
    </form>
  </div>
  	<!-- End #login-content -->
	</div>
<!-- End #login-wrapper -->
	</div> 
  </body>
</html>
