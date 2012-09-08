package com.jsons.demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Demo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String fileUrl="http://www.yihaodian.com/ctg/s2/c5228-%E9%A5%AE%E7%94%A8%E6%B0%B4/b0/a-s1-v0-p4-price-d0-f04-m1-rt0-pid-k/";
		  URL url = new URL(fileUrl);
		  HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		  DataInputStream in = new DataInputStream(connection.getInputStream()); 
		  

	}
	public static boolean saveUrlAs(String fileUrl, String savePath)
  	{ 
	  try
	  {
		  URL url = new URL(fileUrl);
		  HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		  DataInputStream in = new DataInputStream(connection.getInputStream()); 
		  
		  DataOutputStream out = new DataOutputStream(new FileOutputStream(savePath)); 
		  byte[] buffer = new byte[8192];
		  int count = 0;
		  while ((count = in.read(buffer)) > 0)/*�����������ֽڵ���ʽ��ȡ��д��buffer��*/
		  {
			  out.write(buffer, 0, count);
		  }
		  if(buffer.length==0){
			  return false;
		  }
		  out.close();/*��������Ϊ�ر�����������Լ�������Դ�Ĺ̶���ʽ*/
		  in.close();
		  connection.disconnect();
		  return true;/*������Դ��ȡ���洢���سɹ�����true*/ 
	  }
	  catch (Exception e)
	  {
		  System.out.println(e + fileUrl + savePath);
		  return false;
	  }
  }

}
