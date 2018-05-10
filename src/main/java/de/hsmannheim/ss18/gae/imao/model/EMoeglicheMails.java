package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public enum EMoeglicheMails {
	LOB("Gut gemacht. Weiter so!"),
	ABMAHNUNG("Schlimm, schlimm, schlimm. Hören Sie auf leute umzubringen, sonst sind Sie gefeuert!"),
	GERAET_GEKAUFT("Ich habe ein neues Gerät für Sie gekauft."), 
	DEFAULT_MAIL("JVBERi0xLjQNJeLjz9MNCjQgMCBvYmoNPDwvTGluZWFyaXplZCAxL0wgMTUzODA4L08gNi9FIDE0"
					+ "OTgxNC9OIDEvVCAxNTM2MDkvSCBbIDc3NiAxODFdPj4NZW5kb2JqDSAgICAgICAgICAgICAgICAg"
					+ "DQp4cmVmDQo0IDI0DQowMDAwMDAwMDE2IDAwMDAwIG4NCjAwMDAwMDA5NTcgMDAwMDAgbg0KMDAw"
					+ "MDAwMTAxNyAwMDAwMCBuDQowMDAwMDAxMjUyIDAwMDAwIG4NCjAwMDAwMDEyODIgMDAwMDAgbg0K");

	private String mailText;

	EMoeglicheMails(String mailText) {

		this.mailText = mailText;
	}

	public String getMailText() {
		return mailText;
	}
}
