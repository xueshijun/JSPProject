package com.htmlparse.threesixzerobuy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.File.TxtWriter;
import com.htmlparse.amazon.ADianShang;
import com.htmlparse.amazon.TagNode;

public class FirstDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("====================BEGIN");
		//GET FIRST CLASS
		String url="http://www.360buy.com/allSort.aspx";
		firstClass_get(url);
		System.out.println("====================END");

	}

	public static void firstClass_get(String mainUrl) throws IOException{
		Document doc=new ADianShang().docGet_URL(mainUrl); 
		ArrayList<TagNode> list=new ArrayList<TagNode>();
		//LEFT SIDE
		Elements links=doc.select("body>#allsort>.fl>.m>.mc>dl>dd>em>a"); 
		for(Element  link:links){
			
			/* http://e.360buy.com/ebook.html
			 * /products/1713-3296-000.html
			 * products/737-794-798.html  
			 * format the url style
			 * */
			String href=link.attr("href");
			if(!href.startsWith("http://")){ //例子： /products/1713-3296-000.html 和例子：products/737-794-798.html 
				
				if(!href.startsWith("/")){//例子：products/737-794-798.html 
					href="http://www.360buy.com/"+href;
				}else {//例子： /products/1713-3296-000.html
					href="http://www.360buy.com"+href;
				}
			} 
			list.add(new TagNode(link.text(), href)); 
		}
		
		//RIGHT SIDE
		links=doc.select("body>#allsort>.fr>.m>.mc>dl>dd>em>a"); 
		for(Element  link:links){
			
			/* http://e.360buy.com/ebook.html
			 * /products/1713-3296-000.html
			 * products/737-794-798.html  
			 * format the url style
			 * */
			String href=link.attr("href");
			if(!href.startsWith("/")){//例子：products/737-794-798.html 
				href="http://www.360buy.com/"+href;
			}else if(!href.startsWith("http://")){ //例子： /products/1713-3296-000.html
				href="http://www.360buy.com"+href;
			}
			list.add(new TagNode(link.text(), href)); 
		}		
		
	
		//saving the TagNode
		for(TagNode tagNode:list){
			System.out.println(tagNode); 
			TxtWriter.appendToFile(tagNode.getHref(),new File("File/360/first.txt"));
		} 
	}
	
	
}
