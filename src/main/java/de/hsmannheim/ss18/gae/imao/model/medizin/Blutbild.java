package de.hsmannheim.ss18.gae.imao.model.medizin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.hsmannheim.ss18.gae.imao.model.enums.EGeschlecht;
import de.hsmannheim.ss18.gae.imao.model.enums.EKrankheit;

import java.util.Random;

public class Blutbild {

	// Blutbild normalwerte
	// Haemoglobinkonzentration | w-> 3,9-5,3 Mio./µl | m->4,3-5,7Mio./µl
	private static final double DEFAULT_MIN_FRAU_Haemoglobinkonzentration = 3.9;
	private static final double DEFAULT_MAX_FRAU_Haemoglobinkonzentration = 5.3;
	private static final double DEFAULT_MIN_MANN_Haemoglobinkonzentration = 4.3;
	private static final double DEFAULT_MAX_MANN_Haemoglobinkonzentration = 5.7;

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

	// ########################################grenzwerte start

	// grenzwerte Haemoglobinkonzentration Frau
	private static final double KRANK_Haemoglobinkonzentration_FRAU_ERHOET_LEICHT = DEFAULT_MAX_FRAU_Haemoglobinkonzentration
			+ ((DEFAULT_MAX_FRAU_Haemoglobinkonzentration - DEFAULT_MIN_FRAU_Haemoglobinkonzentration) * 0.1);
	private static final double KRANK_Haemoglobinkonzentration_FRAU_ERHOET_STARK = DEFAULT_MAX_FRAU_Haemoglobinkonzentration
			+ ((DEFAULT_MAX_FRAU_Haemoglobinkonzentration - DEFAULT_MIN_FRAU_Haemoglobinkonzentration) * 0.25);

	private static final double KRANK_Haemoglobinkonzentration_FRAU_GERING_LEICHT = DEFAULT_MIN_FRAU_Haemoglobinkonzentration
			- ((DEFAULT_MAX_FRAU_Haemoglobinkonzentration - DEFAULT_MIN_FRAU_Haemoglobinkonzentration) * 0.1);
	private static final double KRANK_Haemoglobinkonzentration_FRAU_GERING_STARK = DEFAULT_MIN_FRAU_Haemoglobinkonzentration
			- (DEFAULT_MAX_FRAU_Haemoglobinkonzentration - DEFAULT_MIN_FRAU_Haemoglobinkonzentration) * 0.25;

	// grenzwerte Haemoglobinkonzentration Mann
	private static final double KRANK_Haemoglobinkonzentration_MANN_ERHOET_LEICHT = DEFAULT_MAX_MANN_Haemoglobinkonzentration
			+ ((DEFAULT_MAX_MANN_Haemoglobinkonzentration - DEFAULT_MIN_MANN_Haemoglobinkonzentration) * 0.1);
	private static final double KRANK_Haemoglobinkonzentration_MANN_ERHOET_STARK = DEFAULT_MAX_MANN_Haemoglobinkonzentration
			+ ((DEFAULT_MAX_MANN_Haemoglobinkonzentration - DEFAULT_MIN_MANN_Haemoglobinkonzentration) * 0.25);

	private static final double KRANK_Haemoglobinkonzentration_MANN_GERING_LEICHT = DEFAULT_MIN_MANN_Haemoglobinkonzentration
			- ((DEFAULT_MAX_MANN_Haemoglobinkonzentration - DEFAULT_MIN_MANN_Haemoglobinkonzentration) * 0.1);
	private static final double KRANK_Haemoglobinkonzentration_MANN_GERING_STARK = DEFAULT_MIN_MANN_Haemoglobinkonzentration
			- (DEFAULT_MAX_MANN_Haemoglobinkonzentration - DEFAULT_MIN_MANN_Haemoglobinkonzentration) * 0.25;

