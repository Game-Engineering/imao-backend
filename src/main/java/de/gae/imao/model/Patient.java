package de.gae.imao.model;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {

	private int patientID;
	private int erscheinungsID;
	private String krankheit;

	public Patient() {
	}

	public Patient(String name, int alter, int patientID, int erscheinungsID) {
		super(name, alter);
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

}
