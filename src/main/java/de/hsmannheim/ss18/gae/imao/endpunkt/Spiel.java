package de.hsmannheim.ss18.gae.imao.endpunkt;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;

import de.hsmannheim.ss18.gae.imao.model.Arzt;
import de.hsmannheim.ss18.gae.imao.model.EGeschlecht;
import de.hsmannheim.ss18.gae.imao.model.Manager;
import de.hsmannheim.ss18.gae.imao.model.Person;

@Path("/spiel")
public class Spiel extends ResourceConfig {
	protected static Arzt arzt;
	protected static Manager manager;

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String hello() {
		return "<h1>It Works!</h1>" + "<h3>Benutze die mitlere Maustaste zum &ouml;ffnen der Links </h3>"
				+ "<h4>Start:</h4>"
				+ "<a href=\"localhost:8080/imao/api/spiel/start/arzt/Max/Mustermann/maennlich\">localhost:8080/imao/api/spiel/start/arzt/Max/Mustermann/maennlich</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/start/arzt/Susanne/Mustermann/weiblich\">localhost:8080/imao/api/spiel/start/arzt/Susanne/Mustermann/weiblich</a><br>"
				+ "<h4>Arzt:</h4>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/neueRunde\">localhost:8080/imao/api/spiel/medizin/neueRunde</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/getPatient\">localhost:8080/imao/api/spiel/medizin/getPatient</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/getAnamnese/1\">localhost:8080/imao/api/spiel/medizin/getAnamnese/1</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/getBlutbild/1\">localhost:8080/imao/api/spiel/medizin/getBlutbild/1</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/getUltraschall/1\">localhost:8080/imao/api/spiel/medizin/getUltraschall/1</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/getRoentgen/1\">localhost:8080/imao/api/spiel/medizin/getRoentgen/1</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/getKatalog\">localhost:8080/imao/api/spiel/medizin/getKatalog</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/getUntersuchungsmethoden\">localhost:8080/imao/api/spiel/medizin/getUntersuchungsmethoden</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/diagnose/KrankheitID\">localhost:8080/imao/api/spiel/medizin/diagnose/KrankheitID</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/getAlleKrankheiten\">localhost:8080/imao/api/spiel/medizin/getAlleKrankheiten</a><br>"
				+ "<h4>Manager:</h4>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/neueRunde\">localhost:8080/imao/api/spiel/wirtschaft/neueRunde</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getKatalog\">localhost:8080/imao/api/spiel/wirtschaft/getKatalog</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/gkaufeGeraet/geraetID\">localhost:8080/imao/api/spiel/wirtschaft/kaufeGeraet/geraetID</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/ginterview\">localhost:8080/imao/api/spiel/wirtschaft/interview</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/ginterview/antwortID\">localhost:8080/imao/api/spiel/wirtschaft/interview/1</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/ghaltePressekonferenz\">localhost:8080/imao/api/spiel/wirtschaft/haltePressekonferenz</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getBudgetbreicht\">localhost:8080/imao/api/spiel/wirtschaft/getBudgetbreicht</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getArztbreicht\">localhost:8080/imao/api/spiel/wirtschaft/getArztbreicht</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getMails\">localhost:8080/imao/api/spiel/wirtschaft/getMails</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getMoeglicheSendeMails\">localhost:8080/imao/api/spiel/wirtschaft/getMoeglicheSendeMails</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/sendeMail/mailID\">localhost:8080/imao/api/spiel/wirtschaft/sendeMail/1</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getAktuelleSponsoren\">localhost:8080/imao/api/spiel/wirtschaft/getAktuelleSponsoren</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getMoeglicheSponsoren\">localhost:8080/imao/api/spiel/wirtschaft/getMoeglicheSponsoren</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/werbeSponsorAn/sponsorID\">localhost:8080/imao/api/spiel/wirtschaft/werbeSponsorAn/2</a><br>";
	}

	/**
	 * 
	 * @param type
	 *            ("arzt"/"wirtschaft")
	 * @param vorname
	 * @param nachname
	 * @return
	 */
	@GET
	@Path("/start/{type}/{vorname}/{nachname}/{geschlecht}")
	@Produces(MediaType.APPLICATION_JSON)
	public String start(@PathParam("type") String type, @PathParam("vorname") String vorname,
			@PathParam("nachname") String nachname, @PathParam("geschlecht") String geschlecht) {
		Person person = null;
		if ("arzt".equals(type)) {
			if (EGeschlecht.WEIBLICH.name().equals(geschlecht.toUpperCase())) {
				arzt = new Arzt(vorname, nachname, EGeschlecht.WEIBLICH);
			} else {
				arzt = new Arzt(vorname, nachname, EGeschlecht.MAENNLICH);
			}
			person = arzt;
		} else if ("wirtschaft".equals(type)) {
			if (EGeschlecht.WEIBLICH.name().equals(geschlecht.toUpperCase())) {
				manager = new Manager(vorname, nachname, EGeschlecht.WEIBLICH);
			} else {
				manager = new Manager(vorname, nachname, EGeschlecht.MAENNLICH);
			}
			person = manager;
		}

		return person.toString();
	}

	public static Arzt getArzt() {
		return arzt;
	}

	public static Manager getManager() {
		return manager;
	}

}