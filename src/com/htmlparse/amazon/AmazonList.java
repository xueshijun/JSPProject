package com.htmlparse.amazon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AmazonList {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException { 
		System.out.println("=======BEGIN=======");
		
		String url="http://www.amazon.cn/gp/site-directory/ref=topnav_sad"; 

		//---------------
		System.out.println("======1=======");
		List<TagNode> list_First=FirstDemo.firstList_get(url);
		System.out.println(list_First);
		
		//-------------- 
		System.out.println("======2=======");
		List<TagNode>  list_Second=SecondDemo.secondList_get(list_First);
		System.out.println(list_Second);
		
		//---------------
		System.out.println("======3=======");
		List<TagNode> list_Third=ThirdDemo.third_get(list_Second); 
		System.out.println(list_Third);
		
		//---------------
		System.out.println("======4======");
		List<String> list_Fourth=FourthDemo.pageUrl_get(list_Third);
		System.out.println(list_Fourth);
		 
		//---------------------
		System.out.println("======5=======");
		Map<String,String> map=FifthDemo.getItemListByUrl(list_Fourth);
		System.out.println(map.values());		
		
		//------------------------------------
		
		System.out.println("======END=======");
	} 
}
	/*
	 * 
	 * 
	 * 

//		String url="http://www.amazon.cn/gp/site-directory/ref=topnav_sad";
//		List<TagNode> list_First =FirstDemo.firstList_get(url);
//		
//		
////		中文图书href: /%E5%9B%BE%E4%B9%A6/b/ref=sd_allcat_bo/477-4836852-2471734?ie=UTF8&node=658390051
//		List<TagNode> list_Second=new ArrayList<TagNode>();
//		list_Second.add(new TagNode(list_First.get(0).getName(),"http://www.amazon.cn"+list_First.get(0).getHref()));
//
////		[文学]href: /%E6%96%87%E5%AD%A6%E5%9B%BE%E4%B9%A6/b/ref=amb_link_30653652_1/477-5774786-1059911?ie=UTF8&node=658394051&pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=left-3&pf_rd_r=1X4G1BBQDB4YDSQNHMYE&pf_rd_t=101&pf_rd_p=65146492&pf_rd_i=658390051
//		List<TagNode> list_Third=new ArrayList<TagNode>();
//		list_Third.add(
//				new TagNode(
//						list_Third.get(0).getName(),
//						"http://www.amazon.cn"+list_Third.get(0).getHref()));
//		
//		
		//集成上一层和下一层测试
//		<a href=""><b>[文学]</b></a> 
		String str="http://www.amazon.cn/%E6%96%87%E5%AD%A6%E5%9B%BE%E4%B9%A6/b/ref=amb_link_30653652_1/478-2893587-9147252?ie=UTF8&node=658394051&pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=left-3&pf_rd_r=014HAC4P897SN17K18CM&pf_rd_t=101&pf_rd_p=65146492&pf_rd_i=658390051";
		// 小说
//		String str="http://www.amazon.cn/%E5%B0%8F%E8%AF%B4/b/ref=amb_link_30653652_2/478-2893587-9147252?ie=UTF8&node=658393051&pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=left-3&pf_rd_r=014HAC4P897SN17K18CM&pf_rd_t=101&pf_rd_p=65146492&pf_rd_i=658390051";
		
//		
//		List<TagNode> list_Third=new ArrayList<TagNode> ();
//		list_Third.add(new TagNode("小说",str));
//		
//		List<TagNode> thirdList= ThirdDemo.Third_get(list_Third);
//		for(int i=0;i<list_Third.size();i++){ 
//			FifthDemo.getItemListByUrl(
//					FourthDemo.pageUrl_get("http://www.amazon.cn"+
//							list_Third.get(i).getHref()));  
//		}
		
	 * 
	 * */ 