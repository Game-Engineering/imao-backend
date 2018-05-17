package de.hsmannheim.ss18.gae.imao.model;

public enum EMoeglicheMails {
	LOB("Gut gemacht. Weiter so!"), ABMAHNUNG(
			"Schlimm, schlimm, schlimm. Hören Sie auf leute umzubringen, sonst sind Sie gefeuert!"), GERAET_GEKAUFT(
					"Ich habe ein neues Gerät für Sie gekauft."), DEFAULT_MAIL(
							"AAAAAAAAAAABBBBBBBBBBBBB CCCCCCCCCCCCCCCCCCCCCCCC"
									+ "AAAAAAAAAAABBBBBBBBBBBBB CCCCCCCCCCCCCCCCCCCCCCCC"
									+ "AAAAAAAAAAABBBBBBBBBBBBB CCCCCCCCCCCCCCCCCCCCCCCC"
									+ "AAAAAAAAAAABBBBBBBBBBBBB CCCCCCCCCCCCCCCCCCCCCCCC"
									+ "AAAAAAAAAAABBBBBBBBBBBBB CCCCCCCCCCCCCCCCCCCCCCCC"
									+ "AAAAAAAAAAABBBBBBBBBBBBB CCCCCCCCCCCCCCCCCCCCCCCC"
									+ "AAAAAAAAAAABBBBBBBBBBBBB CCCCCCCCCCCCCCCCCCCCCCCC"
									+ "AAAAAAAAAAABBBBBBBBBBBBB CCCCCCCCCCCCCCCCCCCCCCCCD");

	private String mailText;

	EMoeglicheMails(String mailText) {

		this.mailText = mailText;
	}

	public String getMailText() {
		return mailText;
	}
}
