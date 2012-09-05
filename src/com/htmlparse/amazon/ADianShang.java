package com.htmlparse.amazon;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/* ****** ****** */

public class ADianShang { 
    private int CONNECT_TIMEOUT_INI = 1000/*ms*/;
    public int CONNECT_TIMEOUT_INI_get() { 
    	return CONNECT_TIMEOUT_INI ; 
    }
    public void CONNECT_TIMEOUT_INI_set(int val) { 
    	CONNECT_TIMEOUT_INI = val; 
    	return; 
    }
//
    private int CONNECT_TIMEOUT_FIN = 16000/*ms*/;
    public int CONNECT_TIMEOUT_FIN_get() { return CONNECT_TIMEOUT_FIN ; }
    public void CONNECT_TIMEOUT_FIN_set(int val) { CONNECT_TIMEOUT_FIN = val; return; }
//
    private static void _geterr (String URL) {
		System.err.println ("Connection failed: URL = " + URL);
		return;
    }
    public Document docGet_URL(String URL)
    {
		int timeout = CONNECT_TIMEOUT_INI;
		Connection conn = Jsoup.connect(URL);
		Document doc;
		while (true) {
		    conn = conn.timeout(timeout);
		    try {
				doc = conn.get(); break;
		    }catch (IOException _) {
				timeout = 2 * timeout;
				if (timeout > CONNECT_TIMEOUT_FIN) { 
					_geterr(URL); 
					doc = null; 
					break;
				}
		    }
		}
		return doc;
    }
//
    public String docGetTitle_doc (Document doc)
    {
    	return doc.title();
    }
//
} // end of [class]

/* ****** ****** */

/* end of [ADianShang.java] */
