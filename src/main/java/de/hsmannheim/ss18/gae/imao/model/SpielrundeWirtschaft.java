package de.hsmannheim.ss18.gae.imao.model;

import de.hsmannheim.ss18.gae.imao.endpunkt.Spiel;

public class SpielrundeWirtschaft extends Spielrunde {
	private static final String AUFGABE_1 = "Aufgabe1: Halten Sie eine Pressekonferenz";
	private static final String AUFGABE_2 = "Aufgabe2: Geben Sie ein Interview";
	private static final String AUFGABE_3 = "Aufgabe3: Senden Sie eine Abmahmung an den Arzt";

	private String aufgabe;

	public SpielrundeWirtschaft(int runde) {
		super(runde);
		erzeugeNeueRunde();
	}

	@Override
	protected void erzeugeNeueRunde() {
		// Spiel.getManager().setBudget(1000);
		// Spiel.getManager().setRuf(1000);

	}

	public String sendeMail(int ID) {
		String absender;
		String mailInhalt;
		if (ID == 1) {
			absender = "" + Spiel.getManager().vorname + " " + Spiel.getManager().vorname;
			mailInhalt = EMoeglicheMails.valueOf("" + ID).getMailText();
			System.out.println(absender + ", " + mailInhalt);
			Mail mail = new Mail(absender, mailInhalt);
			Spiel.getManager().sendeMail(mail);
			if (Spiel.getArzt() != null) {
				Spiel.getArzt().erhalteMail(mail);
			}
			return mail.toString();
		} else {
			return "Mail nicht gesendet";
		}
	}
}
