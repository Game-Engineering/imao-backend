package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SpielrundeWirtschaft extends Spielrunde {
	private Aufgabe aufgabe = null;

	public SpielrundeWirtschaft(int runde, Manager manager, Arzt arzt) {
		super(runde, manager, arzt);
		erzeugeNeueRunde();
	}

	@Override
	protected void erzeugeNeueRunde() {
		nachricht = "Runde " + runde + " wurde gestartet.";
		long neuerRuf = manager.getRuf();
		if (aufgabe != null) {
			if (!aufgabe.isErledigt()) {
				neuerRuf -= aufgabe.getRufschaden();
			}
		}
		manager.setBudget(1000);
		manager.setRuf(neuerRuf);
		aufgabe = new Aufgabe();
	}

	public String sendeMail(String ID) {
		String absender;
		String mailInhalt;
		absender = "" + manager.vorname + " " + manager.nachname;
		switch (ID.toUpperCase()) {
		case "LOB":
			mailInhalt = EMoeglicheMails.LOB.getMailText();
			break;
		case "ABMAHNUNG":
			mailInhalt = EMoeglicheMails.ABMAHNUNG.getMailText();
			if (EAufgaben.ARZT_ABMAHNEN.equals(aufgabe.getAufgabe())) {
				aufgabe.erledigt();
			}
			break;
		case "GERAET_GEKAUFT":
			mailInhalt = EMoeglicheMails.GERAET_GEKAUFT.getMailText();
			break;
		default:
			mailInhalt = EMoeglicheMails.DEFAULT_MAIL.getMailText();
		}

		System.out.println(absender + ", " + mailInhalt);
		Mail mail = new Mail(absender, mailInhalt);
		manager.sendeMail(mail);
		if (arzt != null) {
			arzt.erhalteMail(mail);
		}
		return mail.toString();

	}

	public String haltePressekonferenz() {
		manager.setRuf(manager.getRuf() + 10);
		if (EAufgaben.PRESSEKONFERENZ.equals(aufgabe.getAufgabe())) {
			aufgabe.erledigt();
		}
		return "Eine Pressekonferenz wurde gehalten Ihr Ruf ist um 10 Gestiegen";
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("nachricht", nachricht);
		objectNode.put("budget", manager.getBudget());
		objectNode.put("ruf", manager.getRuf());
		objectNode.put("runde", runde);
		objectNode.put("aufgabe", aufgabe.getAufgabe().name());

		return objectNode.toString();
	}

	public Aufgabe getAufgabe() {
		return aufgabe;
	}

}
