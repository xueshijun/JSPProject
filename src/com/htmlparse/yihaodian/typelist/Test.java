package com.htmlparse.yihaodian.typelist;
//--------------------------------------
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.File.TxtWriter;
//--------------------------------------
public class Test {


	public static void main(String[] args){  
		System.out.println("==========================BEGIN============================");	 
		System.out.println("==========================BEGIN============================");
		list_1_get();
// 
//
//		String [] str1={"4079701","4077020","4086052","3818802"};
//		String [] str2={"2132701","3818816","1205517","4079701","4077020","4086052"};
		
		
		System.out.println("==========================END============================");	
		System.out.println("==========================END============================");
	}
	 
	
	/**
	 * 
	 * http://www.yihaodian.com/ctg/s2/c5228-%E9%A5%AE%E7%94%A8%E6%B0%B4/5/
	 * */
	public static Document doc_get(String url){ 
		int TIME_OUT=200;
		while(true){ 
			try { 
				return  Jsoup.connect(url).timeout(TIME_OUT*2)
					.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.77 Safari/535.7").get();
			} catch (Exception ex) { 
				try{
					return  Jsoup.connect(url).timeout(TIME_OUT*3).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.77 Safari/535.7").get();
				}catch(Exception ex2){
					try{
						return  Jsoup.connect(url).timeout(TIME_OUT*4).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.77 Safari/535.7").get();
					}catch(Exception ex3){
						try{
							return  Jsoup.connect(url).timeout(TIME_OUT*5).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.77 Safari/535.7").get();
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

	/**Get List from page of navigator list
	 *  
	 * http://www.yihaodian.com/product/listAll.do  
	 * */ 
	public static void list_1_get(){ 
		long current=0;
		Document doc=doc_get("http://www.yihaodian.com/product/listAll.do"); 
		if(doc!=null){
									 //div>.allsort.sortwidth>div.fl>
									 //div>.allsort.sortwidth>div.fr>									
			Elements links=doc.select("div>.allsort.sortwidth>div>div.alonesort"); 
			System.out.println("==========BEGIN================");
 
			for(Element link_alonesort:links){//get first class panel
				Elements link_mt= link_alonesort.select("div.mt");//first class property
				System.out.println("----FIRST CLASS");
				System.out.println(link_mt.text());
				
				Elements link_fores= link_alonesort.select("div.mc>dl");
				/**BEGIN*******get second class panel**/
				for(Element link_fore:link_fores){ 

					Elements link_dds=link_fore.select("dd");
					/**BEGIN*******get second class panel**/
					for(Element link_dd:link_dds){  
						
						Element link_dt=link_dd.parent().select("dt>a").first();//second class property
						System.out.println("--------SECOND CLASS");
						System.out.println(link_dt.text()); 
						
						Elements link_as=link_dd.select("em>span>a"); //third class property  
						/**BEGIN*******get third class panel**/
						for(Element link_a:link_as){//get forth class panel
							System.out.println("------------CLASS THREE"); 
							System.out.println(link_a.text()+"---"+link_a.absUrl("href")); //third class property 
							
							
							int count=1;
							/**版本三
							ArrayList<Number> list=new ArrayList<Number>();
							while(true){
								System.out.println(link_a.absUrl("href")+"/b0/a-s1-v0-p"
										+String.valueOf(count)+"-price-d0-f04-m1-rt0-pid-k/"); 
//								http://www.yihaodian.com/ctg/s2/c5228-%E9%A5%AE%E7%94%A8%E6%B0%B4/b0/a-s1-v0-p2-price-d0-f04-m1-rt0-pid-k/
								String [] strList=productsIdList_get(
										link_a.absUrl("href")+"b0/a-s1-v0-p"
										+String.valueOf(count)+"-price-d0-f04-m1-rt0-pid-k/");
								list=new ArrayList<Number>(list);
								list= addAll(list, strList); 
								count++;
								if(strList.length<10){
									break;
								}
							}
							Iterator<Number> iterator=list.iterator();
							while(iterator.hasNext()){
								try {
									TxtWriter.appendToFile(iterator.next().getName(),new File("File/idlist.txt"));
									System.out.println(iterator.next().getName()+"写入完成");
								} catch (IOException e) { 
									e.printStackTrace();
								} 
							}
							System.out.println("写入完成");
							list.clear();
//							*/

							
							/**版本一
							for(int i=1;i<50;i++){
								System.out.println(
										link_a.absUrl("href")+"b0/a-s1-v0-p"
										+String.valueOf(i)+"-price-d0-f04-m1-rt0-pid-k/");
								String [] strList=productsIdList_get(
										link_a.absUrl("href")+"b0/a-s1-v0-p"
										+String.valueOf(i)+"-price-d0-f04-m1-rt0-pid-k/");
								for(int j=0;j< strList.length;j++){
									try {
										TxtWriter.appendToFile(strList[j],new File("File/idlist.txt"));
									} catch (IOException e) { 
										e.printStackTrace();
									} 
								} 
								try {
									Thread.sleep(2000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}*/
							/**
							 * 版本二*/ 
							String [] strListBackup = {};
							while(true){ 
								System.out.println(link_a.absUrl("href")+"b0/a-s1-v0-p"+String.valueOf(count)+"-price-d0-f04-m1-rt0-pid-k/");
								String [] strList=productsIdList_get(
										link_a.absUrl("href")+"b0/a-s1-v0-p"
										+String.valueOf(count)+"-price-d0-f04-m1-rt0-pid-k/");
								count++;
								if(compareTo(strListBackup,strList)==false){//不相似
									strListBackup=strList;
								}else{//相似
									break;
								}   
								//写入文件
								for(int i=0;i< strList.length;i++){
									try {
										TxtWriter.appendToFile(strList[i],new File("File/idlist.txt"));
										current++;
									} catch (IOException e) { 
										e.printStackTrace();
									} 
								} 
							}
							
	
//							break;
						}
						/**BEGIN*******get third class panel**/
//						break;
					}
					/**END********get second class panel*************/ 
//					break;
				} 
				/**END********get first class panel*************/ 
				System.out.println("++++++++++++++++++++++++++++++++++++++++++");
//				break;
			} 
			System.out.println(current);
			System.out.println("===========END==================");
		}else{
			System.out.println("Error");
		}
	}


	/**GET Product id list from one page
	 * 
	 * http://www.yihaodian.com/ctg/s2/c5228-%E9%A5%AE%E7%94%A8%E6%B0%B4/b0/a-s1-v0-p1-price-d0-f04-m1-rt0-pid-k/
	 * 
	 * */
	public static String [] productsIdList_get(String url){
		String [] arrList = null; 
		Document doc=doc_get(url); 
		if(doc!=null){
			Elements link_span=doc.select(".searchwrap.w980>#bodyRight>input#productsIdList");
//			System.out.println(link_span.attr("value"));
			arrList =link_span.attr("value").split(","); 
			return arrList;
		}
		return arrList;
	} 

	/**并集
	 **/
	public static ArrayList<Number> addAll(List<Number> oldList,String []str){
		ArrayList<Number> list=new ArrayList<Number>();
		Number num;
		if(str==null||str.length==0){
			return list; 
		}
		for(int i=0;i<str.length;i++){
			num=new Number(str[i]);
			if(!list.contains(num)){
				list.add(num);
			}
		}
		System.out.println(list);
		return list; 
	}
	
	

	/**数组相似度检测
	 * 
	 * 
	 * 	String [] str1={"4079701","4077020","4086052","3818802","1041525","4079827","4076985","1825073","2132698","3818917",
				"4081188","3818893","2709762","3818812","3818985","2132700","1205518","1672360","4077022","4079824",
				"4077087","4079831","1924388","2132699","4077021","4079829","3819035","1348986","4079834","2132716",
				"1672362","2132702","4079701","4077020","4086052","3818802","1041525"};
		String [] str2={"2132701","3818816","1205517","4098777","1348985","4077072","3980132","1376757","3818884",
				"1850388","1908492","3818934","4079820","2132719","3976789","1886062","4081192","3818808","3818803","2066576",
				"2941989","3818748","4062223","1693112","2638658","1376758","3900101","1825075","2787037","4079821",
				"1348989","2639001","4079701","4077020","4086052","3818802","1041525"};

		System.out.println(Arrays.toString(str1));
		System.out.println(Arrays.toString(str2));
		Arrays.sort(str1); 
		System.out.println(Arrays.toString(str1)); 
		Arrays.sort(str2); 
		System.out.println(Arrays.toString(str2));
	 * */
	public static boolean compareTo(String [] strListBackup,String [] strList){ 
		String [] max,min;
		if(strList==null||strListBackup==null){//不包含
			return false;
		}
		if( strListBackup.length<strList.length){
			max=strListBackup;			min=strList;
		}else{
			max=strList;			min=strListBackup;;
		}
		
		Arrays.sort(max);
//		System.out.println("长数组:"+Arrays.toString(max));
		Arrays.sort(min);
//		System.out.println("短数组:"+Arrays.toString(min));
		
		int current=0;
		for(int i=0;i<max.length;i++){
			for(int j=0;j<min.length;j++){ 
				if(max[i].equals(min[j])){
//					System.out.println(max[i]+"=="+min[j]);
					 current++;
				}else{
//					System.out.println(max[i]+"!="+min[j]);
				}
			}
		} 
		if(current>=min.length){
//			System.out.println(current+"true"); 
			return true;
		}else{
//			System.out.println(current+"false"); 
			return false;
		} 
	}
	
	
	
//	/**Get List from quick navigator 
//	 * 
//	 * http://www.yihaodian.com/1/
//	 * http://www.yihaodian.com/product/listAll.do
//	 * */
//	public static void list_2_get(){ 
//		Document doc=doc_get("http://www.yihaodian.com/product/listAll.do"); 
//		if(doc!=null){
//			/**"http://www.yihaodian.com/product/listAll.do"*/
//			Elements links=doc.select(".nav_bar>.wrap>#allSortOuterbox>#allCategoryHeader>.allsort_out>.allsort>li>h3>a"); 
//			System.out.println("==========BEGIN================");
// 
//			for(Element link:links){  
//				//<a title="食品饮料" tk="CatMenu_5135_61" target="_blank" href="http://www.yihaodian.com/channel/5135">食品饮料</a>
//				System.out.println(link.text()
//						+"------"+link.attr("href")
//						+"------"+link.attr("tk"));
////				System.out.println(link.toString());
//			}
//			System.out.println("===========END==================");
//		}else{
//			System.out.println("Error");
//		}
//	}
	
	
//
//	/**Bug!!!!!!!!
//	 * Get page  url of product list 
//	 * 
//	 * http://www.yihaodian.com/ctg/s2/c5228-%E9%A5%AE%E7%94%A8%E6%B0%B4/
//	 * http://www.yihaodian.com/ctg/s2/c5319-%E5%85%B6%E5%AE%83%E7%BD%90%E5%A4%B4/
//	 */
//	public static String pageCount_get(String url){ 
//		Document doc=doc_get(url); 
//		if(doc!=null){
//			Elements link_span=doc.select(".searchwrap.w980>#bodyRight>.turnPageBottom>input#pageCountPage");
//			return  link_span.attr("value"); 
//		}
//		return "";
//	}
	
	
//	/** Get list of page
//	 * Example:
//	 * 饮用水列表分析
//	 * 第一页：
//	 * http://www.yihaodian.com/ctg/s2/c5228-%E9%A5%AE%E7%94%A8%E6%B0%B4/b0/a-s1-v0-p1-price-d0-f04-m1-rt0-pid-k/
//	 * 第二页
//	 * http://www.yihaodian.com/ctg/s2/c5228-%E9%A5%AE%E7%94%A8%E6%B0%B4/b0/a-s1-v0-p2-price-d0-f04-m1-rt0-pid-k/
//	 * 第三页
//	 * http://www.yihaodian.com/ctg/s2/c5228-%E9%A5%AE%E7%94%A8%E6%B0%B4/b0/a-s1-v0-p3-price-d0-f04-m1-rt0-pid-k/
//	 * 第四页
//	 * http://www.yihaodian.com/ctg/s2/c5228-%E9%A5%AE%E7%94%A8%E6%B0%B4/b0/a-s1-v0-p4-price-d0-f04-m1-rt0-pid-k/
//	 * 第五页
//	 * http://www.yihaodian.com/ctg/s2/c5228-%E9%A5%AE%E7%94%A8%E6%B0%B4/b0/a-s1-v0-p5-price-d0-f04-m1-rt0-pid-k/
//	 * 第六页
//	 * http://www.yihaodian.com/ctg/s2/c5228-%E9%A5%AE%E7%94%A8%E6%B0%B4/b0/a-s1-v0-p6-price-d0-f04-m1-rt0-pid-k/
//	 * 第七页(无效页面)
//	 * http://www.yihaodian.com/ctg/s2/c5228-%E9%A5%AE%E7%94%A8%E6%B0%B4/b0/a-s1-v0-p7-price-d0-f04-m1-rt0-pid-k/
//	 */
//	public static Vector<String> pageList_get(String basicUrl){
//		 Vector<String>  vector=new  Vector<String>();
//		 int count=0;
//		 while(true){
//			 Document doc=doc_get(basicUrl); 
//			 if(doc!=null){
//				 Elements links=doc.select(".searchwrap.w980>#bodyRight>.itemSearchResult");
//				 if(links.size()==0){
//					 
//				 }
//		
//			 } 
//		 }
//		 return vector;
//	}
//	
//	/**
//	 * All bugs
//	 */
//	public static void productId_get(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException{  
////		Document doc=doc_get(url);
//		Document doc=doc_get_ajax(url);
//			if(doc!=null){
//				Elements links=doc.select(".searchwrap.w980>#bodyRight>.itemSearchResult>ul>li");
//				for(Element link:links){
//					System.out.println(link.attr("id").replace("producteg_",""));
////					vector.add(link.attr("id").replace("producteg_",""));
//				}  
//			} 	 
//	}  
//	
	
	
}
