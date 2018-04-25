package de.hsmannheim.ss18.gae.imao.model;

public enum EMoeglicheMails {
	MAIL_1(1, "Lob"), MAIL_2(2, "Abmahnung"), MAIL_3(3, "Gerät wurde gekauft");

	private String mailText;
	private int id;

	EMoeglicheMails(int id, String mailText) {
		this.id = id;
		this.mailText = mailText;
	}

	public int getId() {
		return id;
	}

	public String getMailText() {
		return mailText;
	}
}
