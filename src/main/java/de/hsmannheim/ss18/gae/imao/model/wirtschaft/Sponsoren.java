package de.hsmannheim.ss18.gae.imao.model.wirtschaft;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.hsmannheim.ss18.gae.imao.model.StatusToString;

/**
 * Verwaltende Klasse für Sponsoren
 * 
 * @author lange
 *
 */
public class Sponsoren {
	private Sponsor[] sponsoren;
	public static int idCounter = 0;

	private Manager manager;
	private boolean sponsorenErstellt = false;

	public Sponsoren(Manager manager) {
		this.manager = manager;
		if (!this.sponsorenErstellt) {
			erstelleAlleSponsoren();
			this.sponsorenErstellt = true;
		}
	}

	private int getIdCount() {
		return idCounter++;
	}

	/**
	 * 
	 */
	private void erstelleAlleSponsoren() {

		this.sponsoren = new Sponsor[5];

		this.sponsoren[0] = new Sponsor(this.getIdCount(), "Jack Foxskin", 100000, 0, 12, 0, 0, Integer.MIN_VALUE);
		this.sponsoren[0].setAngeworben(true);
		this.sponsoren[1] = new Sponsor(this.getIdCount(), "Blue Bull", 300000, 20, 6, 0, 0, 0);
		this.sponsoren[2] = new Sponsor(this.getIdCount(), "Engelbert Fasan", 50000, 50, 48, 0, 0, 0);
		this.sponsoren[3] = new Sponsor(this.getIdCount(), "Sachse AG", 1600000, 150, 3, 0, 0, 0);
		this.sponsoren[4] = new Sponsor(this.getIdCount(), "Stodo Arzneimittel", 200000, 80, 12, 0, 0, 0);
		// this.sponsoren[5] = new Sponsor(this.getIdCount(), "Navortis", 4600000, 0, 0,
		// 0, 0, 0);
		// this.sponsoren[6] = new Sponsor(this.getIdCount(), "Rob Bash GmbH", 7800000,
		// 0, 0, 0, 0, 0);
		// this.sponsoren[7] = new Sponsor(this.getIdCount(), "IBN", 8000000, 0, 0, 0,
		// 0, 0);
		// this.sponsoren[8] = new Sponsor(this.getIdCount(), "BWM", 9400000, 0, 0, 0,
		// 0, 0);
		// this.sponsoren[9] = new Sponsor(this.getIdCount(), "McDagobert´s", 9700000,
		// 0, 0, 0, 0, 0);
		// this.sponsoren[10] = new Sponsor(this.getIdCount(), "Mississippis", 14000000,
		// 0, 0, 0, 0, 0);
		// this.sponsoren[11] = new Sponsor(this.getIdCount(), "Pear", 23000000, 0, 0,
		// 0, 0, 0);
		// this.sponsoren[12] = new Sponsor(this.getIdCount(), "Gigasoft", 11500000, 0,
		// 0, 0, 0, 0);
		// this.sponsoren[13] = new Sponsor(this.getIdCount(), "Globerunner", 18600,
		// -100, 0, 0, 0, 0);

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

					this.manager.erhalteMail(new Mail(this.sponsoren[i].getSponsorName(), "Sponsoringanfrage",
							"Sehr geehrter Vorstand von IMAO,\n"
									+ "Es freut uns Ihnen mitteilen zu können, dass wir die Aktivitäten von IMAO unterstützen wollen und zu diesem Zweck die Organisation mit einem Geldbetrag in Höhe von "
									+ this.sponsoren[i].getMonatlicherBetrag() + "€ für die Dauer von "
									+ this.sponsoren[i].getZeitraum() + ". Monaten unterstützen.\n\n"
									+ "Mit freundlichen Grüßen,\n" + this.sponsoren[i].getSponsorName()));
					return StatusToString.ok("Der Sponsor (" + sponsorId + ") wurde angeworben");
				}
			}
		}

		for (int i = 0; i < this.sponsoren.length; i++) {
			if (sponsorId == this.sponsoren[i].getSponsorID()) {
				this.manager.erhalteMail(new Mail(this.sponsoren[i].getSponsorName(), "Sponsoringanfrage",
						"Sehr geehrter Vorstand von IMAO,\n\n"
								+ "herzlichen Dank für Ihre Anfrage und Ihr damit verbundenes Interesse an unserem Unternehmen. "
								+ "Wir haben uns sehr darüber gefreut, dass Sie im Rahmen einer Sponsoringanfrage an uns gedacht haben.\n"
								+ "Aufgrund der zahlreichen Anfragen, die im Laufe eines Jahres an uns herangetragen werden, ist es uns jedoch leider nicht möglich, jedem Wunsch zu entsprehcen. "
								+ "Aus diesem Grund können wir Ihre Anfrage nicht berücksichtigen.\n"
								+ "Mit freundlichen Grüßen,\n\n" + this.sponsoren[i].getSponsorName()));
			}
		}
		return StatusToString.fehler("Der Sponsor (" + sponsorId + ") wurde nicht angeworben");
	}

	/**
	 * gibt ein JSON zurück mit allen bereits angeworbenen Sponsoren
	 * 
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
	 * gibt alle Sponsoren, auch diejenigen, für die der Ruf nicht ausreicht als
	 * JSON zurück
	 * 
	 * @return
	 */
	public String getAlleSponsoren() {
		return getVerfuegbareSponsoren(Integer.MAX_VALUE);
	}

	/**
	 * gibt alle Sponsoren zurück, für die der Ruf ausreicht
	 * 
	 * @param ruf
	 * @return
	 */
	public String getVerfuegbareSponsoren(long ruf) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();

		ArrayNode arrayNode = mapper.createArrayNode();

		for (int i = 0; i < this.sponsoren.length; i++) {
			if (ruf >= this.sponsoren[i].getBenoetigtesAnsehen()) {
				if (!this.sponsoren[i].isAngeworben()) {
					ObjectNode objectNode1 = sponsorNode(i);
					arrayNode.add(objectNode1);
				}
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
		objectNode.put("anspruch", 0);// Skalar 1-10, welche sich aus
										// anwerbekosten und -dauer
										// zusammensetzt
		objectNode.put("angeworben", sponsoren[i].isAngeworben());

		return objectNode;
	}
}
