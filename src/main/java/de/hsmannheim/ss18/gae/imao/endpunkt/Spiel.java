package de.hsmannheim.ss18.gae.imao.endpunkt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;

import de.hsmannheim.ss18.gae.imao.model.Anamnese;
import de.hsmannheim.ss18.gae.imao.model.Arzt;
import de.hsmannheim.ss18.gae.imao.model.Blutbild;
import de.hsmannheim.ss18.gae.imao.model.Diagnose;
import de.hsmannheim.ss18.gae.imao.model.GeraetGekauft;
import de.hsmannheim.ss18.gae.imao.model.Krankheit;
import de.hsmannheim.ss18.gae.imao.model.Manager;
import de.hsmannheim.ss18.gae.imao.model.Patient;
import de.hsmannheim.ss18.gae.imao.model.Person;
import de.hsmannheim.ss18.gae.imao.model.Roentgen;
import de.hsmannheim.ss18.gae.imao.model.Spielrunde;
import de.hsmannheim.ss18.gae.imao.model.Ultraschall;
import de.hsmannheim.ss18.gae.imao.model.Untersuchungsmethode;

@Path("/spiel")
public class Spiel extends ResourceConfig {

	private static int rundencount = 0;
	private static Spielrunde runde;
	private static Arzt arzt;
	private static Manager manager;

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String hello() {
		return "<h1>It Works!</h1>" + "<h3>Benutze die mitlere Maustaste zum &ouml;ffnen der Links </h3>"
				+ "<a href=\"localhost:8080/imao/api/spiel/start/arzt/Max/Mustermann\">localhost:8080/imao/api/spiel/start/arzt/Max/Mustermann</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/neueRunde\">localhost:8080/imao/api/spiel/neueRunde</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getPatient\">localhost:8080/imao/api/spiel/getPatient</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getAnamnese/1\">localhost:8080/imao/api/spiel/getAnamnese/1</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getBlutbild/1\">localhost:8080/imao/api/spiel/getBlutbild/1</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getUltraschall/1\">localhost:8080/imao/api/spiel/getUltraschall/1</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getRoentgen/1\">localhost:8080/imao/api/spiel/getRoentgen/1</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getUntersuchungsmethoden\">localhost:8080/imao/api/spiel/getUntersuchungsmethoden</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/diagnose/KrankheitID\">localhost:8080/imao/api/spiel/diagnose/KrankheitID</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getAlleKrankheiten\">localhost:8080/imao/api/spiel/getAlleKrankheiten</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getKatalog\">localhost:8080/imao/api/spiel/getKatalog</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/kaufeGeraet/geraetID\">localhost:8080/imao/api/spiel/kaufeGeraet/geraetID</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/interview\">localhost:8080/imao/api/spiel/interview</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/interview/antwortID\">localhost:8080/imao/api/spiel/interview/1</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/haltePressekonferenz\">localhost:8080/imao/api/spiel/haltePressekonferenz</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getBudgetbreicht\">localhost:8080/imao/api/spiel/getBudgetbreicht</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getArztbreicht\">localhost:8080/imao/api/spiel/getArztbreicht</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getMails\">localhost:8080/imao/api/spiel/getMails</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getMoeglicheSendeMails\">localhost:8080/imao/api/spiel/getMoeglicheSendeMails</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/sendeMail/mailID\">localhost:8080/imao/api/spiel/sendeMail/1</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getAktuelleSponsoren\">localhost:8080/imao/api/spiel/getAktuelleSponsoren</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getMoeglicheSponsoren\">localhost:8080/imao/api/spiel/getMoeglicheSponsoren</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/werbeSponsorAn/sponsorID\">localhost:8080/imao/api/spiel/werbeSponsorAn/2</a><br>";
	}

