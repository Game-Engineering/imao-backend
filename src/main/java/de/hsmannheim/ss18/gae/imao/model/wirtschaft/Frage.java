package de.hsmannheim.ss18.gae.imao.model.wirtschaft;

/**
 *  Wird verwendet für Pressekonferenz und fürs Interview. 
 *  Zu einer Frage gehören eine gewisse Anzahl an Antworten
 * 
 * @author lange
 *
 */
class Frage {

	private String frage;
	private Antwort[] antworten;

	/**
	 *
	 * @param frage
	 * @param antworten
	 */
	Frage(String frage, Antwort[] antworten) {
		this.frage = frage;
		this.antworten = antworten;
	}

	String getFrage() {
		return this.frage;
	}

	Antwort[] getAntworten() {
		return this.antworten;
	}

}
