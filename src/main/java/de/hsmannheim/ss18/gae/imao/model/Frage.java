package de.hsmannheim.ss18.gae.imao.model;

/**
 *  Wird verwendet für Pressekonferenz und fürs Interview. 
 *  Zu einer Frage gehören eine gewisse Anzahl an Antworten
 * 
 * @author lange
 *
 */
public class Frage {

	private String frage;
	private Antwort[] antworten;

	public Frage(String frage, Antwort[] antworten) {
		this.frage = frage;
		this.antworten = antworten;
	}

	public String getFrage() {
		return this.frage;
	}

	public Antwort[] getAntworten() {
		return this.antworten;
	}

}
