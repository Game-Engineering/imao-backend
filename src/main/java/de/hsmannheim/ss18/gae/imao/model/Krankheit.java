package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Krankheit {
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
			this.ultraschall = new Ultraschall(patientID, 1);
			this.roentgen = new Roentgen(patientID, 1);
			this.blutbild = new Blutbild(patientID, 1);
			this.anamnese = new Anamnese(patientID, 1);
			this.erscheinung = 1;
			this.ID = 1;
			break;
		case CHOLERA:
			this.ultraschall = new Ultraschall(patientID, 2);
			this.roentgen = new Roentgen(patientID, 2);
			this.blutbild = new Blutbild(patientID, 2);
			this.anamnese = new Anamnese(patientID, 2);
			this.erscheinung = 2;
			this.ID = 2;
			break;
		case BILHARZIOSE:
			this.ultraschall = new Ultraschall(patientID, 3);
			this.roentgen = new Roentgen(patientID, 3);
			this.blutbild = new Blutbild(patientID, 3);
			this.anamnese = new Anamnese(patientID, 3);
			this.erscheinung = 3;
			this.ID = 3;
			break;
		case HIV:
			this.ultraschall = new Ultraschall(patientID, 4);
			this.roentgen = new Roentgen(patientID, 4);
			this.blutbild = new Blutbild(patientID, 4);
			this.anamnese = new Anamnese(patientID, 4);
			this.erscheinung = 4;
			this.ID = 4;
			break;
		case HAUTLEISHMANIASIS:
			this.ultraschall = new Ultraschall(patientID, 10);
			this.roentgen = new Roentgen(patientID, 10);
			this.blutbild = new Blutbild(patientID, 10);
			this.anamnese = new Anamnese(patientID, 10);
			this.erscheinung = 10;
			this.ID = 10;
			break;
		case HEP_A:
			this.ultraschall = new Ultraschall(patientID, 5);
			this.roentgen = new Roentgen(patientID, 5);
			this.blutbild = new Blutbild(patientID, 5);
			this.anamnese = new Anamnese(patientID, 5);
			this.erscheinung = 5;
			this.ID = 5;
			break;
		case HEP_B:
			this.ultraschall = new Ultraschall(patientID, 6);
			this.roentgen = new Roentgen(patientID, 6);
			this.blutbild = new Blutbild(patientID, 6);
			this.anamnese = new Anamnese(patientID, 6);
			this.erscheinung = 6;
			this.ID = 6;
			break;
		case TETANUS:
			this.ultraschall = new Ultraschall(patientID, 7);
			this.roentgen = new Roentgen(patientID, 7);
			this.blutbild = new Blutbild(patientID, 7);
			this.anamnese = new Anamnese(patientID, 7);
			this.erscheinung = 7;
			this.ID = 7;
			break;
		case GELBFIEBER:
			this.ultraschall = new Ultraschall(patientID, 8);
			this.roentgen = new Roentgen(patientID, 8);
			this.blutbild = new Blutbild(patientID, 8);
			this.anamnese = new Anamnese(patientID, 8);
			this.erscheinung = 8;
			this.ID = 8;
			break;
		case DENGUE_FIEBER:
			this.ultraschall = new Ultraschall(patientID, 9);
			this.roentgen = new Roentgen(patientID, 9);
			this.blutbild = new Blutbild(patientID, 9);
			this.anamnese = new Anamnese(patientID, 9);
			this.erscheinung = 9;
			this.ID = 9;
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
