package de.hsmannheim.ss18.gae.imao.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Arzt extends Person {
	private List<Mail> posteingang = new ArrayList<>();
	private List<Mail> postausgang = new ArrayList<>();
	private long budget;
	private long ruf;

	/**
	 * 
	 * @param vorname
	 * @param nachname
	 */
	public Arzt(String vorname, String nachname, EGeschlecht geschlecht) {
		super(vorname, nachname, geschlecht);
	}

	public void erhalteMail(Mail mail) {
		this.posteingang.add(mail);
	}

	public void sendeMail(Mail mail) {
		this.postausgang.add(mail);
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("vorname", this.vorname);
		objectNode.put("nachname", this.nachname);
		objectNode.put("geschlecht", this.geschlecht.toString());

		return objectNode.toString();
	}

	public long getBudget() {
		return budget;
	}

	public void setBudget(long budget) {
		this.budget = budget;
	}

	public long getRuf() {
		return ruf;
	}

	public void setRuf(long ruf) {
		this.ruf = ruf;
	}

}