	// grenzwerte LEUKOZYTEN
	private static final double KRANK_LEUKOZYTEN_ERHOET_LEICHT = DEFAULT_MAX_LEUKOZYTEN
			+ ((DEFAULT_MAX_LEUKOZYTEN - DEFAULT_MIN_LEUKOZYTEN) * 0.1);
	private static final double KRANK_LEUKOZYTEN_ERHOET_STARK = DEFAULT_MAX_LEUKOZYTEN
			+ ((DEFAULT_MAX_LEUKOZYTEN - DEFAULT_MIN_LEUKOZYTEN) * 0.25);

	private static final double KRANK_LEUKOZYTEN_GERING_LEICHT = DEFAULT_MIN_LEUKOZYTEN
			- ((DEFAULT_MAX_LEUKOZYTEN - DEFAULT_MIN_LEUKOZYTEN) * 0.1);
	private static final double KRANK_LEUKOZYTEN_GERING_STARK = DEFAULT_MIN_LEUKOZYTEN
			- (DEFAULT_MAX_LEUKOZYTEN - DEFAULT_MIN_LEUKOZYTEN) * 0.25;

	// grenzwerte THROMBOZYTEN
	private static final double KRANK_THROMBOZYTEN_ERHOET_LEICHT = DEFAULT_MAX_THROMBOZYTEN
			+ ((DEFAULT_MAX_THROMBOZYTEN - DEFAULT_MIN_THROMBOZYTEN) * 0.1);
	private static final double KRANK_THROMBOZYTEN_ERHOET_STARK = DEFAULT_MAX_THROMBOZYTEN
			+ ((DEFAULT_MAX_THROMBOZYTEN - DEFAULT_MIN_THROMBOZYTEN) * 0.25);

	private static final double KRANK_THROMBOZYTEN_GERING_LEICHT = DEFAULT_MIN_THROMBOZYTEN
			- ((DEFAULT_MAX_THROMBOZYTEN - DEFAULT_MIN_THROMBOZYTEN) * 0.1);
	private static final double KRANK_THROMBOZYTEN_GERING_STARK = DEFAULT_MIN_THROMBOZYTEN
			- (DEFAULT_MAX_THROMBOZYTEN - DEFAULT_MIN_THROMBOZYTEN) * 0.25;

	// grenzwerte MCH
	private static final double KRANK_MCH_ERHOET_LEICHT = DEFAULT_MAX_MCH + ((DEFAULT_MAX_MCH - DEFAULT_MIN_MCH) * 0.1);
	private static final double KRANK_MCH_ERHOET_STARK = DEFAULT_MAX_MCH + ((DEFAULT_MAX_MCH - DEFAULT_MIN_MCH) * 0.25);

	private static final double KRANK_MCH_GERING_LEICHT = DEFAULT_MIN_MCH - ((DEFAULT_MAX_MCH - DEFAULT_MIN_MCH) * 0.1);
	private static final double KRANK_MCH_GERING_STARK = DEFAULT_MIN_MCH - (DEFAULT_MAX_MCH - DEFAULT_MIN_MCH) * 0.25;

	// grenzwerte MCHC
	private static final double KRANK_MCHC_ERHOET_LEICHT = DEFAULT_MAX_MCHC
			+ ((DEFAULT_MAX_MCHC - DEFAULT_MIN_MCHC) * 0.1);
	private static final double KRANK_MCHC_ERHOET_STARK = DEFAULT_MAX_MCHC
			+ ((DEFAULT_MAX_MCHC - DEFAULT_MIN_MCHC) * 0.25);

	private static final double KRANK_MCHC_GERING_LEICHT = DEFAULT_MIN_MCHC
			- ((DEFAULT_MAX_MCHC - DEFAULT_MIN_MCHC) * 0.1);
	private static final double KRANK_MCHC_GERING_STARK = DEFAULT_MIN_MCHC
			- (DEFAULT_MAX_MCHC - DEFAULT_MIN_MCHC) * 0.25;

	// grenzwerte MCV
	private static final double KRANK_MCV_ERHOET_LEICHT = DEFAULT_MAX_MCV + ((DEFAULT_MAX_MCV - DEFAULT_MIN_MCV) * 0.1);
	private static final double KRANK_MCV_ERHOET_STARK = DEFAULT_MAX_MCV + ((DEFAULT_MAX_MCV - DEFAULT_MIN_MCV) * 0.25);

