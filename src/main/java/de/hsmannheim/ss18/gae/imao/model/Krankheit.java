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
		switch (krankheit.toString()) {
		case "MASERN":
			this.ultraschall = new Ultraschall(patientID, 1);
			this.roentgen = new Roentgen(patientID, 1);
			this.blutbild = new Blutbild(patientID, 1);
			this.anamnese = new Anamnese(patientID, 1);
			this.erscheinung = 1;
			this.ID = 1;
			break;
		case "CHOLERA":
			this.ultraschall = new Ultraschall(patientID, 2);
			this.roentgen = new Roentgen(patientID, 2);
			this.blutbild = new Blutbild(patientID, 2);
			this.anamnese = new Anamnese(patientID, 2);
			this.erscheinung = 2;
			this.ID = 2;
			break;
		case "BILHARZIOSE":
			this.ultraschall = new Ultraschall(patientID, 3);
			this.roentgen = new Roentgen(patientID, 3);
			this.blutbild = new Blutbild(patientID, 3);
			this.anamnese = new Anamnese(patientID, 3);
			this.erscheinung = 3;
			this.ID = 3;
			break;
		default:
			krankheit = EKrankheit.MASERN;
			this.ultraschall = new Ultraschall(patientID, 1);
			this.roentgen = new Roentgen(patientID, 1);
			this.blutbild = new Blutbild(patientID, 1);
			this.anamnese = new Anamnese(patientID, 1);
			this.erscheinung = 1;
			this.ID = 1;
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
