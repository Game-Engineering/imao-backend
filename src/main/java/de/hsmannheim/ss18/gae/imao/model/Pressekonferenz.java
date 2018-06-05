package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Pressekonferenz {

	private PressekonferenzThema[] pressekonferenzThemen;
	private int punkteFuerAktuelleKonferenz = 0;
	private int aktuellerFrageIndex = 0;
	private String einleitung = null;

	private SpielrundeWirtschaft spielrundeWirtschaft;

	public Pressekonferenz(SpielrundeWirtschaft spielrundeWirtschaft) {
		this.spielrundeWirtschaft=spielrundeWirtschaft;
		createKonferenzThemen();
	}
	
	public PressekonferenzThema[] getPressekonferenzThemen() {
		return this.pressekonferenzThemen;
	}

	public String startePressekonferenz() {
		for (int i = 0; i < this.pressekonferenzThemen.length; i++) {
			if (this.pressekonferenzThemen[i].isVerfuegbar()) {
				return frageToString(this.pressekonferenzThemen[i].getFragen()[this.aktuellerFrageIndex], this.pressekonferenzThemen[i].getThema(), "ok", this.einleitung);
			}
		}

		return StatusToString.fehler("Momentan ist keine Pressekonferenz verfügbar");
	}

	public String antwortePressekonferenz(int antwortIndex) {
		for (int i = 0; i < this.pressekonferenzThemen.length; i++) {
			if (this.pressekonferenzThemen[i].isVerfuegbar()) {
				if (antwortIndex < this.pressekonferenzThemen[i].getFragen()[this.aktuellerFrageIndex].getAntworten().length && antwortIndex >=0) {
					
					if (this.aktuellerFrageIndex==0) {
						this.einleitung=this.pressekonferenzThemen[i].getFragen()[this.aktuellerFrageIndex].getAntworten()[antwortIndex].getAntwort();
					}
					
					this.punkteFuerAktuelleKonferenz += this.pressekonferenzThemen[i].getFragen()[this.aktuellerFrageIndex].getAntworten()[antwortIndex].getPunkte();
					this.aktuellerFrageIndex++;
					
					if(this.aktuellerFrageIndex < this.pressekonferenzThemen[i].getFragen().length) {
						return startePressekonferenz();
					}else {//pressekonferenz beenden
						
						this.spielrundeWirtschaft.beendePressekonferenz(this.punkteFuerAktuelleKonferenz);
						this.aktuellerFrageIndex=0;
						this.punkteFuerAktuelleKonferenz=0;
						this.einleitung=null;
						this.pressekonferenzThemen[i].setVerfuegbar(false);
						
						ObjectMapper mapper = new ObjectMapper();
						ArrayNode arrayNode = mapper.createArrayNode();
						ObjectNode objectNode = mapper.createObjectNode();
						
						objectNode.put("name", "pressekonferenz");
						objectNode.put("thema", this.pressekonferenzThemen[i].getThema());
						objectNode.put("status", "ENDE");

						objectNode.set("frage", null);				
						objectNode.set("antworten", null);

						return objectNode.toString();
					}
					
				}
				return StatusToString.notReady("Die Pressekonferenz ist verfügbar, es gibt nur keinen Inhalt");
			}
		}

		return StatusToString.fehler("Momentan ist keine Pressekonferenz verfügbar");
	}

	
	/**
	 * 
	 * @param frage
	 * @param thema
	 * @param status
	 * @param einleitung (kann null sein)
	 * @return
	 */
	private String frageToString(Frage frage, String thema, String status, String einleitung) {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode arrayNode = mapper.createArrayNode();
		ObjectNode objectNode = mapper.createObjectNode();
		
		objectNode.put("name", "pressekonferenz");
		objectNode.put("thema", thema);
		objectNode.put("status", status);
		
		if(einleitung != null) {
			objectNode.put("einleitung", einleitung);
		}
		
		objectNode.put("frage", frage.getFrage());
		
		for (int j = 0; j < frage.getAntworten().length; j++) {
			arrayNode.add(frage.getAntworten()[j].getAntwort());
		}
		
		objectNode.set("antworten", arrayNode);

		return objectNode.toString();
	}

	private void createKonferenzThemen() {

		this.pressekonferenzThemen = new PressekonferenzThema[3];

		// Fragen und Antworten für 1. Pressekonferenz
		Antwort[] p1f1a = { new Antwort("Sehr geehrtes Publikum,\r\n"
				+ "wie Ihnen sicher zu Ohren gekommen ist, haben wir in den letzten Tagen und Wochen ein erhöhtes Maß an Sterblichkeit bei den Patienten unserer Ärzte erlebt. Es ist uns leider kaum möglich, von unserem Standort hier etwas daran zu ändern. Unsere Ärzte vor Ort tun ihr Möglichstes, um weitere Tode zu verhindern. Leider ist von hier aus nicht auszuschließen, dass es sich bei den Todesfällen um Fehler seitens des Arztes handelt. Dieser Möglichkeit gehen wir selbstverständlich sorgsam nach.\r\n"
				+ "Haben Sie Fragen?\r\n", -8),
				new Antwort("Sehr geehrtes Publikum,\r\n"
						+ "es ist uns mitgeteilt worden, dass einer der Ärzte im Krisengebiet nicht sorgfältig genug arbeitet und dadurch das Leben von vielen Patienten achtlos aufs Spiel setzt. Mehrere Patienten sind dadurch bereits ums Leben gekommen. Leider können wir von hier aus nichts weiter tun, als zu hoffen, dass sich die Lage vor Ort von allein beruhigen wird.\r\n"
						+ "Haben Sie Fragen?\r\n", -10) };
		Frage p1f1 = new Frage("Welche Mittteilung gibst du der auf der Pressekonferenz?", p1f1a);

		Antwort[] p1f2a = {
				new Antwort("Leider liegen uns über die tatsächlichen Zahlen keine genauen Auskünfte vor.", -1),
				new Antwort(
						"Es handelt sich nur um einige wenige, vielleicht 2 bis 4. Die Situation ist unter Kontrolle.",
						0),
				new Antwort("Leider sind es wohl mehr als 5.", -2) };
		Frage p1f2 = new Frage(
				"Wie viele Menschen sind denn nun eigentlich bis zu diesem Zeitpunkt ums Leben gekommen?", p1f2a);

		Antwort[] p1f3a = { new Antwort("Wir haben Kontakt zum Arzt vor Ort aufgenommen und müssen jetzt abwarten.", 0),
				new Antwort(
						"Wir werden zweifellos einen neuen Arzt in die Gegend schicken. Ob der derzeitige Arzt trotzdem dort bleibt, müssen wir noch entscheiden.",
						1),
				new Antwort(
						"Leider können wir gar nichts tun. Der Arzt wird das Problem allein lösen müssen. Die Kontaktaufnahme zu ihm ist gescheitert.",
						-2) };
		Frage p1f3 = new Frage("Welche Schritte wollen Sie jetzt einleiten?", p1f3a);

		Antwort[] p1f4a = { new Antwort(
				"Die derzeitigen Zustände sind zutiefst bedauerlich. Unser Ziel bleibt die Rettung von Menschenleben.",
				0), new Antwort("Ja, leider können wir nichts tun, um das zu verhindern.", -2),
				new Antwort("Wir können leider nicht jeden retten. Immerhin retten wir ein paar der Menschen dort.",
						-1) };
		Frage p1f4 = new Frage("Widerspricht das, was passiert, nicht genau Ihrer eigentlichen Mission?", p1f4a);

		Frage[] Pre1Fragen = { p1f1, p1f2, p1f3, p1f4 };
		this.pressekonferenzThemen[0] = new PressekonferenzThema(1,
				"Falsche Diagnosen führen zu vielen Toten im Krisengebiet, in dem IMAO aktiv ist", 10, 1, Pre1Fragen);

		// Fragen und Antworten für 2. Pressekonferenz
		Antwort[] p2f1a = { new Antwort("Sehr geehrtes Publikum,\r\n"
				+ "wir freuen uns sehr, Ihnen mitteilen zu können, dass unsere Ärzte zurzeit hervorragende Arbeit leisten. Viele Menschen, die sonst sterben würden, werden durch unsere Ärzte im Krisengebiet erfolgreich behandelt. Das hilft nicht nur den einzelnen, sondern auch den jeweiligen Familien.\r\n"
				+ "Haben Sie Fragen?\r\n", 7),
				new Antwort("Sehr geehrtes Publikum,\r\n"
						+ "in den letzten Wochen haben wir mit Freuden beobachten dürfen, was für vortreffliche Leistung unsere Ärzte erbringen. Viele Menschen konnten erfolgreich behandelt werden. Für diese Menschen ist unser Projekt ein Segen mit Möglichkeiten, die sie sonst niemals hätten. Wir retten nicht nur einzelne Menschenleben. Ganze Familien werden durch unsere Arbeit gerettet. Eine Neufinanzierung des Projekts ist was wir uns alle erhoffen.\r\n"
						+ "Haben Sie Fragen?\r\n", 8) };
		Frage p2f1 = new Frage("Welche Mittteilung gibst du der auf der Pressekonferenz?", p2f1a);

		Antwort[] p2f2a = { new Antwort(
				"Wir möchten unseren Kollegen vor Ort gerne noch weitere Unterstützung anbieten. Leider hängt das von unseren finanziellen Mitteln ab. Wir können ohne zusätzliche Gelder keine Ausweitung des Projekts realisieren.",
				0),
				new Antwort(
						"Wir tun unser Möglichstes, um dem Arzt vor Ort weitere Unterstützung in Form von mehr Geräten und Personal zur Verfügung zu stellen, damit seine Arbeit für unser Projekt effektiv gefördert wird.",
						1),
				new Antwort(
						"Wenn wir mehr Mittel zur Verfügung hätten, würden wir innerhalb kurzer Zeit schon ein ähnliches Projekt in einem anderen Land beginnen. Die Planung dafür existiert bereits.",
						2) };
		Frage p2f2 = new Frage("Haben Sie vor, die Mission auszuweiten?", p2f2a);

		Antwort[] p2f3a = { new Antwort(
				"Natürlich verdanken wir das Leben dieser Menschen einzig und allein den behandelnden Ärzten.", 1),
				new Antwort(
						"Die gesamte Organisation IMAO hilft, diese Menschen zu retten. Jeder einzelne Teil muss funktionieren, damit wir effektiv helfen und etwas verändern können.",
						1),
				new Antwort(
						"Der Verdienst geht bestimmt an alle irgendwie Beteiligten. Das sind die Ärzte, aber auch die Verwaltung hier und alle Sponsoren, die das Projekt unterstützen oder unterstützt haben.",
						3) };
		Frage p2f3 = new Frage("Wessen Verdienst sind die geretteten Menschenleben nun genau?", p2f3a);

		Antwort[] p2f4a = { new Antwort(
				"Wir sollten jetzt auf keinen Fall etwas ändern. Solange es gut funktioniert, kann eine Änderung nur alles schlechter machen.",
				-1),
				new Antwort(
						"Wir müssen uns darauf konzentrieren, die Finanzierung des Projekts sicherzustellen, damit die Kräfte vor Ort von uns weiterhin erstklassig ausgestattet werden können.",
						0),
				new Antwort(
						"Zweifellos verdanken wir das Ergebnis dem Arzt. Er wird von uns eine Gehaltserhöhung bekommen, um ihn dazu zu ermutigen, weiterhin so gute Arbeit zu leisten.",
						1) };
		Frage p2f4 = new Frage("Wie kann man ein derart positives Ergebnis beibehalten?", p2f4a);

		Frage[] Pre2Fragen = { p2f1, p2f2, p2f3, p2f4 };
		this.pressekonferenzThemen[1] = new PressekonferenzThema(2, "IMAOs Ärzte retten viele Menschenleben.",
				10, 1, Pre2Fragen);
		

		// Fragen und Antworten für 3. Pressekonferenz
		Antwort[] p3f1a = { new Antwort("Sehr geehrtes Publikum,\r\n" + 
				"wie Sie sicher gehört haben, herrscht zurzeit in unserem Einsatzgebiet eine schwere Dürre. Das Wasser ist für alles Nötige zu knapp. Unsere Ärzte haben große Schwierigkeiten, mit der Situation umzugehen. Es gibt kaum genug Wasser, um zu trinken und die nötige Hygiene für die effektive Behandlung von Patienten zu gewährleisten. Außerdem werden durch die Wasserknappheit auch mehr Menschen krank. Wir bitten deshalb um Ihre Unterstützung.\r\n" + 
				"Haben Sie Fragen?\r\n", 3),
				new Antwort("Sehr geehrtes Publikum,\r\n" + 
						"unsere großartigen Ärzteteams sind im Moment in einem Gebiet aktiv, in dem eine schwere Dürre herrscht. Die Wasserknappheit gefährdet die dort lebenden Menschen und erschwert den Ärzten die Arbeit sehr. Um unseren Ärzten jetzt möglichst schnell und effektiv zu helfen, brauchen wir dringend finanzielle Unterstützung. Jede einzelne Spende rettet jetzt Menschenleben.\r\n" + 
						"Haben Sie Fragen?\r\n", 4) };
		Frage p3f1 = new Frage("Welche Mittteilung gibst du der auf der Pressekonferenz?", p3f1a);

		Antwort[] p3f2a = { new Antwort(
				"Die gespendeten Gelder werden unmittelbar in die benötigten Ressourcen investiert und diese werden schnellstmöglich in die betroffenen Gebiete gebracht.",
				2),
				new Antwort(
						"Jeder Cent ist wichtig. Wir kaufen davon Wasser und andere Ressourcen, um unseren Ärzten zu helfen.",
						2),
				new Antwort(
						"Die gespendeten Gelder werden von einer vertrauenswürdigen Bank verwaltet, die es sinnvoll investiert, um an den richtigen Stellen zu helfen.",
						-1) };
		Frage p3f2 = new Frage("Wie landet das gespendete Geld im Krisengebiet?", p3f2a);

		Antwort[] p3f3a = { new Antwort(
				"Die Menschen, die von der Dürre betroffen sind, verdursten. Wir müssen ihnen in erster Linie Trinkwasser zur Verfügung stellen.", 3),
				new Antwort(
						"Der Wassermangel verschlechtert die Hygiene. Unsere Ärzte brauchen dringend Wasser, um effektiv behandeln zu können.",
						1),
				new Antwort(
						"Der Wassermangel verschlechtert die Hygiene. Dadurch werden die betroffenen Menschen leichter krank und brauchen medizinische Versorgung. Die Menschen müssen sich waschen können, um die Arbeit der Ärzte zu minimieren.",
						1) };
		Frage p3f3 = new Frage("Wofür wird das durch Spendengelder gekaufte Wasser im Krisengebiet eingesetzt?", p3f3a);

		Antwort[] p3f4a = { new Antwort(
				"Das sollten Sie wohl eher einen Wetterexperten fragen.",
				-3),
				new Antwort(
						"Leider ist zurzeit davon auszugehen, dass die Dürre anhält, deshalb müssen wir dringend helfen.",
						1),
				new Antwort(
						"Wetterexperten gehen davon aus, dass die Dürre bald vorüber sein wird. Wir müssen also nur über einen begrenzten Zeitraum verstärkt helfen. Schnell zu reagieren hat jetzt oberste Priorität, um möglichst vielen Menschen zu helfen.",
						2) };
		Frage p3f4 = new Frage("Wird sich die Lage noch verschlimmern?", p3f4a);

		Frage[] Pre3Fragen = { p3f1, p3f2, p3f3, p3f4 };
		this.pressekonferenzThemen[2] = new PressekonferenzThema(3, "IMAOs Ärzte retten viele Menschenleben.",
				10, 1, Pre3Fragen);

	}

}
