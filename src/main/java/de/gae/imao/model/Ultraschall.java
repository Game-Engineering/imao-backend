package de.gae.imao.model;

public class Ultraschall {

	private String name;
	private int ultraschallID;
	private long budget;

	public Ultraschall() {
	}

	public Ultraschall(String name, int ultraschallID, long budget) {
		this.ultraschallID = ultraschallID;
		this.name = name;
		this.budget = budget;
	}

	public int getUltraschallID() {
		return ultraschallID;
	}

	public void setUltraschallID(int ultraschallID) {
		this.ultraschallID = ultraschallID;
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
