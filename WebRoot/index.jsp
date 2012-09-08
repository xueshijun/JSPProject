  
 <%-- page language="java" import="java.util.*" pageEncoding="ISO-8859-1"--%>
<%@ page contentType="text/html; charset=gbk" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.sql.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
  -->

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  
<meta http-equiv="Content-Type" content="text/html;charset=utf8">
    <base href="<%=basePath%>"> 
    <title>Ê×Ò³</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page"> 
	
<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="resources/css/reset.css" type="text/css" media="screen" />
<!-- Main Stylesheet -->
<link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen" />
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="resources/css/invalid.css" type="text/css" media="screen" />
<!--                       Javascripts                       -->
<!-- jQuery -->
<script type="text/javascript" src="resources/scripts/jquery-1.3.2.min.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript" src="resources/scripts/simpla.jquery.configuration.js"></script>
<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="resources/scripts/facebox.js"></script>
<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" src="resources/scripts/jquery.wysiwyg.js"></script>
</head>
<body id="login">
  <div id="login-wrapper" class="png_bg">
  <div id="login-top"> 
  <div id="login-content">
    <form method="post"  action="result.jsp">
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
