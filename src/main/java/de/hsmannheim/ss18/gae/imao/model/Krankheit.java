package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Krankheit implements KrankheitID {
	private EKrankheit krankheit;
	private Ultraschall ultraschall;
	private Roentgen roentgen;
	private int erscheinung;
	private Blutbild blutbild;
	private Anamnese anamnese;
	private ESymptom[] symptome;
	int patientID;

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

	public Krankheit(EKrankheit krankheit, int patientID) {
		super();
		this.krankheit = krankheit;
		switch (krankheit) {
		case MASERN:
			this.ultraschall = new Ultraschall(patientID, MASERN);
			this.roentgen = new Roentgen(patientID, MASERN);
			this.blutbild = new Blutbild(patientID, MASERN);
			this.symptome = SYMPTOME_MASERN;
			this.anamnese = new Anamnese(this);
			this.erscheinung = 1;
			this.patientID = MASERN;

			break;
		case CHOLERA:
			this.ultraschall = new Ultraschall(patientID, CHOLERA);
			this.roentgen = new Roentgen(patientID, CHOLERA);
			this.blutbild = new Blutbild(patientID, CHOLERA);
			this.symptome = SYMPTOME_CHOLERA;
			this.anamnese = new Anamnese(this);
			this.erscheinung = 2;
			this.patientID = CHOLERA;

			break;
		case BILHARZIOSE:
			this.ultraschall = new Ultraschall(patientID, BILHARZIOSE);
			this.roentgen = new Roentgen(patientID, BILHARZIOSE);
			this.blutbild = new Blutbild(patientID, BILHARZIOSE);
			this.symptome = SYMPTOME_BILHARZIOSE;
			this.anamnese = new Anamnese(this);
			this.erscheinung = 3;
			this.patientID = BILHARZIOSE;

			break;
		case HIV:
			this.ultraschall = new Ultraschall(patientID, HIV);
			this.roentgen = new Roentgen(patientID, HIV);
			this.blutbild = new Blutbild(patientID, HIV);
			this.symptome = SYMPTOME_HIV;
			this.anamnese = new Anamnese(this);
			this.erscheinung = 4;
			this.patientID = HIV;
			
			break;
		case HAUTLEISHMANIASIS:
			this.ultraschall = new Ultraschall(patientID, HAUTLEISHMANIASIS);
			this.roentgen = new Roentgen(patientID, HAUTLEISHMANIASIS);
			this.blutbild = new Blutbild(patientID, HAUTLEISHMANIASIS);
			this.symptome = SYMPTOME_HAUTLEISHMANIASIS;
			this.anamnese = new Anamnese(this);
			this.erscheinung = 10;
			this.patientID = HAUTLEISHMANIASIS;
			
			break;
		case HEP_A:
			this.ultraschall = new Ultraschall(patientID, HEP_A);
			this.roentgen = new Roentgen(patientID, HEP_A);
			this.blutbild = new Blutbild(patientID, HEP_A);
			this.symptome = SYMPTOME_HEP;
			this.anamnese = new Anamnese(this);
			this.erscheinung = 5;
			this.patientID = HEP_A;
			
			break;
		case HEP_B:
			this.ultraschall = new Ultraschall(patientID, HEP_B);
			this.roentgen = new Roentgen(patientID, HEP_B);
			this.blutbild = new Blutbild(patientID, HEP_B);
			this.symptome = SYMPTOME_HEP;
			this.anamnese = new Anamnese(this);
			this.erscheinung = 6;
			this.patientID = HEP_B;
			
			break;
		case TETANUS:
			this.ultraschall = new Ultraschall(patientID, TETANUS);
			this.roentgen = new Roentgen(patientID, TETANUS);
			this.blutbild = new Blutbild(patientID, TETANUS);
			this.symptome = SYMPTOME_TETANUS;
			this.anamnese = new Anamnese(this);
			this.erscheinung = 7;
			this.patientID = TETANUS;
			
			break;
		case GELBFIEBER:
			this.ultraschall = new Ultraschall(patientID, GELBFIEBER);
			this.roentgen = new Roentgen(patientID, GELBFIEBER);
			this.blutbild = new Blutbild(patientID, GELBFIEBER);
			this.symptome = SYMPTOME_GELBFIEBER;
			this.anamnese = new Anamnese(this);
			this.erscheinung = 8;
			this.patientID = GELBFIEBER;
			
			break;
		case DENGUE_FIEBER:
			this.ultraschall = new Ultraschall(patientID, DENGUE_FIEBER);
			this.roentgen = new Roentgen(patientID, DENGUE_FIEBER);
			this.blutbild = new Blutbild(patientID, DENGUE_FIEBER);
			this.symptome = SYMPTOME_DENGUE_FIEBER;
			this.anamnese = new Anamnese(this);
			this.erscheinung = 9;
			this.patientID = DENGUE_FIEBER;
			
			break;

		default:

		}
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("erscheinung", this.erscheinung);

		return objectNode.toString();
	}

}
