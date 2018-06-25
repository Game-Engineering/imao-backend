package de.hsmannheim.ss18.gae.imao.model.wirtschaft;

/**
 * Wird verwendet für Pressekonferenz und fürs Interview
 * eine Antwort gehört zu einer Frage und gibt eine gewisse Punktzahl, 
 * welche sich auf den Ruf auswirkt.
 * @author lange
 *
 */
class Antwort {
		private String antwort;
		private int punkte;

	/**
	 *
	 * @param antwort
	 * @param punkte
	 */
	Antwort(String antwort, int punkte) {
			this.antwort=antwort;
			this.punkte=punkte;
		}
		
		String getAntwort() {
			return this.antwort;
		}
		
		int getPunkte() {
			return this.punkte;
		}
}
