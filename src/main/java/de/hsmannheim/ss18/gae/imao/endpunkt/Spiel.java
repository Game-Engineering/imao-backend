package de.hsmannheim.ss18.gae.imao.endpunkt;

import de.hsmannheim.ss18.gae.imao.model.MyWiki;
import de.hsmannheim.ss18.gae.imao.model.Person;
import de.hsmannheim.ss18.gae.imao.model.enums.EGeschlecht;
import de.hsmannheim.ss18.gae.imao.model.medizin.Arzt;
import de.hsmannheim.ss18.gae.imao.model.wirtschaft.Interview;
import de.hsmannheim.ss18.gae.imao.model.wirtschaft.Manager;
import de.hsmannheim.ss18.gae.imao.model.wirtschaft.Sponsoren;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/spiel")
public class Spiel extends ResourceConfig {
	protected static Arzt arzt = null;
	protected static Manager manager = null;
	protected static MyWiki wiki=null;

	/**
	 * nicht aktuell
	 * @return Liste möglicher aufrufe
	 */
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String hello() {
		return "<h1>It Works!</h1>" + "<h3>Benutze die mitlere Maustaste zum &ouml;ffnen der Links </h3>"
				+ "<h4>Start:</h4>"
				+ "<a href=\"localhost:8080/imao/api/spiel/erzeuge/arzt/Max/Mustermann/maennlich\">localhost:8080/imao/api/spiel/erzeuge/arzt/Max/Mustermann/maennlich</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/erzeuge/manager/Susanne/Mustermann/weiblich\">localhost:8080/imao/api/spiel/erzeuge/manager/Susanne/Mustermann/weiblich</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/start/arzt\">localhost:8080/imao/api/spiel/start/arzt</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/start/manager\">localhost:8080/imao/api/spiel/start/manager</a><br>"
				+ "<h4>Arzt:</h4>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/neueRunde\">localhost:8080/imao/api/spiel/medizin/neueRunde</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/getPatient\">localhost:8080/imao/api/spiel/medizin/getPatient</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/getAnamnese/0\">localhost:8080/imao/api/spiel/medizin/getAnamnese/0</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/getBlutbild/0\">localhost:8080/imao/api/spiel/medizin/getBlutbild/0</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/getUltraschall/0\">localhost:8080/imao/api/spiel/medizin/getUltraschall/0</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/getRoentgen/0\">localhost:8080/imao/api/spiel/medizin/getRoentgen/0</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/getKatalog\">localhost:8080/imao/api/spiel/medizin/getKatalog</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/getUntersuchungsmethoden\">localhost:8080/imao/api/spiel/medizin/getUntersuchungsmethoden</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/diagnose/0/2\">localhost:8080/imao/api/spiel/medizin/diagnose/PatientID/KrankheitID</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/medizin/getAlleKrankheiten\">localhost:8080/imao/api/spiel/medizin/getAlleKrankheiten</a><br>"
				+ "<h4>Manager:</h4>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/neueRunde\">localhost:8080/imao/api/spiel/wirtschaft/neueRunde</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getKatalog\">localhost:8080/imao/api/spiel/wirtschaft/getKatalog</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/kaufeGeraet/Ultraschall\">localhost:8080/imao/api/spiel/wirtschaft/kaufeGeraet/geraetID</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getInterviewPartner\">localhost:8080/imao/api/spiel/wirtschaft/getInterviewPartner</a><br>"
				+ "localhost:8080/imao/api/spiel/wirtschaft/interview/interviewPartnerID<br>"
				+ "localhost:8080/imao/api/spiel/wirtschaft/interview/interviewPartnerID/antwortID<br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/startePressekonferenz\">localhost:8080/imao/api/spiel/wirtschaft/startePressekonferenz</a><br>"
				+ "localhost:8080/imao/api/spiel/wirtschaft/pressekonferenz/antwortID<br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getBudgetbreicht\">localhost:8080/imao/api/spiel/wirtschaft/getBudgetbreicht</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getArztbreicht\">localhost:8080/imao/api/spiel/wirtschaft/getArztbreicht</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getMails\">localhost:8080/imao/api/spiel/wirtschaft/getMails</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getMoeglicheSendeMails\">localhost:8080/imao/api/spiel/wirtschaft/getMoeglicheSendeMails</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/sendeMail/lob\">localhost:8080/imao/api/spiel/wirtschaft/sendeMail/mailID</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getAktuelleSponsoren\">localhost:8080/imao/api/spiel/wirtschaft/getAktuelleSponsoren</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getMoeglicheSponsoren\">localhost:8080/imao/api/spiel/wirtschaft/getMoeglicheSponsoren</a><br>"
				+ "localhost:8080/imao/api/spiel/wirtschaft/werbeSponsorAn/sponsorID<br>";
	}

	/**
	 * Teste ob Server läuft
	 * @return
	 */
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public String test() {
		return "{OK}";
	}

