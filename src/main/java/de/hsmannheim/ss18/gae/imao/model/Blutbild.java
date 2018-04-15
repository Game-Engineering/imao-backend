package de.hsmannheim.ss18.gae.imao.model;

import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Blutbild {

	// Blutbild normalwerte
	// Erythrozyten | w-> 3,9-5,3 Mio./µl | m->4,3-5,7Mio./µl
	private static final double DEFAULT_MIN_FRAU_ERYTHROZYTEN = 3.9;
	private static final double DEFAULT_MAX_FRAU_ERYTHROZYTEN = 5.3;
	private static final double DEFAULT_MIN_MANN_ERYTHROZYTEN = 4.3;
	private static final double DEFAULT_MAX_MANN_ERYTHROZYTEN = 5.7;

	// Leukozyten | w-> 3.800-10.500/µl | w-> 3.800-10.500/µl
	private static final double DEFAULT_MIN_LEUKOZYTEN = 3800;
	private static final double DEFAULT_MAX_LEUKOZYTEN = 10500;

	// Thrombozyten | w-> 140.000-345.000/µl | m-> 140.000-345.000/µl
	private static final double DEFAULT_MIN_THROMBOZYTEN = 140000;
	private static final double DEFAULT_MAX_THROMBOZYTEN = 345000;

	// Hämoglobinkonzentration (Hb) | w-> 12-16 g/dl | m-> 13,5-17 g/dl
	private static final double DEFAULT_MIN_FRAU_HAEMOGLOBINKONZENTRATION = 12;
	private static final double DEFAULT_MAX_FRAU_HAEMOGLOBINKONZENTRATION = 16;
	private static final double DEFAULT_MIN_MANN_HAEMOGLOBINKONZENTRATION = 13.5;
	private static final double DEFAULT_MAX_MANN_HAEMOGLOBINKONZENTRATION = 17;

	// Hämatokrit (Hkt) | w-> 37-48 %| m-> 40-52 %
	private static final double DEFAULT_MIN_FRAU_HAEMATOKRIT = 37;
	private static final double DEFAULT_MAX_FRAU_HAEMATOKRIT = 48;
	private static final double DEFAULT_MIN_MANN_HAEMATOKRIT = 40;
	private static final double DEFAULT_MAX_MANN_HAEMATOKRIT = 52;

	// MCH | w-> 28-34 pg (Pikogramm pro Zelle) | m-> 28-34 pg (Pikogramm pro
	// Zelle)
	private static final double DEFAULT_MIN_MCH = 28;
	private static final double DEFAULT_MAX_MCH = 34;

	// MCHC | w-> 33-36 g/dl | m-> 33-36 g/dl
	private static final double DEFAULT_MIN_MCHC = 33;
	private static final double DEFAULT_MAX_MCHC = 36;

	// MCV | w-> 85-95 fl (Femtoliter) | m-> 85-95 fl (Femtoliter)
	private static final double DEFAULT_MIN_MCV = 85;
	private static final double DEFAULT_MAX_MCV = 95;

	Krankheit krankheit;

	private double erythrozyten;
	private double leukozyten;
	private double thrombozyten;
	private double haemoglobinkonzentration;
	private double haematokrit;
	private double mch;
	private double mchc;
	private double mcv;

/**
 * 
 * @param Krankheit krankheit
 */
	public Blutbild(Krankheit krankheit) {
		// TODO auslesen und richtiges übergeben
		this.krankheit = krankheit;
		this.erstelleNormalesBlutbild(krankheit.getPatient().getGeschlecht());
		this.erstelleKrankesBlutbild(krankheit.getKrankheit());
	}

	/**
	 * Erstellt ein Blutbild ohne Abweichung der Normalwerte
	 * 
	 * @param EGeschlecht gender
	 */
	private void erstelleNormalesBlutbild(EGeschlecht gender) {
		this.leukozyten = random(DEFAULT_MIN_LEUKOZYTEN, DEFAULT_MAX_LEUKOZYTEN);
		this.thrombozyten = random(DEFAULT_MIN_THROMBOZYTEN, DEFAULT_MAX_THROMBOZYTEN);
		this.mch = random(DEFAULT_MIN_MCH, DEFAULT_MAX_MCH);
		this.mchc = random(DEFAULT_MIN_MCHC, DEFAULT_MAX_MCHC);
		this.mcv = random(DEFAULT_MIN_MCV, DEFAULT_MAX_MCV);
		if (gender == EGeschlecht.MAENNLICH) {
			this.erythrozyten = random(DEFAULT_MIN_MANN_ERYTHROZYTEN, DEFAULT_MAX_MANN_ERYTHROZYTEN);
			this.haemoglobinkonzentration = random(DEFAULT_MIN_MANN_HAEMOGLOBINKONZENTRATION,
					DEFAULT_MAX_MANN_HAEMOGLOBINKONZENTRATION);
			this.haematokrit = random(DEFAULT_MIN_MANN_HAEMATOKRIT, DEFAULT_MAX_MANN_HAEMATOKRIT);
		} else if (gender == EGeschlecht.WEIBLICH) {
			this.erythrozyten = random(DEFAULT_MIN_FRAU_ERYTHROZYTEN, DEFAULT_MAX_FRAU_ERYTHROZYTEN);
			this.haemoglobinkonzentration = random(DEFAULT_MIN_FRAU_HAEMOGLOBINKONZENTRATION,
					DEFAULT_MAX_FRAU_HAEMOGLOBINKONZENTRATION);
			this.haematokrit = random(DEFAULT_MIN_FRAU_HAEMATOKRIT, DEFAULT_MAX_FRAU_HAEMATOKRIT);
		}
	}

	/**
	 * Ändert ein normales Blutbild entsprechend einer Krankheit ab
	 * 
	 * @param EKrankheit krankheit
	 */
	private void erstelleKrankesBlutbild(EKrankheit krankheit) {
		switch (krankheit) {
		case HIV:
			// TODO Blutbild an Krankheit anpassen
			break;
		case BILHARZIOSE:
			// TODO Blutbild an Krankheit anpassen
			break;
		case HAUTLEISHMANIASIS:
			// TODO Blutbild an Krankheit anpassen
			break;
		case MASERN:
			// TODO Blutbild an Krankheit anpassen
			break;
		case HEP_A:
			// TODO Blutbild an Krankheit anpassen
			break;
		case HEP_B:
			// TODO Blutbild an Krankheit anpassen
			break;
		case CHOLERA:
			// TODO Blutbild an Krankheit anpassen
			break;
		case TETANUS:
			// TODO Blutbild an Krankheit anpassen
			break;
		case GELBFIEBER:
			// TODO Blutbild an Krankheit anpassen
			break;
		case DENGUE_FIEBER:
			// TODO Blutbild an Krankheit anpassen
			break;

		default:
			break;
		}
	}

	/**
	 * Generiert eine zufälliges double im Bereich min/max mit 2 Nachkommastellen
	 * 
	 * @param max
	 * @param min
	 * @return
	 */
	private double random(double min, double max) {
		Random r = new Random();
		double n = min + (max - min) * r.nextDouble();
		n = Math.round(n * 100) / 100.0;
		return n;
	}

	public String toString() {

		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		// TODO Budget anpassen
		objectNode.put("budget", "900");
		objectNode.put("name", "Blutbild");
		// objectNode.put("BlutbildID", this.blutbildID);
		objectNode.put("Erythrozyten", this.erythrozyten);
		objectNode.put("Leukozyten", this.leukozyten);
		objectNode.put("Thrombozyten", this.thrombozyten);
		objectNode.put("Haemoglobinkonzentration", this.haemoglobinkonzentration);
		objectNode.put("Haematokrit", this.haematokrit);
		objectNode.put("MCH", this.mch);
		objectNode.put("MCHC", this.mchc);
		objectNode.put("MCV", this.mcv);

		return objectNode.toString();
	}
}
