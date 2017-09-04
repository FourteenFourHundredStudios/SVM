package com.fourteenfourhundred.jroute;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class Response {
	
	OutputStreamWriter writer;
	Map<String,String> headers=new HashMap<String,String>();
	public int status=200;
	
	public Response(OutputStreamWriter writer){
		this.writer=writer;
		setHeader("Content-Type","text/html; charset=UTF-8");
		setHeader("Content-Encoding","UTF-8");
		setHeader("Server","JRoute");
		setHeader("Accept-Ranges","bytes");
	}
	
	public void setHeader(String key,String value){
		headers.put(key, value);
	}
	
	public void setHeader(int status,String key,String value){
		this.status=status;
		headers.put(key, value);
	}
	
	public void end(){
		try{
			writer.write(getHeaders());
			writer.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void redirect(String location){
		setHeader(301,"Location",location);
		end();
	}
	
	public void setCookie(String name,String value,Date date){
		//Date: Wed, 21 Oct 2015 07:28:00 GMT
		//      Tue Jun 06 20:29:36 CDT 2017
		 
		//System.out.println(date);
		setHeader("Set-Cookie", name+"="+value+"; Expires="+date);
	}
	
	public void sendFile(String file){
		try{
			BufferedReader br=new BufferedReader(new FileReader(new File("WebContent/"+file)));
		    String line="";	
		    String fileData="";
		    while((line=br.readLine())!=null){
		    	fileData+=line;
		    }
		    br.close();
		    send(fileData);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void sendFile(String file,String[][]...params){
		try{
			BufferedReader br=new BufferedReader(new FileReader(new File("WebContent/"+file)));
		    String line="";	
		    String fileData="";
		    while((line=br.readLine())!=null){
		    	fileData+=line;
		    }
		    br.close();
		    send(fileData);
		}catch(Exception e){
			e.printStackTrace();
		}
	}



	
	public void send(String str){
		try{
			writer.write(getHeaders());
			writer.write(str);
			writer.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getHeaders(){
		String str="";
		for(Map.Entry<String, String> entry : headers.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    str+=key+": "+value+"\r\n";
		}
		str+="\r\n";
		return "HTTP/1.1 "+status+"\r\n"+str;
	}

}
