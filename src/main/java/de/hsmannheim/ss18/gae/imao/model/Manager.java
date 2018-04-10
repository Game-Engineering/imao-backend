package de.hsmannheim.ss18.gae.imao.model;

public class Manager extends Person {

	public Manager() {
		super();
	}

	/**
	 * 
	 * @param String
	 *            vorname
	 * @param String
	 *            nachname
	 * @param int
	 *            alter
	 */
	public Manager(String vorname, String nachname, int alter, EGeschlecht geschlecht) {
		super(vorname, nachname, alter, geschlecht);
	}
}