	private static final double KRANK_MCV_GERING_LEICHT = DEFAULT_MIN_MCV - ((DEFAULT_MAX_MCV - DEFAULT_MIN_MCV) * 0.1);
	private static final double KRANK_MCV_GERING_STARK = DEFAULT_MIN_MCV - (DEFAULT_MAX_MCV - DEFAULT_MIN_MCV) * 0.25;

	// grenzwerte HAEMOGLOBINKONZENTRATION Frau
	private static final double KRANK_HAEMOGLOBINKONZENTRATION_FRAU_ERHOET_LEICHT = DEFAULT_MAX_FRAU_HAEMOGLOBINKONZENTRATION
			+ ((DEFAULT_MAX_FRAU_HAEMOGLOBINKONZENTRATION - DEFAULT_MIN_FRAU_HAEMOGLOBINKONZENTRATION) * 0.1);
	private static final double KRANK_HAEMOGLOBINKONZENTRATION_FRAU_ERHOET_STARK = DEFAULT_MAX_FRAU_HAEMOGLOBINKONZENTRATION
			+ ((DEFAULT_MAX_FRAU_HAEMOGLOBINKONZENTRATION - DEFAULT_MIN_FRAU_HAEMOGLOBINKONZENTRATION) * 0.25);

	private static final double KRANK_HAEMOGLOBINKONZENTRATION_FRAU_GERING_LEICHT = DEFAULT_MIN_FRAU_HAEMOGLOBINKONZENTRATION
			- ((DEFAULT_MAX_FRAU_HAEMOGLOBINKONZENTRATION - DEFAULT_MIN_FRAU_HAEMOGLOBINKONZENTRATION) * 0.1);
	private static final double KRANK_HAEMOGLOBINKONZENTRATION_FRAU_GERING_STARK = DEFAULT_MIN_FRAU_HAEMOGLOBINKONZENTRATION
			- (DEFAULT_MAX_FRAU_HAEMOGLOBINKONZENTRATION - DEFAULT_MIN_FRAU_HAEMOGLOBINKONZENTRATION) * 0.25;

	// grenzwerte HAEMOGLOBINKONZENTRATION Mann
	private static final double KRANK_HAEMOGLOBINKONZENTRATION_MANN_ERHOET_LEICHT = DEFAULT_MAX_MANN_HAEMOGLOBINKONZENTRATION
			+ ((DEFAULT_MAX_MANN_HAEMOGLOBINKONZENTRATION - DEFAULT_MIN_MANN_HAEMOGLOBINKONZENTRATION) * 0.1);
	private static final double KRANK_HAEMOGLOBINKONZENTRATION_MANN_ERHOET_STARK = DEFAULT_MAX_MANN_HAEMOGLOBINKONZENTRATION
			+ ((DEFAULT_MAX_MANN_HAEMOGLOBINKONZENTRATION - DEFAULT_MIN_MANN_HAEMOGLOBINKONZENTRATION) * 0.25);

	private static final double KRANK_HAEMOGLOBINKONZENTRATION_MANN_GERING_LEICHT = DEFAULT_MIN_MANN_HAEMOGLOBINKONZENTRATION
			- ((DEFAULT_MAX_MANN_HAEMOGLOBINKONZENTRATION - DEFAULT_MIN_MANN_HAEMOGLOBINKONZENTRATION) * 0.1);
	private static final double KRANK_HAEMOGLOBINKONZENTRATION_MANN_GERING_STARK = DEFAULT_MIN_MANN_HAEMOGLOBINKONZENTRATION
			- (DEFAULT_MAX_MANN_HAEMOGLOBINKONZENTRATION - DEFAULT_MIN_MANN_HAEMOGLOBINKONZENTRATION) * 0.25;

