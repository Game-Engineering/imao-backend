package de.hsmannheim.ss18.gae.imao.model.wirtschaft;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.hsmannheim.ss18.gae.imao.model.StatusToString;

/**
 * Verwaltende Klasse für Sponsoren
 * @author lange
 *
 */
public class Sponsoren {
	private Sponsor[] sponsoren;
	public static int idCounter = 1;

	private Manager manager;
	private boolean sponsorenErstellt = false;

	public Sponsoren(Manager manager) {
		this.manager=manager;
		if (!this.sponsorenErstellt) {
			erstelleAlleSponsoren();
			this.sponsorenErstellt = true;
		}
	}

	private int getIdCount() {
		return idCounter++;
	}

	/**
	 * Erstelle alle Sponsoren
	 * monatlicher Betrag entspricht 0,01% des Firmenwertes
	 */
	private void erstelleAlleSponsoren() {

		this.sponsoren = new Sponsor[14];

		this.sponsoren[0] = new Sponsor(this.getIdCount(), "Jack Foxskin", 100000, 0, 12, 0, 0, Integer.MIN_VALUE);
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
		this.sponsoren[13] = new Sponsor(this.getIdCount(), "Globerunner", 18600, -100, 0, 0, 0, 0);

	}

	/**
	 * wenn das Ansehen passt, wird der Sponsor direkt angeworben (keine
	 * Kosten/dauer)
	 * 
	 * @param sponsorId
	 */
	public String werbeSponsorAn(int sponsorId, long ruf) {
		for (int i = 0; i < this.sponsoren.length; i++) {
			if (sponsorId == this.sponsoren[i].getSponsorID()) {
				if (this.sponsoren[i].getBenoetigtesAnsehen() <= ruf) {
					this.sponsoren[i].setAngeworben(true);
					
					this.manager.erhalteMail(new Mail(this.sponsoren[i].getSponsorName(), "Sponsoringanfrage", "Wir freuen uns ihnen mitteilen zu können, dass wir sie für "+ this.sponsoren[i].getAnwerbedauer()+" Monate mit einem monatlichen Betrag von "+this.sponsoren[i].getMonatlicherBetrag()+"€ unterstützen werden. \n\n Mit freundlichen Grüßen, \n"+this.sponsoren[i].getSponsorName()));
					return StatusToString.ok("Der Sponsor ("+sponsorId+") wurde angeworben");
				}
			}
		}

		for (int i = 0; i < this.sponsoren.length; i++) {
			if (sponsorId == this.sponsoren[i].getSponsorID()) {
				this.manager.erhalteMail(new Mail(this.sponsoren[i].getSponsorName(), "Sponsoringanfrage", "Es tut uns leid ihnen mitteilen zu müssen, das wir sie nicht unterstützen könen. \n\n Mit freundlichen Grüßen, \n"+this.sponsoren[i].getSponsorName()));
			}
		}
		return StatusToString.fehler("Der Sponsor ("+sponsorId+") wurde nicht angeworben");
	}

	/**
	 * gibt ein JSON zurück mit allen bereits angeworbenen Sponsoren
	 * @return
	 */
	public String getAktuelleSponsoren() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();

		ArrayNode arrayNode = mapper.createArrayNode();
		for (int i = 0; i < this.sponsoren.length; i++) {
			if (this.sponsoren[i].isAngeworben()) {

				ObjectNode objectNode1 = sponsorNode(i);
				arrayNode.add(objectNode1);
			}
		}

		objectNode.set("aktuelleSponsoren", arrayNode);
		return objectNode.toString();
	}

	/**
	 * gibt alle Sponsoren, auch diejenigen, für die der Ruf nicht ausreicht als JSON zurück
	 * @return
	 */
	public String getAlleSponsoren() {
		return getVerfuegbareSponsoren(Integer.MAX_VALUE);
	}

	/**
	 * gibt alle Sponsoren zurück, für die der Ruf ausreicht
	 * @param ruf
	 * @return
	 */
	public String getVerfuegbareSponsoren(long ruf) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		
		ArrayNode arrayNode = mapper.createArrayNode();

		for (int i = 0; i < this.sponsoren.length; i++) {
			if (ruf >= this.sponsoren[i].getBenoetigtesAnsehen()) {

				ObjectNode objectNode1 = sponsorNode(i);
				arrayNode.add(objectNode1);
			}
		}
		
		objectNode.set("verfuegbareSponsoren", arrayNode);
		return objectNode.toString();
	}

	private ObjectNode sponsorNode(int i) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("ID", sponsoren[i].getSponsorID());
		objectNode.put("sponsorName", sponsoren[i].getSponsorName());
		objectNode.put("monatlicherBetrag", sponsoren[i].getMonatlicherBetrag());
		objectNode.put("benoetigtesAnsehen", sponsoren[i].getBenoetigtesAnsehen());
		objectNode.put("zeitraum", sponsoren[i].getZeitraum());
		objectNode.put("absprungansehen", sponsoren[i].getAbsprungansehen());
		objectNode.put("anspruch", 0);// Skalar 1-10, welche sich aus anwerbekosten und -dauer zusammensetzt
		objectNode.put("angeworben", sponsoren[i].isAngeworben());

		return objectNode;
	}
}
