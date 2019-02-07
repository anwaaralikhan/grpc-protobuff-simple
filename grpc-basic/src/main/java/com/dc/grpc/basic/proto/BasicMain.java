package com.dc.grpc.basic.proto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import com.dc.grpc.basic.Simple.SimpleMessage;

public class BasicMain {
	public static void main(String[] args) {
		System.out.println("Basic Project using Google Proto Buffers");	
		SimpleMessage.Builder builder = SimpleMessage.newBuilder();
		
		// simple fields
		builder
			.setId(33)
			.setBasic(true)
			.setName("Basic Name using GRPC");
		
		// repeated fields
		builder.addBasicList(1);
		builder.addBasicList(11);
		builder.addBasicList(111);
		
		builder.addAllBasicList(Arrays.asList(44,55,666));
		System.out.println(builder.toString());

		// get final object
		SimpleMessage message = builder.build();
		System.out.println("Id ::" +message.getId());
		System.out.println("Name :: " +message.getId());
		
		// write protocol buffers binary to a file
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream("I:\\LOGS\\GRPC\\simple_message.bin");
			message.writeTo(outputStream);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream("I:\\LOGS\\GRPC\\simple_message.bin");
			SimpleMessage messageFromFile = SimpleMessage.parseFrom(inputStream);
			System.out.println("Message from file : "+ messageFromFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		// send as byte array
		byte[] bytes = message.toByteArray();
		
	}
}