	// grenzwerte HAEMATOKRIT Frau
	private static final double KRANK_HAEMATOKRIT_FRAU_ERHOET_LEICHT = DEFAULT_MAX_FRAU_HAEMATOKRIT
			+ ((DEFAULT_MAX_FRAU_HAEMATOKRIT - DEFAULT_MIN_FRAU_HAEMATOKRIT) * 0.1);
	private static final double KRANK_HAEMATOKRIT_FRAU_ERHOET_STARK = DEFAULT_MAX_FRAU_HAEMATOKRIT
			+ ((DEFAULT_MAX_FRAU_HAEMATOKRIT - DEFAULT_MIN_FRAU_HAEMATOKRIT) * 0.25);

	private static final double KRANK_HAEMATOKRIT_FRAU_GERING_LEICHT = DEFAULT_MIN_FRAU_HAEMATOKRIT
			- ((DEFAULT_MAX_FRAU_HAEMATOKRIT - DEFAULT_MIN_FRAU_HAEMATOKRIT) * 0.1);
	private static final double KRANK_HAEMATOKRIT_FRAU_GERING_STARK = DEFAULT_MIN_FRAU_HAEMATOKRIT
			- (DEFAULT_MAX_FRAU_HAEMATOKRIT - DEFAULT_MIN_FRAU_HAEMATOKRIT) * 0.25;

	// grenzwerte HAEMATOKRIT Mann
	private static final double KRANK_HAEMATOKRIT_MANN_ERHOET_LEICHT = DEFAULT_MAX_MANN_HAEMATOKRIT
			+ ((DEFAULT_MAX_MANN_HAEMATOKRIT - DEFAULT_MIN_MANN_HAEMATOKRIT) * 0.1);
	private static final double KRANK_HAEMATOKRIT_MANN_ERHOET_STARK = DEFAULT_MAX_MANN_HAEMATOKRIT
			+ ((DEFAULT_MAX_MANN_HAEMATOKRIT - DEFAULT_MIN_MANN_HAEMATOKRIT) * 0.25);

	private static final double KRANK_HAEMATOKRIT_MANN_GERING_LEICHT = DEFAULT_MIN_MANN_HAEMATOKRIT
			- ((DEFAULT_MAX_MANN_HAEMATOKRIT - DEFAULT_MIN_MANN_HAEMATOKRIT) * 0.1);
	private static final double KRANK_HAEMATOKRIT_MANN_GERING_STARK = DEFAULT_MIN_MANN_HAEMATOKRIT
			- (DEFAULT_MAX_MANN_HAEMATOKRIT - DEFAULT_MIN_MANN_HAEMATOKRIT) * 0.25;

	// ##############################################grenzwerte ende

	// Erhöhte Thrombozyten
//	private static final double krankThrombozytenErhoetLeichtMin = DEFAULT_MAX_THROMBOZYTEN;
//	private static final double krankThrombozytenErhoetLeichtMax = DEFAULT_MAX_THROMBOZYTEN
//			+ ((DEFAULT_MAX_THROMBOZYTEN - DEFAULT_MIN_THROMBOZYTEN) * 0.1);
//	private static final double krankThrombozytenErhoetStarkMin = DEFAULT_MAX_THROMBOZYTEN
//			+ ((DEFAULT_MAX_THROMBOZYTEN - DEFAULT_MIN_THROMBOZYTEN) * 0.1);
//	private static final double krankThrombozytenErhoetStarkMax = DEFAULT_MAX_THROMBOZYTEN
//			+ ((DEFAULT_MAX_THROMBOZYTEN - DEFAULT_MIN_THROMBOZYTEN) * 0.25);

	// Geringe Thrombozyten
//	private static final double krankThrombozytenGeringLeichtMin = DEFAULT_MIN_THROMBOZYTEN
//			- ((DEFAULT_MAX_THROMBOZYTEN - DEFAULT_MIN_THROMBOZYTEN) * 0.1);
//	private static final double krankThrombozytenGeringLeichtMax = DEFAULT_MIN_THROMBOZYTEN;
//	private static final double krankThrombozytenGeringStarkMin = DEFAULT_MIN_THROMBOZYTEN
//			- (DEFAULT_MAX_THROMBOZYTEN - DEFAULT_MIN_THROMBOZYTEN) * 0.25;
//	private static final double krankThrombozytenGeringStarkMax = DEFAULT_MIN_THROMBOZYTEN
//			- (DEFAULT_MAX_THROMBOZYTEN - DEFAULT_MIN_THROMBOZYTEN) * 0.1;

