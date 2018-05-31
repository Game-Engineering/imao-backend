package de.hsmannheim.ss18.gae.imao.model;


public class InterviewFrage {


	private String frage;
	private Antwort[] antworten;
	

	
	public InterviewFrage(String frage, Antwort[] antworten) {
		this.frage =frage;
		this.antworten=antworten;
	}
	
	public String getFrage() {
		return this.frage;
	}
	
	public Antwort[] getAntworten() {
		return this.antworten;
	}

	static class Antwort{
		private String antwort;
		private int punkte;
		
		public Antwort(String antwort,int punkte) {
			this.antwort=antwort;
			this.punkte=punkte;
		}
		
		public String getAntwort() {
			return this.antwort;
		}
		
		public int getPunkte() {
			return this.punkte;
		}
	}
	
}
