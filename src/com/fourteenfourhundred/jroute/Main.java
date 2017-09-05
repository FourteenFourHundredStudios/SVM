package com.fourteenfourhundred.jroute;

import java.io.IOException;

public class Main extends JRouteServer{


	
	public Main(){
		
		start("localhost",8090);
		
		
		
	}

	public void onServerStart(){
		System.out.println("Server started on "+ip+":"+port+"!");

		
		on(new JRouter("echo"){			
			public void request(Request req,Response res){
				res.send(req.getParam(0));
			}
		});
		
		on(new JRouter("exit"){			
			public void request(Request req,Response res){				
				try {
					req.connection.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public static void main(String[] args){
		new Main();
	}
	
}
