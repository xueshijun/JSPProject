package com.htmlparse.yihaodian;

import com.baseUrl.JingDongPage;

public class YiHaoDian extends JingDongPage {

	public YiHaoDian(String url) {
		super(url);
		this.strMobileUrl=url.replace("http://www.yihaodian.com", "http://m.yihaodian.com");
	}
}
