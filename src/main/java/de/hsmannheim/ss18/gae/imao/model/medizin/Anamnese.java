package de.hsmannheim.ss18.gae.imao.model.medizin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.hsmannheim.ss18.gae.imao.model.StatusToString;
import de.hsmannheim.ss18.gae.imao.model.enums.ESymptom;

import java.util.Random;

public class Anamnese {
	private Krankheit krankheit;
	private String[] dialog1VerfuegbareFragen = { DIALOG_1_FRAGE_1, DIALOG_1_FRAGE_2, DIALOG_1_FRAGE_3, DIALOG_1_FRAGE_4,
			DIALOG_1_FRAGE_5, DIALOG_1_FRAGE_6 };
	private ESymptom[] verfuegbareSymptome;
	private ESymptom letztesSymtom = null;
	private ESymptom[] bekannteSymtome = {};
	private String antwort = "";
	private String weitereOption = null;
	private int schmerzFuerLetztesSymptom;
	private int dauerFuerLetztesSymptom;

	//Fragen des Arztes an den Patienten bei einer normalen Annamnese
	private static final String DIALOG_1_FRAGE_1 = "Wie lange haben Sie diese Beschwerden schon?";
	private static final String DIALOG_1_FRAGE_2 = "Und auf einer Skala von 1-10, wie stark schätzen Sie Ihre Beschwerden ein?";
	private static final String DIALOG_1_FRAGE_3 = "Haben Sie weitere Beschwerden?";
	private static final String DIALOG_1_FRAGE_4 = "Ich brauche eine Blutprobe von Ihnen.";
	private static final String DIALOG_1_FRAGE_5 = "Ich brauche ein Ultraschallbild von Ihnen.";
	private static final String DIALOG_1_FRAGE_6 = "Warten Sie bitte hier.";

	public Anamnese(Krankheit krankheit) {
		this.krankheit = krankheit;
		this.verfuegbareSymptome = krankheit.getSymptome();
	}

	/**
	 * starte die Anamnese
	 * @return
	 */
	public String getAntwort() {
		if(this.letztesSymtom == null) {
			entferneSymptom();
		}
		this.antwort = "Hallo! " + symptomText();
		this.weitereOption=null;
		return erstelleJSON();
	}

	/**
	 * reagiere auf Antwort
	 * @param frage
	 * @return
	 */
	public String getAntwort(int frage) {
		if(this.dialog1VerfuegbareFragen.length <= frage) {
			return StatusToString.fehler("Fehlerhafte FrageID");
		}
		erstelleAntwort(dialog1VerfuegbareFragen[frage]);
		return erstelleJSON();

	}

	/**
	 * 
	 * @return
	 */
	private String erstelleJSON() {
		ObjectMapper mapper = new ObjectMapper();

		ArrayNode arrayNode = mapper.createArrayNode();
		for (int i = 0; i < this.dialog1VerfuegbareFragen.length; i++) {
			arrayNode.add(this.dialog1VerfuegbareFragen[i]);
		}

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("name", "Anamnese");
		objectNode.put("krankheitDEBUG", krankheit.getKrankheit().toString());
		objectNode.put("antwort", this.antwort);
		objectNode.set("fragen", arrayNode);
		objectNode.put("option", this.weitereOption);

		return objectNode.toString();
	}

