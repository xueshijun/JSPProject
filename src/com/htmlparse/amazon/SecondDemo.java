package com.htmlparse.amazon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.File.TxtWriter;

public class SecondDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

//		//中文图书href: /%E5%9B%BE%E4%B9%A6/b/ref=sd_allcat_bo/477-5506659-6005908?ie=UTF8&node=658390051 
//		String str="http://www.amazon.cn/%E5%9B%BE%E4%B9%A6/b?ie=UTF8&node=658390051";
//		List<TagNode> list=new ArrayList<TagNode>();
//		list.add(new TagNode("中文图书",str)); 	 
//		System.out.println(secondList_get(list));
		
		//从文档中获取一级目录链接地址
		List<TagNode> firstList=new ArrayList<TagNode>();
		InputStreamReader reader=new InputStreamReader( new  FileInputStream("File/first.txt"));
		BufferedReader br=new BufferedReader(reader);
		String str="";
		while((str=br.readLine())!=null){ 
			System.out.println(str); //
			firstList.add(new TagNode("",str)); 
			List<TagNode> secondList=secondList_get(firstList);
			for(int i=0;i<secondList.size();i++){ 
				System.out.println("写入:"+secondList.get(i).getHref());
				TxtWriter.appendToFile(secondList.get(i).getHref(),new File("File/second.txt"));
			}
			firstList.clear();
		}
		System.out.println("=================end=================");
		 
	}
	public static List<TagNode> secondList_get(List<TagNode> firstList) throws IOException{
		List<TagNode> secondList=new ArrayList<TagNode>();
		for(int i=0;i<firstList.size();i++){
			Document doc=new ADianShang().docGet_URL("http://www.amazon.cn"+firstList.get(i).getHref());
			System.out.println("#11111111#" +firstList.get(i));
//			TxtWriter.appendToFile("#11111111#" +list_Url.get(i),new File("File/amazonItems.txt"));
			Elements links;
	//		Elements links=doc.select("body>div#page-wrap>div#content>div#leftcol>div#leftcolatf>div.left_nav>ul>li>a:has(b)");
			/**
			 * 尽可能的降低异常
			 * */
			try{
				links=doc.select("body>div#page-wrap>div#content>div#leftcol>div#leftcolatf>div.left_nav").first().select("ul>li>a");
			}catch(Exception ex1){
				try{
				//http://www.amazon.cn/%E4%BD%93%E8%82%B2%E7%94%A8%E5%93%81/b/ref=sd_allcat_se/480-9052499-2864259?ie=UTF8&node=42783071
				links=doc.select("body>table>tbody>tr>td>table>tbody>tr>td.amabot_left>div.left_nav").first().select("ul>li>a");
				}catch(Exception ex2){
					//http://www.amazon.cn/%E6%9C%8D%E8%A3%85%E6%9C%8D%E9%A5%B0/b/ref=sd_allcat_clo/478-7275490-8600931?ie=UTF8&node=2016156051
//					links=doc.select("body>#page-wrap>#content>#leftcol>#leftcolatf>.left_nav").first().select("ul>li>a");
					//还是异常?这个店不一般
					TxtWriter.appendToFile("############异常############"+firstList.get(i).getName()+"http://www.amazon.cn"+firstList.get(i).getHref(),new File("File/firstFaild.txt"));
					System.out.println("############异常############"+firstList.get(i).getName()+"http://www.amazon.cn"+firstList.get(i).getHref());
					continue;
				}
			}
			if(links!=null){
				for(Element link:links){
					if(link==null){break;}
					secondList.add(new TagNode(link.text(),link.attr("href").replace("&amp;","&")));
					System.out.println(new TagNode("#2222222222222222#" +link.text(),link.attr("href").replace("&amp;","&")));
				}	
			}else{
				TxtWriter.appendToFile(firstList.get(i).getName()+"http://www.amazon.cn"+firstList.get(i).getHref(),new File("File/firstFaild.txt"));
				System.out.println("############异常############"+firstList.get(i).getName()+"http://www.amazon.cn"+firstList.get(i).getHref()); 
				continue;
			} 
		}
		return secondList;
	} 
}
