package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.hsmannheim.ss18.gae.imao.model.enums.EHautfarbe;
import de.hsmannheim.ss18.gae.imao.model.enums.EHautveränderung;
import de.hsmannheim.ss18.gae.imao.model.enums.EKrankheit;
import de.hsmannheim.ss18.gae.imao.model.enums.ESymptom;

public class Krankheit {
	private EKrankheit krankheit;
	private Ultraschall ultraschall;
	private Roentgen roentgen;
	private int erscheinung;
	private Blutbild blutbild;
	private Anamnese anamnese;
	private ESymptom[] symptome;
	private EHautfarbe symptomHautfarbe;
	private EHautveränderung symptomHautveraenderung;
	Patient patient;

	// Symptome für Krankheiten
	private static final ESymptom[] SYMPTOME_HIV = { ESymptom.JUCKREITZ, ESymptom.FIEBER_NORMAL, ESymptom.NACHTSCHWEIS,
			ESymptom.LYMPHKNOTEN_GESCHWOLLEN, ESymptom.GEWICHTSVERLUST };
	private static final ESymptom[] SYMPTOME_BILHARZIOSE = { ESymptom.JUCKREITZ, ESymptom.FIEBER_NORMAL,
			ESymptom.FIEBER_LANG, ESymptom.HARNWEGSBEFALL };
	private static final ESymptom[] SYMPTOME_HAUTLEISHMANIASIS = { ESymptom.EINSTICHSTELLE_KNOETCHENBILDUNG,
			ESymptom.EINSTICHSTELLE_BEULE };
	private static final ESymptom[] SYMPTOME_MASERN = { ESymptom.JUCKREITZ, ESymptom.FIEBER_NORMAL, ESymptom.HUSTEN,
			ESymptom.SCHNUPFEN, ESymptom.BINDEHAUTENTZUENDUNG, ESymptom.DURCHFALL };
	private static final ESymptom[] SYMPTOME_HEP = { ESymptom.FIEBER_NORMAL, ESymptom.MUEDIGKEIT,
			ESymptom.ERBRECHEN_UEBELKEIT, ESymptom.VOELLEGEFUEHL, ESymptom.HAUT_GELB, ESymptom.AUGEN_GELBFAERBUNG,
			ESymptom.URIN_BRAUN, ESymptom.STUHL_HELL };
	private static final ESymptom[] SYMPTOME_CHOLERA = { ESymptom.BRECHDURCHFALL };
	private static final ESymptom[] SYMPTOME_TETANUS = { ESymptom.MUSKELSCHMERZEN, ESymptom.KIEFERSPERRE,
			ESymptom.SCHLUCKSTOERUNG, ESymptom.ATEMSTOERUNG, ESymptom.GESICHTSMUSKELKRAEMPFE };
	private static final ESymptom[] SYMPTOME_GELBFIEBER = { ESymptom.FIEBER_NORMAL, ESymptom.FIEBER_HOCH_WECHSELHAFT,
			ESymptom.MUEDIGKEIT, ESymptom.ERBRECHEN_UEBELKEIT, ESymptom.ERBRECHEN_BLUT, ESymptom.HAUT_GELB };
	private static final ESymptom[] SYMPTOME_DENGUE_FIEBER = { ESymptom.FIEBER_NORMAL, ESymptom.ERBRECHEN_UEBELKEIT,
			ESymptom.ERBRECHEN_BLUT, ESymptom.MUSKELSCHMERZEN, ESymptom.BLUTUNG, ESymptom.SCHUETTELFROST,
			ESymptom.GELENKSCHMERZEN, ESymptom.PULS_NIEDRIG };

