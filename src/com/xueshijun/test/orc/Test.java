package com.xueshijun.test.orc;

import java.io.File;   
import java.io.IOException;   
import java.util.Date;

import com.ocr.OCR;
 
public class Test {
 
    public static void main(String[] args) throws Exception {   
        String path = "E:\\images\\gp280127,3.png";      
 
//        	int count=0;
//        	while(count<10) {
	        	System.out.println("=============BEGIN================");
	        	Date beginTime=new Date();
	            String valCode = new OCR().recognizeText(new File(path), "png");      
	            System.out.println(valCode.trim());  
	            Date endTime=new Date();
	            long timeOfSearch=endTime.getTime()-beginTime.getTime();
	        	System.out.println(timeOfSearch+"ms\n=============END================");
//	        	count++;
//        	}
      
    }   

}