	/**
	 *
	 * @return
	 */
	@GET
	@Path("/neuesSpiel")
	@Produces(MediaType.APPLICATION_JSON)
	public String neuesSpiel() {
		Medizin.resetRundencount();
		Medizin.resetRundeArzt();
		Wirtschaft.resetAufgabe();
		Wirtschaft.resetRundeManager();
		Wirtschaft.resetRundencount();
		Sponsoren.idCounter=0;
		Interview.idCounter=0;
		arzt=null;
		manager=null;
		
		return "{neues Spiel wurde gestartet}";
	}

	/**
	 *
	 * @param type
	 * @return
	 */
	@GET
	@Path("/start/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public String start(@PathParam("type") String type) {
		String ueberschrift = "";
		String text = "";
		if ("arzt".equals(type)) {
			if (arzt != null) {
				ueberschrift = "Willkommen bei IMAO!";
						text = "Sie sind gerade in Ihrem Zelt (in der Wüste) angekommen, Ihnen stehen verschiedene Untersuchungsmethoden zur Verfügung. "
						+ "Einige Patienten warten schon auf Sie. Behandeln Sie so viele Patienten wie möglich. "
						+ "Mit jedem erfolgreich behandelten Patienten steigt der Ruf Ihrer Organisation. "
						+ "Wenn Sie neue Gerätschaften benötigen, können Sie diese bei Ihrem Manager anfordern. "
						+ "Viel Erfolg\n"
				
						+ "Ihr IMAO-Team";
			} else {
				ueberschrift = "Fehler";
				text = "bitte erzeugen Sie zuerst einen Arzt";
			}
		} else if ("manager".equals(type)) {
			if (manager != null) {
				ueberschrift = "Willkommen bei IMAO!";
						text = "Sie haben gerade Ihren neuen Job bei IMAO begonnen. "
						+ "Ihr Vorgänger hat ziemlich schlecht gewirtschaftet. "
						+ "Sie haben nur ein Camp, das nicht besonders gut ausgerüstet ist. "
						+ "Der Ruf der Organisation ist im Keller und Sie haben nur noch einen Sponsor. "
						+ "Steigern Sie den Ruf der Organisation, gewinnen Sie neue Sponsoren und bauen Sie das Camp aus. "
						+ "Viel Erfolg\n"
						+ "Ihr IMAO-Team";
			} else {
				ueberschrift = "Fehler";
				text = "bitte erzeugen Sie zuerst einen Manager";
			}
		} else {
			ueberschrift = "Fehler";
			text = "Den Typ gibe es nicht, bitte geben sie arzt oder manager ein";
		}

		return "{\"ueberschrift\" :\""+ueberschrift+" \",\"text\" :\""+text+"\"}";
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
	@Path("/erzeuge/{type}/{vorname}/{nachname}/{geschlecht}")
	@Produces(MediaType.APPLICATION_JSON)
	public String erzeuge(@PathParam("type") String type, @PathParam("vorname") String vorname,
			@PathParam("nachname") String nachname, @PathParam("geschlecht") String geschlecht) {
		Person person = null;
		wiki = new MyWiki();
		if ("arzt".equals(type)) {
			if (EGeschlecht.WEIBLICH.name().equals(geschlecht.toUpperCase())) {
				arzt = new Arzt(vorname, nachname, EGeschlecht.WEIBLICH);
			} else {
				arzt = new Arzt(vorname, nachname, EGeschlecht.MAENNLICH);
			}
			person = arzt;
		} else if ("manager".equals(type)) {
			if (EGeschlecht.WEIBLICH.name().equals(geschlecht.toUpperCase())) {
				manager = new Manager(vorname, nachname, EGeschlecht.WEIBLICH);
			} else {
				manager = new Manager(vorname, nachname, EGeschlecht.MAENNLICH);
			}
			person = manager;
		} else {
			return "Den Typ gibe es nicht, bitte geben sie arzt oder manager ein";
		}

		return person.toString();
	}

	/**
	 *
	 * @return
	 */
	@GET
	@Path("/getWikiKategorien")
	@Produces(MediaType.APPLICATION_JSON)
	public String getWikiKategorien() {

		return wiki.getWikiKategorie();
	}

	/**
	 *
	 * @param kategorieIndex
	 * @return
	 */
	@GET
	@Path("/getWikiElement/{index}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getWikiElement(@PathParam("index") String kategorieIndex) {

		return wiki.getWikiElement(Integer.parseInt(kategorieIndex));
	}

	/**
	 *
	 * @param kategorieIndex
	 * @param frageID
	 * @return
	 */
	@GET
	@Path("/getWikiElement/{index}/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getWikiElement(@PathParam("index") String kategorieIndex, @PathParam("id") String frageID) {

		return wiki.getWikiElement(Integer.parseInt(kategorieIndex), Integer.parseInt(frageID));
	}

	public static Arzt getArzt() {
		return arzt;
	}

	public static Manager getManager() {
		return manager;
	}

}