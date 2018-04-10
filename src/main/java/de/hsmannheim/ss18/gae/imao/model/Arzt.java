package de.hsmannheim.ss18.gae.imao.model;

public class Arzt extends Person {

	private String foo = "bar";

	public Arzt() {
		super();
	}

/**
 * 
 * @param vorname
 * @param nachname
 */
	public Arzt(String vorname, String nachname) {
		super(vorname, nachname);
	}

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

}
