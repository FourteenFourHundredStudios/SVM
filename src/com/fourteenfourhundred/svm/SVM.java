package com.fourteenfourhundred.svm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SVM {

	public static String password;
	
	public SVM(){
		
	}
	
	public static void initSettings(){
		String fileData="";
		try{
			BufferedReader br=new BufferedReader(new FileReader(new File("settings.json")));
		    String line="";	
		    while((line=br.readLine())!=null){
		    	fileData+=line;
		    }
		    br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		JsonElement jelement = new JsonParser().parse(fileData);
	    JsonObject  obj = jelement.getAsJsonObject();
	    
	    password=obj.get("password").getAsString();
	    
	    System.out.println(password);
	}
	
}
