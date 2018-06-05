package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class InterviewPartner {

	private int id;
	private String name;
	private int maxAnsehen;
	private int schwierigkeit;
	
	private Frage[] fragen;
	private boolean verfuegbar = true;

	/**
	 * 
	 * @param id
	 * @param name
	 * @param maxAnsehen
	 * @param schweirigkeit
	 */
	public InterviewPartner(int id, String name, int maxAnsehen, int schweirigkeit,Frage[] fragen) {
		this.id = id;
		this.name = name;
		this.maxAnsehen = maxAnsehen;
		this.schwierigkeit = schweirigkeit;
		this.fragen=fragen;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getMaxAnsehen() {
		return maxAnsehen;
	}

	public void setMaxAnsehen(int maxAnsehen) {
		this.maxAnsehen = maxAnsehen;
	}

	public int getSchwierigkeit() {
		return schwierigkeit;
	}

	public void setSchwierigkeit(int schwierigkeit) {
		this.schwierigkeit = schwierigkeit;
	}

	public boolean isVerfuegbar() {
		return verfuegbar;
	}
	
	public Frage[] getFragen() {
		return this.fragen;
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("ID", id);
		objectNode.put("name", name);
		objectNode.put("maxAnsehen", maxAnsehen);
		objectNode.put("schwierigkeit", schwierigkeit);

		return objectNode.toString();
	}

}
