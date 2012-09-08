<%--@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"--%>
<%@ page contentType="text/html; charset=gbk" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.sql.SQLException" %>

<%@ page import="org.apache.lucene.document.Document" %>
<%@ page import="org.apache.lucene.document.Field" %>
<%@ page import="org.apache.lucene.index.IndexWriter" %>
<%@ page import="org.apache.lucene.queryParser.QueryParser" %>
<%@ page import="org.apache.lucene.index.Term" %>
<%@ page import="org.apache.lucene.queryParser.ParseException" %>
 
<%@ page import="org.apache.lucene.search.IndexSearcher" %>
<%@ page import="org.apache.lucene.search.Query" %>
<%@ page import="org.apache.lucene.search.ScoreDoc" %>  
<%@ page import="org.apache.lucene.search.TopDocs" %>
<%@ page import="org.apache.lucene.store.Directory" %>
<%@ page import="org.apache.lucene.store.FSDirectory" %>
<%@ page import="org.apache.lucene.util.Version" %>
<%@ page import="org.apache.lucene.analysis.WhitespaceAnalyzer" %> 
<%@ page import="org.apache.lucene.analysis.standard.StandardAnalyzer" %>
<%@ page import="org.apache.lucene.search.BooleanQuery" %>
<%@ page import="org.apache.lucene.search.BooleanClause" %>   




<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <base href="<%=basePath%>">
    
    <title>My JSP 'SearchResults.jsp' starting page</title>
    
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
<!-- jQuery Datepicker Plugin -->
<script type="text/javascript" src="resources/scripts/jquery.datePicker.js"></script>
<script type="text/javascript" src="resources/scripts/jquery.date.js"></script>
</head>

<jsp:useBean id="connection" scope="request" class="com.tools.mysql.JDBCconnection"/>

<%request.setCharacterEncoding("gbk");%>  

