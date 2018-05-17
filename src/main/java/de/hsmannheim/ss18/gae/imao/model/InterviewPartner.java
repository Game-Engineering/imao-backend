package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class InterviewPartner {


	private int id;
	private String name;
	private int maxAnsehen;
	private int schwierigkeit;

	private boolean angeworben=false;
	private boolean verfuegbar=true;
	

	public InterviewPartner(int id, String name, int maxAnsehen, int schweirigkeit) {
		this.id=id;
		this.name=name;
		this.maxAnsehen=maxAnsehen;
		this.schwierigkeit=schweirigkeit;
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
	
	public boolean isAngeworben() {
		return angeworben;
	}

	public boolean isVerfuegbar() {
		return verfuegbar;
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("ID", id);
		objectNode.put("name", name);
		objectNode.put("maxAnsehen", maxAnsehen);
		objectNode.put("schwierigkeit",schwierigkeit);

		return objectNode.toString();
	}
	
	

}
