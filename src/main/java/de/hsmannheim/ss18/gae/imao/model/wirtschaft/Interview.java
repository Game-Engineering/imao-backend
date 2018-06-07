package de.hsmannheim.ss18.gae.imao.model.wirtschaft;

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
		this.interviewPartner = new InterviewPartner[3];

		// Fragen und Antworten für 1. Interviewpartner
		Antwort[] p1f1a = { new Antwort("Wir haben IMAO von Grund auf aufgebaut und es uns zum Ziel gemacht das Krisengebiet in Lybien zu unterstützen, dank unserer fantastischen Ärzte und der Unterstützung unsrer Sponsoren ist uns dies sehr gelungen.", 2), new Antwort("Wir haben Ärzte eingestellt, die ihre Arbeit ernst nehmen und dieser sehr erfolgreich nachgehen, dafür sind wir ser dankbar.", 1),
				new Antwort("So genau können wir uns das auch nich erklären, wir hoffen es geht so weiter.", -1), new Antwort("Wenn wir das nur wüssten, dann könnten wir so weiter machen.", -2) };
		Frage p1f1 = new Frage("Ihre Organisaton hat in den letzten Wochen viele Menschenleben gerettet, wie erklären Sie sich diesen großen Erfolg?", p1f1a);

		Antwort[] p1f2a = { new Antwort("Wir werden genaue Forschungen anstellen und hrausfinden, wie wir unser Standards aufrecht erhalten und unsere Versorgungsmögichkeiten verbessern können.", 2), new Antwort("Vorerst ist unser Plan nichts an den bestehenden Systemen zu verändern, da es bisher gut funktioniert hat.", 1),
				new Antwort("Da momentan keine finaziellen Mittel zu Verfügung stehen sind uns die Hände gebunden, was die Weiterentwicklung es des Camps angeht.", -1), new Antwort("Die Weiterentwicklung und finanzielle Unterstützung des Camps steht momentan nicht auf unserer Prioritäten Liste.", -2) };
		Frage p1f2 = new Frage("Wie wird es Ihnen möglich sein diese Standards aufrecht zu erhalten, wenn Sie sich nicht sicher sind, wie genau der Erfolg entstanden ist?", p1f2a);

		Antwort[] p1f3a = { new Antwort("Wir finanzieren uns durch Sponsoren, die bereit sind für einen bestimmten Zeitraum die Organisation zu fördern und zu unterstützen. Wir sind dankbar für jeden dieser Sponsoren, egal in welcher Größenordnung die Unterstützung ausfällt.", 2), new Antwort("Die Oranisation wird wirtschaftlich von mir geleitet und Gelder die wir als Spenden empfangen werden entsprechend der Bedürfnisse eingesetzt.", 1),
				new Antwort("Die Finanzierung läuft über Spendengelder die dann in verschiedene Bereiche fließen.", -1), new Antwort("Wie genau die Finanzierung dieser Organisation funktioniert soll nicht Gegenstand des heutigen Interviews sein.", -2) };
		Frage p1f3 = new Frage("Können Sie uns erklären, wie die Finanzierung der Forschung und die Unterstützung des Camps funktioniert?", p1f3a);

		Frage[] InPar1Fragen = { p1f1, p1f2, p1f3};
		this.interviewPartner[0] = new InterviewPartner(idCount(), "Gundel Gaus", 6, 1, InPar1Fragen);
		
		//Partner 2
		//Partner 2
				Antwort[] p2f1a = { new Antwort("IMAO ist dafür da Menschen in Krisengebieten zu unterstützen und sie ärztlich zu versorgen. Da geht es vornemlich darum so zu unterstützen, dass man Krankheiten möglichst vollständig bekämpft, allerdings sind unsere Möglichkeiten hier aufgrund der Lage sehr eingeschränkt.",2), new Antwort("Wir werden in Krisengebieten tötig, wo medizinische unterstützung nötig wid, allerdings ist es nicht ganz so einfach, wie man es sich evtl vorstellt, so bringt die geographische Lage ganz eigene Herausforderungen mit sich.",1), new Antwort("Wir bieten medzinische Hilfe, wo diese fehlt, das können Krisengebiete aller Art sein.",-1), new Antwort("In Kriengebieten gibt es Nöte, denen begegnen wir,so weit es uns möglich ist.",-2)};
				Frage p2f1 = new Frage("Können Sie uns erklären, was das Ziel Ihrer Organisation ist?", p2f1a);
				
				Antwort[] p2f2a = { new Antwort("Im Grenzgebiet zu Lybien haben wir ein Arzt-Zelt aufgebaut, in diesem werden Patienten behandelt, die sich in welcher Form auch immer in einem hilfsbedürftigen Zustand befinden. Unser Arzt vr Ort ist darauf spezialisiert gerade gebietstypische Krankheiten zu erkennen und erfolgreich zu behandeln.",2), new Antwort("Ein Arzt bietet die Möglichkeit, dass er von sowohl Einhiemischen, als auch anders in die Krisengebiete gelangte Personen aufgesucht werden kann und diese dann so gut es geht behandelt.",1), new Antwort("Es erfolgt eine Behandlung durch einen Arzt, der in den ausgewählten Krisengebieten tätig ist, um dort Menschenleben zu retten.",-1), new Antwort("Naja wie das bei Ärzten so funktioniert, der Patient kommt rein und geht hoffentlich gesund wieder raus.",-2)};
				Frage p2f2 = new Frage("Wie kann man sich diese Versorgung in den Krisengebieten vorstellen?", p2f2a);
				
				Antwort[] p2f3a = { new Antwort("Wir stellen unserem Arzt natürlich alles zur Verfügung, was er für die grundsätzliche Diagnose brauchen, hierbei ist natürlich vor allem die Anamnese und das persönliche Gespräch mit den Patienten von großer Bedeutung, aber auch normale Untersuchungsmöglichkeiten sind gegeben.",2), new Antwort("Die Ausrüstung in einem Krisengebiet entspricht natürlich nicht der, die wir hier vor Ort erwarten würden, entsprich aber dem, was wir zur Diagnse benötigen.",1), new Antwort("In so einem Krisengebiet ist die Ausstattung natürlich nicht so ausgefeilt, wie wir uns das wünschen würden, aber ausreichend.",-1), new Antwort("Wie soll ich das nun sagen? Krisengebiete heißen nicht umsonst so, es ist auch die medizinische Versorgung kritisch und nur den Umständen entsprechend möglich.",-2)};
				Frage p2f3 = new Frage("Ist die Ausrüstung die der Arzt zur Verfügung hat denn ausreichend, um die Behandlungen erfolgreich durchführen zu können?", p2f3a);
				
				Frage[] InPar2Fragen = { p2f1, p2f2, p2f3};
				this.interviewPartner[1] = new InterviewPartner(idCount(), "Rüdiger Strauch", 6, 1, InPar2Fragen);

				
		//Partner 3
		Antwort[] p3f1a = { new Antwort("",2), new Antwort("",1), new Antwort("",-1), new Antwort("",-2)};
		Frage p3f1 = new Frage("", p3f1a);
		
		Antwort[] p3f2a = { new Antwort("",2), new Antwort("",1), new Antwort("",-1), new Antwort("",-2)};
		Frage p3f2 = new Frage("", p3f2a);
		
		Antwort[] p3f3a = { new Antwort("",2), new Antwort("",1), new Antwort("",-1), new Antwort("",-2)};
		Frage p3f3 = new Frage("", p3f3a);
		
		Frage[] InPar3Fragen = { p3f1, p3f2, p3f3};
		this.interviewPartner[2] = new InterviewPartner(idCount(), "", 6, 1, InPar3Fragen);

	}

	public static int idCounter = 0;

	private static int idCount() {
		return idCounter++;
	}

}
