package com.htmlparse.amazon;

public class TagNode {

	private String name;
	private String href;
	public TagNode(String name,String href){
		this.name=name;
		this.href=href;  
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String toString(){
//		return "Node:"+name+"  href: "+href;
		return name+"href: "+href+"\n";
	}
}
