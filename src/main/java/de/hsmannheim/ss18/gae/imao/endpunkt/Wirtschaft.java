package de.hsmannheim.ss18.gae.imao.endpunkt;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.hsmannheim.ss18.gae.imao.model.GeraetGekauft;
import de.hsmannheim.ss18.gae.imao.model.Spielrunde;

@Path("/wirtschaft")
public class Wirtschaft extends Spiel {
	private static int rundencount = 0;
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/neueRunde")
	@Produces(MediaType.APPLICATION_JSON)
	public String neueRunde() {
		runde = new Spielrunde(++rundencount);
		return runde.toString();
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
		GeraetGekauft gekauft = new GeraetGekauft(runde.kaufeGeraet(geraet), 1000);

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

		return "Frage, ID, AntwortA, ID, AntwortB, ID, AntwortC, ID, AntwortD, ID";
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

		return "Frage, ID, AntwortA, ID, AntwortB, ID, AntwortC, ID, AntwortD, ID";
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/getMoeglicheSendeMails")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMoeglicheSendeMails() {

		return "Frage, ID, AntwortA, ID, AntwortB, ID, AntwortC, ID, AntwortD, ID";
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

		return " neue Frage, ID, AntwortA, ID, AntwortB, ID, AntwortC, ID, AntwortD, ID";
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
