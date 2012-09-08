package com.htmlparse.amazon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.File.TxtWriter;

public class FourthDemo {
	/*在火狐中看到的亚马逊标签规则如下,实际捕获不到数据,
	 * 这种适合具有分页规则的页面
	<a href="/s/ref=sr_pg_2?rh=n%3A658390051%2Cn%3A%21658391051%2Cn%3A658394051%2Cn%3A2126308051&amp;page=2&amp;bbn=2126308051&amp;ie=UTF8&amp;qid=1346418652">2</a>
	Elements basicUrl=doc.select("body>#main>#searchTemplate>#rightContainerATF>#rightResultsATF>#centerBelow>#bottomBar>#pagn");
	Elements basicUrl=doc.select("body>#main>.searchTemplate.listLayout.so_cn_zh>#rightContainerATF>#rightResultsATF>#centerBelow>.srSprite.spr_header.hdr>.pagn");
 */
/*根据上一级传过来的页面抓取底部分页,
 * 但是根据底部页面抓出来的具有分页规律的页面再次进行抓取底部分页却行不通？
 * 是jsoup规则不对？
 * 
 * 
【影视文学】href: /b/ref=amb_link_29041852_1/478-2893587-9147252?ie=UTF8&node=2126308051&pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=left-1&pf_rd_r=105PFWG24HSVNS1FRB9W&pf_rd_t=101&pf_rd_p=64839072&pf_rd_i=658394051
	String url="http://www.amazon.cn/b/ref=amb_link_29041852_1/478-2893587-9147252?ie=UTF8&node=2126308051&pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=left-1&pf_rd_r=09Q25B5MN4ZA89KD85C2&pf_rd_t=101&pf_rd_p=64839072&pf_rd_i=658394051";
   电影文学      href: /b/ref=amb_link_29041852_2/478-2893587-9147252?ie=UTF8&node=2126309051&pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=left-1&pf_rd_r=105PFWG24HSVNS1FRB9W&pf_rd_t=101&pf_rd_p=64839072&pf_rd_i=658394051
	String url="http://www.amazon.cn/b/ref=amb_link_29041852_2/478-2893587-9147252?ie=UTF8&node=2126309051&pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=left-1&pf_rd_r=105PFWG24HSVNS1FRB9W&pf_rd_t=101&pf_rd_p=64839072&pf_rd_i=658394051";
最后一页(根据具有分页规则的页面进行,失败)
	String url="http://www.amazon.cn/s/ref=sr_pg_42?rh=n%3A658390051%2Cn%3A!658391051%2Cn%3A658394051%2Cn%3A2126308051&page=42&ie=UTF8&qid=1346428102";
	
	
	Document doc=new ADianShang().docGet_URL(url); 
	Elements pageUrls=doc.getElementsByClass("pagnTable");
 	System.out.println(pageUrls.select("table>tbody>tr>td>span"));   
 * 
 * 
 * 
 * */
	public static void main(String[] args) throws IOException {
//		 【影视文学】href: /b/ref=amb_link_29041852_1/478-2893587-9147252?ie=UTF8&node=2126308051&pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=left-1&pf_rd_r=105PFWG24HSVNS1FRB9W&pf_rd_t=101&pf_rd_p=64839072&pf_rd_i=658394051
//		String url="http://www.amazon.cn/b/ref=amb_link_29041852_1/478-2893587-9147252?ie=UTF8&node=2126308051&pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=left-1&pf_rd_r=09Q25B5MN4ZA89KD85C2&pf_rd_t=101&pf_rd_p=64839072&pf_rd_i=658394051";
//		, 电影文学      href: /b/ref=amb_link_29041852_2/478-2893587-9147252?ie=UTF8&node=2126309051&pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=left-1&pf_rd_r=105PFWG24HSVNS1FRB9W&pf_rd_t=101&pf_rd_p=64839072&pf_rd_i=658394051
//		String url="http://www.amazon.cn/b/ref=amb_link_29041852_2/478-2893587-9147252?ie=UTF8&node=2126309051&pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=left-1&pf_rd_r=105PFWG24HSVNS1FRB9W&pf_rd_t=101&pf_rd_p=64839072&pf_rd_i=658394051";

		System.out.println("=================BEGIN=================");
		
//		//----------------------------------------
//		//测试是否为空 
//		//最后一页43		 http://www.amazon.cn/s/ref=sr_pg_43?rh=n%3A658390051%2Cn%3A!658391051%2Cn%3A658394051%2Cn%3A2126308051&page=43&ie=UTF8&qid=1346511554
//		String url_Last="http://www.amazon.cn/s/ref=sr_pg_43?rh=n%3A658390051%2Cn%3A!658391051%2Cn%3A658394051%2Cn%3A2126308051&page=43&ie=UTF8&qid=1346511554";
//	 	System.out.println("测试继续(PS:最后一页就不继续了):"+isContinue(url_Last)); 
//	 
//		//----------------------------------------
//	 	//根据上层页面条转过来的基础页面(没有分页规则)获取具有分页规则的所有页面
//	 	String basicUrl="/b/ref=amb_link_29041852_1/478-2893587-9147252?ie=UTF8&node=2126308051&pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=left-1&pf_rd_r=105PFWG24HSVNS1FRB9W&pf_rd_t=101&pf_rd_p=64839072&pf_rd_i=658394051";
//	 	List<TagNode> list_Fourth=new ArrayList<TagNode>();
//	 	list_Fourth.add(new TagNode("影视文学", basicUrl));
//		System.out.println(pageUrl_get(list_Fourth)); 
//		
		
		List<TagNode> thirdList=new ArrayList<TagNode>();
		InputStreamReader reader=new InputStreamReader( new  FileInputStream("File/third.txt"));
		BufferedReader br=new BufferedReader(reader);
		String str="";
		while((str=br.readLine())!=null){
			thirdList.add(new TagNode("",str));  
			List<String> fourthList= pageUrl_get(thirdList); 
			for(int i=0;i<fourthList.size();i++){   
				System.out.println("写入:"+fourthList.get(i));
				TxtWriter.appendToFile(fourthList.get(i),new File("File/fourth.txt"));
			}
			thirdList.clear();
		} 
		System.out.println("=================end=================");
		 
	}

