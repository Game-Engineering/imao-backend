package de.hsmannheim.ss18.gae.imao.endpunkt;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.hsmannheim.ss18.gae.imao.model.GeraetGekauft;
import de.hsmannheim.ss18.gae.imao.model.StatusToString;
import de.hsmannheim.ss18.gae.imao.model.enums.EGeschlecht;
import de.hsmannheim.ss18.gae.imao.model.enums.EMoeglicheMails;
import de.hsmannheim.ss18.gae.imao.model.medizin.Arzt;
import de.hsmannheim.ss18.gae.imao.model.wirtschaft.Aufgabe;
import de.hsmannheim.ss18.gae.imao.model.wirtschaft.SpielrundeWirtschaft;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("spiel/wirtschaft")
public class Wirtschaft extends Spiel {
	private static int rundencount = 0;
	private static SpielrundeWirtschaft rundeManager;
	private static Aufgabe aufgabe = null;

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/neueRunde")
	@Produces(MediaType.APPLICATION_JSON)
	public String neueRunde() {
		if (manager == null) {
			return "Sie Haben keinen Manager angelegt";
		}
		if (arzt == null) {
			arzt = new Arzt("John", "Smith", EGeschlecht.MAENNLICH);
			arzt.getAusgaben().put("Blutbild Patient 1", 10);
			arzt.getAusgaben().put("Blutbild Patient 2", 10);
			arzt.getAusgaben().put("Blutbild Patient 3", 10);
			arzt.getAusgaben().put("Blutbild Patient 4", 10);
			arzt.getAusgaben().put("Blutbild Patient 5", 10);
			arzt.getAusgaben().put("Blutbild Patient 6", 10);
			arzt.getAusgaben().put("Blutbild Patient 7", 10);
			arzt.getAusgaben().put("Blutbild Patient 8", 10);
			arzt.getAusgaben().put("Blutbild Patient 9", 10);
			arzt.getAusgaben().put("Blutbild Patient 10", 10);
			arzt.getAusgaben().put("Blutbild Patient 11", 10);
			arzt.getAusgaben().put("Blutbild Patient 12", 10);
			arzt.getRufzuwachs().put("Patient 1 erfolgreich behandelt", 5);
			arzt.getRufzuwachs().put("Patient 3 erfolgreich behandelt", 5);
			arzt.getRufzuwachs().put("Patient 5 erfolgreich behandelt", 5);
			arzt.getRufzuwachs().put("Patient 8 erfolgreich behandelt", 5);
			arzt.getRufzuwachs().put("Patient 9 erfolgreich behandelt", 5);
			arzt.getRufzuwachs().put("Patient 11 erfolgreich behandelt", 5);
			arzt.getRufzuwachs().put("Patient 12 erfolgreich behandelt", 5);
			arzt.getRufzuwachs().put("Patient 13 erfolgreich behandelt", 5);
			arzt.getRufverlust().put("Patient 2 nicht erfolgreich behandelt", 10);
			arzt.getRufverlust().put("Patient 4 nicht erfolgreich behandelt", 10);
			arzt.getRufverlust().put("Patient 6 nicht erfolgreich behandelt", 10);
			arzt.getRufverlust().put("Patient 7 nicht behandelt", 10);
			arzt.getRufverlust().put("Patient 10 nichterfolgreich behandelt", 10);
			arzt.getRufverlust().put("Patient 14 nicht behandelt", 10);
			arzt.getRufverlust().put("Patient 15 nicht behandelt", 10);
			arzt.getRufverlust().put("Patient 16 nicht behandelt", 10);
			arzt.getRufverlust().put("Patient 17 nicht behandelt", 10);
			arzt.getRufverlust().put("Patient 18 nicht behandelt", 10);

		}
		rundeManager = new SpielrundeWirtschaft(++rundencount, manager, arzt, aufgabe);
		aufgabe = rundeManager.getAufgabe();
		return rundeManager.toString();
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/getArztDaten")
	@Produces(MediaType.APPLICATION_JSON)
	public String getArztDaten() {

		return arzt.toString();
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/getKatalog")
	@Produces(MediaType.APPLICATION_JSON)
	public String getKatalog() {
		// TODO Liste auslesen

		return rundeManager.getKatalog().toString();
	}

	/**
	 * 
	 * @param geraet
	 *            (String)
	 * @return
	 */
	@GET
	@Path("/kaufeGeraet/{geraet}")
	@Produces(MediaType.APPLICATION_JSON)
	public String kaufeGeraet(@PathParam("geraet") String geraet) {
		GeraetGekauft gekauft = new GeraetGekauft(rundeManager.kaufeGeraet(geraet), 1000);
		return gekauft.toString();
	}

	/**
	 *
	 * @return
	 */
	@GET
	@Path("/getInterviewPartner")
	@Produces(MediaType.APPLICATION_JSON)
	public String getInterviewPartner() {
		return rundeManager.getManager().getInterview().getInterviewParter();
		/*
		 * List<InterviewPartner> partnerListe = new ArrayList<>(); partnerListe.add(
		 * new InterviewPartner(1, "Hans", 35, 3)); partnerListe.add( new
		 * InterviewPartner(2, "Peter", 30, 2)); partnerListe.add( new
		 * InterviewPartner(5, "Wurst", 10, 0)); return
		 * " { \"partnerListe\":"+partnerListe.toString()+"}";
		 */
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/interview/{partnerID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String interview(@PathParam("partnerID") String partnerID) {
		return rundeManager.getManager().getInterview().startInterview(Integer.parseInt(partnerID), "neue/erste Anfrage");
		//return "Frage, ID, AntwortA, ID, AntwortB, ID, AntwortC, ID, AntwortD, ID";
	}

	/**
	 * 
	 * @param antwortID
	 *            (String)
	 * @return
	 */
	@GET
	@Path("/interview/{partnerID}/{antwortID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String interview(@PathParam("partnerID") String partnerID, @PathParam("antwortID") String antwortID) {
		// bei letzter Antwort prüfen ob Inerview die aufgabe war ind wenn ja
		// auf aufgabe.erledigt() ausführen
		return rundeManager.getManager().getInterview().getInterview(Integer.parseInt(partnerID), Integer.parseInt(antwortID));
		//return " neue Frage, ID, AntwortA, ID, AntwortB, ID, AntwortC, ID, AntwortD, ID";
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/getBudgetbreicht")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBudgetbreicht() {

		return rundeManager.getBudgetbericht();
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/getArztbreicht")
	@Produces(MediaType.APPLICATION_JSON)
	public String getArztbreicht() {

		return rundeManager.getArztbericht();
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/getMails")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMails() {
		System.out.println(manager.getPosteingang().toString());
		return "{ \"mailliste\" :" + manager.getPosteingang().toString() + "}";
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/getMoeglicheSendeMails")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMoeglicheSendeMails() {

		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put(EMoeglicheMails.LOB.name(), EMoeglicheMails.LOB.getMailText());
		objectNode.put(EMoeglicheMails.ABMAHNUNG.name(), EMoeglicheMails.ABMAHNUNG.getMailText());
//		objectNode.put(EMoeglicheMails.GERAET_GEKAUFT.name(), EMoeglicheMails.GERAET_GEKAUFT.getMailText());

		return objectNode.toString();

	}

	/**
	 * 
	 * @param mailID
	 *            (String)
	 * @return
	 */
	@GET
	@Path("/sendeMail/{mailID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String sendeMail(@PathParam("mailID") String mailID) {

		return rundeManager.sendeMail(mailID);
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/getAktuelleSponsoren")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAktuelleSponsoren() {
		return rundeManager.getManager().getSponsoren().getAktuelleSponsoren();
		//return "{\"Frage\": \"ID\", \"AntwortA\": \"ID\", \"AntwortB\",: \"ID\", \"AntwortC\": \"ID\", \"AntwortD\": \"ID\"}";
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/getMoeglicheSponsoren")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMoeglicheSponsoren() {
		return rundeManager.getManager().getSponsoren().getVerfuegbareSponsoren(rundeManager.getManager().getRuf());
		//return "Frage, ID, AntwortA, ID, AntwortB, ID, AntwortC, ID, AntwortD, ID";
	}
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/getAlleSponsoren")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAlleSponsoren() {
		return rundeManager.getManager().getSponsoren().getAlleSponsoren();
		//return "Frage, ID, AntwortA, ID, AntwortB, ID, AntwortC, ID, AntwortD, ID";
	}

	/**
	 * 
	 * @param sponsorID
	 *            String)
	 * @return
	 */
	@GET
	@Path("/werbeSponsorAn/{sponsorID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String werbeSponsorAn(@PathParam("sponsorID") String sponsorID) {
		return rundeManager.getManager().getSponsoren().werbeSponsorAn(Integer.parseInt(sponsorID),rundeManager.getManager().getRuf());
		//return " neue Frage, ID, AntwortA, ID, AntwortB, ID, AntwortC, ID, AntwortD, ID";
	}
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/startePressekonferenz")
	@Produces(MediaType.APPLICATION_JSON)
	public String startePressekonferenz() {
		//FIXME hier tritt eine NullPointerException auf 
		return rundeManager.startePressekonferenz();
		
	}
	
	/**
	 * 
	 * @param antwortID
	 * @return
	 */
	@GET
	@Path("/pressekonferenz/{antwortID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String antwortePressekonferenz(@PathParam("antwortID") String antwortID) {
		return rundeManager.antwortePressekonferenz(Integer.parseInt(antwortID));
	}

	/**
	 * Füge Ruf hinzu
	 * @param menge
	 * @return
	 */
	@GET
	@Path("/cheatRUF/{menge}")
	@Produces(MediaType.APPLICATION_JSON)
	public String cheatRUF(@PathParam("menge") String menge) {
		if(Integer.parseInt(menge) < 0) {
			manager.rufVerlust("CHEAT", Integer.parseInt(menge)*-1);
			return StatusToString.ok("Ruf verringert");
		}else if(Integer.parseInt(menge) > 0) {
			manager.rufZuwachs("CHEAT", Integer.parseInt(menge));
			return StatusToString.ok("Ruf erhöht");
		}
		return StatusToString.fehler("Ruf nicht geändert");
	}

	/**
	 * Füge Geld hinzu
	 * @param menge
	 * @return
	 */
	@GET
	@Path("/cheatBUDGET/{menge}")
	@Produces(MediaType.APPLICATION_JSON)
	public String cheatBUDGET(@PathParam("menge") String menge) {
		if(Integer.parseInt(menge) < 0) {
			manager.ausgabe("CHEAT", Integer.parseInt(menge)*-1);
			return StatusToString.ok("Budget verringert");
		}else if(Integer.parseInt(menge) > 0) {
			manager.einnahme("CHEAT", Integer.parseInt(menge));
			return StatusToString.ok("Budget erhöht");
		}
		return StatusToString.fehler("Budget nicht geändert");
	}
	

	public static void resetRundencount() {
		Wirtschaft.rundencount = 0;
	}

	public static void resetRundeManager() {
		rundeManager = null;
	}

	public static void resetAufgabe() {
		Wirtschaft.aufgabe = null;
	}

}
