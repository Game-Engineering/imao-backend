package de.hsmannheim.ss18.gae.imao.model.wirtschaft;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Das Thema einer Presserkonferenz. Eine Pressekonferenz besitzt Fragen und ist
 * erst Verfügbar wenn, ein bestimmtes Erigniss eingetreten ist.
 * 
 * @author lange
 *
 */
public class PressekonferenzThema {

	private int id;
	private String thema;
	private int maxAnsehen;
	private int schwierigkeit;

	private Frage[] fragen;
	private boolean verfuegbar = false;

	/**
	 * 
	 * @param id
	 * @param thema
	 * @param maxAnsehen
	 * @param schweirigkeit
	 * @param fragen
	 */
	public PressekonferenzThema(int id, String thema, int maxAnsehen, int schweirigkeit, Frage[] fragen) {
		this.id = id;
		this.thema = thema;
		this.maxAnsehen = maxAnsehen;
		this.schwierigkeit = schweirigkeit;
		this.fragen = fragen;
	}

	public int getId() {
		return id;
	}

	public String getThema() {
		return thema;
	}

	public int getMaxAnsehen() {
		return maxAnsehen;
	}

	public int getSchwierigkeit() {
		return schwierigkeit;
	}

	public Frage[] getFragen() {
		return fragen;
	}

	public boolean isVerfuegbar() {
		return verfuegbar;
	}

	public void setVerfuegbar(boolean verfuegbar) {
		this.verfuegbar = verfuegbar;
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("ID", getId());
		objectNode.put("Thema", getThema());
		objectNode.put("maxAnsehen", getMaxAnsehen());
		objectNode.put("schwierigkeit", getSchwierigkeit());
		objectNode.put("Verfuegbar", isVerfuegbar());

		return objectNode.toString();
	}

}
