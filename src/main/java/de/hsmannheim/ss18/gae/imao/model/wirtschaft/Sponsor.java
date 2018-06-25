package de.hsmannheim.ss18.gae.imao.model.wirtschaft;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Sponsorobjekt wird von der Klasse "Sponsoren" erzeugt.
 * @author lange
 *
 */
public class Sponsor {

	private int sponsorID;
	private String sponsorName;
	private int monatlicherBetrag; // Betrag, welcher der Sponsor im Monat zahlt
	private int benoetigtesAnsehen; // Ansehen, welches benötigt wird um den Sponsor anwerben zu können
	private int zeitraum; // Zeitraum, über den der Sponsor verfügbar bleibt
	private int anwerbekosten; // Kosten, welche notwendig sind um den Sponsor anwerben zu können
	private int anwerbedauer; // Dauer, wie lange es braucht diesen Sponsor anzuwerben
	private int absprungansehen; // Ansehensminimum, bei dem der Sponsor abspringt
	private boolean angeworben;
	// private EBoni[] boni; //Bonus, welchen der Sponsor mit sich bringt
	// private EMali[] mali; //Mali, welche der Sponsor mit sich bringt

	/**
	 * 
	 * @param sponsorId
	 * @param name
	 * @param monatlicherBetrag
	 * @param benoetigtesAnsehen
	 * @param zeitraum
	 * @param anwerbekosten
	 * @param anwerbedauer
	 * @param absprungansehen
	 */
	public Sponsor(int sponsorId, String name, int monatlicherBetrag, int benoetigtesAnsehen, int zeitraum, int anwerbekosten,
			int anwerbedauer, int absprungansehen) {
		this.sponsorID = sponsorId;
		this.sponsorName = name;
		this.monatlicherBetrag = monatlicherBetrag;
		this.benoetigtesAnsehen = benoetigtesAnsehen;
		this.zeitraum = zeitraum;
		this.anwerbedauer = anwerbedauer;
		this.anwerbekosten = anwerbekosten;
		this.absprungansehen = absprungansehen;
		this.angeworben = false;
	}
	
	public int getSponsorID() {
		return sponsorID;
	}

	public String getSponsorName() {
		return sponsorName;
	}

	public int getMonatlicherBetrag() {
		return monatlicherBetrag;
	}

	public int getBenoetigtesAnsehen() {
		return benoetigtesAnsehen;
	}

	public int getZeitraum() {
		return zeitraum;
	}

	public int getAnwerbekosten() {
		return anwerbekosten;
	}

	public int getAnwerbedauer() {
		return anwerbedauer;
	}

	public int getAbsprungansehen() {
		return absprungansehen;
	}

	public boolean isAngeworben() {
		return angeworben;
	}
	
	public void setAngeworben(boolean angeworben) {
		this.angeworben=angeworben;
	}

	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		
		objectNode.put("ID", this.getSponsorID());
		objectNode.put("sponsorName", this.getSponsorName());
		objectNode.put("monatlicherBetrag", this.getMonatlicherBetrag());
		objectNode.put("benoetigtesAnsehen", this.getBenoetigtesAnsehen());
		objectNode.put("zeitraum", this.getZeitraum());
		objectNode.put("absprungansehen", this.getAbsprungansehen());
		objectNode.put("anspruch", 0);// Skalar 1-10, welche sich aus anwerbekosten und -dauer zusammensetzt
		objectNode.put("angeworben", this.isAngeworben());
		
		return objectNode.toString();
	}

}
