package de.hsmannheim.ss18.gae.imao.model;

import java.util.Random;

import de.hsmannheim.ss18.gae.imao.endpunkt.Medizin;

public class Aufgabe {
	private EAufgaben aufgabe = null;
	private boolean erledigt = false;
	private long rufschaden = 0;

	public Aufgabe() {
		neueAufgabe();
	}

	private void neueAufgabe() {
		switch (new Random().nextInt(5)) {
		case 0:
			aufgabe = EAufgaben.INTERVIEW;
			rufschaden = 10;
			break;
		case 1:
			aufgabe = EAufgaben.PRESSEKONFERENZ;
			rufschaden = 10;
			break;
		case 2:
			aufgabe = EAufgaben.GREAET_KAUFEN;
			rufschaden = 10;
			break;
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
		sendeAufgabenMail();
	}

	private void sendeAufgabenMail() {
		Medizin.getManager().erhalteMail(new Mail("CHEF", aufgabe.getAufgabeText()));
	}

	public void erledigt() {
		this.erledigt = true;
	}

	public boolean isErledigt() {
		return erledigt;
	}

	public long getRufschaden() {
		return rufschaden;
	}

	public EAufgaben getAufgabe() {
		return aufgabe;
	}

}
