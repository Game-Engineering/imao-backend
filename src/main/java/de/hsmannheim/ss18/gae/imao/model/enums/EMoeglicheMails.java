package de.hsmannheim.ss18.gae.imao.model.enums;

/**
 * 
 * enum für E-Mail texte
 *
 */
public enum EMoeglicheMails {
	LOB("Gut gemacht. Weiter so!"), ABMAHNUNG("Sehr geehrter Arzt,\n"
			+ "aufgrund der vielen Fehldiagnosen und Todesfällen bleibt uns nichts anderes übrig, als Ihnen eine Abmahnung zu erteilen. "
			+ "Die Nachforschungen haben ergeben, dass es sich bei diesen Fehlern fast ausschließlich um Arztfehler handelt, dies können wir nicht ohne Folgen lassen. "
			+ "Diese E-Mail ist eine rechtsgültige Abmahnung und eine sofortige Besserung Ihres Handeln wird unsererseits erwaret.\n"
			+ "Mit freundliche Grüßen,\n\n" + "das Management von IMAO")
//	,GERAET_GEKAUFT(	"Ich habe ein neues Gerät für Sie gekauft.")
	;

	private String mailText;

	EMoeglicheMails(String mailText) {

		this.mailText = mailText;
	}

	public String getMailText() {
		return mailText;
	}
}
