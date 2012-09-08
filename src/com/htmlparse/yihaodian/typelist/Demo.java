package com.htmlparse.yihaodian.typelist;	

import java.io.IOException;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Demo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		String url="http://www.yihaodian.com/ctg/s2/c5228-%E9%A5%AE%E7%94%A8%E6%B0%B4/b0/a-s1-v0-p21-price-d0-f04-m1-rt0-pid-k/";
		String url="http://www.yihaodian.com/ctg/s2/c5228-%E9%A5%AE%E7%94%A8%E6%B0%B4/b926202/";
		System.out.println("===================");		 
		System.out.println(Arrays.toString(productsIdList_get(url)));
		System.out.println("==================="); 
	}
	public static String [] productsIdList_get(String url) throws IOException{
		String [] arrList = null; 
		Document doc=doc_get(url);
		if(doc!=null){
			Elements link_span=doc.select("body>div.searchwrap.w980>div#bodyRight>#search_result>#plist>#search_table>#productsIdList");
//			System.out.println(link_span.attr("value"));
			arrList =link_span.attr("value").split(","); 
			return arrList;
		}
		return arrList;
	}
	
	
	public static Document doc_get(String url){ 
		int TIME_OUT=200;
		while(true){ 
			try { 
				return  Jsoup.connect(url).timeout(TIME_OUT*2)
				.userAgent("Mozilla/5.0 (Windows NT 5.1; rv:15.0) Gecko/20100101 Firefox/15.0")
				.get();
			} catch (Exception ex) { 
				try{
					return  Jsoup.connect(url).timeout(TIME_OUT*3).userAgent("Mozilla/5.0 (Windows NT 5.1; rv:15.0) Gecko/20100101 Firefox/15.0").get();
				}catch(Exception ex2){
					try{
						return  Jsoup.connect(url).timeout(TIME_OUT*4).userAgent("Mozilla/5.0 (Windows NT 5.1; rv:15.0) Gecko/20100101 Firefox/15.0").get();
					}catch(Exception ex3){
						try{
							return  Jsoup.connect(url).timeout(TIME_OUT*5).userAgent("Mozilla/5.0 (Windows NT 5.1; rv:15.0) Gecko/20100101 Firefox/15.0").get();
						}catch(Exception ex4){
							try{
								return  Jsoup.connect(url).timeout(TIME_OUT*6).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.77 Safari/535.7").get();
							}catch(Exception ex5){
								try{
									return  Jsoup.connect(url).timeout(TIME_OUT*7).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.77 Safari/535.7").get();
								}catch(Exception ex6){
									try{
										return  Jsoup.connect(url).timeout(TIME_OUT*8).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.77 Safari/535.7").get();
									}catch(Exception ex7){
										try{
											return  Jsoup.connect(url).timeout(TIME_OUT*10).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.77 Safari/535.7").get();
										}catch(Exception ex8){
										 
										}
									}
								}
							}
						}
					}
				}
			}
		}  
	}

}
