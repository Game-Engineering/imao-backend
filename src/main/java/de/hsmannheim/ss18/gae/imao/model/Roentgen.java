package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Roentgen {
	private int patientID;
	private int roentgenID;

	private final int ROENTGEN_GESUND = 0;
	private final int ROENTGEN_K1 = 1;
	private final int ROENTGEN_K2 = 2;
	private final int ROENTGEN_K3 = 3;
	private final int ROENTGEN_K4 = 4;
	private final int ROENTGEN_K5 = 5;
	private final int ROENTGEN_K6 = 6;

	/**
	 * 
	 * @param int
	 *            patientID
	 */
	public Roentgen(int patientID, int roentgenID) {
		this.patientID = patientID;
		this.roentgenID = roentgenID;
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("name", "Roentgen");
		objectNode.put("budget", "900");
		objectNode.put("RoentgenID", this.roentgenID);

		return objectNode.toString();
	}

}
