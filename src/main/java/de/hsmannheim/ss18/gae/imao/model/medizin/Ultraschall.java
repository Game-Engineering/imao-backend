package de.hsmannheim.ss18.gae.imao.model.medizin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Ultraschall {
	//private Krankheit krankheit;
	private int ultraschallID;
	
	private static final int ULTRASCHALL_GESUND = 0;
//	private static final int ULTRASCHALL_K1 = 1;
//	private static final int ULTRASCHALL_K2 = 2;
//	private static final int ULTRASCHALL_K3 = 3;
//	private static final int ULTRASCHALL_K4 = 4;
//	private static final int ULTRASCHALL_K5 = 5;
//	private static final int ULTRASCHALL_K6 = 6;

	/**
	 * weist einer Krankheit die Passende BildID zu
	 * @param krankheit
	 */
	public Ultraschall(Krankheit krankheit) {
		//TODO reduziere Budget
		//this.krankheit = krankheit;
		switch (krankheit.getKrankheit()) {
		case MASERN:
			this.ultraschallID = ULTRASCHALL_GESUND;
			break;
//		case CHOLERA:
//			this.ultraschallID = ULTRASCHALL_GESUND;
//			break;
//		case BILHARZIOSE:
//			this.ultraschallID = ULTRASCHALL_GESUND;
//			break;
//		case HIV:
//			this.ultraschallID = ULTRASCHALL_GESUND;
//			break;
		case HAUTLEISHMANIASIS:
			this.ultraschallID = ULTRASCHALL_GESUND;
			break;
		case HEP_A:
			this.ultraschallID = ULTRASCHALL_GESUND;
			break;
		case HEP_B:
			this.ultraschallID = ULTRASCHALL_GESUND;
			break;
//		case TETANUS:
//			this.ultraschallID = ULTRASCHALL_GESUND;
//			break;
//		case GELBFIEBER:
//			this.ultraschallID = ULTRASCHALL_GESUND;
//			break;
//		case DENGUE_FIEBER:
//			this.ultraschallID = ULTRASCHALL_GESUND;
//			break;
		}
	}


	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("name", "Ultraschall");
		objectNode.put("budget", "900");
		objectNode.put("UltraschallID", this.ultraschallID);

		return objectNode.toString();
	}

}
