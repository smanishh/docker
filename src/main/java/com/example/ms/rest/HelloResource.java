package com.example.ms.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {
	

	@RequestMapping(value = "/pdfs/{param}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPdf(@PathVariable String param) {
		
		if("1".equals(param)) {
			Path pdfPath = Paths.get("C:\\Users\\ms\\Desktop\\docker.pdf");
			byte[] pdf = null;
			try {
				pdf = Files.readAllBytes(pdfPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.OK).body(pdf);
		} else if("2".equals(param)) {
			//HttpStatus.valueOf(02)
			HttpStatus str = HttpStatus.INTERNAL_SERVER_ERROR;
			//System.out.println(typeof(HttpStatus.INTERNAL_SERVER_ERROR));
			System.out.println(HttpStatus.INTERNAL_SERVER_ERROR);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} else if ("3".equals(param)) {
			return ResponseEntity.status(HttpStatus.valueOf(422)).build();
		}
		return null;
	}
	
	@GetMapping
	public String hello() {
		return "Hello Manish!!";		
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String getProduct() {
		String data = "[{\"id\": \"1\",\"name\": \"Honey\"},{\"id\": \"2\",\"name\": \"Almond\"}]";
		System.out.println("data: " + data);
		return data;
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String postProduct() {
		String data = "{\"id\":\"3\",\"name\":\"Ginger\"}";
		System.out.println("data: " + data);
		return data;
	}
	
}


