package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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

	private Sponsor[] sponsoren;
	private static int idCounter = 1;

	private boolean sponsorenErstellt = false;

	public Sponsor() {
		if (!this.sponsorenErstellt) {
			erstelleAlleSponsoren();
			this.sponsorenErstellt = true;
		}
	}

	private Sponsor(String name, int monatlicherBetrag, int benoetigtesAnsehen, int zeitraum, int anwerbekosten,
			int anwerbedauer, int absprungansehen) {
		this.sponsorID = Sponsor.idCounter;
		this.sponsorName = name;
		this.monatlicherBetrag = monatlicherBetrag;
		this.benoetigtesAnsehen = benoetigtesAnsehen;
		this.zeitraum = zeitraum;
		this.anwerbedauer = anwerbedauer;
		this.anwerbekosten = anwerbekosten;
		this.absprungansehen = absprungansehen;
		this.angeworben = false;

		Sponsor.idCounter++;
	}

	public int getID() {
		return this.sponsorID;
	}

	/**
	 * wenn das Ansehen passt, wird der Sponsor direkt angeworben (keine
	 * Kosten/dauer)
	 * 
	 * @param sponsorId
	 */
	public void werbeSponsorAn(int sponsorId, int ansehen) {
		for (int i = 0; i < this.sponsoren.length; i++) {
			if (sponsorId == this.sponsoren[i].getID()) {
				if (this.sponsoren[i].benoetigtesAnsehen <= ansehen) {
					this.sponsoren[i].angeworben = true;
				}
			}
		}
	}

	/**
	 * monatlicher Betrag entspricht 0,01% des Firmenwertes
	 */
	private void erstelleAlleSponsoren() {
		this.sponsoren = new Sponsor[15];

		this.sponsoren[0] = new Sponsor("Jack Foxskin", 100000, 0, 1, 0, 0, Integer.MIN_VALUE);
		this.sponsoren[0].angeworben = true;

		this.sponsoren[1] = new Sponsor("Blue Bull", 0, 0, 0, 0, 0, 0);
		this.sponsoren[2] = new Sponsor("Engelbert Fasan", 0, 0, 0, 0, 0, 0);
		this.sponsoren[3] = new Sponsor("Sachse AG", 1600000, 0, 0, 0, 0, 0);
		this.sponsoren[4] = new Sponsor("Stodo Arzneimittel", 200000, 0, 0, 0, 0, 0);
		this.sponsoren[5] = new Sponsor("Navortis", 4600000, 0, 0, 0, 0, 0);
		this.sponsoren[6] = new Sponsor("Rob Bash GmbH", 7800000, 0, 0, 0, 0, 0);
		this.sponsoren[7] = new Sponsor("IBN", 8000000, 0, 0, 0, 0, 0);
		this.sponsoren[8] = new Sponsor("BWM", 9400000, 0, 0, 0, 0, 0);
		this.sponsoren[9] = new Sponsor("McDagobert´s", 9700000, 0, 0, 0, 0, 0);
		this.sponsoren[10] = new Sponsor("Mississippis", 14000000, 0, 0, 0, 0, 0);
		this.sponsoren[11] = new Sponsor("Pear", 23000000, 0, 0, 0, 0, 0);
		this.sponsoren[12] = new Sponsor("Gigasoft", 11500000, 0, 0, 0, 0, 0);
		this.sponsoren[13] = new Sponsor("Globerunner", 18600, 0, 0, 0, 0, 0);

	}

	public String getAlleSponsoren() {
		return getVerfuegbareSponsoren(Integer.MAX_VALUE);
	}

	public String getVerfuegbareSponsoren(int ansehen) {
		ObjectMapper mapper = new ObjectMapper();

		ArrayNode arrayNode = mapper.createArrayNode();

		for (int i = 0; i < this.sponsoren.length; i++) {
			if (ansehen >= this.sponsoren[i].benoetigtesAnsehen) {
				ObjectNode objectNode = mapper.createObjectNode();
				objectNode.put("ID", this.sponsoren[i].sponsorID);
				objectNode.put("sponsorName", this.sponsoren[i].sponsorName);
				objectNode.put("monatlicherBetrag", this.sponsoren[i].monatlicherBetrag);
				objectNode.put("benoetigtesAnsehen", this.sponsoren[i].benoetigtesAnsehen);
				objectNode.put("zeitraum", this.sponsoren[i].zeitraum);
				objectNode.put("absprungansehen", this.sponsoren[i].absprungansehen);
				objectNode.put("anspruch", 0);// Skalar 1-10, welche sich aus anwerbekosten und -dauer zusammensetzt
				objectNode.put("angeworben", this.sponsoren[i].angeworben);

				arrayNode.add(objectNode);
			}
		}

		return arrayNode.toString();
	}

}
