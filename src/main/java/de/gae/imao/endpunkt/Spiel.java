package de.gae.imao.endpunkt;

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

import de.gae.imao.model.Anamnese;
import de.gae.imao.model.Arzt;
import de.gae.imao.model.Blutbild;
import de.gae.imao.model.DiagnoseErgebniss;
import de.gae.imao.model.GeraetGekauft;
import de.gae.imao.model.Manager;
import de.gae.imao.model.Patient;
import de.gae.imao.model.Person;
import de.gae.imao.model.Spielrunde;
import de.gae.imao.model.Ultraschall;
import de.gae.imao.model.Untersuchungsmethode;

@Path("/spiel")
public class Spiel extends ResourceConfig {
	private List<Patient> aktuellePatienten;

	 @GET
	 @Path("/")
	 @Produces(MediaType.TEXT_PLAIN)
	 public String hello() {
	 return "It works";
	}

	@GET
	@Path("/start/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person starteSpiel(@PathParam("type") String type) {
		if (type.equals("arzt")) {
			return new Arzt("Arzt Mustermann", 30);
		} else {
			return new Manager("Manager Mustermann", 30);
		}
	}

	@GET
	@Path("/neueRunde")
	@Produces(MediaType.APPLICATION_JSON)
	public Spielrunde neueRunde() {
		// TODO Patienten erzeugen
		// TODO Budget berechnen
		// TODO Ruf berechnen
		// TODO Anzahl der Patienten berechnen
		// TODO runde Z�hlen

		Spielrunde runde = new Spielrunde("erste Runde", 1000, 0, 5, 1);
		return runde;
	}

	@GET
	@Path("/getPatatient")
	@Produces(MediaType.APPLICATION_JSON)
	public Patient getPatatient() {
		// TODO Patienten hereinbitten

		Patient runde = new Patient("Patient 1", 30, 1, 1);
		return runde;
	}

	@GET
	@Path("/getKatalog")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Untersuchungsmethode> getKatalog() {
		// TODO Liste auslesen

		List<Untersuchungsmethode> methoden = new ArrayList<>();
		methoden.add(new Untersuchungsmethode("Anamnese", 0, 0, true));
		methoden.add(new Untersuchungsmethode("Blutbild", 10, 0, true));
		methoden.add(new Untersuchungsmethode("Ultraschall", 50, 1000, false));
		return methoden;
	}

	@GET
	@Path("/kaufeGeraet/{geraet}")
	@Produces(MediaType.APPLICATION_JSON)
	public GeraetGekauft kaufeGeraet(@PathParam("geraet") String geraet) {
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
		return gekauft;
	}

	@GET
	@Path("/getUntersuchungsmethoden")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Untersuchungsmethode> getUntersuchungsmethoden() {
		List<Untersuchungsmethode> methoden = new ArrayList<>();
		// TODO Untersuchungsmethoden Speichern bzw aus Speicher laden

		methoden.add(new Untersuchungsmethode("Anamnese", 0, 0, true));
		methoden.add(new Untersuchungsmethode("Blutbild", 10, 0, true));
		return methoden;
	}

	@GET
	@Path("/getAnamnese/{patient}")
	@Produces(MediaType.APPLICATION_JSON)
	public Anamnese getAnamnese(@PathParam("id") int id) {
		// Kosten abziehen
		Map<String, String> fragebogen = new HashMap<>();
		fragebogen.put("Frage 1", "Antwort 1");
		fragebogen.put("Frage 2", "Antwort 2");
		fragebogen.put("Frage 3", "Antwort 3");
		Anamnese anamnese = new Anamnese(fragebogen, "Anamnese", 1000);
		return anamnese;
	}

	@GET
	@Path("/getBlutbild/{patient}")
	@Produces(MediaType.APPLICATION_JSON)
	public Blutbild getBlutbild(@PathParam("id") int id) {
		// Kosten abziehen
		Blutbild blutbild = new Blutbild("Blutbild", 1, 990);
		return blutbild;
	}

	@GET
	@Path("/getUltraschall/{patient}")
	@Produces(MediaType.APPLICATION_JSON)
	public Ultraschall getUltraschall(@PathParam("id") int id) {
		// Kosten abziehen
		Ultraschall ultraschall = new Ultraschall("Ultraschall", 1, 950);
		return ultraschall;
	}

	@GET
	@Path("/diagnose/{krankheit}")
	@Produces(MediaType.APPLICATION_JSON)
	public DiagnoseErgebniss diagnose(@PathParam("krankheit") String krankheit) {
		DiagnoseErgebniss ergebniss;
		if (krankheit.equals("abc")) {
			// erfolgreich => Ruf zuwachs
			ergebniss = new DiagnoseErgebniss("Erfolgreich", 900, 10);
		} else {
			// nicht erfolgreich => Ruf verlust
			ergebniss = new DiagnoseErgebniss("NICHT Erfolgreich", 900, 0);
		}

		return ergebniss;
	}

}
