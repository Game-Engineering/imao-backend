package de.hsmannheim.ss18.gae.imao.model.medizin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Diagnose {
	private String nachricht;
	private long budget;
	private long ruf;

	public Diagnose() {
	}

	/**
	 *
	 * @param nachricht
	 * @param budget
	 * @param ruf
	 */
	public Diagnose(String nachricht, long budget, long ruf) {
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

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("nachricht", this.nachricht);
		objectNode.put("budget", this.budget);
		objectNode.put("ruf", this.ruf);

		return objectNode.toString();
	}

}
