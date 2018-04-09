package de.hsmannheim.ss18.gae.imao.model;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {

	private int patientID;
	private int erscheinungsID;
	private String krankheit;

	public Patient() {
	}

	public Patient(String vorname,String nachname, int alter, int patientID, int erscheinungsID) {
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
	
	@Override
	public String toString() {
		String string = "{\"name\":{\"vorname\":\"Justus\", \"nachname\":\"Jonas\"}, \"alter\":18, \"ID\":1234}";
		return string;
	}

}
