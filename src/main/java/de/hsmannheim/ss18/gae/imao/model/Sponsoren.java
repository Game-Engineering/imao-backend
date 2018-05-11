package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Sponsoren {
	private Sponsor[] sponsoren;
	private static int idCounter = 1;

	private boolean sponsorenErstellt = false;

	public Sponsoren() {
		if (!this.sponsorenErstellt) {
			erstelleAlleSponsoren();
			this.sponsorenErstellt = true;
		}
	}

	private int getIdCount() {
		return this.idCounter++;
	}

	/**
	 * monatlicher Betrag entspricht 0,01% des Firmenwertes
	 */
	private void erstelleAlleSponsoren() {

		this.sponsoren = new Sponsor[15];

		this.sponsoren[0] = new Sponsor(this.getIdCount(), "Jack Foxskin", 100000, 0, 1, 0, 0, Integer.MIN_VALUE);
		this.sponsoren[0].setAngeworben(true);

		this.sponsoren[1] = new Sponsor(this.getIdCount(), "Blue Bull", 0, 0, 0, 0, 0, 0);
		this.sponsoren[2] = new Sponsor(this.getIdCount(), "Engelbert Fasan", 0, 0, 0, 0, 0, 0);
		this.sponsoren[3] = new Sponsor(this.getIdCount(), "Sachse AG", 1600000, 0, 0, 0, 0, 0);
		this.sponsoren[4] = new Sponsor(this.getIdCount(), "Stodo Arzneimittel", 200000, 0, 0, 0, 0, 0);
		this.sponsoren[5] = new Sponsor(this.getIdCount(), "Navortis", 4600000, 0, 0, 0, 0, 0);
		this.sponsoren[6] = new Sponsor(this.getIdCount(), "Rob Bash GmbH", 7800000, 0, 0, 0, 0, 0);
		this.sponsoren[7] = new Sponsor(this.getIdCount(), "IBN", 8000000, 0, 0, 0, 0, 0);
		this.sponsoren[8] = new Sponsor(this.getIdCount(), "BWM", 9400000, 0, 0, 0, 0, 0);
		this.sponsoren[9] = new Sponsor(this.getIdCount(), "McDagobert´s", 9700000, 0, 0, 0, 0, 0);
		this.sponsoren[10] = new Sponsor(this.getIdCount(), "Mississippis", 14000000, 0, 0, 0, 0, 0);
		this.sponsoren[11] = new Sponsor(this.getIdCount(), "Pear", 23000000, 0, 0, 0, 0, 0);
		this.sponsoren[12] = new Sponsor(this.getIdCount(), "Gigasoft", 11500000, 0, 0, 0, 0, 0);
		this.sponsoren[13] = new Sponsor(this.getIdCount(), "Globerunner", 18600, 0, 0, 0, 0, 0);

	}

	/**
	 * wenn das Ansehen passt, wird der Sponsor direkt angeworben (keine
	 * Kosten/dauer)
	 * 
	 * @param sponsorId
	 */
	public void werbeSponsorAn(int sponsorId, int ansehen) {
		for (int i = 0; i < this.sponsoren.length; i++) {
			if (sponsorId == this.sponsoren[i].getSponsorID()) {
				if (this.sponsoren[i].getBenoetigtesAnsehen() <= ansehen) {
					this.sponsoren[i].setAngeworben(true);
				}
			}
		}
	}

	public String getAlleSponsoren() {
		return getVerfuegbareSponsoren(Integer.MAX_VALUE);
	}

	public String getVerfuegbareSponsoren(int ansehen) {
		ObjectMapper mapper = new ObjectMapper();

		ArrayNode arrayNode = mapper.createArrayNode();

		for (int i = 0; i < this.sponsoren.length; i++) {
			if (ansehen >= this.sponsoren[i].getBenoetigtesAnsehen()) {
				ObjectNode objectNode = mapper.createObjectNode();
				objectNode.put("ID", this.sponsoren[i].getSponsorID());
				objectNode.put("sponsorName", this.sponsoren[i].getSponsorName());
				objectNode.put("monatlicherBetrag", this.sponsoren[i].getMonatlicherBetrag());
				objectNode.put("benoetigtesAnsehen", this.sponsoren[i].getBenoetigtesAnsehen());
				objectNode.put("zeitraum", this.sponsoren[i].getZeitraum());
				objectNode.put("absprungansehen", this.sponsoren[i].getAbsprungansehen());
				objectNode.put("anspruch", 0);// Skalar 1-10, welche sich aus anwerbekosten und -dauer zusammensetzt
				objectNode.put("angeworben", this.sponsoren[i].isAngeworben());

				arrayNode.add(objectNode);
			}
		}
		return arrayNode.toString();
	}
}