	// Erhöhte Leukozyten
//	private static final double krankLeukozytenErhoetLeichtMin = DEFAULT_MAX_LEUKOZYTEN;
//	private static final double krankLeukozytenErhoetLeichtMax = DEFAULT_MAX_LEUKOZYTEN
//			+ (DEFAULT_MAX_LEUKOZYTEN - DEFAULT_MIN_LEUKOZYTEN) * 0.1;
//	private static final double krankLeukozytenErhoetStarkMin = DEFAULT_MAX_LEUKOZYTEN
//			+ (DEFAULT_MAX_LEUKOZYTEN - DEFAULT_MIN_LEUKOZYTEN) * 0.1;
//	private static final double krankLeukozytenErhoetStarkMax = DEFAULT_MAX_LEUKOZYTEN
//			+ (DEFAULT_MAX_LEUKOZYTEN - DEFAULT_MIN_LEUKOZYTEN) * 0.25;

	// Geringe Leukozyten
//	private static final double krankLeukozytenGeringLeichtMin = DEFAULT_MIN_LEUKOZYTEN
//			- (DEFAULT_MAX_LEUKOZYTEN - DEFAULT_MIN_LEUKOZYTEN) * 0.1;
//	private static final double krankLeukozytenGeringLeichtMax = DEFAULT_MIN_LEUKOZYTEN;
//	private static final double krankLeukozytenGeringStarkMin = DEFAULT_MIN_LEUKOZYTEN
//			- (DEFAULT_MAX_LEUKOZYTEN - DEFAULT_MIN_LEUKOZYTEN) * 0.25;
//	private static final double krankLeukozytenGeringStarkMax = DEFAULT_MIN_LEUKOZYTEN
//			- (DEFAULT_MAX_LEUKOZYTEN - DEFAULT_MIN_LEUKOZYTEN) * 0.1;

//	private Krankheit krankheit;
	private EGeschlecht geschlecht;

	private double Haemoglobinkonzentration;
	private double leukozyten;
	private double thrombozyten;
	private double haemoglobinkonzentration;
	private double haematokrit;
	private double mch;
	private double mchc;
	private double mcv;

	/**
	 * 
	 * @param krankheit
	 */
	public Blutbild(Krankheit krankheit) {
		// TODO auslesen und richtiges übergeben
//		this.krankheit = krankheit;
		this.geschlecht = krankheit.getPatient().getGeschlecht();
		this.erstelleNormalesBlutbild(this.geschlecht);
		this.erstelleKrankesBlutbild(this.geschlecht, krankheit.getKrankheit());
	}

	/**
	 * Erstelle ein Blutbild ohne Abweichungen der Normalwerte
	 * @param gender
	 */
	private void erstelleNormalesBlutbild(EGeschlecht gender) {
		this.leukozyten = random(DEFAULT_MIN_LEUKOZYTEN, DEFAULT_MAX_LEUKOZYTEN);
		this.thrombozyten = random(DEFAULT_MIN_THROMBOZYTEN, DEFAULT_MAX_THROMBOZYTEN);
		this.mch = random(DEFAULT_MIN_MCH, DEFAULT_MAX_MCH);
		this.mchc = random(DEFAULT_MIN_MCHC, DEFAULT_MAX_MCHC);
		this.mcv = random(DEFAULT_MIN_MCV, DEFAULT_MAX_MCV);
		if (gender == EGeschlecht.MAENNLICH) {
			this.Haemoglobinkonzentration = random(DEFAULT_MIN_MANN_Haemoglobinkonzentration, DEFAULT_MAX_MANN_Haemoglobinkonzentration);
			this.haemoglobinkonzentration = random(DEFAULT_MIN_MANN_HAEMOGLOBINKONZENTRATION,
					DEFAULT_MAX_MANN_HAEMOGLOBINKONZENTRATION);
			this.haematokrit = random(DEFAULT_MIN_MANN_HAEMATOKRIT, DEFAULT_MAX_MANN_HAEMATOKRIT);
		} else if (gender == EGeschlecht.WEIBLICH) {
			this.Haemoglobinkonzentration = random(DEFAULT_MIN_FRAU_Haemoglobinkonzentration, DEFAULT_MAX_FRAU_Haemoglobinkonzentration);
			this.haemoglobinkonzentration = random(DEFAULT_MIN_FRAU_HAEMOGLOBINKONZENTRATION,
					DEFAULT_MAX_FRAU_HAEMOGLOBINKONZENTRATION);
			this.haematokrit = random(DEFAULT_MIN_FRAU_HAEMATOKRIT, DEFAULT_MAX_FRAU_HAEMATOKRIT);
		}
	}

