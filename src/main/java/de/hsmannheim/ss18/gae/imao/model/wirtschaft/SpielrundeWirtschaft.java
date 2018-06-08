package de.hsmannheim.ss18.gae.imao.model.wirtschaft;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.hsmannheim.ss18.gae.imao.model.Spielrunde;
import de.hsmannheim.ss18.gae.imao.model.enums.EAufgaben;
import de.hsmannheim.ss18.gae.imao.model.enums.EMoeglicheMails;
import de.hsmannheim.ss18.gae.imao.model.medizin.Arzt;
import de.hsmannheim.ss18.gae.imao.model.medizin.Untersuchungsmethode;

public class SpielrundeWirtschaft extends Spielrunde {
	private Aufgabe aufgabe = null;
	private String arztbericht;
	private String budgetbericht;
	private Pressekonferenz pressekonferenz;

	public SpielrundeWirtschaft(int runde, Manager manager, Arzt arzt, Aufgabe aufgabe) {
		super(runde, manager, arzt);
		this.aufgabe = aufgabe;
		erzeugeNeueRunde();
	}

	public Pressekonferenz getPressekonferenz() {
		return this.pressekonferenz;
	}
	
	@Override
	protected void erzeugeNeueRunde() {
		nachricht = "Runde " + runde + " wurde gestartet.";
		
		//Pressekonferenzen nicht mehr verfügbar
		if (this.pressekonferenz == null) {
			this.pressekonferenz = new Pressekonferenz(this);
		}
		for (int i = 0; i < pressekonferenz.getPressekonferenzThemen().length; i++) {
			this.pressekonferenz.getPressekonferenzThemen()[i].setVerfuegbar(false);
		}
		
		if (aufgabe != null) {
			if (!aufgabe.isErledigt()) {
				manager.rufVerlust("Nicht erfüllte Aufgabe", aufgabe.getRufschaden());
			}
		}
		aufgabe = new Aufgabe(runde, pressekonferenz, this.getManager().getInterview(), this.getManager().getSponsoren());

		List<Mail> mails = new ArrayList<>();
		mails.add(aufgabe.getAufgabenMail());
		if (runde == 1) {
			mails.add(new Mail(arzt.vorname + ", " + arzt.nachname, "Hilferuf", "Sehr geehrter IMAO Manager,\n"
					+ "Im Camp ist Chaos ausgebrochen, wir brauchen Hilfe, bitte senden Sie uns Hilfe in Form von Geld oder Materialien.\n\n"
					+ "Ihr IMAO Arzt"));

		}
		Iterator<Untersuchungsmethode> it = untersuchungsmethoden.iterator();
		while (it.hasNext()) {
			Untersuchungsmethode m = it.next();
			if (m.isFreigeschaltet()) {
				manager.ausgabe(m.getName() + " Unterhalt", m.getUnterhaltsKosten());
			}
		}
		manager.einnahme("Sponsor 1", 10000);
		getBerichteVonLetzterRunde();
		manager.rundenanfang(mails);

	}

	public String sendeMail(String ID) {
		String absender = "" + manager.vorname + " " + manager.nachname;
		String betreff;
		String mailInhalt;
		switch (ID.toUpperCase()) {
		case "LOB":
			mailInhalt = EMoeglicheMails.LOB.getMailText();
			betreff = "Lob";
			break;
		case "ABMAHNUNG":
			mailInhalt = EMoeglicheMails.ABMAHNUNG.getMailText();
			betreff = "Abmahnung";
			if (EAufgaben.ARZT_ABMAHNEN.equals(aufgabe.getAufgabe())) {
				aufgabe.erledigt();
			}
			break;
//		case "GERAET_GEKAUFT":
//			mailInhalt = EMoeglicheMails.GERAET_GEKAUFT.getMailText();
//			betreff = "Gerät gekauft";
//			break;
		default:
			mailInhalt = "Mail konnte nicht gesendet werden.";
			betreff = "Fehler";
			
		}

		System.out.println(absender + ", " + betreff + ", " + mailInhalt);
		Mail mail = new Mail(absender, betreff, mailInhalt);
		manager.sendeMail(mail);
		if (arzt != null) {
			arzt.erhalteMail(mail);
		}
		return mail.toString();

	}

	public String startePressekonferenz() {
		return this.pressekonferenz.startePressekonferenz();
	}

	public String antwortePressekonferenz(int id) {
		return this.pressekonferenz.antwortePressekonferenz(id);
	}

	public void beendePressekonferenz(int rufzuwachs) {
		if (rufzuwachs > 0) {
			manager.rufZuwachs("Gute Pressekonferenz", rufzuwachs);
		}else if(rufzuwachs < 0) {
			manager.rufVerlust("Schlechte Pressekonferenz", rufzuwachs*-1);
		}
		
		aufgabe.erledigt();
	}

	public List<Untersuchungsmethode> kaufeGeraet(String geraet) {
		Iterator<Untersuchungsmethode> iterator = untersuchungsmethoden.iterator();
		while (iterator.hasNext()) {
			Untersuchungsmethode methode = iterator.next();
			if (geraet.equals(methode.getName())) {

				manager.ausgabe(methode.getName() + " gekauft", methode.getAnschaffungsKosten());
				methode.setFreigeschaltet(true);
			}
		}

//		if (EAufgaben.GREAET_KAUFEN.equals(aufgabe.getAufgabe())) {
//			aufgabe.erledigt();
//		}
		return untersuchungsmethoden;
	}

	private void getBerichteVonLetzterRunde() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode budget = mapper.createObjectNode();
		budget.put("einnahmen", manager.getEinnahmen().toString());
		budget.put("ausgaben", manager.getAusgaben().toString());
		budget.put("rufzuwachs", manager.getRufzuwachs().toString());
		budget.put("rufverlust", manager.getRufverlust().toString());
		budget.put("Rufbilanz", manager.getRufbilanz());
		budget.put("Gesamtausgaben", manager.getBudgetbilanz());
		budgetbericht = budget.toString();

		ObjectNode medBericht = mapper.createObjectNode();
		medBericht.put("ausgaben", arzt.getAusgaben().toString());
		medBericht.put("rufzuwachs", arzt.getRufzuwachs().toString());
		medBericht.put("rufverlust", arzt.getRufverlust().toString());
		medBericht.put("erfolgreich behandelte Patienten", 8);
		medBericht.put("nicht erfolgreich behandelte Patienten", 4);
		medBericht.put("nicht behandelte Patienten", 6);
		medBericht.put("Rufbilanz", -60);
		medBericht.put("Gesamtausgaben", 120);
		arztbericht = medBericht.toString();
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

	public String getArztbericht() {
		return arztbericht;
	}

	public String getBudgetbericht() {
		return budgetbericht;
	}

	public Manager getManager() {
		return manager;
	}

}
