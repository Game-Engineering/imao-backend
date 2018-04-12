package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Ultraschall {
	private int patientID;
	private int ultraschallID;

	private final int ULTRASCHALL_GESUND = 0;
	private final int ULTRASCHALL_K1 = 1;
	private final int ULTRASCHALL_K2 = 2;
	private final int ULTRASCHALL_K3 = 3;
	private final int ULTRASCHALL_K4 = 4;
	private final int ULTRASCHALL_K5 = 5;
	private final int ULTRASCHALL_K6 = 6;

	/**
	 * 
	 */
	public Ultraschall() {

	}

	/**
	 * 
	 * @param int
	 *            patientID
	 */
	public Ultraschall(int patientID, int ultraschallID) {
		this.patientID = patientID;
		this.ultraschallID = ultraschallID;
	}

	/**
	 * 
	 */
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("name", "Ultraschall");
		objectNode.put("budget", "900");
		objectNode.put("UltraschallID", this.ultraschallID);

		return objectNode.toString();
	}

}
