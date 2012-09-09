/**
 * HX-2012-08-10:
 * storing information on each product found at www.360buy.com
 */

package com.htmlparse.threesixzerobuy;

/*
package DEBGEN.JingDong;
*/

/* ****** ****** */

import java.util.ArrayList;

/* ****** ****** */
 
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* ****** ****** */

public class JD_360buy_item {
//
    private String strTitle= "__NONE__";
    private String strIdent= "__NONE__";
    private String strMarketPrice="__NONE__";
    private String strJingDongPrice="__NONE__";
    private String strProductInfo="__NONE__";
//
    public JD_360buy_item(Document doc)
    {
	if (doc!=null) strTitle_set(doc);
	if (doc!=null) strIdent_set(doc);
	if (doc!=null) strJingDongPrice_set(doc);
	if (doc!=null) strProductInfo_set(doc);
    }
//
    public String strTitle_get()
    {
	return strTitle ;
    }
    public void strTitle_set(Document doc)
    {
	if (doc!=null) {
	    Element link =
		doc.select("div.right-extra>#name>h1").first();
	    strTitle = link.text();
	} // end of [if]
	if (strTitle==null) strTitle = "__NONE__";
	return ;
    }
//
    public String strIdent_get()
    {
	return strIdent ;
    }
    public void strIdent_set(Document doc)
    {
	if (doc!=null) {
	    Elements links =
		doc.select("div.right-extra>#summary>li");
	    for (Element link:links) {
		String ident = link.getElementsByTag("span").text();
		if (ident!=null) {
		    strIdent = ident; break;
		} // end of [if]
	    }
	}
	if (strIdent==null) strIdent = "__NONE__";
	return;
    }
//
    public String strMarketPrice_get ()
    {
	return strMarketPrice ; 
    }
//
    public String strJingDongPrice_get ()
    {
	return strJingDongPrice ; 
    }

    public void strJingDongPrice_set(Document doc)
    {
	if (doc!=null) {
	    Elements links =
		doc.select("div.right-extra>#summary>li");
	    for (Element link:links) {
		Element image = link.select("img[src$=.png]").first();
		if (image!=null) {
		    strJingDongPrice = image.attr("src"); break;
		} // end of [if]
	    }
	}
	if (strJingDongPrice==null) strJingDongPrice = "__NONE__";
	return;
    }
//
    public String strProductInfo_get ()
    {
	return strProductInfo ; 
    }


    public void strProductInfo_set (Document doc)
    {
	strProductInfo = null;
	if (doc!=null) {
	    Elements links, links2 ;
	    links = doc.select("TABLE DIV[oldclass=pcp_zhengzi]");
	    links2 = doc.select("TABLE DIV[oldoldclass=pcp_zhengzi]");
	    links.addAll(links2) ;
	    links2 = doc.select("TABLE DIV[oldoldoldclass=pcp_zhengzi]");
	    links.addAll(links2) ;
	    for (Element link:links) {
		if (strProductInfo==null) {
		    strProductInfo = "\n" + link.text();
		} else {
		    strProductInfo = strProductInfo + "\n" + link.text();
		} // end of [if]
	    }
	}
	if (strProductInfo==null) strProductInfo = "__NONE__";
	return;
    }
//
    public void myprint () {
	System.out.println ("title = " + strTitle);
	System.out.println ("ident = " + strIdent);
	System.out.println ("market price = " + strMarketPrice);
	System.out.println ("JingDong price = " + strJingDongPrice);
	System.out.println ("product info = " + strProductInfo);
	return;
    }
//
} // end of [JD_360buy_item]

/* ****** ****** */

/* end of [JD_360buy_item.java] */
