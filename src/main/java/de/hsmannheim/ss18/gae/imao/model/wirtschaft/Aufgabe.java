package de.hsmannheim.ss18.gae.imao.model.wirtschaft;

import java.util.Random;

import de.hsmannheim.ss18.gae.imao.model.enums.EAufgaben;

public class Aufgabe {
	private EAufgaben aufgabe = null;
	private boolean erledigt = false;
	private int rufschaden = 0;
	private Mail aufgabenMail;

	public Aufgabe(int runde) {
		neueAufgabe(runde);
	}

	/**
	 * erstelle zufällig eine neue Aufgabe für den Manager
	 * this.aufgabe enthält neue Aufgabe. 
	 * Es wird eine Email an den Manager gesendet.
	 * @param runde
	 */
	private void neueAufgabe(int runde) {
		switch (new Random().nextInt(7)) {
		case 0:
			aufgabe = EAufgaben.INTERVIEW;
			rufschaden = 10;
			break;
		case 1:
			aufgabe = EAufgaben.PRESSEKONFERENZ_DUERRE;
			rufschaden = 10;
			break;
		case 5:
			aufgabe = EAufgaben.PRESSEKONFERENZ_GUTEARBEIT;
			rufschaden = 0;
			break;
		case 6:
			aufgabe = EAufgaben.PRESSEKONFERENZ_VIELETOTE;
			rufschaden = 10;
			break;
//		case 2:
//			aufgabe = EAufgaben.GREAET_KAUFEN;
//			rufschaden = 10;
//			break;
		case 3:
			aufgabe = EAufgaben.SPONSOR_ANWERBEN;
			rufschaden = 10;
			break;
		case 4:
			aufgabe = EAufgaben.ARZT_ABMAHNEN;
			rufschaden = 10;
			break;
		default:
			aufgabe = EAufgaben.INTERVIEW;
			rufschaden = 10;
		}
		sendeAufgabenMail(runde);
	}

	private void sendeAufgabenMail(int runde) {
		aufgabenMail = new Mail("CHEF", "Aufgabe Runde "+runde,aufgabe.getAufgabeText());
	}

	public void erledigt() {
		this.erledigt = true;
	}

	public boolean isErledigt() {
		return erledigt;
	}

	public int getRufschaden() {
		return rufschaden;
	}

	public EAufgaben getAufgabe() {
		return aufgabe;
	}

	public Mail getAufgabenMail() {
		return aufgabenMail;
	}

}
