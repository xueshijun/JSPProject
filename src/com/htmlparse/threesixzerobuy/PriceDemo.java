package com.htmlparse.threesixzerobuy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.File.TxtWriter;
import com.htmlparse.amazon.ADianShang;
import com.htmlparse.amazon.TagNode;
import com.image.SaveInternetImage;

public class PriceDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		
		
	}
	
	/** 
	 * SAVING THE PRICE-IMAGE OF PRODUCT.
	 * 
	 * 前6个比较特殊
	 */
	public static void priceImage_get(){
  		SaveInternetImage pic = new SaveInternetImage();/*创建实例*/
  		for(int i=1;i<9999999;i++){
//	  		  String photoUrl ="http://price.360buyimg.com/gp280127,3.png";
	  		  String photoUrl ="http://price.360buyimg.com/gp"+i+",3.png"; 
	  		  String fileName = photoUrl.substring(photoUrl.lastIndexOf("/")+1);
	  		  //Saving path
	  		  String filePath = "E:"+File.separator+"images"+File.separator;
	  		  if(pic.saveUrlAs(photoUrl, filePath + fileName)){
		  		  System.out.println("Run ok!\n Get URL file " );
		  		  System.out.println(filePath);
		  		  System.out.println(fileName);
	  		  }
  		}
	}

	//----------------------------------------------------------------------
	
	

	//----------------------------------------------------------------------

}
