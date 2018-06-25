package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.hsmannheim.ss18.gae.imao.model.enums.EGeschlecht;

public class Person {

	public String vorname;
	public String nachname;
	protected EGeschlecht geschlecht;

	/**
	 * Arzt und Manager Erben von dieser Klasse
	 * @param vorname
	 * @param nachname
	 * @param geschlecht
	 */
	public Person(String vorname, String nachname, EGeschlecht geschlecht) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.geschlecht = geschlecht;
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("vorname", this.vorname);
		objectNode.put("nachname", this.nachname);

		return objectNode.toString();
	}

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public EGeschlecht getGeschlecht() {
		return geschlecht;
	}

}
