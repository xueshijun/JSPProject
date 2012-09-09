package com.htmlparse.threesixzerobuy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.File.TxtWriter;
import com.htmlparse.amazon.ADianShang;

public class SecondDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("====================BEGIN"); 
		/**GET SECOND CLASS*/
		//�ж��Ƿ�����Ʒ��ҳ�б�����һҳ
//		String url="http://www.360buy.com/products/1713-3258-3258-0-0-0-0-0-0-0-1-1-1.html";
//		System.out.println(isLast(url));
		
//		//��ȡ��Ʒ������ҳ�ĵ�ַ
//		String url="http://www.360buy.com/products/1713-3258-000.html";
//		secondClass_get(url);
		
		//���� 
		InputStreamReader reader=new InputStreamReader( new  FileInputStream("File/360/first.txt"));
		BufferedReader br=new BufferedReader(reader);
		String str="";
		while((str=br.readLine())!=null){
			System.out.println("ʹ��......."+str);
			try{
				secondClass_get(str); 
			}catch(Exception ex){
				System.out.println("�쳣ҳ��,��¼��......."+str);
				TxtWriter.appendToFile(str,new File("File/360/firstFailed.txt")); 
			}
		} 
		
		System.out.println("====================END");
	}
	public static void secondClass_get(String mainUrl) throws IOException{
		System.out.println(mainUrl);
		Document doc=new ADianShang().docGet_URL(mainUrl); 
		ArrayList<String> pageList=new ArrayList<String>();
		
		/*ȷ���ǲ�����ȫ���ӷ���ҳ
		 * ����ȡ����http://www.360buy.com/products/1713-3258-000.htmlҳ���
		 * ����÷���������Ʒ����ַ
		 */ 
		Element link=doc.select("body>.w.main>.left>#sortlist>.mc>.extra>a").first(); 
		String basicUrl=link.attr("href").trim();//http://www.360buy.com/products/1713-3258-3258.html
//		System.out.println(basicUrl);
		if(basicUrl!=""){ 
//			//���з�ҳ���� �ķ�ҳ��ַ 			       http://www.360buy.com/products/1713-3258-3258-0-0-0-0-0-0-0-1-1-2.html
			
			basicUrl=basicUrl.substring(0,basicUrl.indexOf(".html"));//http://www.360buy.com/products/1713-3258-3258
//			System.out.println(basicUrl);
			String pageUrl=basicUrl+".html";//��ҳ��ַ
			int i=1;
			while(!isLast(pageUrl)){ 
				pageUrl=basicUrl+"-0-0-0-0-0-0-0-1-1-"+i+++".html";
				TxtWriter.appendToFile(pageUrl,new File("File/360/second.txt")); 
				System.out.println("����д��.........."+pageUrl); 
				pageList.add(pageUrl);

			} 
		} 
		for(String page:pageList ){
			TxtWriter.appendToFile(page,new File("File/360/second.txt")); 
			System.out.println("����д��.........."+page);
		}
	} 
	/**
	 * ���ݸ����ķ�ҳURL,�ж��Ƿ������һҳ
	 * http://www.360buy.com/products/1713-3258-3258-0-0-0-0-0-0-0-1-1-1.html
	 * */
	public static boolean isLast(String url){
		Document doc=new ADianShang().docGet_URL(url);  
		Element link=doc.select("body>.w.main>.right-extra>.m.clearfix>.pagin.pagin-m.fr>a.next").first();
		if(link==null){
			return true;
		} 
		return false;
	}
}
