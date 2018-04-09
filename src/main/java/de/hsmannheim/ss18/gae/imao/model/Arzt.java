package de.hsmannheim.ss18.gae.imao.model;

public class Arzt extends Person {

	private String foo = "bar";

	public Arzt() {
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
	public Arzt(String vorname, String nachname, int alter) {
		super(vorname, nachname, alter);
	}

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

}
