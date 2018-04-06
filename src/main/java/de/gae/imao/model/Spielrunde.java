package de.gae.imao.model;

public class Spielrunde {
	private String nachricht;
	private long budget;
	private long ruf;
	private int wartendePatienten;
	private int runde;

	public Spielrunde() {
	}

	public Spielrunde(String nachricht, long budget, long ruf, int wartendePatienten, int runde) {
		this.nachricht = nachricht;
		this.budget = budget;
		this.ruf = ruf;
		this.wartendePatienten = wartendePatienten;
		this.runde = runde;
	}

	public String getNachricht() {
		return nachricht;
	}

	public void setNachricht(String nachricht) {
		this.nachricht = nachricht;
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

	public int getWartendePatienten() {
		return wartendePatienten;
	}

	public void setWartendePatienten(int wartendePatienten) {
		this.wartendePatienten = wartendePatienten;
	}

	public int getRunde() {
		return runde;
	}

	public void setRunde(int runde) {
		this.runde = runde;
	}

	@Override
	public String toString() {
		return "Spielrunde [Message=" + nachricht + ", Budget=" + budget + ", Ruf=" + ruf + ", WartendePatienten="
				+ wartendePatienten + ", Runde=" + runde + "]";
	}
}
