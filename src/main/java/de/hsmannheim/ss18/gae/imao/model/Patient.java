package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Patient extends Person {

	private int patientID;
	private int erscheinungsID;
	private String krankheit;

	public Patient() {

	}

	/**
	 * 
	 * @param String
	 *            vorname
	 * @param String
	 *            nachname
	 * @param int
	 *            alter
	 * @param int
	 *            patientID
	 * @param int
	 *            erscheinungsID
	 */
	public Patient(String vorname, String nachname, int alter, int patientID, int erscheinungsID) {
		super(vorname, nachname, alter);
		this.patientID = patientID;
		this.erscheinungsID = erscheinungsID;
		krankheit = "Grippe";
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

	public void generierePatient() {
		Patient neuerPatient = new Patient();

	}

	@Override
	public String toString() {

		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNodeName = mapper.createObjectNode();
		objectNodeName.put("vorname", this.getVorname());
		objectNodeName.put("nachname",this.getNachname());

		ObjectNode objectNodeSymptome = mapper.createObjectNode();
		objectNodeSymptome.put("S1", "s1");
		objectNodeSymptome.put("S2", "s2");
		objectNodeSymptome.put("S3", "s3");
		objectNodeSymptome.put("S4", "s4");
		objectNodeSymptome.put("S5", "s5");
		
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("name", objectNodeName);
		objectNode.put("symptome", objectNodeSymptome);
		objectNode.put("alter", this.getAlter());
		objectNode.put("ID", this.getErscheinungsID());
		
		return objectNode.toString();

		/*
		 * {
		 * "name":
		 * 		{"vorname":"Max","nachname":"Mustermann"},
		 * "symptome":
		 * 		{"S1":"s1","S2":"s2","S3":"s3","S4":"s4","S5":"s5"},
		 * "alter":"",
		 * "geschlecht":"m/w",
		 * "ID":""
		 * }
		 * 
		 */
		//return string;
	}

}
