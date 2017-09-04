package com.fourteenfourhundred.jroute;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class JRouteServer {

	public ServerSocket server;
	public ArrayList<JRouter> routers = new ArrayList<JRouter>();
	public int port;
	public String ip;
	
	public void start(String ip,int port){
		try{
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
				Request request = new Request(headers);
				Response response = new Response(new OutputStreamWriter(socket.getOutputStream()));
				for(JRouter router:routers){
					if(router.page.equals(request.page)){
						if(request.requestType.toLowerCase().equals("get")){
							router.get(request,response);
						}else if(request.requestType.toLowerCase().equals("post")){
							router.post(request,response);
						}
					}
				}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
		}
	     
	    class action extends Thread{
	        public void run(){
	            try{
	                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	                String line="";
	                String request="";
	                while((line = in.readLine()) != null){
	                	if(line.equals(""))break;
	                	request+=line+"\n";
	                }
	                respondToRequest(request);
	                in.close();
	            }catch(Exception e){
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
}
