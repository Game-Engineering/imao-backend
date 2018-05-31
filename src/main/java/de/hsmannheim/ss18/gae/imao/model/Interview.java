package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.hsmannheim.ss18.gae.imao.model.InterviewFrage.Antwort;
import jersey.repackaged.com.google.common.collect.ObjectArrays;

public class Interview {

	InterviewPartner[] interviewPartner;

	public Interview() {
		createInterviewPartner();
	}

	public String getInterviewParter() {

		ObjectMapper mapper = new ObjectMapper();
		ArrayNode arrayNode = mapper.createArrayNode();

		for (int i = 0; i < interviewPartner.length; i++) {
			if (interviewPartner[i].isVerfuegbar()) {
				ObjectNode objectNode = mapper.createObjectNode();
				objectNode.put("id", interviewPartner[i].getId());
				objectNode.put("name", interviewPartner[i].getName());
				objectNode.put("maxAnsehen", interviewPartner[i].getMaxAnsehen());
				objectNode.put("schwierigkeit", interviewPartner[i].getSchwierigkeit());
				
				arrayNode.add(objectNode);
			}
		}
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.set("interviewPartner", arrayNode);
		return objectNode.toString();
	
	}

	public String startInterview(int InterviewpartnerID) {

		for (int i = 0; i < this.interviewPartner.length; i++) {
			if (this.interviewPartner[i].getId() == InterviewpartnerID && this.interviewPartner[i].isVerfuegbar()) {
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode objectNode = mapper.createObjectNode();
				ArrayNode arrayNode = mapper.createArrayNode();

				objectNode.put("name", "interview");
				objectNode.put("partner", this.interviewPartner[i].getName());
				objectNode.put("frage", this.interviewPartner[i].getFragen()[0].getFrage());
				
				//System.out.println("DEBUG - ANTWORTEN DES PARTNERS: "+this.interviewPartner[i].getFragen()[0].getAntworten());
				
				for (int j = 0; j < this.interviewPartner[i].getFragen()[0].getAntworten().length; j++) {
					//System.out.println("DEBUG - ANTWORT DES PARTNERS: " + this.interviewPartner[i].getFragen()[0].getAntworten()[j]);
					arrayNode.add(this.interviewPartner[i].getFragen()[0].getAntworten()[j].getAntwort());
				}

				objectNode.set("antworten", arrayNode);
				return objectNode.toString();
			}
		}
		return "ERROR: ID falsch oder Partner nicht verfügbar.";
	}

	public String getInterview(int InterviewpartnerID, int antwortID) {

		return null;
	}

	private void createInterviewPartner() {
		this.interviewPartner = new InterviewPartner[1];

		// Fragen und Antworten für 1. Interviewpartner
		Antwort[] p1f1a = { new Antwort("Antwort 10P", 10), new Antwort("Antwort 20P", 20),
				new Antwort("Antwort 0P", 0), new Antwort("Antwort -10P", -10) };
		InterviewFrage p1f1 = new InterviewFrage("Frage 1", p1f1a);

		Antwort[] p1f2a = { new Antwort("Antwort 10P", 10), new Antwort("Antwort 20P", 20),
				new Antwort("Antwort 0P", 0), new Antwort("Antwort -10P", -10) };
		InterviewFrage p1f2 = new InterviewFrage("Frage 2", p1f2a);

		Antwort[] p1f3a = { new Antwort("Antwort 10P", 10), new Antwort("Antwort 20P", 20),
				new Antwort("Antwort 0P", 0), new Antwort("Antwort -10P", -10) };
		InterviewFrage p1f3 = new InterviewFrage("Frage 2", p1f3a);

		Antwort[] p1f4a = { new Antwort("Antwort 10P", 10), new Antwort("Antwort 20P", 20),
				new Antwort("Antwort 0P", 0), new Antwort("Antwort -10P", -10) };
		InterviewFrage p1f4 = new InterviewFrage("Frage 2", p1f4a);

		Antwort[] p1f5a = { new Antwort("Antwort 10P", 10), new Antwort("Antwort 20P", 20),
				new Antwort("Antwort 0P", 0), new Antwort("Antwort -10P", -10) };
		InterviewFrage p1f5 = new InterviewFrage("Frage 2", p1f5a);

		InterviewFrage[] InPar1Fragen = { p1f1, p1f2, p1f3, p1f4, p1f5 };
		this.interviewPartner[0] = new InterviewPartner(idCount(), "InPar1", 10, 1, InPar1Fragen);

	}

	private static int idCount = 1;

	private static int idCount() {
		return idCount++;
	}

}
