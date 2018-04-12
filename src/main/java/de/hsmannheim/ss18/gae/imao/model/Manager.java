package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Manager extends Person {

	public Manager() {
		super();
	}

	public Manager(String vorname, String nachname) {
		super(vorname, nachname);
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("vorname", this.vorname);
		objectNode.put("nachname", this.nachname);

		return objectNode.toString();
	}
}
