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
	Patient patient;

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
	
	public Patient getPatient() {
		return patient;
	}

	public Krankheit(EKrankheit krankheit, Patient patient) {
		this.krankheit = krankheit;
		this. patient=patient;
		switch (krankheit) {
		case MASERN:		
			this.symptome = SYMPTOME_MASERN;			
			this.erscheinung = 1;

			break;
		case CHOLERA:	
			this.symptome = SYMPTOME_CHOLERA;			
			this.erscheinung = 2;

			break;
		case BILHARZIOSE:		
			this.symptome = SYMPTOME_BILHARZIOSE;			
			this.erscheinung = 3;

			break;
		case HIV:		
			this.symptome = SYMPTOME_HIV;			
			this.erscheinung = 4;
		
			break;
		case HAUTLEISHMANIASIS:		
			this.symptome = SYMPTOME_HAUTLEISHMANIASIS;			
			this.erscheinung = 10;
			
			break;
		case HEP_A:
			this.symptome = SYMPTOME_HEP;
			this.erscheinung = 5;
			
			break;
		case HEP_B:
			this.symptome = SYMPTOME_HEP;
			this.erscheinung = 6;
		
			break;
		case TETANUS:
			this.symptome = SYMPTOME_TETANUS;
			this.erscheinung = 7;
			
			break;
		case GELBFIEBER:
			this.symptome = SYMPTOME_GELBFIEBER;
			this.erscheinung = 8;
		
			break;
		case DENGUE_FIEBER:
			this.symptome = SYMPTOME_DENGUE_FIEBER;
			this.erscheinung = 9;
			
			break;

		default:

		}
		this.blutbild = new Blutbild(this);
		this.anamnese = new Anamnese(this);
		this.roentgen=new Roentgen(this);
		this.ultraschall=new Ultraschall(this);
	}



	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("erscheinung", this.erscheinung);

		return objectNode.toString();
	}

}
