package de.gae.imao.model;

public class DiagnoseErgebniss {
	private String nachricht;
	private long budget;
	private long ruf;

	public DiagnoseErgebniss() {
	}

	public DiagnoseErgebniss(String nachricht, long budget, long ruf) {
		this.nachricht = nachricht;
		this.budget = budget;
		this.ruf = ruf;
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

}
