package de.hsmannheim.ss18.gae.imao.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SpielrundeWirtschaft extends Spielrunde {
	private Aufgabe aufgabe = null;
	private String arztbericht;
	private String budgetbericht;

	public SpielrundeWirtschaft(int runde, Manager manager, Arzt arzt, Aufgabe aufgabe) {
		super(runde, manager, arzt);
		this.aufgabe = aufgabe;
		erzeugeNeueRunde();
	}

	@Override
	protected void erzeugeNeueRunde() {
		nachricht = "Runde " + runde + " wurde gestartet.";
		if (aufgabe != null) {
			if (!aufgabe.isErledigt()) {
				manager.rufVerlust("Nicht erfüllte Aufgabe", aufgabe.getRufschaden());
			}
		}
		aufgabe = new Aufgabe(runde);

		List<Mail> mails = new ArrayList<>();
		mails.add(aufgabe.getAufgabenMail());
		if (runde == 1) {
			mails.add(new Mail(arzt.vorname + ", " + arzt.nachname,"Hilferuf", "Hilfe, ich versinke im Chaos!!!"));

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
		String absender= "" + manager.vorname + " " + manager.nachname;
		String betreff;
		String mailInhalt;
				switch (ID.toUpperCase()) {
		case "LOB":
			mailInhalt = EMoeglicheMails.LOB.getMailText();
			betreff="Lob";
			break;
		case "ABMAHNUNG":
			mailInhalt = EMoeglicheMails.ABMAHNUNG.getMailText();
			betreff="Abmahnung";
			if (EAufgaben.ARZT_ABMAHNEN.equals(aufgabe.getAufgabe())) {
				aufgabe.erledigt();
			}
			break;
		case "GERAET_GEKAUFT":
			mailInhalt = EMoeglicheMails.GERAET_GEKAUFT.getMailText();
			betreff="Gerät gekauft";
			break;
		default:
			mailInhalt = EMoeglicheMails.DEFAULT_MAIL.getMailText();
			betreff="Default";
		}

		System.out.println(absender + ", " +betreff+ ", " + mailInhalt);
		Mail mail = new Mail(absender,betreff, mailInhalt);
		manager.sendeMail(mail);
		if (arzt != null) {
			arzt.erhalteMail(mail);
		}
		return mail.toString();

	}

	public String haltePressekonferenz() {
		manager.rufZuwachs("Pressekonferenz", 10);
		manager.ausgabe("Pressekonferenz", 50);
		if (EAufgaben.PRESSEKONFERENZ.equals(aufgabe.getAufgabe())) {
			aufgabe.erledigt();
		}
		return "Eine Pressekonferenz wurde gehalten. Ihr Ruf ist um 10 gestiegen.";
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

		if (EAufgaben.GREAET_KAUFEN.equals(aufgabe.getAufgabe())) {
			aufgabe.erledigt();
		}
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
