package com.htmlparse.amazon;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Amazon_Item {


	//
	    private String strURL = "**NONE**";
	    private String strTitle = "**NONE**";
	    private String strBrand = "**NONE**";
	    private String strAmazonPrice ="**NONE**";
	    private String strMKTPrice ="**NONE**"; 
	    private String strProductInfo="**NONE**";
	//
	    

	
//--------------------------------
    public Amazon_Item(Document doc)
    {
		if (doc!=null) strTitle_set(doc);
		if (doc!=null) strBrand_set(doc);
		if (doc!=null) strAmazonPrice_set(doc);
		if (doc!=null) strMKTPrice_set(doc); 
		if (doc!=null) strProductInfo_set(doc);
    }
//-------------------------------
    public String strURL_get()
    {
    	return strURL;
    }
    public void strURL_set(String url)
    {
    	strURL = url; return ;
    }
    
//--------------------------------
    public String strTitle_get()
    {
    	return strTitle ;
    }
    public void strTitle_set(Document doc)
    {
		if (doc!=null) {
			Element link = doc.select("form#handleBuy>div.buying>h1.parseasinTitle>span#btAsinTitle").first();
		    if (link!=null) strTitle = link.text();
		} // end of [if]
		if (strTitle==null) strTitle = "**NONE**";
		return ;
    }
    
//--------------------------------
    public String strAmazonPrice_get ()
    {
    	return strAmazonPrice ; 
    }
    public void strAmazonPrice_set(Document doc)
    {
    	if (doc!=null) {
			Element link = doc.select("form#handleBuy>table>tbody>tr>td>div#priceBlock>table.product>tbody>tr>td>span#actualPriceValue>b.priceLarge").first();
		    if (link!=null) strAmazonPrice = link.text();
		} // end of [if]
		if (strAmazonPrice==null) strAmazonPrice = "**NONE**";
		return ;
    }
//
    public String strMKTPrice_get ()
    {
    	return strMKTPrice ; 
    }
    public void strMKTPrice_set(Document doc)
    { 
    	if (doc!=null) {
			Element link = doc.select("form#handleBuy>table>tbody>tr>td>div#priceBlock>table.product>tbody>tr>td>span.listprice").first();
		    if (link!=null) 
		    	strMKTPrice = link.text();
		} // end of [if]
		if (strMKTPrice==null) strMKTPrice = "**NONE**";
		return ;
    }
//
    public String strBrand_get (Document doc)
    {
    	return strBrand;
    }
    public void strBrand_set(Document doc)
    {
      	if (doc!=null) {
			Element link = doc.select("form#handleBuy>div.buying>a").first();
		    if (link!=null) 
		    	strBrand = link.text();
		} // end of [if]
		if (strBrand==null) strBrand = "**NONE**";
		return ;
    }
//
    public String strProductInfo_get ()
    {
    	return strProductInfo ; 
    }
    public void strProductInfo_set (Document doc)
    { 
      	if (doc!=null) {
			Element link = doc.select("body.dp>div#divsinglecolumnminwidth>table>tbody>tr>td.bucket>div.content").first();
		    if (link!=null) 
		    	strProductInfo = link.text();// link.toString();
		} // end of [if]
		if (strProductInfo==null) strProductInfo = "**NONE**";
		return ;
    }
//
    public void myprint () {
		System.out.println ("URL = " + strURL);
		System.out.println ("title = " + strTitle);
		System.out.println ("Brand = " + strBrand);
		System.out.println ("Amazon price = " + strAmazonPrice);
		System.out.println ("market price = " + strMKTPrice); 
		System.out.println ("product info = " + strProductInfo);
		return;
    }
//

}
