package com.ssau.streamingservice;

import com.ssau.streamingservice.services.AcceptingService;
import com.ssau.streamingservice.services.StreamingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

//@SpringBootApplication
public class AcceptingServiceApplication {

	public static void main(String[] args) throws IOException {
		//SpringApplication.run(AcceptingServiceApplication.class, args);
		AcceptingService.startAccept(8081);
	}

}
