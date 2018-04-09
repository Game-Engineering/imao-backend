package de.hsmannheim.ss18.gae.imao.model;

import java.util.Map;

public class Anamnese {
	private Map<String, String> fragebogen;
	private String name;
	private long budget;

	public Anamnese() {
	}

	public Anamnese(Map<String, String> fragebogen, String name, long budget) {
		this.fragebogen = fragebogen;
		this.name = name;
		this.budget = budget;
	}

	public Map<String, String> getFragebogen() {
		return fragebogen;
	}

	public void setFragebogen(Map<String, String> fragebogen) {
		this.fragebogen = fragebogen;
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

	@Override
	public String toString() {
		return "Untersuchungsmethode [Fragebogen=" + fragebogen + ", Name=" + name + ", Budget=" + budget + "]";
	}
}
