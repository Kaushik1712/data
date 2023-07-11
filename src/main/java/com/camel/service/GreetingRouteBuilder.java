package com.camel.service;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class GreetingRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:greet").process(exchange -> {
			String name = exchange.getIn().getBody(String.class);
			String greeting = generateGreeting(name);
			exchange.getMessage().setBody(greeting);
		}).convertBodyTo(String.class) 
				.to("direct:result");

		from("direct:result").log("Response: ${body}");
	}
	private String generateGreeting(String name) {
	    int hour = java.time.LocalTime.now().getHour();
	    String greeting;
	    if (hour >= 5 && hour < 12) {
	        greeting = "Good morning";
	    } else if (hour >= 12 && hour < 18) {
	        greeting = "Good afternoon";
	    } else if (hour >= 18 && hour < 24) {
	        greeting = "Good evening";
	    } else {
	        greeting = "Good night";
	    }
	    return greeting + " :) " + name.trim().replaceAll("[\"']", ""); 
	}

}
