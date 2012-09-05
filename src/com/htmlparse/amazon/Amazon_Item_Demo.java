package com.htmlparse.amazon;

import org.jsoup.nodes.Document;

public class Amazon_Item_Demo {

	//http://www.amazon.cn/gp/site-directory/ref=topnav_sad
	public static void main(String[] args) { 
		//http://www.amazon.cn/gp/product/B008156XQG
		String url="http://www.amazon.cn/gp/product/B008156XQG";
		Document doc=new ADianShang().docGet_URL(url); 
		Amazon_Item dp=new Amazon_Item(doc);
		dp.strURL_set(url);
		dp.myprint();
	}

}
