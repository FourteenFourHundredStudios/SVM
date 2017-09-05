package com.fourteenfourhundred.jroute;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.OutputStreamWriter;




public class Response {
	
	OutputStreamWriter writer;
	public int status=200;
	
	public Response(OutputStreamWriter writer){
		this.writer=writer;

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
			writer.write(str+"\n");
			writer.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	


}
