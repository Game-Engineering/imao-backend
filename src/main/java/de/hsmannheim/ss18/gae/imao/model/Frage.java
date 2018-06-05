package de.hsmannheim.ss18.gae.imao.model;

public class Frage {

	private String frage;
	private Antwort[] antworten;
	

	
	public Frage(String frage, Antwort[] antworten) {
		this.frage =frage;
		this.antworten=antworten;
	}
	
	public String getFrage() {
		return this.frage;
	}
	
	public Antwort[] getAntworten() {
		return this.antworten;
	}
	
	
}
