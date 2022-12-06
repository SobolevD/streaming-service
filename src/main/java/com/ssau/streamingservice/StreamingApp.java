package com.ssau.streamingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class StreamingApp {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(StreamingApp.class, args);
	}

}
