package de.hsmannheim.ss18.gae.imao.model;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class GeraetGekauft {
	private List<Untersuchungsmethode> methoden;
	private long budget;

	public GeraetGekauft(List<Untersuchungsmethode> methoden, long budget) {
		this.methoden = methoden;
		this.budget = budget;
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("budget", this.budget);
		objectNode.put("wartendePatienten", this.methoden.toString());

		return objectNode.toString();
	}

}
