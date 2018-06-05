package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class StatusToString {

	
	
	public static String fehler(String nachricht) {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("status", "fehler");
		objectNode.put("nachricht", nachricht);
		
		return objectNode.toString();
	}
	
	public static String ok(String nachricht) {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("status", "ok");
		objectNode.put("nachricht", nachricht);
		
		return objectNode.toString();
	}
	
	public static String notReady(String nachricht) {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("status", "notReady");
		objectNode.put("nachricht", nachricht);
		
		return objectNode.toString();
	}
	
	
}
