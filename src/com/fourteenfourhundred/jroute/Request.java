package com.fourteenfourhundred.jroute;

import java.net.Socket;

public class Request {

	Socket connection;
	String request;
	String[] params;
	
	
	public Request(String rawHeaders,Socket connection){
		params=rawHeaders.split(" ");
		request=params[0];
		this.connection=connection;
	}

	public String getParam(int i){
		return params[i+1];
	}
	
	public void parseRequest(){
		
	}
	
}