	/**
	 * 
	 * @param type ("arzt"/"wirtschaft")
	 * @param vorname
	 * @param nachname
	 * @return
	 */
	@GET
	@Path("/start/{type}/{vorname}/{nachname}")
	@Produces(MediaType.APPLICATION_JSON)
	public String start(@PathParam("type") String type, @PathParam("vorname") String vorname,
			@PathParam("nachname") String nachname) {
		Person person = null;
		if ("arzt".equals(type)) {
			arzt = new Arzt(vorname, nachname);
			person = arzt;
		} else if ("wirtschaft".equals(type)) {
			manager = new Manager(vorname, nachname);
			person = manager;
		}

		return person.toString();
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/neueRunde")
	@Produces(MediaType.APPLICATION_JSON)
	public String neueRunde() {
		// TODO Patienten erzeugen
		// TODO Budget berechnen
		// TODO Ruf berechnen
		// TODO Anzahl der Patienten berechnen
		// TODO runde Zählen

		runde = new Spielrunde(++rundencount);
		return runde.toString();
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/getPatient")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPatatient() {
		Patient pat = runde.getPatient();
		if (pat != null) {
			return runde.getPatient().toString();
		} else {
			return "{\"Nachricht\":\"Kein Patient\"}";
		}
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

		return runde.getKatalog().toString();
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/getUntersuchungsmethoden")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUntersuchungsmethoden() {
		return runde.getUntersuchungsmethoden().toString();
	}

	/**
	 * 
	 * @param patientID (int)
	 * @return
	 */
	@GET
	@Path("/getBlutbild/{patientID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBlutbild(@PathParam("patientID") int patientID) {
		Blutbild neuesBlutbild;
		Patient p = runde.getPatient(patientID);
		if (p != null) {
			neuesBlutbild = p.getKrankheit().getBlutbild();
		} else {
			return "Patient nicht in Zelt";
		}
		// TODO kosten
		// TODO Blutbild aus Patienten auslesen und senden.
		return neuesBlutbild.toString();
	}

	/**
	 * 
	 * @param patientID (int)
	 * @return
	 */
	@GET
	@Path("/getUltraschall/{patientID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUltraschall(@PathParam("patientID") int patientID) {
		Ultraschall neuesUltraschall;
		Patient p = runde.getPatient(patientID);
		if (p != null) {
			neuesUltraschall = p.getKrankheit().getUltraschall();
		} else {
			return "Patient nicht in Zelt";
		}
		// TODO Kosten abziehen
		// TODO Ultraschall aus Patienten auslesen und senden.
		return neuesUltraschall.toString();
	}

	/**
	 * 
	 * @param patientID (int)
	 * @return
	 */
	@GET
	@Path("/getRoentgen/{patientID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRoentgen(@PathParam("patientID") int patientID) {
		Roentgen neuesRoentgen;
		Patient p = runde.getPatient(patientID);
		if (p != null) {
			neuesRoentgen = p.getKrankheit().getRoentgen();
		} else {
			return "Patient nicht in Zelt";
		}
		// TODO Kosten abziehen
		// TODO Ultraschall aus Patienten auslesen und senden.
		return neuesRoentgen.toString();
	}

	/**
	 * Anamnese mit veränderung der Befragung
	 * @param patientID (int)
	 * @param frageID (int)
	 * @return
	 */
	@GET
	@Path("/getAnamnese/{patientID}/{frageID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAnamnese(@PathParam("patientID") int patientID, @PathParam("frageID") int frageID) {
		String antwort;
		Patient p = runde.getPatient(patientID);
		if (p != null) {
			antwort = p.getKrankheit().getAnamnese().getAntwort(frageID);
		} else {
			return "Patient nicht in Zelt";
		}
		return antwort;
	}

	/**
	 * Anamnese aufruf ohne veränderung/nebenwirkungen, ideal um Anamnese zu starten
	 * @param patientID (int)
	 * @return
	 */
	@GET
	@Path("/getAnamnese/{patientID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String startAnamnese(@PathParam("patientID") int patientID) {
		String antwort;
		Patient p = runde.getPatient(patientID);
		if (p != null) {
			antwort = p.getKrankheit().getAnamnese().getAntwort();
		} else {
			return "Patient nicht in Zelt";
		}
		return antwort;
	}

	/**
	 * 
	 * @param patientID (int)
	 * @param krankheit (int)
	 * @return
	 */
	@GET
	@Path("/diagnose/{patientID}/{krankheit}")
	@Produces(MediaType.APPLICATION_JSON)
	public String diagnose(@PathParam("patientID") int patientID, @PathParam("krankheit") int krankheit) {
		Diagnose ergebniss = runde.setDiagnose(krankheit);

		return ergebniss.toString();
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/getAlleKrankheiten")
	@Produces(MediaType.APPLICATION_JSON)
	public String diagnose() {
		String ergebniss = Krankheit.getAlleKrankheiten();

		return ergebniss;
	}

	/**
	 * 
	 * @param geraet (String)
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
	 * @param antwortID (String)
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
	 * @param mailID (String)
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
	 * @param sponsorID String)
	 * @return
	 */
	@GET
	@Path("/werbeSponsorAn/{sponsorID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String werbeSponsorAn(@PathParam("sponsorID") String sponsorID) {

		return " neue Frage, ID, AntwortA, ID, AntwortB, ID, AntwortC, ID, AntwortD, ID";
	}
}