	/**
	 * erstelle ID abhängig von Geschlecht, Alter und Krankheit
	 * @param patient
	 * @return ID
	 */
	private int brerchneErscheinung(Patient patient) {
		int id = 0;

		switch (patient.getGeschlecht()) {
		case MAENNLICH:
			switch (krankheit) {
			case MASERN:
				if (patient.getAlter() <= 25) {
					id = 5;
				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
					id = 5;
				} else {
					id = 5;
				}
				break;
//			case CHOLERA:
//				if (patient.getAlter() <= 25) {
//					id = 8;
//				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
//					id = 8;
//				} else {
//					id = 8;
//				}
//				break;
//			case BILHARZIOSE:
//				if (patient.getAlter() <= 25) {
//					id = 1;
//				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
//					id = 1;
//				} else {
//					id = 1;
//				}
//				break;
//			case HIV:
//				if (patient.getAlter() <= 25) {
//					id = 1;
//				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
//					id = 1;
//				} else {
//					id = 1;
//				}
//				break;
			case HEP_A:
				if (patient.getAlter() <= 25) {
					id = 3;
				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
					id = 3;
				} else {
					id = 3;
				}
				break;
			case HEP_B:
				if (patient.getAlter() <= 25) {
					id = 3;
				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
					id = 3;
				} else {
					id = 3;
				}
				break;
//			case TETANUS:
//				if (patient.getAlter() <= 25) {
//					id = 1;
//				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
//					id = 1;
//				} else {
//					id = 1;
//				}
//				break;
//			case GELBFIEBER:
//				if (patient.getAlter() <= 25) {
//					id = 1;
//				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
//					id = 1;
//				} else {
//					id = 1;
//				}
//				break;
//			case DENGUE_FIEBER:
//				if (patient.getAlter() <= 25) {
//					id = 1;
//				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
//					id = 1;
//				} else {
//					id = 1;
//				}
//				break;
			case HAUTLEISHMANIASIS:
				if (patient.getAlter() <= 25) {
					id = 1;
				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
					id = 1;
				} else {
					id = 1;
				}
				break;
			default:
				id = 1;
			}
			break;
		case WEIBLICH:
			switch (krankheit) {
			case MASERN:
				if (patient.getAlter() <= 25) {
					id = 6;
				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
					id = 6;
				} else {
					id = 6;
				}
				break;
//			case CHOLERA:
//				if (patient.getAlter() <= 25) {
//					id = 1;
//				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
//					id = 1;
//				} else {
//					id = 1;
//				}
//				break;
//			case BILHARZIOSE:
//				if (patient.getAlter() <= 25) {
//					id = 1;
//				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
//					id = 1;
//				} else {
//					id = 1;
//				}
//				break;
//			case HIV:
//				if (patient.getAlter() <= 25) {
//					id = 7;
//				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
//					id = 7;
//				} else {
//					id = 7;
//				}
//				break;
			case HEP_A:
				if (patient.getAlter() <= 25) {
					id = 4;
				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
					id = 4;
				} else {
					id = 4;
				}
				break;
			case HEP_B:
				if (patient.getAlter() <= 25) {
					id = 4;
				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
					id = 4;
				} else {
					id = 4;
				}
				break;
//			case TETANUS:
//				if (patient.getAlter() <= 25) {
//					id = 1;
//				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
//					id = 1;
//				} else {
//					id = 1;
//				}
//				break;
//			case GELBFIEBER:
//				if (patient.getAlter() <= 25) {
//					id = 1;
//				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
//					id = 1;
//				} else {
//					id = 1;
//				}
//				break;
//			case DENGUE_FIEBER:
//				if (patient.getAlter() <= 25) {
//					id = 9;
//				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
//					id = 9;
//				} else {
//					id = 9;
//				}
//				break;
			case HAUTLEISHMANIASIS:
				if (patient.getAlter() <= 25) {
					id = 2;
				} else if (patient.getAlter() > 25 && patient.getAlter() <= 35) {
					id = 2;
				} else {
					id = 2;
				}
				break;
			default:
				id = 1;
			}
			break;
		default:
			id = 1;
		}
		return id;
	}

	/**
	 * 
	 * @return JSON mit allen vorhandenen Krankheiten
	 */
	public static String getAlleKrankheiten() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put(EKrankheit.MASERN.name(), EKrankheit.MASERN.getId());
//		objectNode.put(EKrankheit.CHOLERA.name(), EKrankheit.CHOLERA.getId());
//		objectNode.put(EKrankheit.BILHARZIOSE.name(), EKrankheit.BILHARZIOSE.getId());
//		objectNode.put(EKrankheit.HIV.name(), EKrankheit.HIV.getId());
		objectNode.put(EKrankheit.HEP_A.name(), EKrankheit.HEP_A.getId());
		objectNode.put(EKrankheit.HEP_B.name(), EKrankheit.HEP_B.getId());
//		objectNode.put(EKrankheit.TETANUS.name(), EKrankheit.TETANUS.getId());
//		objectNode.put(EKrankheit.GELBFIEBER.name(), EKrankheit.GELBFIEBER.getId());
//		objectNode.put(EKrankheit.DENGUE_FIEBER.name(), EKrankheit.DENGUE_FIEBER.getId());
		objectNode.put(EKrankheit.HAUTLEISHMANIASIS.name(), EKrankheit.HAUTLEISHMANIASIS.getId());

