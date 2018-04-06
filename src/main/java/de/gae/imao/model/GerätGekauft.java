package de.gae.imao.model;

import java.util.List;

public class GerätGekauft {
	private List<Untersuchungsmethode> methoden;
	private long budget;

	public GerätGekauft() {
	}

	public GerätGekauft(List<Untersuchungsmethode> methoden, long budget) {
		this.methoden = methoden;
		this.budget = budget;
	}

	public List<Untersuchungsmethode> getMethoden() {
		return methoden;
	}

	public void setMethoden(List<Untersuchungsmethode> methoden) {
		this.methoden = methoden;
	}

	public long getBudget() {
		return budget;
	}

	public void setBudget(long budget) {
		this.budget = budget;
	}

}
