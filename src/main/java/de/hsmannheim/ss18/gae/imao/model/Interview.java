package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Interview {

	InterviewPartner[] interviewPartner;

	public Interview() {

		createInterviewPartner();
	}

	public String getInterviewParter() {

		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode1 = mapper.createObjectNode();
		for (int i = 0; i < interviewPartner.length; i++) {
			if (interviewPartner[i].isVerfuegbar()) {
				ObjectNode objectNode2 = mapper.createObjectNode();
				objectNode2.put("name", interviewPartner[i].getName());
				objectNode2.put("maxAnsehen", interviewPartner[i].getMaxAnsehen());
				objectNode2.put("schwierigkeit", interviewPartner[i].getSchwierigkeit());

				objectNode1.set(String.valueOf(interviewPartner[i].getId()), objectNode2);
			}
		}

		return objectNode1.toString();
	}

	public String startInterview() {

		return null;
	}

	public String getInterview(int antwortID) {

		return null;
	}

	private void createInterviewPartner() {

	}

}
