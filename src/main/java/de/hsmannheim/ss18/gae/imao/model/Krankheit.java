package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Krankheit implements KrankheitID{
	private EKrankheit krankheit;
	private Ultraschall ultraschall;
	private Roentgen roentgen;
	private int erscheinung;
	private Blutbild blutbild;
	private Anamnese anamnese;
	int ID;

	public Krankheit(EKrankheit krankheit, int patientID) {
		super();
		this.krankheit = krankheit;
		switch (krankheit) {
		case MASERN:
			this.ultraschall = new Ultraschall(patientID, MASERN);
			this.roentgen = new Roentgen(patientID, MASERN);
			this.blutbild = new Blutbild(patientID, MASERN);
			this.anamnese = new Anamnese(patientID, MASERN);
			this.erscheinung = 1;
			this.ID = MASERN;
			break;
		case CHOLERA:
			this.ultraschall = new Ultraschall(patientID, CHOLERA);
			this.roentgen = new Roentgen(patientID, CHOLERA);
			this.blutbild = new Blutbild(patientID, CHOLERA);
			this.anamnese = new Anamnese(patientID, CHOLERA);
			this.erscheinung = 2;
			this.ID = CHOLERA;
			break;
		case BILHARZIOSE:
			this.ultraschall = new Ultraschall(patientID, BILHARZIOSE);
			this.roentgen = new Roentgen(patientID, BILHARZIOSE);
			this.blutbild = new Blutbild(patientID, BILHARZIOSE);
			this.anamnese = new Anamnese(patientID, BILHARZIOSE);
			this.erscheinung = 3;
			this.ID = BILHARZIOSE;
			break;
		case HIV:
			this.ultraschall = new Ultraschall(patientID, HIV);
			this.roentgen = new Roentgen(patientID, HIV);
			this.blutbild = new Blutbild(patientID, HIV);
			this.anamnese = new Anamnese(patientID, HIV);
			this.erscheinung = 4;
			this.ID = HIV;
			break;
		case HAUTLEISHMANIASIS:
			this.ultraschall = new Ultraschall(patientID, HAUTLEISHMANIASIS);
			this.roentgen = new Roentgen(patientID, HAUTLEISHMANIASIS);
			this.blutbild = new Blutbild(patientID, HAUTLEISHMANIASIS);
			this.anamnese = new Anamnese(patientID, HAUTLEISHMANIASIS);
			this.erscheinung = 10;
			this.ID = HAUTLEISHMANIASIS;
			break;
		case HEP_A:
			this.ultraschall = new Ultraschall(patientID, HEP_A);
			this.roentgen = new Roentgen(patientID, HEP_A);
			this.blutbild = new Blutbild(patientID, HEP_A);
			this.anamnese = new Anamnese(patientID, HEP_A);
			this.erscheinung = 5;
			this.ID = HEP_A;
			break;
		case HEP_B:
			this.ultraschall = new Ultraschall(patientID, HEP_B);
			this.roentgen = new Roentgen(patientID, HEP_B);
			this.blutbild = new Blutbild(patientID, HEP_B);
			this.anamnese = new Anamnese(patientID, HEP_B);
			this.erscheinung = 6;
			this.ID = HEP_B;
			break;
		case TETANUS:
			this.ultraschall = new Ultraschall(patientID, TETANUS);
			this.roentgen = new Roentgen(patientID, TETANUS);
			this.blutbild = new Blutbild(patientID, TETANUS);
			this.anamnese = new Anamnese(patientID, TETANUS);
			this.erscheinung = 7;
			this.ID = TETANUS;
			break;
		case GELBFIEBER:
			this.ultraschall = new Ultraschall(patientID, GELBFIEBER);
			this.roentgen = new Roentgen(patientID, GELBFIEBER);
			this.blutbild = new Blutbild(patientID, GELBFIEBER);
			this.anamnese = new Anamnese(patientID, GELBFIEBER);
			this.erscheinung = 8;
			this.ID = GELBFIEBER;
			break;
		case DENGUE_FIEBER:
			this.ultraschall = new Ultraschall(patientID, DENGUE_FIEBER);
			this.roentgen = new Roentgen(patientID, DENGUE_FIEBER);
			this.blutbild = new Blutbild(patientID, DENGUE_FIEBER);
			this.anamnese = new Anamnese(patientID, DENGUE_FIEBER);
			this.erscheinung = 9;
			this.ID = DENGUE_FIEBER;
			break;

		default:

		}
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

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("erscheinung", this.erscheinung);

		return objectNode.toString();
	}

}
