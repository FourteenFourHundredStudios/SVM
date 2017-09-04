package com.fourteenfourhundred.jroute;


public class Request {

	
	String request;
	String[] params;
	
	
	public Request(String rawHeaders){
		params=rawHeaders.split(" ");
		request=params[0];
		parseRequest();
	}

	public String getParam(int i){
		return params[i+1];
	}
	
	public void parseRequest(){
		
	}
	
}
