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

public class ThirdDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
//		List<TagNode> list=new ArrayList<TagNode> ();
//		
////		<li><a href=""><b>[文学]</b></a></li> 
////		/%E6%96%87%E5%AD%A6%E5%9B%BE%E4%B9%A6/b/ref=amb_link_30653652_1?ie=UTF8&amp;node=658394051&amp;pf_rd_m=A1AJ19PSB66TGU&amp;pf_rd_s=left-3&amp;pf_rd_r=0MR5NCRWFV1E4HTS8S8C&amp;pf_rd_t=101&amp;pf_rd_p=65146492&amp;pf_rd_i=658390051
//		String str="/%E6%96%87%E5%AD%A6%E5%9B%BE%E4%B9%A6/b/ref=amb_link_30653652_1?ie=UTF8&amp;node=658394051&amp;pf_rd_m=A1AJ19PSB66TGU&amp;pf_rd_s=left-3&amp;pf_rd_r=0MR5NCRWFV1E4HTS8S8C&amp;pf_rd_t=101&amp;pf_rd_p=65146492&amp;pf_rd_i=658390051".replace("&amp;", "&");
//		list.add(new TagNode("文学",str));
//		
////		<li><a href="">小说</a></li>
////		/%E5%B0%8F%E8%AF%B4/b/ref=amb_link_30653652_2?ie=UTF8&amp;node=658393051&amp;pf_rd_m=A1AJ19PSB66TGU&amp;pf_rd_s=left-3&amp;pf_rd_r=0MR5NCRWFV1E4HTS8S8C&amp;pf_rd_t=101&amp;pf_rd_p=65146492&amp;pf_rd_i=658390051
//		str="/%E5%B0%8F%E8%AF%B4/b/ref=amb_link_30653652_2?ie=UTF8&amp;node=658393051&amp;pf_rd_m=A1AJ19PSB66TGU&amp;pf_rd_s=left-3&amp;pf_rd_r=0MR5NCRWFV1E4HTS8S8C&amp;pf_rd_t=101&amp;pf_rd_p=65146492&amp;pf_rd_i=658390051".replace("&amp;", "&");
//		list.add(new TagNode("小说",str));
//		
////		<li><a href=""><b>浏览所有图书分类</b></a></li>
////		 /gp/feature.html/ref=amb_link_30653652_58?ie=UTF8&amp;docId=42108&amp;pf_rd_m=A1AJ19PSB66TGU&amp;pf_rd_s=left-3&amp;pf_rd_r=0MR5NCRWFV1E4HTS8S8C&amp;pf_rd_t=101&amp;pf_rd_p=65146492&amp;pf_rd_i=658390051
//		str="/gp/feature.html/ref=amb_link_30653652_58?ie=UTF8&amp;docId=42108&amp;pf_rd_m=A1AJ19PSB66TGU&amp;pf_rd_s=left-3&amp;pf_rd_r=0MR5NCRWFV1E4HTS8S8C&amp;pf_rd_t=101&amp;pf_rd_p=65146492&amp;pf_rd_i=658390051".replace("&amp;", "&");
//		list.add(new TagNode("浏览所有图书分类",str));
//		System.out.println(third_get(list)); 
		
		
		
		List<TagNode> secondList=new ArrayList<TagNode>();
		InputStreamReader reader=new InputStreamReader( new  FileInputStream("File/second.txt"));
		BufferedReader br=new BufferedReader(reader);
		String str="";
		while((str=br.readLine())!=null){
			secondList.add(new TagNode("",str));  
			List<TagNode> thirdList= third_get(secondList);
			for(int i=0;i<thirdList.size();i++){   
				System.out.println("写入:"+thirdList.get(i).getHref());
				TxtWriter.appendToFile(thirdList.get(i).getHref(),new File("File/third.txt"));
			}
			secondList.clear();
		}
		System.out.println("=================end=================");
	}
	public static List<TagNode>  third_get(List<TagNode> list) throws IOException{
		List<TagNode> thirdList=new ArrayList<TagNode>();
		for(int i=0;i<list.size();i++){
			try{
				Document doc=new ADianShang().docGet_URL("http://www.amazon.cn"+list.get(i).getHref().replace("&amp;", "&"));
			//  Elements links=doc.select("body>div#page-wrap>div#content>div#leftcol>div#leftcolatf>div.left_nav>ul>li>a:has(b)");
				Elements links=doc.select("body>div#page-wrap>div#content>div#leftcol>div#leftcolatf>div.left_nav>ul>li>a");
				for(Element link:links){
					thirdList.add(new TagNode(link.text(),link.attr("href").replace("&amp;", "&")));
					System.out.println(link.text()+"--"+link.attr("href").replace("&amp;", "&"));
				}
			}catch(Exception ex){
				TxtWriter.appendToFile(
						"#3333333333333#"+list.get(i).getName()+"http://www.amazon.cn"+list.get(i).getHref().replace("&amp;", "&"),
						new File("File/secondFaild.txt"));
				System.out.println("#异常#"+list.get(i).getName()+"http://www.amazon.cn"+list.get(i).getHref().replace("&amp;", "&"));
				continue;
			}
	 		
		}
		return thirdList;
	}

}