<body> 
  <div id="main-content">
    <!-- Main Content Section with everything -->
    <noscript>
    <!-- Show a notification if the user has disabled javascript -->
    <div class="notification error png_bg">
      <div> Java script is disabled or is not supported by your browser. Please 
	  <a href="http://browsehappy.com/" title="Upgrade to a better browser">
	  upgrade</a> your browser or 
	  <a href="http://www.google.com/support/bin/answer.py?answer=23852" title="Enable Javascript in your browser">
	  enable</a> Java script to navigate the interface properly.
        Download From <a href="http://www.exet.tk">exet.tk</a></div>
    </div>
    </noscript>
    <!-- Page Head -->
    <h2>Welcome!</h2>
    <p id="page-intro"> </p>
    
    <div class="clear"></div>
    <!-- End .clear -->
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
		<center>    
			<h3>About 231312 results</h3>	
		</center>
   
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content">
        <div class="tab-content default-tab" id="tab1">  

	
	 <p>筛选条件：<%=request.getParameter("select")%></p> 
     <p>搜索内容： <%=request.getParameter("key")%> </p>
     <p>搜索结果如下表</p>

          <table>
            <thead>
              <tr>  
                <th>ItemNumber</th>
				<th>ItemName</th>
				<th>ItemType</th>
				<th>MarketPrice</th>
				<th>JingDongPrice</th>
                <th>JingDongPriceUrl</th>
                <th>PageUrl</th> 
              </tr>
            </thead>
           	<tbody>
            <%      
            if(request.getParameter("key")!=null){
		        //使用索引
				//----------------------------------
				Directory dir=FSDirectory.open(new File(com.lucene.threesixzero.Demo.INDEX_STORE_PATH));
				IndexSearcher searcher=new IndexSearcher(dir); 
				
				BooleanQuery boolQuery=new BooleanQuery();
				
				QueryParser parser1=new QueryParser(Version.LUCENE_30,
					"ItemName",//ItemNumber,ItemName,ItemType,MarketPrice
						new StandardAnalyzer(Version.LUCENE_30)); 
				Query query1=parser1.parse(request.getParameter("key"));/*ParseException*/
					 
				QueryParser parser2=new QueryParser(Version.LUCENE_30,
						"ItemType",//ItemNumber,ItemName,ItemType,MarketPrice
						new StandardAnalyzer(Version.LUCENE_30)); 
				Query query2=parser2.parse(request.getParameter("key"));/*ParseException*/	
					
				boolQuery.add(query1,BooleanClause.Occur.SHOULD);
				boolQuery.add(query2,BooleanClause.Occur.SHOULD);	 
				
		 		long start=System.currentTimeMillis();
				TopDocs hits=searcher.search(boolQuery,100);
				 
				long end=System.currentTimeMillis();
				 
				for(ScoreDoc scoreDoc:hits.scoreDocs){
					Document doc=searcher.doc(scoreDoc.doc);
					System.out.println(doc.get("ItemNumber")+doc.get("ItemName")+doc.get("ItemType")+doc.get("MarketPrice"));
					
					String sql="select ItemNumber,ItemName,ItemType,MarketPrice,JingDongPrice,JingDongPriceUrl,PageUrl from product where ItemNumber = '"+doc.get("ItemNumber")+"'";
					try{
						ResultSet rs=connection.executeQuery(sql);
						while(rs.next()){%>
		            	<tr>
			                <td><a href="#" title="title"><%=rs.getString("ItemNumber")%></a></td>
			                <td><%=rs.getString("ItemName")%></td>
			                <td><%=rs.getString("ItemType")%></td> 
			                <td><%=rs.getString("MarketPrice")%></td> 
			             	<td><%=rs.getString("JingDongPrice")%></td>
			     			<td><%=rs.getString("JingDongPriceUrl")%></td>
			     			<td><%=rs.getString("PageUrl")%></td>       
		             	</tr>  
		              <%
		              }
		            }
		           catch (Exception ex) {
		           %>
		                <tr> 
		        			<td>1Exception!</td>
							<td>Exception!</td>
							<td>Exception!</td>
							<td>Exception!</td>
							<td>Exception!</td>
							<td>Exception!</td>
						</tr> 
		        <%}
		        }
		        System.out.println("found "+hits.totalHits+" matches in "+(end-start)+"ms");
		        searcher.close();
		        }   
		        %>
		  
    
            </tbody> 
			<tfoot>
              <tr>
                <td colspan="6">
                  <div class="bulk-actions align-left"> 
                  
				  </div>
                  <div class="pagination"> 
					<a href="#" title="First Page">&laquo; First</a>
					<a href="#" title="Previous Page">&laquo; Previous</a> 
					<a href="#" class="number" title="1">1</a> <a href="#" class="number" title="2">2</a> 
					<a href="#" class="number current" title="3">3</a> <a href="#" class="number" title="4">4</a> 
					<a href="#" title="Next Page">Next &raquo;</a><a href="#" title="Last Page">Last &raquo;</a> 
					<span></span><span></span><span></span>
				  </div>
                  <!-- End .pagination -->
                  <div class="clear"></div>
                </td>
              </tr>
            </tfoot>         
		 </table>
        </div>
      
      </div>
      <!-- End .content-box-content -->
    </div>
    <!-- End .content-box -->
	<div class="clear"></div>
	
<!-- Start Notifications -->
    <div class="notification attention png_bg"> <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
      <div> Attention notification. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vulputate, sapien quis fermentum luctus, libero. </div>
    </div>
    <div class="notification information png_bg"> <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
      <div> Information notification. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vulputate, sapien quis fermentum luctus, libero. </div>
    </div>
    <div class="notification success png_bg"> <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
      <div> Success notification. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vulputate, sapien quis fermentum luctus, libero. </div>
    </div>
    <div class="notification error png_bg"> <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
      <div> Error notification. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vulputate, sapien quis fermentum luctus, libero. </div>
    </div> 
<!-- End Notifications -->
  		
  		
    <div id="footer"> <small>
      <!-- Remove this notice or replace it with whatever you want -->
      &#169; Copyright 2012- ALRS | <a href="#">Top</a> </small> </div>
    <!-- End #footer -->
  </div>  
</body>
<!-- Download From www.exet.tk-->
</html>
