package de.hsmannheim.ss18.gae.imao.model;

public class Arzt extends Person {
	
	private String foo = "bar";

	public Arzt() {
		super();
	}

	public Arzt(String name, int alter) {
		super(name, alter);
	}

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}
	
}
