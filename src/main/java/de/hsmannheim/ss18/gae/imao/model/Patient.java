package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Patient extends Person {

	private static int IDcount = 0;
	private int patientID;
	private int alter;
	private EGeschlecht geschlecht;
	private int erscheinungsID;
	private EKrankheit krankheit;
	private EDiagnoseErgebniss diagnose = EDiagnoseErgebniss.KEINE_DIAGNOSE;

	/**
	 * 
	 */
	public Patient() {

	}

	/**
	 * 
	 * @param vorname
	 * @param nachname
	 * @param alter
	 * @param geschlecht
	 * @param krankheit
	 */
	public Patient(String vorname, String nachname, int alter, EGeschlecht geschlecht, EKrankheit krankheit) {
		super(vorname, nachname);
		this.geschlecht = geschlecht;
		this.alter = alter;
		this.krankheit = krankheit;
		this.patientID = IDcount++;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public int getErscheinungsID() {
		return erscheinungsID;
	}

	public void setErscheinungsID(int erscheinungsID) {
		this.erscheinungsID = erscheinungsID;
	}

	public static int getIDcount() {
		return IDcount;
	}

	public static void setIDcount(int iDcount) {
		IDcount = iDcount;
	}

	public EKrankheit getKrankheit() {
		return krankheit;
	}

	public void setKrankheit(EKrankheit krankheit) {
		this.krankheit = krankheit;
	}

	public EDiagnoseErgebniss getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(EDiagnoseErgebniss diagnose) {
		this.diagnose = diagnose;
	}

	@Override
	public String toString() {

		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNodeName = mapper.createObjectNode();
		objectNodeName.put("vorname", this.getVorname());
		objectNodeName.put("nachname", this.getNachname());

		ObjectNode objectNodeSymptome = mapper.createObjectNode();
		objectNodeSymptome.put("S1", "s1");
		objectNodeSymptome.put("S2", "s2");
		objectNodeSymptome.put("S3", "s3");
		objectNodeSymptome.put("S4", "s4");
		objectNodeSymptome.put("S5", "s5");

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("name", objectNodeName);
		objectNode.put("symptome", objectNodeSymptome);
		objectNode.put("alter", this.alter);
		objectNode.put("ID", this.getErscheinungsID());

		return objectNode.toString();

		/*
		 * { "name": {"vorname":"Max","nachname":"Mustermann"}, "symptome":
		 * {"S1":"s1","S2":"s2","S3":"s3","S4":"s4","S5":"s5"}, "alter":"",
		 * "geschlecht":"m/w", "ID":"" }
		 */
	}

}
