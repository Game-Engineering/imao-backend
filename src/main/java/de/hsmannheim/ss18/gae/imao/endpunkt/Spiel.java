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
	protected static Arzt arzt = null;
	protected static Manager manager = null;

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
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/interview\">localhost:8080/imao/api/spiel/wirtschaft/interview</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/interview/1\">localhost:8080/imao/api/spiel/wirtschaft/interview/antwortID</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/haltePressekonferenz\">localhost:8080/imao/api/spiel/wirtschaft/haltePressekonferenz</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getBudgetbreicht\">localhost:8080/imao/api/spiel/wirtschaft/getBudgetbreicht</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getArztbreicht\">localhost:8080/imao/api/spiel/wirtschaft/getArztbreicht</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getMails\">localhost:8080/imao/api/spiel/wirtschaft/getMails</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getMoeglicheSendeMails\">localhost:8080/imao/api/spiel/wirtschaft/getMoeglicheSendeMails</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/sendeMail/lob\">localhost:8080/imao/api/spiel/wirtschaft/sendeMail/mailID</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getAktuelleSponsoren\">localhost:8080/imao/api/spiel/wirtschaft/getAktuelleSponsoren</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/getMoeglicheSponsoren\">localhost:8080/imao/api/spiel/wirtschaft/getMoeglicheSponsoren</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/wirtschaft/werbeSponsorAn/2\">localhost:8080/imao/api/spiel/wirtschaft/werbeSponsorAn/sponsorID</a><br>";
	}

	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public String test() {
		return "{OK}";
	}
	
	@GET
	@Path("/neuesSpiel")
	@Produces(MediaType.APPLICATION_JSON)
	public String neuesSpiel() {
		Medizin.resetRundencount();
		Medizin.resetRundeArzt();
		Wirtschaft.resetAufgabe();
		Wirtschaft.resetRundeManager();
		Wirtschaft.resetRundencount();
		arzt=null;
		manager=null;
		
		return "{neues Spiel wurde gestartet}";
	}
	
	@GET
	@Path("/start/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public String start(@PathParam("type") String type) {
		String ueberschrift = "";
		String text = "";
		if ("arzt".equals(type)) {
			if (arzt != null) {
				ueberschrift = "Willkommen bei IMAO";
				text = "Sie sind gerade in Ihrem Zelt (in der Wüste) angekommen, ihnen sehen verschiedene Unersuchungsmethoden zur verfügung,"
						+ "Einige Patienten warten schon auf Sie, behandeln Sie so viele Patienten wie möglich.  "
						+ "Mit jedem erfolgreich behandelten Patienten steigt der Ruf Ihrer Organisation. "
						+ "Wenn Sie neue Gerätschaften benötigen können Sie diese bei ihrem Manager anfordern. "
						+ "Viel Erfolg "
						+ "Ihr IMAO Team";
			} else {
				ueberschrift = "Fehler";
				text = "bitte erzeugen Sie zuerst einen Arzt";
			}
		} else if ("manager".equals(type)) {
			if (manager != null) {
				ueberschrift = "Willkommen bei IMAO";
				text = "Sie haben gerade Ihren neuen Job bei IMAO begonnen. "
						+ "Ihr vorgänger hat ziemlich schlecht gewirtschaftet. "
						+ "Sie haben nur ein Camp, das nicht besonders gut ausgreüstet ist. "
						+ "Der Ruf der Organisation ist im Keller und Sie haben nur noch einen Sponsor. "
						+ "Steigern Sie den Ruf der Organisation, gewinnwn Sie neue Sponsoren und bauen Sie das Camp aus. "
						+ "Viel Erfolg "
						+ "Ihr IMAO Team";
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

	public static Arzt getArzt() {
		return arzt;
	}

	public static Manager getManager() {
		return manager;
	}

}