	/**
	 * 获取分页的页面 
	 * 
	 * Bug:假如页面只有一页的话会异常,
	 * @param url 上级页面传过来的URL（基础URL）	
	 * @return 分页的URL数组(所有该商品的页面)
	 * @throws IOException 
	 */
	public static List<String> pageUrl_get(List<TagNode> lists) throws IOException{
		List<String> list_Page=new ArrayList<String>(); 
		for(int i=0;i<lists.size();i++){
			try{
				Document doc=new ADianShang().docGet_URL("http://www.amazon.cn"+lists.get(i).getHref());  //获取页面的元素
				Elements pageUrls=doc.getElementsByClass("pagnTable");
				String basiUrls=pageUrls.select("table>tbody>tr>td>span>a").attr("href").replace("&amp;", "@");
				int count=1; 	 
				while(true){
					//	/s/ref=sr_pg_2?rh=n%3A658390051%2Cn%3A%21658391051%2Cn%3A658394051%2Cn%3A2126308051&amp;page=2&amp;ie=UTF8&amp;qid=1346515746
					String itemUrl=basiUrls.
					replace("page=2", "page="+count).
					replace("sr_pg_2", "sr_pg_"+count); 
					list_Page.add(itemUrl);
					System.out.println(itemUrl);
					count++;
					if(isContinue(itemUrl)==false||count>1000){
						System.out.println("重复");
						break;
					}
				}
			}catch(Exception ex){
				System.out.println("#异常#"+"http://www.amazon.cn"+lists.get(i).getHref());
				TxtWriter.appendToFile("#异常#"+"http://www.amazon.cn"+lists.get(i).getHref(),new File("File/thirdFaild.txt"));
				continue;
			}
		}
		return list_Page;
	}
	/*
	 * 具有分页规则的页面数据
	 * 
	 * 非最后一页40
<div id="pagn" class="pagn"> 
 <span class="pagnPrev"> <a title="上一页" id="pagnPrevLink" class="pagnPrev" href="/s/ref=sr_pg_39?rh=n%3A658390051%2Cn%3A%21658391051%2Cn%3A658394051%2Cn%3A2126308051&amp;page=39&amp;ie=UTF8&amp;qid=1346429148">&laquo;上一页</a> </span> 
 <span class="pagnSep">|</span> 
 <span class="pagnLead"></span> 
 <span class="pagnLink"><a href="/s/ref=sr_pg_1?rh=n%3A658390051%2Cn%3A%21658391051%2Cn%3A658394051%2Cn%3A2126308051&amp;ie=UTF8&amp;qid=1346429148">1</a></span> 
 <span class="pagnMore">...</span> 
 <span class="pagnLink"><a href="/s/ref=sr_pg_39?rh=n%3A658390051%2Cn%3A%21658391051%2Cn%3A658394051%2Cn%3A2126308051&amp;page=39&amp;ie=UTF8&amp;qid=1346429148">39</a></span> 
 <span class="pagnCur">40</span> 
 <span class="pagnLink"><a href="/s/ref=sr_pg_41?rh=n%3A658390051%2Cn%3A%21658391051%2Cn%3A658394051%2Cn%3A2126308051&amp;page=41&amp;ie=UTF8&amp;qid=1346429148">41</a></span> 
 <span class="pagnLink"><a href="/s/ref=sr_pg_42?rh=n%3A658390051%2Cn%3A%21658391051%2Cn%3A658394051%2Cn%3A2126308051&amp;page=42&amp;ie=UTF8&amp;qid=1346429148">42</a></span> 
 <span class="pagnSep">|</span> 
 <span class="pagnNext"> <a title="下一页" id="pagnNextLink" class="pagnNext" href="/s/ref=sr_pg_41?rh=n%3A658390051%2Cn%3A%21658391051%2Cn%3A658394051%2Cn%3A2126308051&amp;page=41&amp;ie=UTF8&amp;qid=1346429148">下一页&raquo;</a> </span> 
 <br clear="all" /> 
</div>
	 * count=42
	 * 非最后一页41
<div id="pagn" class="pagn"> 
 <span class="pagnPrev"> <a title="上一页" id="pagnPrevLink" class="pagnPrev" href="/s/ref=sr_pg_40?rh=n%3A658390051%2Cn%3A%21658391051%2Cn%3A658394051%2Cn%3A2126308051&amp;page=40&amp;ie=UTF8&amp;qid=1346429119">&laquo;上一页</a> </span> 
 <span class="pagnSep">|</span> 
 <span class="pagnLead"></span> 
 <span class="pagnLink"><a href="/s/ref=sr_pg_1?rh=n%3A658390051%2Cn%3A%21658391051%2Cn%3A658394051%2Cn%3A2126308051&amp;ie=UTF8&amp;qid=1346429119">1</a></span> 
 <span class="pagnMore">...</span> 
 <span class="pagnLink"><a href="/s/ref=sr_pg_40?rh=n%3A658390051%2Cn%3A%21658391051%2Cn%3A658394051%2Cn%3A2126308051&amp;page=40&amp;ie=UTF8&amp;qid=1346429119">40</a></span> 
 <span class="pagnCur">41</span> 
 <span class="pagnLink"><a href="/s/ref=sr_pg_42?rh=n%3A658390051%2Cn%3A%21658391051%2Cn%3A658394051%2Cn%3A2126308051&amp;page=42&amp;ie=UTF8&amp;qid=1346429119">42</a></span> 
 <span class="pagnSep">|</span> 
 <span class="pagnNext"> <a title="下一页" id="pagnNextLink" class="pagnNext" href="/s/ref=sr_pg_42?rh=n%3A658390051%2Cn%3A%21658391051%2Cn%3A658394051%2Cn%3A2126308051&amp;page=42&amp;ie=UTF8&amp;qid=1346429119">下一页&raquo;</a> </span> 
 <br clear="all" /> 
</div>
	 * count=42
	 * 最后一页 42
<div id="pagn" class="pagn"> 
 <span class="pagnPrev"> <a title="上一页" id="pagnPrevLink" class="pagnPrev" href="/s/ref=sr_pg_41?rh=n%3A658390051%2Cn%3A%21658391051%2Cn%3A658394051%2Cn%3A2126308051&amp;page=41&amp;ie=UTF8&amp;qid=1346429004">&laquo;上一页</a> </span> 
 <span class="pagnSep">|</span> 
 <span class="pagnLead"></span> 
 <span class="pagnLink"><a href="/s/ref=sr_pg_1?rh=n%3A658390051%2Cn%3A%21658391051%2Cn%3A658394051%2Cn%3A2126308051&amp;ie=UTF8&amp;qid=1346429004">1</a></span> 
 <span class="pagnMore">...</span> 
 <span class="pagnLink"><a href="/s/ref=sr_pg_40?rh=n%3A658390051%2Cn%3A%21658391051%2Cn%3A658394051%2Cn%3A2126308051&amp;page=40&amp;ie=UTF8&amp;qid=1346429004">40</a></span> 
 <span class="pagnLink"><a href="/s/ref=sr_pg_41?rh=n%3A658390051%2Cn%3A%21658391051%2Cn%3A658394051%2Cn%3A2126308051&amp;page=41&amp;ie=UTF8&amp;qid=1346429004">41</a></span> 
 <span class="pagnCur">42</span> 
 <span class="pagnSep">|</span> 
 <span class="pagnDisabled">下一页&raquo;</span> 
 <br clear="all" /> 
</div> 
	 **/
	/**
	 * 判断是否是最后一页
	 * 主要判断pagnNext标签是否存在,若有,则继续,否则最后一页
	 * @param pageUrl
	 * @return 
	 */
	public static boolean isContinue(String pageUrl){
		Document doc=new ADianShang().docGet_URL(pageUrl);  	
		if(doc==null){
			System.out.println("网络连接失败!");
		}
		Elements pageUrls=doc.select("body>#main>#searchTemplate>#rightContainerATF>#rightResultsATF>#centerBelow>#bottomBar>#pagn>span.pagnNext");
//		System.out.println(pageUrls);
		if(pageUrls==null||pageUrls.toString().equals("")){//是最后一页,不再继续
			
			return false;
		}
		else{ 
			return true;
		}
	}
	
}
