package de.hsmannheim.ss18.gae.imao.model.enums;

/**
 * 
 * enum für E-Mail texte
 *
 */
public enum EMoeglicheMails {
	LOB("Gut gemacht. Weiter so!"), ABMAHNUNG(
			"Schlimm, schlimm, schlimm. Hören Sie auf, Leute umzubringen, sonst sind Sie gefeuert."), GERAET_GEKAUFT(
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
