package com.lucene.threesixzero;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.tools.mysql.MySql;

 

public class Demo {
	//在指定目录中创建索引
	public final static String INDEX_STORE_PATH="TSZ"+File.separator;
	 
	public static void main(String[] args) throws  ParseException, IOException, SQLException {
		
//		
		search("ItemName","美");
	}
	private static Document getDocument(String ItemNumber,String ItemName,String ItemType,String MarketPrice) throws SQLException{
		Document doc=new Document(); 
			doc.add(
					new Field("ItemNumber",ItemNumber,Field.Store.YES,Field.Index.NOT_ANALYZED));
			doc.add(
					new Field("ItemName",ItemName,Field.Store.YES,Field.Index.ANALYZED));
			doc.add(
					new Field("ItemType",ItemType,Field.Store.YES,Field.Index.ANALYZED));
			doc.add(
					new Field("MarketPrice",MarketPrice,Field.Store.YES,Field.Index.NO)); 
		return doc;
	} 
	
	public static void create() throws IOException, SQLException{
		//创建索引
		//------------------------------------
		//在指定目录创建索引
		long start=System.currentTimeMillis(); 
 		Directory dir=FSDirectory.open(new File(INDEX_STORE_PATH));	/**IOException*/
 		
		IndexWriter writer=new IndexWriter(dir,
				new StandardAnalyzer(Version.LUCENE_30),
				true,IndexWriter.MaxFieldLength.UNLIMITED);
		//添加文档
		
		MySql mysql=new MySql();
		Connection conn=mysql.getConnetction("mystore"); 
		ResultSet rs=mysql.getRes(conn, "select ItemNumber,ItemName,ItemType,MarketPrice from product");
		while(rs.next()){ 
		writer.addDocument(
				getDocument(rs.getString("ItemNumber"),
							rs.getString("ItemName"),
							rs.getString("ItemType"),
							"100.00"));  
		}

		long end=System.currentTimeMillis();
		System.out.println("create index cost "+(end-start)+" ms");
		writer.close();
	}
	
	public static void search(String key,String value ) throws IOException, ParseException{

		//使用索引
		//----------------------------------
		Directory dirS=FSDirectory.open(new File(INDEX_STORE_PATH));
		IndexSearcher searcher=new IndexSearcher(dirS);  
		
//		QueryParser parser=new QueryParser(Version.LUCENE_30,
//				key,//ItemNumber,ItemName,ItemType,MarketPrice
//				new StandardAnalyzer(Version.LUCENE_30));
//		Query query=parser.parse(value);/**ParseException*/
		
		BooleanQuery query=new BooleanQuery();
		
		TermQuery termQuery1=new TermQuery(new Term("ItemName","美的"));
		
		TermQuery termQuery2=new TermQuery(new Term("ItemType",""));
			
		query.add(termQuery1,BooleanClause.Occur.MUST);
		query.add(termQuery2,BooleanClause.Occur.SHOULD);	
   
		
		
		long start2=System.currentTimeMillis();
		TopDocs hits=searcher.search(query,100);
		System.out.println(hits.totalHits);
		long end2=System.currentTimeMillis();
		 
		for(ScoreDoc scoreDoc:hits.scoreDocs){
			Document doc=searcher.doc(scoreDoc.doc);
			System.out.println(doc.get("ItemNumber")+doc.get("ItemName")+doc.get("ItemType")+doc.get("MarketPrice"));
		}
		System.out.println("found "+hits.totalHits+" matches in "+(end2-start2)+"ms");

		searcher.close(); 
	}
}
