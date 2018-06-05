package de.hsmannheim.ss18.gae.imao.model;

public class Antwort {
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