		return objectNode.toString();
	}

	public EKrankheit getKrankheit() {
		return krankheit;
	}

	public Ultraschall getUltraschall() {
		return ultraschall;
	}

	public Roentgen getRoentgen() {
		return roentgen;
	}

	public int getErscheinung() {
		return erscheinung;
	}

	public Blutbild getBlutbild() {
		return blutbild;
	}

	public Anamnese getAnamnese() {
		return anamnese;
	}

	public ESymptom[] getSymptome() {
		return symptome;
	}

	public Patient getPatient() {
		return patient;
	}

	/**
	 * erstellt Krankheit und Dazugehörige objekte (Annamnese, Blutbild, Roentgen und Ultraschall)
	 * @param krankheit
	 * @param patient
	 */
	public Krankheit(EKrankheit krankheit, Patient patient) {
		this.krankheit = krankheit;
		this.patient = patient;
		switch (krankheit) {
		case MASERN:
			this.symptome = SYMPTOME_MASERN;
			this.symptomHautfarbe = EHautfarbe.NORMAL;
			this.symptomHautveraenderung = EHautveränderung.PUSTELN;
			this.erscheinung = brerchneErscheinung(patient);

			break;
//		case CHOLERA:
//			this.symptome = SYMPTOME_CHOLERA;
//			this.symptomHautveraenderung = EHautveränderung.NORMAL;
//			this.symptomHautfarbe = EHautfarbe.NORMAL;
//			this.erscheinung = brerchneErscheinung(patient);
//
//			break;
//		case BILHARZIOSE:
//			this.symptome = SYMPTOME_BILHARZIOSE;
//			this.symptomHautveraenderung = EHautveränderung.NORMAL;
//			this.symptomHautfarbe = EHautfarbe.NORMAL;
//			this.erscheinung = brerchneErscheinung(patient);
//
//			break;
//		case HIV:
//			this.symptome = SYMPTOME_HIV;
//			this.symptomHautveraenderung = EHautveränderung.NORMAL;
//			this.symptomHautfarbe = EHautfarbe.NORMAL;
//			this.erscheinung = brerchneErscheinung(patient);
//
//			break;
		case HAUTLEISHMANIASIS:
			this.symptome = SYMPTOME_HAUTLEISHMANIASIS;
			this.symptomHautveraenderung = EHautveränderung.NORMAL;
			this.symptomHautfarbe = EHautfarbe.NORMAL;
			this.erscheinung = brerchneErscheinung(patient);

			break;
		case HEP_A:
			this.symptome = SYMPTOME_HEP;
			this.symptomHautveraenderung = EHautveränderung.NORMAL;
			this.symptomHautfarbe = EHautfarbe.GELB;
			this.erscheinung = brerchneErscheinung(patient);

			break;
		case HEP_B:
			this.symptome = SYMPTOME_HEP;
			this.symptomHautveraenderung = EHautveränderung.NORMAL;
			this.symptomHautfarbe = EHautfarbe.GELB;
			this.erscheinung = brerchneErscheinung(patient);

			break;
//		case TETANUS:
//			this.symptome = SYMPTOME_TETANUS;
//			this.symptomHautveraenderung = EHautveränderung.NORMAL;
//			this.symptomHautfarbe = EHautfarbe.NORMAL;
//			this.erscheinung = brerchneErscheinung(patient);
//
//			break;
//		case GELBFIEBER:
//			this.symptome = SYMPTOME_GELBFIEBER;
//			this.symptomHautveraenderung = EHautveränderung.NORMAL;
//			this.symptomHautfarbe = EHautfarbe.NORMAL;
//			this.erscheinung = brerchneErscheinung(patient);
//
//			break;
//		case DENGUE_FIEBER:
//			this.symptome = SYMPTOME_DENGUE_FIEBER;
//			this.symptomHautveraenderung = EHautveränderung.NORMAL;
//			this.symptomHautfarbe = EHautfarbe.NORMAL;
//			this.erscheinung = brerchneErscheinung(patient);
//
//			break;

		default:

		}
		this.blutbild = new Blutbild(this);
		this.anamnese = new Anamnese(this);
		this.roentgen = new Roentgen(this);
		this.ultraschall = new Ultraschall(this);
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("erscheinung", this.erscheinung);

		return objectNode.toString();
	}

}
