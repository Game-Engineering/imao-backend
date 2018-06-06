package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Interview {

	InterviewPartner[] interviewPartner;

	private int aktuellerInterviewPartner = 0;
	private int punkteFuerAktuellesInterview = 0;
	private int aktuellerFrageIndex = 0;
	private Manager manager;

	public Interview(Manager manager) {
		this.manager = manager;
		createInterviewPartner();
	}

	/**
	 * return ein JSON mit allen verfügbaren InterviewPartnern
	 * @return
	 */
	public String getInterviewParter() {

		ObjectMapper mapper = new ObjectMapper();
		ArrayNode arrayNode = mapper.createArrayNode();

		for (int i = 0; i < interviewPartner.length; i++) {
			if (interviewPartner[i].isVerfuegbar()) {
				ObjectNode objectNode = mapper.createObjectNode();
				objectNode.put("id", interviewPartner[i].getId());
				objectNode.put("name", interviewPartner[i].getName());
				objectNode.put("maxAnsehen", interviewPartner[i].getMaxAnsehen());
				objectNode.put("schwierigkeit", interviewPartner[i].getSchwierigkeit());

				arrayNode.add(objectNode);
			}
		}
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.set("interviewPartner", arrayNode);
		return objectNode.toString();

	}

	/**
	 * Starte ein Interview, bewirkt keine Änderung der Zustände
	 * @param interviewpartnerID
	 * @param status
	 * @return
	 */
	public String startInterview(int interviewpartnerID, String status) {

		for (int i = 0; i < this.interviewPartner.length; i++) {
			if (this.interviewPartner[i].getId() == interviewpartnerID && this.interviewPartner[i].isVerfuegbar()) {
				if (this.aktuellerInterviewPartner == 0) {
					this.aktuellerInterviewPartner = interviewpartnerID;
				}

				ObjectMapper mapper = new ObjectMapper();
				ObjectNode objectNode = mapper.createObjectNode();
				ArrayNode arrayNode = mapper.createArrayNode();

				objectNode.put("name", "interview");
				objectNode.put("partner", this.interviewPartner[i].getName());
				objectNode.put("status", status);
				objectNode.put("frage", this.interviewPartner[i].getFragen()[this.aktuellerFrageIndex].getFrage());

				for (int j = 0; j < this.interviewPartner[i].getFragen()[this.aktuellerFrageIndex]
						.getAntworten().length; j++) {
					arrayNode.add(this.interviewPartner[i].getFragen()[this.aktuellerFrageIndex].getAntworten()[j]
							.getAntwort());
				}

				objectNode.set("antworten", arrayNode);
				return objectNode.toString();
			}
		}
		return "ERROR: ID falsch oder Interviewpartner nicht verfügbar.";
	}

	/**
	 * Antworte auf Aktuelle frage, bewirkt Änderung der Zustände
	 * @param interviewpartnerID
	 * @param antwortID
	 * @return
	 */
	public String getInterview(int interviewpartnerID, int antwortID) {
		String neuerStatus = "unknown";

		if (this.aktuellerInterviewPartner == interviewpartnerID) {
			for (int i = 0; i < interviewPartner.length; i++) {
				if (interviewPartner[i].getId() == interviewpartnerID) {

					// Antwort auswerten
					if (this.interviewPartner[i].getFragen()[this.aktuellerFrageIndex].getAntworten().length > antwortID
							&& antwortID >= 0) {
						switch (this.interviewPartner[i].getFragen()[this.aktuellerFrageIndex].getAntworten()[antwortID]
								.getPunkte()) {
						case -15:
						case -10:
						case -5:
							neuerStatus = "Der Interviewpartner sieht nicht glücklich aus.";
							break;
						case 5:
						case 10:
							neuerStatus = "Der Interviewpartner sieht zufrieden aus.";
							break;
						case 15:
						case 20:
							neuerStatus = "Der Interviewpartner scheint sehr zufrieden mit der Antwort zu sein.";
							break;
						default:
							neuerStatus = "Der Interviewpartner lies sich keine Reaktion anmerken.";
							break;
						}
						this.punkteFuerAktuellesInterview += this.interviewPartner[i]
								.getFragen()[this.aktuellerFrageIndex].getAntworten()[antwortID].getPunkte();
					} else {
						return "ERROR: Frage nicht verfügbar";
					}
					this.aktuellerFrageIndex++;

					// Neue Frage oder Interview Beenden
					if (this.aktuellerFrageIndex < this.interviewPartner[i].getFragen()[this.aktuellerFrageIndex]
							.getAntworten().length) {
						return startInterview(interviewpartnerID, neuerStatus);
					} else {

						if (this.punkteFuerAktuellesInterview > 0) {
							this.manager.rufZuwachs("Erfolgreiches Interview", this.punkteFuerAktuellesInterview);
						} else if (this.punkteFuerAktuellesInterview < 0) {
							this.manager.rufVerlust("Schlechtes Interview", this.punkteFuerAktuellesInterview);
						}

						this.aktuellerFrageIndex = 0;
						this.aktuellerInterviewPartner = 0;
						this.punkteFuerAktuellesInterview = 0;

						// Über ende des Interviews benachrichtigen
						ObjectMapper mapper = new ObjectMapper();
						ObjectNode objectNode = mapper.createObjectNode();
						ArrayNode arrayNode = mapper.createArrayNode();

						objectNode.put("name", "interview");
						objectNode.put("partner", this.interviewPartner[i].getName());
						objectNode.put("status", neuerStatus);
						objectNode.put("frage", "ENDE");
						objectNode.set("antworten", arrayNode);
						return objectNode.toString();
					}
				}
			}

		} else {
			return "ERROR: Falscher Interviewpartner.";
		}
		return "ERROR: Fehler in getInterview. Ursache unbekannt.";
	}

	/**
	 * Erstelle InterviewPartner
	 */
	private void createInterviewPartner() {
		this.interviewPartner = new InterviewPartner[1];

		// Fragen und Antworten für 1. Interviewpartner
		Antwort[] p1f1a = { new Antwort("Antwort 10P", 10), new Antwort("Antwort 20P", 20),
				new Antwort("Antwort 0P", 0), new Antwort("Antwort -10P", -10) };
		Frage p1f1 = new Frage("Frage 1", p1f1a);

		Antwort[] p1f2a = { new Antwort("Antwort 10P", 10), new Antwort("Antwort 20P", 20),
				new Antwort("Antwort 0P", 0), new Antwort("Antwort -10P", -10) };
		Frage p1f2 = new Frage("Frage 2", p1f2a);

		Antwort[] p1f3a = { new Antwort("Antwort 10P", 10), new Antwort("Antwort 20P", 20),
				new Antwort("Antwort 0P", 0), new Antwort("Antwort -10P", -10) };
		Frage p1f3 = new Frage("Frage 3", p1f3a);

		Antwort[] p1f4a = { new Antwort("Antwort 10P", 10), new Antwort("Antwort 20P", 20),
				new Antwort("Antwort 0P", 0), new Antwort("Antwort -10P", -10) };
		Frage p1f4 = new Frage("Frage 4", p1f4a);

		Antwort[] p1f5a = { new Antwort("Antwort 10P", 10), new Antwort("Antwort 20P", 20),
				new Antwort("Antwort 0P", 0), new Antwort("Antwort -10P", -10) };
		Frage p1f5 = new Frage("Frage 5", p1f5a);

		Frage[] InPar1Fragen = { p1f1, p1f2, p1f3, p1f4, p1f5 };
		this.interviewPartner[0] = new InterviewPartner(idCount(), "InPar1", 10, 1, InPar1Fragen);

	}

	private static int idCount = 1;

	private static int idCount() {
		return idCount++;
	}

}
