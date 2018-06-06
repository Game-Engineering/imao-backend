package de.hsmannheim.ss18.gae.imao.model.wirtschaft;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Mail {
	private String absender;
	private String betreff;
	private String mailInhalt;

	public Mail(String absender, String betreff, String mailInhalt) {
		super();
		this.absender = absender;
		this.betreff = betreff;
		this.mailInhalt = mailInhalt;
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("absender", this.absender);
		objectNode.put("betreff", this.betreff);
		objectNode.put("mailInhalt", this.mailInhalt);

		return objectNode.toString();
	}

}
