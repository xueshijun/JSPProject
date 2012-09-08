package com.htmlparse.amazon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.File.TxtWriter;

public class FirstDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		http://www.amazon.cn/gp/site-directory/ref=topnav_sad
		String url="http://www.amazon.cn/gp/site-directory/ref=topnav_sad";
		
		System.out.println(firstList_get(url));
		List<TagNode> firstLists=firstList_get(url);
		for( TagNode  firstList:firstLists){ 
			TxtWriter.appendToFile(firstList.getHref(),new File("File/first.txt"));
			System.out.println(firstList.getName()+"================"+firstList.getHref());
		}
		System.out.println("==============");
	}
	public static List<TagNode> firstList_get(String mainUrl) throws IOException{
		Document doc=new ADianShang().docGet_URL(mainUrl); 
		ArrayList<TagNode> list=new ArrayList<TagNode>();
		Elements links=doc.select("body>div#siteDirectory>table.popoverContents>tbody>tr>td>div.popover-grouping>div>a"); 
		for(Element link:links){ 
			list.add(new TagNode(link.text(),link.attr("href")));
			System.out.println(new TagNode(link.text(),link.attr("href")));
//			TxtWriter.appendToFile(link.text()+link.attr("href"),new File("File/amazonItems.txt"));

		}
		return list;
	} 
	
}
