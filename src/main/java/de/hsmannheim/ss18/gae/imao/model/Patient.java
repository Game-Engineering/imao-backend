package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.hsmannheim.ss18.gae.imao.model.enums.EDiagnoseErgebnis;
import de.hsmannheim.ss18.gae.imao.model.enums.EGeschlecht;
import de.hsmannheim.ss18.gae.imao.model.enums.EKrankheit;

public class Patient extends Person {

	private static int IDcount = 0;
	private int patientID;
	private int alter;

	private Krankheit krankheit;
	private EDiagnoseErgebnis diagnose = EDiagnoseErgebnis.KEINE_DIAGNOSE;

	/**
	 * 
	 * @param vorname
	 * @param nachname
	 * @param alter
	 * @param geschlecht
	 * @param krankheit
	 */
	public Patient(String vorname, String nachname, int alter, EGeschlecht geschlecht, EKrankheit krankheit) {
		super(vorname, nachname, geschlecht);
		this.alter = alter;
		this.patientID = IDcount++;
		this.krankheit = new Krankheit(krankheit, this);

	}

	@Override
	public String toString() {

		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("vorname", this.getVorname());
		objectNode.put("nachname", this.getNachname());
		objectNode.put("alter", this.alter);
		objectNode.put("geschlecht", this.geschlecht.toString());
		objectNode.put("erscheinungID", this.krankheit.getErscheinung());
		objectNode.put("ID", this.patientID);

		return objectNode.toString();
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
	public static void resetIDCount(){
		IDcount=0;
	}

}
