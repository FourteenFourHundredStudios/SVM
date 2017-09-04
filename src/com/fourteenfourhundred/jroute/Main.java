package com.fourteenfourhundred.jroute;


public class Main extends JRouteServer{


	
	public Main(){
		
		start("localhost",8090);
		
		
		
	}

	public void onServerStart(){
		System.out.println("Server started on "+ip+":"+port+"!");

		
		on(new JRouter("login"){			
			public void request(Request req,Response res){
				res.send("hey");
			}
		});
		
	}
	
	public static void main(String[] args){
		new Main();
	}
	
}
