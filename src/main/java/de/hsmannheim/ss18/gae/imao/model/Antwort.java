package de.hsmannheim.ss18.gae.imao.model;

/**
 * Wird verwendet für Pressekonferenz und fürs Interview
 * eine Antwort gehört zu einer Frage und gibt eine gewisse Punktzahl, 
 * welche sich auf den Ruf auswirkt.
 * @author lange
 *
 */
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
