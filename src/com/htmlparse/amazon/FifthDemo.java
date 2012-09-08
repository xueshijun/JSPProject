package com.htmlparse.amazon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.File.TxtWriter;

public class FifthDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {  
		System.out.println("==================BEGIN");
//		String url="http://www.amazon.cn/s/ref=sr_pg_6?rh=n%3A658390051%2Cn%3A!658391051%2Cn%3A658394051%2Cn%3A2126308051&page=6&ie=UTF8&qid=1346517465";
//		List<String> list=new ArrayList<String>();
//		list.add(url);
//		getItemListByUrl(list);
		
		
		
		List<String> fourthList=new ArrayList<String>();
		InputStreamReader reader=new InputStreamReader( new  FileInputStream("File/fourth.txt"));
		BufferedReader br=new BufferedReader(reader);
		String str="";
		while((str=br.readLine())!=null){
			fourthList.add(str);  
			Map<String,String> fifthList= getItemListByUrl(fourthList); 
			for(int i=0;i<fifthList.size();i++){
				
				TxtWriter.appendToFile("",new File("File/fifth.txt"));
				
				Collection<Entry<String,String>> entrys=fifthList.entrySet();
				Iterator<Entry<String,String>> it=entrys.iterator();
				
				Entry<String,String> strEntry;
				while(it.hasNext()){
					strEntry=it.next(); 
					System.out.println("写入:"+ strEntry.getKey()+"="+strEntry.getValue());
					TxtWriter.appendToFile(strEntry.getKey(),new File("File/fifth.txt"));					
				} 
			}
			fourthList.clear();
		} 
		System.out.println("==================END");
	}  
	/** 
	 * 根据具有分页规则的URL获取商品列表的ID号 
	 * @param list
	 * @return
	 * KEY:String:ID
	 * VALUE:String:Href
	 * @throws IOException 
	 * 
	 */
	public static Map<String,String> getItemListByUrl(List<String> list) throws IOException{
		Map<String,String> map=new HashMap<String,String>();
		for(int i=0;i<list.size();i++){
			
			Document doc=new ADianShang().docGet_URL(list.get(i));  
			 String key, value;
			//http://www.amazon.cn/s/ref=sr_pg_6?rh=n%3A658390051%2Cn%3A!658391051%2Cn%3A658394051%2Cn%3A2126308051&page=6&ie=UTF8&qid=1346517465
			//上半部分的数据
			/**
			 * <div id="result_60" class="result firstRow product" name="B002XQ2I7C">
			 * <div id="result_61" class="result product" name="B008LR8TVG">
			 * <div id="result_62" class="result lastRow product" name="B001F50PM2">
			 */
			//第一行
			Elements links_top_result_firstRow_products=doc.select("body>div#main>#searchTemplate>#rightContainerATF>#rightResultsATF>#center>#atfResults>div.result.firstRow.product");
			for(Element links_top_result_firstRow_product:links_top_result_firstRow_products){
				
				key=links_top_result_firstRow_product.attr("name");
				value=links_top_result_firstRow_product.select("div.data>h3.title>a.title").attr("href");
				map.put(key, value);
				System.out.println(key+"  :   "+value);
				TxtWriter.appendToFile(key+"  :   "+value,new File("File/amazon_Success.txt"));
			}
			Elements links_top_result_products=doc.select("body>div#main>#searchTemplate>#rightContainerATF>#rightResultsATF>#center>#atfResults>div.result.product");
			for(Element links_top_result_product:links_top_result_products){
				key=links_top_result_product.attr("name");
				value=links_top_result_product.select("div.data>h3.title>a.title").attr("href");
				map.put(key, value);
				System.out.println(key+"  :   "+value);
				TxtWriter.appendToFile(key+"  :   "+value,new File("File/amazon_Success.txt"));

			}
			//最后一行
			Elements links_top_result_lastRow_products=doc.select("body>div#main>#searchTemplate>#rightContainerATF>#rightResultsATF>#center>#atfResults>div.result lastRow product");
			for(Element links_top_result_lastRow_product:links_top_result_lastRow_products){
				key=links_top_result_lastRow_product.attr("name");
				value=links_top_result_lastRow_product.select("div.data>h3.title>a.title").attr("href");
				map.put(key, value);
				System.out.println(key+"  :   "+value);
				TxtWriter.appendToFile(key+"  :   "+value,new File("File/amazon_Success.txt"));
			}
			
			//下半部分的数据 
			/**
			 * <div id="result_63" class="result product" name="B008U445BI">
			 * <div id="result_64" class="result product" name="B008U44590">
			 * ............... 
			 * <div id="result_64" class="result lastRow product" name="B0089VFZJ8">
			 */
			//第一行（？？？？？？貌似没有这样的标签出现过,放着备用吧）
			Elements links_bottom_result_firstRow_products=doc.select("body>div#main>#searchTemplate>#rightContainerATF>#rightResultsATF>#centerBelow>#btfResults>.result.firstRow.product");
			for(Element links_bottom_result_firstRow_product : links_bottom_result_firstRow_products){
				key=links_bottom_result_firstRow_product.attr("name");
				value=links_bottom_result_firstRow_product.select("div.data>h3.title>a.title").attr("href");
				map.put(key, value);
				System.out.println(key+"  :   "+value);			
				TxtWriter.appendToFile(key+"  :   "+value,new File("File/amazon_Success.txt"));

			}
			Elements links_bottom_result_products=doc.select("body>div#main>#searchTemplate>#rightContainerATF>#rightResultsATF>#centerBelow>#btfResults>.result.product");
			for(Element links_bottom_result_product : links_bottom_result_products){
				key=links_bottom_result_product.attr("name");
				value=links_bottom_result_product.select("div.data>h3.title>a.title").attr("href");
				map.put(key, value);
				System.out.println(key+"  :   "+value);
				TxtWriter.appendToFile(key+"  :   "+value,new File("File/amazon_Success.txt"));

			}
			Elements links_bottom_result_lastRow_products=doc.select("body>div#main>#searchTemplate>#rightContainerATF>#rightResultsATF>#centerBelow>#btfResults>.result.lastRow.product");
			for(Element links_bottom_result_lastRow_product : links_bottom_result_lastRow_products){
				key=links_bottom_result_lastRow_product.attr("name");
				value=links_bottom_result_lastRow_product.select("div.data>h3.title>a.title").attr("href");
				map.put(key, value);
				System.out.println(key+"  :   "+value);
				TxtWriter.appendToFile(key+"  :   "+value,new File("File/amazon_Success.txt"));

			}
		}
		return map;
	}

}
