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
import de.hsmannheim.ss18.gae.imao.model.DiagnoseErgebniss;
import de.hsmannheim.ss18.gae.imao.model.GeraetGekauft;
import de.hsmannheim.ss18.gae.imao.model.Manager;
import de.hsmannheim.ss18.gae.imao.model.Patient;
import de.hsmannheim.ss18.gae.imao.model.Person;
import de.hsmannheim.ss18.gae.imao.model.Spielrunde;
import de.hsmannheim.ss18.gae.imao.model.Ultraschall;
import de.hsmannheim.ss18.gae.imao.model.Untersuchungsmethode;

@Path("/spiel")
public class Spiel extends ResourceConfig {
	
	private int rundencount = 0;
	private Spielrunde runde;
	private Arzt arzt;
	private Manager manager;

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String hello() {
		return "<h1>It Works!</h1>"
				+"<h3>Benutze die mitlere Maustaste zum &ouml;ffnen der Links </h3>"
				+ "<a href=\"localhost:8080/imao/api/spiel/start/arzt/Max/Mustermann\">localhost:8080/imao/api/spiel/start/arzt/Max/Mustermann</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getPatatient\">localhost:8080/imao/api/spiel/getPatatient</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/neueRunde\">localhost:8080/imao/api/spiel/neueRunde</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getBlutbild/1234\">localhost:8080/imao/api/spiel/getBlutbild/1234</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getUltraschall/1234\">localhost:8080/imao/api/spiel/getUltraschall/1234</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getKatalog\">localhost:8080/imao/api/spiel/getKatalog</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/kaufeGeraet/geraetID\">localhost:8080/imao/api/spiel/kaufeGeraet/geraetID</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getUntersuchungsmethoden\">localhost:8080/imao/api/spiel/getUntersuchungsmethoden</a><br>"
				+ "<a href=\"localhost:8080/imao/api/spiel/getAnamnese/1234\">localhost:8080/imao/api/spiel/getAnamnese/1234</a><br>"
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

		Spielrunde runde = new Spielrunde(1);
		return runde.toString();
	}
	
	@GET
	@Path("/getPatatient")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPatatient() {		
		return new Patient().toString();
	}
	
	@GET
	@Path("/getKatalog")
	@Produces(MediaType.APPLICATION_JSON)
	public String getKatalog() {
		// TODO Liste auslesen

		List<Untersuchungsmethode> methoden = new ArrayList<>();
		methoden.add(new Untersuchungsmethode("Anamnese", 0, 0, true));
		methoden.add(new Untersuchungsmethode("Blutbild", 10, 0, true));
		methoden.add(new Untersuchungsmethode("Ultraschall", 50, 1000, false));
		
		return methoden.toString();
	}
	
	@GET
	@Path("/kaufeGeraet/{geraet}")
	@Produces(MediaType.APPLICATION_JSON)
	public String kaufeGeraet(@PathParam("geraet") String geraet) {
		List<Untersuchungsmethode> methoden = new ArrayList<>();
		methoden.add(new Untersuchungsmethode("Anamnese", 0, 0, true));
		methoden.add(new Untersuchungsmethode("Blutbild", 10, 0, true));
		methoden.add(new Untersuchungsmethode("Ultraschall", 50, 1000, false));
		GeraetGekauft gekauft;
		if (geraet.equals("Ultraschall")) {
			methoden.get(2).setFreigeschaltet(true);
			gekauft = new GeraetGekauft(methoden, 0);
		} else {
			gekauft = new GeraetGekauft(methoden, 1000);
		}
		return gekauft.toString();
	}
	
	@GET
	@Path("/getUntersuchungsmethoden")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUntersuchungsmethoden() {
		List<Untersuchungsmethode> methoden = new ArrayList<>();
		// TODO Untersuchungsmethoden Speichern bzw aus Speicher laden

		methoden.add(new Untersuchungsmethode("Anamnese", 0, 0, true));
		methoden.add(new Untersuchungsmethode("Blutbild", 10, 0, true));
		return methoden.toString();
	}
	
	@GET
	@Path("/getBlutbild/{patientID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBlutbild(@PathParam("patientID") int patientID) {
		Blutbild neuesBlutbild = new Blutbild(patientID);
		// TODO kosten
		// TODO Blutbild aus Patienten auslesen und senden.
		return neuesBlutbild.toString();
	}
	
	@GET
	@Path("/getUltraschall/{patientID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUltraschall(@PathParam("patientID") int patientID) {
		Ultraschall neuesUltraschall = new Ultraschall(patientID);
		// TODO Kosten abziehen
		// TODO Ultraschall aus Patienten auslesen und senden.
		return neuesUltraschall.toString();
	}
	
	@GET
	@Path("/getAnamnese/{patientID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAnamnese(@PathParam("patientID") int patientID) {
		// TODO Kosten abziehen
		Map<String, String> fragebogen = new HashMap<>();
		fragebogen.put("Frage 1", "Antwort 1");
		fragebogen.put("Frage 2", "Antwort 2");
		fragebogen.put("Frage 3", "Antwort 3");
		Anamnese anamnese = new Anamnese(fragebogen, "Anamnese", 1000);
		return anamnese.toString();
	}
	
	@GET
	@Path("/diagnose/{patientID}/{krankheit}")
	@Produces(MediaType.APPLICATION_JSON)
	public String diagnose(@PathParam("patientID") int patientID, @PathParam("krankheit") String krankheit) {
		DiagnoseErgebniss ergebniss;
		if (krankheit.equals("abc")) {
			// erfolgreich => Ruf zuwachs
			ergebniss = new DiagnoseErgebniss("Erfolgreich", 900, 10);
		} else {
			// nicht erfolgreich => Ruf verlust
			ergebniss = new DiagnoseErgebniss("NICHT Erfolgreich", 900, 0);
		}

		return ergebniss.toString();
	}

}