	/**
	 * this.antwort wird aktuallisiert und bekommt die neue Antwort des Patienten
	 * @param frage
	 */
	private void erstelleAntwort(String frage) {

		String antwort = "";

		switch (frage) {
		case DIALOG_1_FRAGE_1:
			antwort = "Es geht mir schon seit " + dauerFuerLetztesSymptom + " Tagen so.";
			this.weitereOption = null;
			entferneFrage(frage);
			break;
		case DIALOG_1_FRAGE_2:

			antwort = "Auf einer Skala von 1-10 würde ich sagen… " + schmerzFuerLetztesSymptom;
			this.weitereOption = null;
			entferneFrage(frage);
			break;
		case DIALOG_1_FRAGE_3:
			if (this.verfuegbareSymptome.length > 0) {
				entferneSymptom();
				antwort += symptomText();
				// System.out.println("*******************Versuche Frage 1 hinzuzufügen");
				addFrage(DIALOG_1_FRAGE_1);
				// System.out.println("*******************Versuche Frage 2 hinzuzufügen");
				addFrage(DIALOG_1_FRAGE_2);
			} else {
				antwort = "Nein, das war es.";
				entferneFrage(frage);
			}
			this.weitereOption = null;
			// NOTE frage wird nicht entfernt
			break;
		case DIALOG_1_FRAGE_4:

			antwort = "Okay, dann machen wir ein Blutbild.";
			this.weitereOption = "blutbild";
			entferneFrage(frage);
			break;
		case DIALOG_1_FRAGE_5:

			antwort = "Okay, dann machen wir ein Ultraschall.";
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

	/**
	 * ändere Daten this.dauerFürLetztesSymptom und this.schmerzFürLetztesSymptom passend für Symptom und erstelle passenden text für symptom
	 * @return
	 */
	private String symptomText() {
		Random rand2 = new Random();
		String antwort = "";

		switch (this.letztesSymtom) {
		case JUCKREITZ:
			antwort = "Ich habe ein Jucken.";
			dauerFuerLetztesSymptom = rand2.nextInt((7 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case FIEBER_NORMAL:
			antwort = "Mir ist sehr warm. Ich glaube, ich habe Fieber.";
			dauerFuerLetztesSymptom = rand2.nextInt((7 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case FIEBER_LANG:
			antwort = "Ja, ich habe schon seit längerem Fieber.";
			dauerFuerLetztesSymptom = rand2.nextInt((14 - 7) + 1) + 7;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case FIEBER_HOCH_WECHSELHAFT:
			antwort = "Mir ist sehr warm, dass muss Fieber sein.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((10 - 5) + 1) + 5;
			break;
		case HARNWEGSBEFALL:
			antwort = "Ich glaube, mit meinem Harnweg stimmt was nicht.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case EINSTICHSTELLE_KNOETCHENBILDUNG:
			antwort = "Hallo! Ich habe hier eine Stelle, an der ich, glaube ich, gestochen wurde.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case EINSTICHSTELLE_BEULE:
			antwort = "Ich habe hier eine große Beule, wo ich vor ein paar Wochen gestochen wurde.";
			dauerFuerLetztesSymptom = rand2.nextInt((20 - 10) + 1) + 7;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case HUSTEN:
			antwort= "*HUST* Ich habe *Hust* Husten.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case SCHNUPFEN:
			antwort = "*Schnief* Meine Nase hört nicht auf zu laufen, *schnief* ich glaube, ich habe Schnupfen.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case BINDEHAUTENTZUENDUNG:
			antwort = "Meine Augen brennen und sind ganz rot.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case DURCHFALL:
			antwort = "Ich habe Durchfall.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case BRECHDURCHFALL:
			antwort = "Ich habe Durchfall und muss mich ab und zu übergeben.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case MUEDIGKEIT:
			antwort = "*Gähn* Ich bin immer so müde.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case ERBRECHEN_UEBELKEIT:
			antwort = "Mir ist total übel.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case ERBRECHEN_BLUT:
			antwort = "Wenn ich mich übergebe, dann breche ich auch Blut.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case VOELLEGEFUEHL:
			antwort = "Mein Bauch fühlt sich an, als würde er gleich platzen.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case HAUT_GELB:
			antwort = "Sehen Sie das nicht? Meine Haut ist total gelb.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case AUGEN_GELBFAERBUNG:
			antwort = "Schauen Sie mir mal in die Augen. Die müssten gelb sein.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case URIN_BRAUN:
			antwort = "Mein Urin sieht komisch aus, der ist braun.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case STUHL_HELL:
			antwort = "Mein Stuhlgang ist merkwürdig hell.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case MUSKELSCHMERZEN:
			antwort = "Meine Muskeln schmerzen.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case KIEFERSPERRE:
			antwort = "Es tut weh, wenn ich den Mund bewege.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case SCHLUCKSTOERUNG:
			antwort = "Ich kann nicht mehr so gut schlucken.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case ATEMSTOERUNG:
			antwort = "Das Atmen fällt mir schwer.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case GESICHTSMUSKELKRAEMPFE:
			antwort = "Ich habe manchmal einen Krampf im Gesicht.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case BLUTUNG:
			antwort = "Schauen Sie mal hier, ich habe hier eine Blutung.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case SCHUETTELFROST:
			antwort = "Mir ist immer so kalt.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case GELENKSCHMERZEN:
			antwort = "Meine Gelenke tun weh.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case PULS_NIEDRIG:
			antwort = "Ich glaube mein Puls ist nicht normal, das sollten Sie sich mal ansehen.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case NACHTSCHWEIS:
			antwort = "Ich schwitze nachts immer so doll.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case LYMPHKNOTEN_GESCHWOLLEN:
			antwort = "Mein Hals ist geschwollen. Das könnten die Lymphknoten sein.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;
		case GEWICHTSVERLUST:
			antwort = "Ich habe in den letzten Tagen stark abgenommen.";
			dauerFuerLetztesSymptom = rand2.nextInt((10 - 1) + 1) + 1;
			schmerzFuerLetztesSymptom = rand2.nextInt((7 - 3) + 1) + 3;
			break;

		default:
			antwort = "Etwas ist schiefgelaufen [Anamnese.java:symptomText()].";
			dauerFuerLetztesSymptom = 0;
			schmerzFuerLetztesSymptom = 0;
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
		String[] newDialog1VerfuegbareFragen = new String[this.dialog1VerfuegbareFragen.length - 1];
		boolean frageEntfernt = false;
		for (int i = 0; i < this.dialog1VerfuegbareFragen.length; i++) {
			if (this.dialog1VerfuegbareFragen[i].equals(frage)) {
				frageEntfernt = true;
			} else {
				if (frageEntfernt) {
					newDialog1VerfuegbareFragen[i - 1] = this.dialog1VerfuegbareFragen[i];
				} else {
					newDialog1VerfuegbareFragen[i] = this.dialog1VerfuegbareFragen[i];
				}
			}
		}
		this.dialog1VerfuegbareFragen = newDialog1VerfuegbareFragen;
	}

	/**
	 * Füge eine frage dem Array der verfügbaren Fragen hinzu, wenn nicht schon
	 * enthalten.
	 * 
	 * @param frage
	 */
	private void addFrage(String frage) {
		// prüfe ob frage beriets vorhanden
		for (int i = 0; i < this.dialog1VerfuegbareFragen.length; i++) {
			if (this.dialog1VerfuegbareFragen[i].equals(frage)) {
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
		String[] newDialog1VerfuegbareFragen = new String[this.dialog1VerfuegbareFragen.length + 1];
		for (int i = 0; i < this.dialog1VerfuegbareFragen.length; i++) {
			newDialog1VerfuegbareFragen[i] = this.dialog1VerfuegbareFragen[i];
		}
		newDialog1VerfuegbareFragen[newDialog1VerfuegbareFragen.length - 1] = frage;

		this.dialog1VerfuegbareFragen = newDialog1VerfuegbareFragen;
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
		ESymptom[] newVerfuegbareSymptome = new ESymptom[this.verfuegbareSymptome.length - 1];
		for (int i = 0; i < newVerfuegbareSymptome.length; i++) {
			newVerfuegbareSymptome[i] = this.verfuegbareSymptome[i + 1];
		}

		// füge neues Symptom zu bekannten hinzu
		ESymptom[] newBekannteSymptome = new ESymptom[this.bekannteSymtome.length + 1];
		for (int i = 0; i < this.bekannteSymtome.length; i++) {
			newBekannteSymptome[i] = this.bekannteSymtome[i];
		}
		newBekannteSymptome[newBekannteSymptome.length - 1] = this.verfuegbareSymptome[0];

		this.letztesSymtom = this.verfuegbareSymptome[0];
		this.verfuegbareSymptome = newVerfuegbareSymptome;
		this.bekannteSymtome = newBekannteSymptome;
	}

}
