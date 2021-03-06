package com.fourteenfourhundred.jroute;
import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.fourteenfourhundred.svm.SVM;


public class JRouteServer {

	public ServerSocket server;
	public ArrayList<JRouter> routers = new ArrayList<JRouter>();
	public int port;
	public String ip;
	public String password;
	
	public void start(String ip,int port){
		try{
			SVM.initSettings();
			this.port=port;
			this.ip=ip;
			server = new ServerSocket(port,5,InetAddress.getByName(ip));
			onServerStart();
	        while(true){
	        	new JRouteConnection(server.accept());
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	public void onServerStart(){
		
	}
	
	public void on(JRouter router){
		routers.add(router);
	}
	
	public class JRouteConnection {
		 
		Socket socket;
	    BufferedReader in;
		
	    public JRouteConnection(Socket socket){
	        this.socket=socket;
	        action ac = new action();
	        ac.start();
	    }
	    
	    public void respondToRequest(String headers){
	    	System.out.println(headers);
	    	try{
				Request request = new Request(headers,socket);
				Response response = new Response(new OutputStreamWriter(socket.getOutputStream()));
				for(JRouter router:routers){
					if(router.page.equals(request.request)){
						router.request(request,response);
					}
				}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
		}
	    
	    public void validation(OutputStreamWriter out,BufferedReader in){
	    	try{
	    		out.write("\n\nSVM Password: ");
	    		out.flush();
	    		String password = in.readLine();
	    		if(!password.equals(SVM.password)){
	    			out.write("\n====Invalid Credentials====\n\n");
	    			out.flush();
	    			out.close();
	    			in.close();
	    		}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }
	     
	    class action extends Thread{
	        public void run(){
	            try{
	                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	                OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
	                
	                validation(out,in);
	                
	                
	                String line;
	                out.write("SVM> ");
	                out.flush();
	                while((line = in.readLine()) != null){
	                	 respondToRequest(line);
	                	 out.write("SVM> ");
	                	 out.flush();
	                }
	            }catch(Exception e){
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
}
