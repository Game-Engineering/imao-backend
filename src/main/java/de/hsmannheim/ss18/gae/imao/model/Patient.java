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

	private Krankheit krankheit;
	private EDiagnoseErgebnis diagnose = EDiagnoseErgebnis.KEINE_DIAGNOSE;

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
		this.patientID = IDcount++;
		this.krankheit = new Krankheit(krankheit, this);

	}

	public EGeschlecht getGeschlecht() {
		return geschlecht;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public Krankheit getKrankheit() {
		return krankheit;
	}

	public EDiagnoseErgebnis getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(EDiagnoseErgebnis diagnose) {
		this.diagnose = diagnose;
	}

	public int getAlter() {
		return alter;
	}

	@Override
	public String toString() {

		ObjectMapper mapper = new ObjectMapper();

		// ObjectNode objectNodeName = mapper.cre / ateObjectNode();

		ObjectNode objectNode = mapper.createObjectNode();
		// objectNode.put("name", objectNodeName);
		// objectNode.put("symptome", objectNodeSymptome);
		objectNode.put("vorname", this.getVorname());
		objectNode.put("nachname", this.getNachname());
		objectNode.put("alter", this.alter);
		objectNode.put("geschlecht", this.geschlecht.toString());
		objectNode.put("erscheinungID", this.krankheit.getErscheinung());
		objectNode.put("ID", this.patientID);

		return objectNode.toString();
	}

}
