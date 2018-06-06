package de.hsmannheim.ss18.gae.imao.endpunkt;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.hsmannheim.ss18.gae.imao.model.Blutbild;
import de.hsmannheim.ss18.gae.imao.model.Diagnose;
import de.hsmannheim.ss18.gae.imao.model.Krankheit;
import de.hsmannheim.ss18.gae.imao.model.Manager;
import de.hsmannheim.ss18.gae.imao.model.Patient;
import de.hsmannheim.ss18.gae.imao.model.Roentgen;
import de.hsmannheim.ss18.gae.imao.model.SpielrundeMedizin;
import de.hsmannheim.ss18.gae.imao.model.Ultraschall;
import de.hsmannheim.ss18.gae.imao.model.enums.EGeschlecht;

@Path("spiel/medizin")
public class Medizin extends Spiel {
	private static int rundencount = 0;
	private static SpielrundeMedizin rundeArzt;

	@Override
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String hello() {
		return "<h1>It Works!</h1>";
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/neueRunde")
	@Produces(MediaType.APPLICATION_JSON)
	public String neueRunde() {
		if (arzt == null) {
			return "Sie Haben keinen Arzt angelegt";
		}
		if (manager == null) {
			manager = new Manager("Dummy", "Dumm", EGeschlecht.MAENNLICH);
		}
		rundeArzt = new SpielrundeMedizin(++rundencount, manager, arzt);
		return rundeArzt.toString();
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/getPatient")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPatatient() {
		Patient pat = rundeArzt.getPatient();
		if (pat != null) {
			return pat.toString();
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

		return rundeArzt.getKatalog().toString();
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/getUntersuchungsmethoden")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUntersuchungsmethoden() {
		return rundeArzt.getUntersuchungsmethoden().toString();
	}

	/**
	 * 
	 * @param patientID
	 *            (int)
	 * @return
	 */
	@GET
	@Path("/getBlutbild/{patientID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBlutbild(@PathParam("patientID") int patientID) {
		Blutbild neuesBlutbild;
		Patient p = rundeArzt.getPatient(patientID);
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
	 * @param patientID
	 *            (int)
	 * @return
	 */
	@GET
	@Path("/getUltraschall/{patientID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUltraschall(@PathParam("patientID") int patientID) {
		Ultraschall neuesUltraschall;
		Patient p = rundeArzt.getPatient(patientID);
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
	 * @param patientID
	 *            (int)
	 * @return
	 */
	@GET
	@Path("/getRoentgen/{patientID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRoentgen(@PathParam("patientID") int patientID) {
		Roentgen neuesRoentgen;
		Patient p = rundeArzt.getPatient(patientID);
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
	 * 
	 * @param patientID
	 *            (int)
	 * @param frageID
	 *            (int)
	 * @return
	 */
	@GET
	@Path("/getAnamnese/{patientID}/{frageID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAnamnese(@PathParam("patientID") int patientID, @PathParam("frageID") int frageID) {
		String antwort;
		Patient p = rundeArzt.getPatient(patientID);
		if (p != null) {
			antwort = p.getKrankheit().getAnamnese().getAntwort(frageID);
		} else {
			return "Patient nicht in Zelt";
		}
		return antwort;
	}

	/**
	 * Anamnese aufruf ohne veränderung/nebenwirkungen, ideal um Anamnese zu
	 * starten
	 * 
	 * @param patientID
	 *            (int)
	 * @return
	 */
	@GET
	@Path("/getAnamnese/{patientID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String startAnamnese(@PathParam("patientID") int patientID) {
		String antwort;
		Patient p = rundeArzt.getPatient(patientID);
		if (p != null) {
			antwort = p.getKrankheit().getAnamnese().getAntwort();
		} else {
			return "Patient nicht in Zelt";
		}
		return antwort;
	}

	/**
	 * 
	 * @param patientID
	 *            (int)
	 * @param krankheit
	 *            (int)
	 * @return
	 */
	@GET
	@Path("/diagnose/{patientID}/{krankheit}")
	@Produces(MediaType.APPLICATION_JSON)
	public String diagnose(@PathParam("patientID") int patientID, @PathParam("krankheit") int krankheit) {
		Diagnose ergebniss = rundeArzt.setDiagnose(krankheit);

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

	public static void resetRundencount() {
		Medizin.rundencount = 0;
	}

	public static void resetRundeArzt() {
		Medizin.rundeArzt = null;
		Patient.resetIDCount();
			}
	
	
	}
