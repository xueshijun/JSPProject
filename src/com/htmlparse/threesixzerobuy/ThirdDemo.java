package com.htmlparse.threesixzerobuy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.File.TxtWriter;
import com.htmlparse.amazon.ADianShang;

public class ThirdDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		String url="http://www.360buy.com/products/1713-3258-3258-0-0-0-0-0-0-0-1-1-549.html";
//		productPage_get(url);
		
		//���� 
		InputStreamReader reader=new InputStreamReader( new  FileInputStream("File/360/second.txt"));
		BufferedReader br=new BufferedReader(reader);
		String str="";
		while((str=br.readLine())!=null){
			System.out.println("ʹ��......."+str);
			try{
				productPage_get(str); 
			}catch(Exception ex){
				System.out.println("�쳣ҳ��,��¼��......."+str);
				TxtWriter.appendToFile(str,new File("File/360/firstFailed.txt")); 
			}
		} 
		
	}
	/** 
	 * ��������ҳ���ȡ����
	 * http://www.360buy.com/products/1713-3258-3258-0-0-0-0-0-0-0-1-1-549.html 
	 * @throws IOException 
	 */
	public static void productPage_get(String url) throws IOException{ 
		Document doc=new ADianShang().docGet_URL(url); 
		Elements links=doc.select("body>.w.main>.right-extra>#plist>.item>dl>dt.p-name>a");
		for(Element link:links){
			System.out.println("д��.........."+link.attr("href")); 
			TxtWriter.appendToFile(link.attr("href"),new File("File/360/third.txt")); 

		}
	}
}
