package com.fourteenfourhundred.jroute;

import java.util.Date;

public class Main extends JRouteServer{


	
	public Main(){
		
		start("localhost",8090);
		
		
	}

	public void onServerStart(){
		System.out.println("Server started on "+ip+":"+port+"!");

		
		on(new JRouter("/"){			
			public void get(Request req,Response res){
				
				/*
				BasicDBObject query = new BasicDBObject("username", "marc");
				DBObject myDoc = DBManager.db.getCollection("users").findOne(query);

				res.send((String) myDoc.get(req.urlParams.get("s")));*/
				res.sendFile("index.html");
			}
		});
		
		
		on(new JRouter("/post"){			
			public void post(Request req,Response res){
				System.out.println("post on /post "+req.urlParams.get("username"));
			
				res.send("math");
			}
			public void get(Request req,Response res){
				System.out.println("get on /post "+req.urlParams.get("username"));
			
				res.send("math");
			}
		});
		
		
		on(new JRouter("/cookie"){
			public void get(Request req,Response res){
				res.setCookie("cookie!", "chocolate chip", new Date());
				res.send("You made it, now u have a cookie!");
			}
		});
		
	}
	
	public static void main(String[] args){
		new Main();
	}
	
}
