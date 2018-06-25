package de.hsmannheim.ss18.gae.imao.model.enums;

/**
 * 
 * enum für E-Mail texte
 *
 */
public enum EMoeglicheMails {
	LOB("Sehr geehrter Arzt,\n\n" + "Wir sind mit ihrer Arbeit in unserem Camp sehr zufrieden.\n"
			+ "Die Quote der geheilten Patienten entspricht unseren Vorstellungen, machen Sie so weiter.\n\n"
			+ "Mit freundlichen Grüßen\n" + "IMAO Manager"), 
	ABMAHNUNG("Sehr geehrter Arzt,\n\n"
			+ "aufgrund der vielen Fehldiagnosen und Todesfällen bleibt uns nichts anderes übrig, "
			+ "als Ihnen eine Abmahnung zu erteilen. Die Nachforschungen haben ergeben, "
			+ "dass es sich bei diesen Fehlern fast ausschließlich um ärztliches Versagen handelt, "
			+ "dies können wir nicht ohne Folgen lassen. Diese E-Mail ist eine rechtsgültige Abmahnung "
			+ "und wir erwarten eine sofortige Besserung Ihrer Arbeitsergebnisse.\n\n"
			+ "Mit freundliche Grüßen,\n" + "das Management von IMAO")
	// ,GERAET_GEKAUFT( "Ich habe ein neues Gerät für Sie gekauft.")
	;

	private String mailText;

	EMoeglicheMails(String mailText) {

		this.mailText = mailText;
	}

	public String getMailText() {
		return mailText;
	}
}
