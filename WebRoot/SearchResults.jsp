<%--@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"--%>
<%@ page contentType="text/html; charset=gbk" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.sql.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'SearchResults.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page"> 

  </head>
  
<jsp:useBean id="connection" scope="request" class="com.tools.mysql.JDBCconnection"/>

<%request.setCharacterEncoding("gbk");%>  
  <body>
     <p>筛选条件：<%=request.getParameter("select")%></p> 
     <p>搜索内容： <%=request.getParameter("key")%> </p>
     <p>搜索结果如下表</p>
          <table width="537" height="57" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#6894d5">
              <tr bgcolor="#B5E1FA">
                <td scope="col">
                	<div align="center">
                		<span class="style2">ItemNumber</span>
                	</div></td>
                <td scope="col"><div align="center"><span class="style2">ItemName</span></div></td> 
                <td scope="col"><div align="center"><span class="style2">ItemType</span></div></td> 
                <td scope="col"><div align="center"><span class="style2">MarketPrice</span></div></td>
                <td scope="col"><div align="center"><span class="style2">JingDongPrice</span></div></td>
                <td scope="col"><div align="center"><span class="style2">JingDongPriceUrl</span></div></td>
           		<td scope="col"><div align="center"><span class="style2">PageUrl</span></div></td>
              </tr>
              
              
   <%
	   if(request.getParameter("key")!=null&&request.getParameter("select")!=null){
           String sql="select ItemNumber,ItemName,ItemType,MarketPrice,JingDongPrice,JingDongPriceUrl,PageUrl from product where "+request.getParameter("select")+" like '%"+request.getParameter("key")+"%'";
           try{
              ResultSet rs=connection.executeQuery(sql);
              while(rs.next()){
	%>
            	<tr>
	                <td><div align="center" class="style2"><%=rs.getString("ItemNumber")%></div></td>
	                <td><div align="center" class="style2"><%=rs.getString("ItemName")%></div></td>
	                <td><div align="center" class="style2"><%=rs.getString("ItemType")%></div></td> 
	                <td><div align="center" class="style2"><%=rs.getString("MarketPrice")%></div></td> 
	             	<td><div align="center" class="style2"><%=rs.getString("JingDongPrice")%></div></td>
	     			<td><div align="center" class="style2"><%=rs.getString("JingDongPriceUrl")%></div></td>
	     			<td><div align="center" class="style2"><%=rs.getString("PageUrl")%></div></td>        
             	</tr>  
              <%}
           }catch (Exception ex) { %>
        		<tr> 
        			<td>1Exception!</td>
					<td>Exception!</td>
					<td>Exception!</td>
					<td>Exception!</td>
					<td>Exception!</td>
					<td>Exception!</td>
				</tr> 
          <%}
        }else{%>
        <%
              String sql="select ItemNumber,ItemName,ItemType,MarketPrice,JingDongPrice,JingDongPriceUrl,PageUrl from product";
              try{
                 ResultSet rs=connection.executeQuery(sql);
                 while(rs.next()){
        %>  
            	<tr>
	                <td><div align="center" class="style2"><%=rs.getString("ItemNumber")%></div></td>
	                <td><div align="center" class="style2"><%=rs.getString("ItemName")%></div></td>
	                <td><div align="center" class="style2"><%=rs.getString("ItemType")%></div></td> 
	                <td><div align="center" class="style2"><%=rs.getString("MarketPrice")%></div></td> 
	             	<td><div align="center" class="style2"><%=rs.getString("JingDongPrice")%></div></td>
	     			<td><div align="center" class="style2"><%=rs.getString("JingDongPriceUrl")%></div></td>
	     			<td><div align="center" class="style2"><%=rs.getString("PageUrl")%></div></td>        
             	</tr>  
           <%}}catch (Exception ex) {
        	   %>
        		<tr> 
        			<td>2Exception!</td>
					<td>Exception!</td>
					<td>Exception!</td>
					<td>Exception!</td>
					<td>Exception!</td>
					<td>Exception!</td>
				</tr> 
         	<%}} %> 
            </table>            
  </body>
</html>
