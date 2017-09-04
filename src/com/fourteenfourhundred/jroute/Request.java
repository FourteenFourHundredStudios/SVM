package com.fourteenfourhundred.jroute;
import java.util.HashMap;
import java.util.Map;

public class Request {

	
	String rawHeaders;
	String page;
	String requestType;
	Map<String,String> urlParams=new HashMap<String,String>();
	
	public Request(String rawHeaders){
		this.rawHeaders=rawHeaders;
		parseRequest();
	}
	
	public void parseRequest(){
		String[] rawHeadersArray=rawHeaders.split("\n");
		page=rawHeadersArray[0].split(" ")[1];
		requestType=rawHeadersArray[0].split(" ")[0];
		
    	if(page.indexOf("?")>-1){
    		if(page.indexOf("&")>-1){
    			String[] part = page.substring(page.indexOf("?")+1).split("&");
    			for(String s:part){
    				urlParams.put(s.split("=")[0],s.split("=")[1]);
    			}
    		}else{
    			urlParams.put(page.substring(page.indexOf("?")+1,page.indexOf("=")), page.substring(page.indexOf("=")+1,page.length()));
    		}
    		page=page.substring(0,page.indexOf("?"));
    	}
    	
    	
	}
	
}
