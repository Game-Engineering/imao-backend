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
				+ "<a href=\"localhost:8080/imao/api/spiel/getPatatient\">localhost:8080/imao/api/spiel/getPatatient</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/neueRunde\">localhost:8080/imao/api/spiel/neueRunde</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getBlutbild/1\">localhost:8080/imao/api/spiel/getBlutbild/1</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getUltraschall/1\">localhost:8080/imao/api/spiel/getUltraschall/1</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getRoentgen/1\">localhost:8080/imao/api/spiel/getRoentgen/1</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getKatalog\">localhost:8080/imao/api/spiel/getKatalog</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/kaufeGeraet/geraetID\">localhost:8080/imao/api/spiel/kaufeGeraet/geraetID</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getUntersuchungsmethoden\">localhost:8080/imao/api/spiel/getUntersuchungsmethoden</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getAnamnese/1\">localhost:8080/imao/api/spiel/getAnamnese/1</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/diagnose/KrankheitID\">localhost:8080/imao/api/spiel/diagnose/KrankheitID</a><br>";
	}

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


	@GET
	@Path("/getPatatient")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPatatient() {
		Patient pat = runde.getPatient();
		if (pat != null) {
			return runde.getPatient().toString();
		} else {
			return "Kein Patient";
		}
	}

	@GET
	@Path("/getKatalog")
	@Produces(MediaType.APPLICATION_JSON)
	public String getKatalog() {
		// TODO Liste auslesen

		return runde.getKatalog().toString();
	}

	@GET
	@Path("/kaufeGeraet/{geraet}")
	@Produces(MediaType.APPLICATION_JSON)
	public String kaufeGeraet(@PathParam("geraet") String geraet) {
		GeraetGekauft gekauft = new GeraetGekauft(runde.kaufeGeraet(geraet), 1000);

		return gekauft.toString();
	}

	@GET
	@Path("/getUntersuchungsmethoden")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUntersuchungsmethoden() {
		return runde.getUntersuchungsmethoden().toString();
	}

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

	@GET
	@Path("/getAnamnese/{patientID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAnamnese(@PathParam("patientID") int patientID) {
		// TODO Kosten abziehen
		Anamnese anamnese;
		Patient p = runde.getPatient(patientID);
		if (p != null) {
			anamnese = p.getKrankheit().getAnamnese();
		} else {
			return "Patient nicht in Zelt";
		}
		return anamnese.toString();
	}

	@GET
	@Path("/diagnose/{patientID}/{krankheit}")
	@Produces(MediaType.APPLICATION_JSON)
	public String diagnose(@PathParam("patientID") int patientID, @PathParam("krankheit") String krankheit) {
		Diagnose ergebniss = runde.setDiagnose(krankheit);

		return ergebniss.toString();
	}

}