package de.hsmannheim.ss18.gae.imao.endpunkt;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.hsmannheim.ss18.gae.imao.model.Arzt;
import de.hsmannheim.ss18.gae.imao.model.EAufgaben;
import de.hsmannheim.ss18.gae.imao.model.EGeschlecht;
import de.hsmannheim.ss18.gae.imao.model.EMoeglicheMails;
import de.hsmannheim.ss18.gae.imao.model.GeraetGekauft;
import de.hsmannheim.ss18.gae.imao.model.SpielrundeWirtschaft;

@Path("spiel/wirtschaft")
public class Wirtschaft extends Spiel {
	private static int rundencount = 0;
	private static SpielrundeWirtschaft rundeManager;

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
			arzt = new Arzt("Dummy", "Dumm", EGeschlecht.MAENNLICH);
		}
		rundeManager = new SpielrundeWirtschaft(++rundencount, manager, arzt);
		return rundeManager.toString();
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
		if (EAufgaben.GREAET_KAUFEN.equals(rundeManager.getAufgabe().getAufgabe())) {
			rundeManager.getAufgabe().erledigt();
		}
		return gekauft.toString();
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/interview")
	@Produces(MediaType.APPLICATION_JSON)
	public String interview() {

		return "Frage, ID, AntwortA, ID, AntwortB, ID, AntwortC, ID, AntwortD, ID";
	}

	/**
	 * 
	 * @param antwortID
	 *            (String)
	 * @return
	 */
	@GET
	@Path("/interview/{antwortID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String interview(@PathParam("antwortID") String antwortID) {
		// bei letzter Antwort prüfen ob Inerview die aufgabe war ind wenn ja
		// auf aufgabe.erledigt() ausführen

		return " neue Frage, ID, AntwortA, ID, AntwortB, ID, AntwortC, ID, AntwortD, ID";
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/haltePressekonferenz")
	@Produces(MediaType.APPLICATION_JSON)
	public String haltePressekonferenz() {

		return rundeManager.haltePressekonferenz();
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/getBudgetbreicht")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBudgetbreicht() {

		return "Frage, ID, AntwortA, ID, AntwortB, ID, AntwortC, ID, AntwortD, ID";
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/getArztbreicht")
	@Produces(MediaType.APPLICATION_JSON)
	public String getArztbreicht() {

		return "Frage, ID, AntwortA, ID, AntwortB, ID, AntwortC, ID, AntwortD, ID";
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
		return manager.getPosteingang().toString();
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
		objectNode.put(EMoeglicheMails.GERAET_GEKAUFT.name(), EMoeglicheMails.GERAET_GEKAUFT.getMailText());
		objectNode.put(EMoeglicheMails.DEFAULT_MAIL.name(), EMoeglicheMails.DEFAULT_MAIL.getMailText());

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

		return "Frage, ID, AntwortA, ID, AntwortB, ID, AntwortC, ID, AntwortD, ID";
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/getMoeglicheSponsoren")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMoeglicheSponsoren() {

		return "Frage, ID, AntwortA, ID, AntwortB, ID, AntwortC, ID, AntwortD, ID";
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

		return " neue Frage, ID, AntwortA, ID, AntwortB, ID, AntwortC, ID, AntwortD, ID";
	}
}
