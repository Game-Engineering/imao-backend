package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Roentgen {
	private Krankheit krankheit;
	private int roentgenID;

	private static final int ROENTGEN_GESUND = 0;
	private static final int ROENTGEN_K1 = 1;
	private static final int ROENTGEN_K2 = 2;
	private static final int ROENTGEN_K3 = 3;
	private static final int ROENTGEN_K4 = 4;
	private static final int ROENTGEN_K5 = 5;
	private static final int ROENTGEN_K6 = 6;

	/**
	 * 
	 * @param krankheit
	 */
	public Roentgen(Krankheit krankheit) {
		//TODO reduziere Budget
		this.krankheit = krankheit;
		switch (krankheit.getKrankheit()) {
		case MASERN:
			this.roentgenID = ROENTGEN_GESUND;
			break;
		case CHOLERA:
			this.roentgenID = ROENTGEN_GESUND;
			break;
		case BILHARZIOSE:
			this.roentgenID = ROENTGEN_GESUND;
			break;
		case HIV:
			this.roentgenID = ROENTGEN_GESUND;
			break;
		case HAUTLEISHMANIASIS:
			this.roentgenID = ROENTGEN_GESUND;
			break;
		case HEP_A:
			this.roentgenID = ROENTGEN_GESUND;
			break;
		case HEP_B:
			this.roentgenID = ROENTGEN_GESUND;
			break;
		case TETANUS:
			this.roentgenID = ROENTGEN_GESUND;
			break;
		case GELBFIEBER:
			this.roentgenID = ROENTGEN_GESUND;
			break;
		case DENGUE_FIEBER:
			this.roentgenID = ROENTGEN_GESUND;
			break;
		}
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
