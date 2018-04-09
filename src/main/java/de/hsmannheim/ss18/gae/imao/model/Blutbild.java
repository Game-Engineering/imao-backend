package de.hsmannheim.ss18.gae.imao.model;

public class Blutbild {

	private String name;
	private int blutbildID;
	private long budget;

	public Blutbild() {
	}

	public Blutbild(String name, int blutbildID, long budget) {
		this.blutbildID = blutbildID;
		this.name = name;
		this.budget = budget;
	}

	public int getBlutbildID() {
		return blutbildID;
	}

	public void setBlutbildID(int blutbildID) {
		this.blutbildID = blutbildID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getBudget() {
		return budget;
	}

	public void setBudget(long budget) {
		this.budget = budget;
	}

}
