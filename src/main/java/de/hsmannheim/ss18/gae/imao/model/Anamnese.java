package de.hsmannheim.ss18.gae.imao.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Anamnese {
	private Map<String, String> fragebogen;
	private int patientID;

	public Anamnese(int patientID, int anamneseID) {
		this.fragebogen = new HashMap<>();
		this.patientID = patientID;
		Random random = new Random();
		switch (anamneseID) {
		case 1:
			fragebogen.put("Hallo. Wie kann ich Ihnen helfen?", "Ich leide an Fieber.");
			fragebogen.put("Wie lange schon? ", "Es geht mir schon seit " + (random.nextInt(4) + 1) + " Tagen so. ");
			fragebogen.put("Auf einer Skala von 1-10, wie stark schätzen Sie Ihre Beschwerden ein?",
					"Auf einer Skala von 1-10 würde ich sagen " + (random.nextInt(5) + 3) + " .");
			fragebogen.put("Haben Sie weitere Beschwerden?",
					"Ja, ich leider außerdem an Hauterscheinung und Juckreiz.");
			fragebogen.put("Wie lange schon? ", "Es geht mir schon seit" + (random.nextInt(4) + 3) + " Tagen so.");
			fragebogen.put("Auf einer Skala von 1-10, wie stark schätzen Sie Ihre Beschwerden ein?",
					"Auf einer Skala von 1-10 würde ich sagen " + (random.nextInt(3) + 6) + " .");
			fragebogen.put("Haben Sie weitere Beschwerden?", "Ja, ich leider außerdem an Husten.");
			fragebogen.put("Haben Sie weitere Beschwerden?", "Ja, ich leider außerdem an Schnupfen.");
			fragebogen.put("Haben Sie weitere Beschwerden?", "Ja, ich leider außerdem an Durchfall.");
			fragebogen.put("Wie lange schon? ", "Es geht mir schon seit" + (random.nextInt(4) + 3) + " Tagen so.");
			fragebogen.put("Auf einer Skala von 1-10, wie stark schätzen Sie Ihre Beschwerden ein?",
					"Auf einer Skala von 1-10 würde ich sagen " + (random.nextInt(6) + 2) + " .");
			fragebogen.put("Hallo. Wie kann ich Ihnen helfen?", "Ich leide an Fieber.");
			fragebogen.put("Wie lange schon? ", "Es geht mir schon seit" + (random.nextInt(3) + 2) + " Tagen so.");
			fragebogen.put("Auf einer Skala von 1-10, wie stark schätzen Sie Ihre Beschwerden ein?",
					"Auf einer Skala von 1-10 würde ich sagen " + (random.nextInt(4) + 3) + " .");
			fragebogen.put("Haben Sie weitere Beschwerden?", "Nein, das war es.");
			break;
		case 2:
			fragebogen.put("Hallo. Wie kann ich Ihnen helfen?", "Ich leide an reiswasserartigem Brechdurchfall.");
			fragebogen.put("Wie lange schon? ",
					"Es geht mir schon seit " + (random.nextInt(33) + 16) + " Stunden so. ");
			fragebogen.put("Auf einer Skala von 1-10, wie stark schätzen Sie Ihre Beschwerden ein?",
					"Auf einer Skala von 1-10 würde ich sagen " + (random.nextInt(6) + 5) + " .");
			fragebogen.put("Haben Sie weitere Beschwerden?", "Nein, das war es.");
			break;
		case 3:
			fragebogen.put("Hallo. Wie kann ich Ihnen helfen?", "Ich leide an Fieber.");
			fragebogen.put("Wie lange schon? ", "Es geht mir schon seit " + (random.nextInt(5) + 5) + " Tagen so. ");
			fragebogen.put("Auf einer Skala von 1-10, wie stark schätzen Sie Ihre Beschwerden ein?",
					"Auf einer Skala von 1-10 würde ich sagen " + (random.nextInt(4) + 3) + " .");
			fragebogen.put("Haben Sie weitere Beschwerden?",
					"Ja, ich leider außerdem an Hauterscheinung und Juckreiz.");
			fragebogen.put("Haben Sie weitere Beschwerden?", "Ja, ich leider außerdem an Harnwegsbefall.");
			fragebogen.put("Haben Sie weitere Beschwerden?", "Nein, das war es.");
			break;

		}
	}

	public Map<String, String> getAnamnese() {
		return fragebogen;
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("name", "Anamnese");
		objectNode.put("budget", "900");
		objectNode.put("fragebogen", this.fragebogen.toString());

		return objectNode.toString();
	}
}