	/**
	 * Ändert ein normales Blutbild entsprechend einer Krankheit ab
	 * @param geschlecht
	 * @param krankheit
	 */
	private void erstelleKrankesBlutbild(EGeschlecht geschlecht, EKrankheit krankheit) {

		switch (krankheit) {
		/*
		 * case HIV: this.leukozyten=random(krankLeukozytenGeringLeichtMin,
		 * krankLeukozytenGeringLeichtMax); break; case BILHARZIOSE: // keine auswirkung
		 * break;
		 */
		case HAUTLEISHMANIASIS:
			// keine auswirkung
			break;
		case MASERN:
			// keine auswirkung
			break;
		case HEP_A:
			// keine auswirkung
			break;
		case HEP_B:
			// keine auswirkung
			break;
		/*
		 * case CHOLERA: // keine auswirkung break; case TETANUS: // keine auswirkung
		 * break; case GELBFIEBER:
		 * this.leukozyten=random(krankLeukozytenErhoetLeichtMin,
		 * krankLeukozytenErhoetLeichtMax);
		 * this.thrombozyten=random(krankThrombozytenGeringLeichtMin,
		 * krankThrombozytenGeringLeichtMax); break; case DENGUE_FIEBER:
		 * this.thrombozyten = random(krankThrombozytenErhoetStarkMin,
		 * krankThrombozytenErhoetStarkMax); break;
		 */

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

	/**
	 * erstelle das Blutbild als JSON mit allen Grenzwerten
	 * @return
	 */
	public String toString() {

		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		// TODO Budget anpassen
		objectNode.put("budget", "900");
		objectNode.put("name", "Blutbild");
		// objectNode.put("BlutbildID", this.blutbildID);
		objectNode.put("Haemoglobinkonzentration", this.Haemoglobinkonzentration);
		if (this.geschlecht.equals(EGeschlecht.MAENNLICH)) {
			objectNode.put("Haemoglobinkonzentration_min_normal", DEFAULT_MIN_MANN_Haemoglobinkonzentration);
			objectNode.put("Haemoglobinkonzentration_max_normal", DEFAULT_MAX_MANN_Haemoglobinkonzentration);
			objectNode.put("Haemoglobinkonzentration_min_veringert", KRANK_Haemoglobinkonzentration_MANN_GERING_LEICHT);
			objectNode.put("Haemoglobinkonzentration_max_veringert", KRANK_Haemoglobinkonzentration_MANN_GERING_STARK);
			objectNode.put("Haemoglobinkonzentration_min_erhoeht", KRANK_Haemoglobinkonzentration_MANN_ERHOET_LEICHT);
			objectNode.put("Haemoglobinkonzentration_max_erhoeht", KRANK_Haemoglobinkonzentration_MANN_ERHOET_STARK);
		} else {
			objectNode.put("Haemoglobinkonzentration_min_normal", DEFAULT_MIN_FRAU_Haemoglobinkonzentration);
			objectNode.put("Haemoglobinkonzentration_max_normal", DEFAULT_MAX_FRAU_Haemoglobinkonzentration);
			objectNode.put("Haemoglobinkonzentration_min_veringert", KRANK_Haemoglobinkonzentration_FRAU_GERING_LEICHT);
			objectNode.put("Haemoglobinkonzentration_max_veringert", KRANK_Haemoglobinkonzentration_FRAU_GERING_STARK);
			objectNode.put("Haemoglobinkonzentration_min_erhoeht", KRANK_Haemoglobinkonzentration_FRAU_ERHOET_LEICHT);
			objectNode.put("Haemoglobinkonzentration_max_erhoeht", KRANK_Haemoglobinkonzentration_FRAU_ERHOET_STARK);
		}

		objectNode.put("Leukozyten", this.leukozyten);
		objectNode.put("Leukozyten_min_normal", DEFAULT_MIN_LEUKOZYTEN);
		objectNode.put("Leukozyten_max_normal", DEFAULT_MAX_LEUKOZYTEN);
		objectNode.put("Leukozyten_min_veringert", KRANK_LEUKOZYTEN_GERING_LEICHT);
		objectNode.put("Leukozyten_max_veringert", KRANK_LEUKOZYTEN_GERING_STARK);
		objectNode.put("Leukozyten_min_erhoeht", KRANK_LEUKOZYTEN_ERHOET_LEICHT);
		objectNode.put("Leukozyten_max_erhoeht", KRANK_LEUKOZYTEN_ERHOET_STARK);
		

		objectNode.put("Thrombozyten", this.thrombozyten);
		objectNode.put("Thrombozyten_min_normal", DEFAULT_MIN_THROMBOZYTEN);
		objectNode.put("Thrombozyten_max_normal", DEFAULT_MAX_THROMBOZYTEN);
		objectNode.put("Thrombozyten_min_veringert", KRANK_THROMBOZYTEN_GERING_LEICHT);
		objectNode.put("Thrombozyten_max_veringert", KRANK_THROMBOZYTEN_GERING_STARK);
		objectNode.put("Thrombozyten_min_erhoeht", KRANK_THROMBOZYTEN_ERHOET_LEICHT);
		objectNode.put("Thrombozyten_max_erhoeht", KRANK_THROMBOZYTEN_ERHOET_STARK);
		
		
		objectNode.put("Haemoglobinkonzentration", this.haemoglobinkonzentration);
		if (this.geschlecht.equals(EGeschlecht.MAENNLICH)) {
			objectNode.put("Haemoglobinkonzentration_min_normal", DEFAULT_MIN_MANN_HAEMOGLOBINKONZENTRATION);
			objectNode.put("Haemoglobinkonzentration_max_normal", DEFAULT_MAX_MANN_HAEMOGLOBINKONZENTRATION);
			objectNode.put("Haemoglobinkonzentration_min_veringert", KRANK_HAEMOGLOBINKONZENTRATION_MANN_GERING_LEICHT);
			objectNode.put("Haemoglobinkonzentration_max_veringert", KRANK_HAEMOGLOBINKONZENTRATION_MANN_GERING_STARK);
			objectNode.put("Haemoglobinkonzentration_min_erhoeht", KRANK_HAEMOGLOBINKONZENTRATION_MANN_ERHOET_LEICHT);
			objectNode.put("Haemoglobinkonzentration_max_erhoeht", KRANK_HAEMOGLOBINKONZENTRATION_MANN_ERHOET_STARK);
		} else {
			objectNode.put("Haemoglobinkonzentration_min_normal", DEFAULT_MIN_FRAU_HAEMOGLOBINKONZENTRATION);
			objectNode.put("Haemoglobinkonzentration_max_normal", DEFAULT_MAX_FRAU_HAEMOGLOBINKONZENTRATION);
			objectNode.put("Haemoglobinkonzentration_min_veringert", KRANK_HAEMOGLOBINKONZENTRATION_FRAU_GERING_LEICHT);
			objectNode.put("Haemoglobinkonzentration_max_veringert", KRANK_HAEMOGLOBINKONZENTRATION_FRAU_GERING_STARK);
			objectNode.put("Haemoglobinkonzentration_min_erhoeht", KRANK_HAEMOGLOBINKONZENTRATION_FRAU_ERHOET_LEICHT);
			objectNode.put("Haemoglobinkonzentration_max_erhoeht", KRANK_HAEMOGLOBINKONZENTRATION_FRAU_ERHOET_STARK);
		}
		
		
		objectNode.put("Haematokrit", this.haematokrit);
		if (this.geschlecht.equals(EGeschlecht.MAENNLICH)) {
			objectNode.put("Haematokrit_min_normal", DEFAULT_MIN_MANN_HAEMATOKRIT);
			objectNode.put("Haematokrit_max_normal", DEFAULT_MAX_MANN_HAEMATOKRIT);
			objectNode.put("Haematokrit_min_veringert", KRANK_HAEMATOKRIT_MANN_GERING_LEICHT);
			objectNode.put("Haematokrit_max_veringert", KRANK_HAEMATOKRIT_MANN_GERING_STARK);
			objectNode.put("Haematokrit_min_erhoeht", KRANK_HAEMATOKRIT_MANN_ERHOET_LEICHT);
			objectNode.put("Haematokrit_max_erhoeht", KRANK_HAEMATOKRIT_MANN_ERHOET_STARK);
		} else {
			objectNode.put("Haematokrit_min_normal", DEFAULT_MIN_FRAU_HAEMATOKRIT);
			objectNode.put("Haematokrit_max_normal", DEFAULT_MAX_FRAU_HAEMATOKRIT);
			objectNode.put("Haematokrit_min_veringert", KRANK_HAEMATOKRIT_FRAU_GERING_LEICHT);
			objectNode.put("Haematokrit_max_veringert", KRANK_HAEMATOKRIT_FRAU_GERING_STARK);
			objectNode.put("Haematokrit_min_erhoeht", KRANK_HAEMATOKRIT_FRAU_ERHOET_LEICHT);
			objectNode.put("Haematokrit_max_erhoeht", KRANK_HAEMATOKRIT_FRAU_ERHOET_STARK);
		}
		
		
		objectNode.put("MCH", this.mch);		
		objectNode.put("MCH_min_normal", DEFAULT_MIN_MCH);
		objectNode.put("MCH_max_normal", DEFAULT_MAX_MCH);
		objectNode.put("MCH_min_veringert", KRANK_MCH_GERING_LEICHT);
		objectNode.put("MCH_max_veringert", KRANK_MCH_GERING_STARK);
		objectNode.put("MCH_min_erhoeht", KRANK_MCH_ERHOET_LEICHT);
		objectNode.put("MCH_max_erhoeht", KRANK_MCH_ERHOET_STARK);
		
		
		objectNode.put("MCHC", this.mchc);
		objectNode.put("MCHC_min_normal", DEFAULT_MIN_MCHC);
		objectNode.put("MCHC_max_normal", DEFAULT_MAX_MCHC);
		objectNode.put("MCHC_min_veringert", KRANK_MCHC_GERING_LEICHT);
		objectNode.put("MCHC_max_veringert", KRANK_MCHC_GERING_STARK);
		objectNode.put("MCHC_min_erhoeht", KRANK_MCHC_ERHOET_LEICHT);
		objectNode.put("MCHC_max_erhoeht", KRANK_MCHC_ERHOET_STARK);
		
		
		objectNode.put("MCV", this.mcv);
		objectNode.put("MCV_min_normal", DEFAULT_MIN_MCV);
		objectNode.put("MCV_max_normal", DEFAULT_MAX_MCV);
		objectNode.put("MCV_min_veringert", KRANK_MCV_GERING_LEICHT);
		objectNode.put("MCV_max_veringert", KRANK_MCV_GERING_STARK);
		objectNode.put("MCV_min_erhoeht", KRANK_MCV_ERHOET_LEICHT);
		objectNode.put("MCV_max_erhoeht", KRANK_MCV_ERHOET_STARK);

		return objectNode.toString();
	}
}
