package de.hsmannheim.ss18.gae.imao.model;

import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Anamnese {
	private Krankheit krankheit;
	private String[] dialog1VerfügbareFragen = { DIALOG_1_FRAGE_1, DIALOG_1_FRAGE_2, DIALOG_1_FRAGE_3, DIALOG_1_FRAGE_4,
			DIALOG_1_FRAGE_5, DIALOG_1_FRAGE_6 };
	private ESymptom[] verfügbareSymptome;
	private ESymptom letztesSymtom = null;
	private ESymptom[] bekannteSymtome = {};
	private String antwort = "";
	private String weitereOption = null;

	private static final String DIALOG_1_FRAGE_1 = "Wie lange haben Sie ihre Beschwerden schon?";
	private static final String DIALOG_1_FRAGE_2 = "Und auf einer Skala von 1-10, wie stark schätzen Sie Ihre Beschwerden ein?";
	private static final String DIALOG_1_FRAGE_3 = "Haben Sie weitere Beschwerden?";
	private static final String DIALOG_1_FRAGE_4 = "Ich brauche eine Blutprobe von Ihnen.";
	private static final String DIALOG_1_FRAGE_5 = "Ich brauche ein Ultraschallbild von Ihnen.";
	private static final String DIALOG_1_FRAGE_6 = "Warten Sie bitte hier.";

	public Anamnese(Krankheit krankheit) {
		this.krankheit = krankheit;
		this.verfügbareSymptome = krankheit.getSymptome();
	}

	public String getAntwort() {
		if(this.letztesSymtom == null) {
			//this.letztesSymtom=this.verfügbareSymptome[0];
			entferneSymptom();
		}
		this.antwort = "Hello! Ich habe " + symptomText();
		this.weitereOption=null;
		return erstelleJSON();
	}

	public String getAntwort(int frage) {
		if(this.dialog1VerfügbareFragen.length <= frage) {
			return "{\"error\"}";
		}
		erstelleAntwort(dialog1VerfügbareFragen[frage]);
		return erstelleJSON();

	}

	private String erstelleJSON() {
		ObjectMapper mapper = new ObjectMapper();

		ArrayNode arrayNode = mapper.createArrayNode();
		for (int i = 0; i < this.dialog1VerfügbareFragen.length; i++) {
			arrayNode.add(this.dialog1VerfügbareFragen[i]);
		}

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("name", "Anamnese");
		objectNode.put("krankheitDEBUG", krankheit.getKrankheit().toString());
		objectNode.put("antwort", this.antwort);
		objectNode.put("fragen", arrayNode);
		objectNode.put("option", this.weitereOption);

		return objectNode.toString();
	}

	private void erstelleAntwort(String frage) {

		String antwort = "";

		switch (frage) {
		case DIALOG_1_FRAGE_1:
			int min1 = 1;
			int max1 = 10;
			Random rand1 = new Random();
			int randomNum1 = rand1.nextInt((max1 - min1) + 1) + min1;
			// TODO anpassen an Syndrom
			antwort = "Es geht mir schon seit " + randomNum1 + " Tagen so.";
			this.weitereOption = null;
			entferneFrage(frage);
			break;
		case DIALOG_1_FRAGE_2:
			int min2 = 1;
			int max2 = 10;
			Random rand2 = new Random();
			int randomNum2 = rand2.nextInt((max2 - min2) + 1) + min2;
			// TODO anpassen an Syndrom
			antwort = "Auf einer Skala von 1-10 würde ich sagen… " + randomNum2;
			this.weitereOption = null;
			entferneFrage(frage);
			break;
		case DIALOG_1_FRAGE_3:
			// TODO frage 1 und 2 hinzufügen wenn nötig
			if (this.verfügbareSymptome.length > 0) {
				entferneSymptom();
				antwort += "Ja, ich habe auch noch " + symptomText() + ".";
				// System.out.println("*******************Versuche Frage 1 hinzuzufügen");
				addFrage(DIALOG_1_FRAGE_1);
				// System.out.println("*******************Versuche Frage 2 hinzuzufügen");
				addFrage(DIALOG_1_FRAGE_2);
			} else {
				antwort = "Nein, das war es.";
			}
			this.weitereOption = null;
			// NOTE frage wird nicht entfernt
			break;
		case DIALOG_1_FRAGE_4:

			antwort = "Okay, dann machen wir ein Blutbild";
			this.weitereOption = "blutbild";
			entferneFrage(frage);
			break;
		case DIALOG_1_FRAGE_5:

			antwort = "Okay, dann machen wir ein Ultraschall";
			this.weitereOption = "ultraschall";
			entferneFrage(frage);
			break;
		case DIALOG_1_FRAGE_6:
			antwort = "Ok, kein Problem, ich warte.";
			this.weitereOption = "ende";
			// NOTE frage wird nicht entfernt
			break;

		default:
			break;
		}

		this.antwort = antwort;
	}

	private String symptomText() {
		String antwort = "";

		switch (this.letztesSymtom) {
		case JUCKREITZ:
			antwort = "Juckreitz";
			break;
		case FIEBER_NORMAL:
			antwort = "Fieber";
			break;
		case FIEBER_LANG:
			antwort = "Fieber";
			// TODO zeit 7 tage +
			break;
		case FIEBER_HOCH_WECHSELHAFT:
			antwort = "sehr hohes Fieber";
			break;
		case HARNWEGSBEFALL:
			antwort = "Harnwegsbefall";
			break;
		case EINSTICHSTELLE_KNOETCHENBILDUNG:
			antwort = "Knötchenbildung Einstichstelle";
			break;
		case EINSTICHSTELLE_BEULE:
			antwort = "große Beule Einstichstelle";
			// TODO zeit nach ein paar wochen
			break;
		case HUSTEN:
			antwort= "Husten";
			break;
		case SCHNUPFEN:
			antwort = "Schnupfen";
			break;
		case BINDEHAUTENTZUENDUNG:
			antwort = "Bindehautentzündung";
			break;
		case DURCHFALL:
			antwort = "Durchfall";
			break;
		case BRECHDURCHFALL:
			antwort = "Reiswasserartiger Brechdurchfall";
			break;
		case MUEDIGKEIT:
			antwort = "Müdigkeit";
			break;
		case ERBRECHEN_UEBELKEIT:
			antwort = "Übelkeit und Erbrechen";
			break;
		case ERBRECHEN_BLUT:
			antwort = "Bluterbrechen";
			break;
		case VOELLEGEFUEHL:
			antwort = "ein Völlegefühl";
			break;
		case HAUT_GELB:
			antwort = "gelbe Haut";
			break;
		case AUGEN_GELBFAERBUNG:
			antwort = "eine Gelbfärbung der Skleren der Augen";
			break;
		case URIN_BRAUN:
			antwort = "braunen Urin";
			break;
		case STUHL_HELL:
			antwort = "eine Hellfärbung des Stuhls";
			break;
		case MUSKELSCHMERZEN:
			antwort = "Muskelschmerzen";
			break;
		case KIEFERSPERRE:
			antwort = "eine Kiefersperre";
			break;
		case SCHLUCKSTOERUNG:
			antwort = "Schluckstörung";
			break;
		case ATEMSTOERUNG:
			antwort = "Atemstörung";
			break;
		case GESICHTSMUSKELKRAEMPFE:
			antwort = "Gesichtsmuskelkrämpfe";
			break;
		case BLUTUNG:
			antwort = "Blutung der Haut";
			break;
		case SCHUETTELFROST:
			antwort = "Schüttelfrost";
			break;
		case GELENKSCHMERZEN:
			antwort = "Gelenkschmerzen";
			break;
		case PULS_NIEDRIG:
			antwort = "einen niedrigen Puls";
			break;
		case NACHTSCHWEIS:
			antwort = "Nachtschweiß";
			break;
		case LYMPHKNOTEN_GESCHWOLLEN:
			antwort = "geschwollene Lymphknoten";
			break;
		case GEWICHTSVERLUST:
			antwort = "Gewichtsverlust";
			break;

		default:
			break;
		}
		return antwort;

	}

	/**
	 * Entferne die frage damit nicht doppelt nachgefragt wird.
	 * 
	 * @param frage
	 */
	private void entferneFrage(String frage) {
		String[] newDialog1VerfügbareFragen = new String[this.dialog1VerfügbareFragen.length - 1];
		boolean frageEntfernt = false;
		for (int i = 0; i < this.dialog1VerfügbareFragen.length; i++) {
			if (this.dialog1VerfügbareFragen[i].equals(frage)) {
				frageEntfernt = true;
			} else {
				if (frageEntfernt) {
					newDialog1VerfügbareFragen[i - 1] = this.dialog1VerfügbareFragen[i];
				} else {
					newDialog1VerfügbareFragen[i] = this.dialog1VerfügbareFragen[i];
				}
			}
		}
		this.dialog1VerfügbareFragen = newDialog1VerfügbareFragen;
	}

	/**
	 * Füge eine frage dem Array der verfügbaren Fragen hinzu, wenn nicht schon
	 * enthalten.
	 * 
	 * @param frage
	 */
	private void addFrage(String frage) {
		// prüfe ob frage beriets vorhanden
		for (int i = 0; i < this.dialog1VerfügbareFragen.length; i++) {
			if (this.dialog1VerfügbareFragen[i].equals(frage)) {
				/*
				 * //DEBUG System.out.println("*******************Frage (" + frage +
				 * ") bereits vorhanden"); for (int j = 0; j <
				 * this.dialog1VerfügbareFragen.length; j++) {
				 * System.out.println("*******************Frage: " +
				 * this.dialog1VerfügbareFragen[j]); } //DEBUG
				 */

				return;
			}
		}

		// füge frage ein
		String[] newDialog1VerfügbareFragen = new String[this.dialog1VerfügbareFragen.length + 1];
		for (int i = 0; i < this.dialog1VerfügbareFragen.length; i++) {
			newDialog1VerfügbareFragen[i] = this.dialog1VerfügbareFragen[i];
		}
		newDialog1VerfügbareFragen[newDialog1VerfügbareFragen.length - 1] = frage;

		this.dialog1VerfügbareFragen = newDialog1VerfügbareFragen;
		/*
		 * //DEBUG System.out.println("*******************Frage (" + frage +
		 * ") eingefügt an position "+(newDialog1VerfügbareFragen.length - 1)); for (int
		 * j = 0; j < newDialog1VerfügbareFragen.length; j++) {
		 * System.out.println("*******************Frage "+j +": " +
		 * this.dialog1VerfügbareFragen[j]); } //DEBUG
		 */
	}

	/**
	 * Entfernt zuletzt gefragtes Symtom aus liste der verfügbaren und fügt es der
	 * bereits bekannten liste hinzu.
	 */
	private void entferneSymptom() {

		// entferne bekanntes Symptom aus verfügbaren
		ESymptom[] newVerfügbareSymptome = new ESymptom[this.verfügbareSymptome.length - 1];
		for (int i = 0; i < newVerfügbareSymptome.length; i++) {
			newVerfügbareSymptome[i] = this.verfügbareSymptome[i + 1];
		}

		// füge neues Symptom zu bekannten hinzu
		ESymptom[] newBekannteSymptome = new ESymptom[this.bekannteSymtome.length + 1];
		for (int i = 0; i < this.bekannteSymtome.length; i++) {
			newBekannteSymptome[i] = this.bekannteSymtome[i];
		}
		newBekannteSymptome[newBekannteSymptome.length - 1] = this.verfügbareSymptome[0];

		this.letztesSymtom = this.verfügbareSymptome[0];
		this.verfügbareSymptome = newVerfügbareSymptome;
		this.bekannteSymtome = newBekannteSymptome;
	}

}
