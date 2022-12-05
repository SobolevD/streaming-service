package com.ssau.streamingservice;

import com.ssau.streamingservice.services.StreamingService;

import java.io.IOException;

//@SpringBootApplication
public class StreamingServiceApplication {

	public static void main(String[] args) throws IOException {
		//SpringApplication.run(StreamingServiceApplication.class, args);
		StreamingService.startWebcamStreaming(8081);
	}

}
