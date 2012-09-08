package com.htmlparse.amazon;

import org.jsoup.nodes.Document;

public class Amazon_Item_Demo {

	
	public static void main(String[] args) { 
		//http://www.amazon.cn/gp/product/B008156XQG
//		String url="http://www.amazon.cn/gp/product/B008156XQG";
		String url="http://www.amazon.cn/%E5%94%90%E5%AE%81%E8%A1%97%E5%B2%81%E6%9C%88-%E7%8E%9B%E6%A0%BC%E4%B8%BD%E7%89%B9%C2%B7%E6%92%92%E5%88%87%E5%B0%94/dp/B001YIYOJ0/ref=sr_1_113?s=books&ie=UTF8&qid=1346728764&sr=1-113";
		Document doc=new ADianShang().docGet_URL(url); 
		Amazon_Item dp=new Amazon_Item(doc);
		dp.strURL_set(url);
		dp.myprint();
	} 
}
