package de.hsmannheim.ss18.gae.imao.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SpielrundeMedizin extends Spielrunde {

	private String nachricht;
	private Patient inZelt;
	private List<Patient> wartendePatienten = new ArrayList<>();
	private List<Patient> behandeltePatienten = new ArrayList<>();
	private List<Patient> nichtBehandeltePatienten = new ArrayList<>();

	String[] lybischeWeiblicheVornamen = { "Aya", "Reem", "Mona", "Nesreen", "Ranai", "Hoda", "Fatima", "Sarah",
			"Marwa", "Eisha" };
	String[] lybischeMaenlicheVornamen = { "Mohammed", "Hamza", "Abdallah", "Khaled", "Ahmeed", "Ibrahim", "Tareq",
			"Ali", "Mahmoud", "Hassan" };
	String[] afrikanischeNachnamen = { "Azikiwe", "Awolowo", "Bello", "Balewa", "Akintola", "Okotie-Eboh", "Nzeogwu",
			"Onwuatuegwu", "Okafor", "Okereke", "Okeke", "Okonkwo", "Okoye", "Okorie", "Obasanjo", "Babangida",
			"Buhari", "Dimka", "Diya", "Odili" };

	/**
	 * 
	 * @param runde
	 */
	public SpielrundeMedizin(int runde, Manager manager, Arzt arzt) {
		super(runde, manager, arzt);
		erzeugeNeueRunde();
	}

	/**
	 * 
	 * @param runde
	 */

	@Override
	protected void erzeugeNeueRunde() {
		nachricht = "Runde " + runde + " wurde gestartet.";
		erzeugePatienten(runde + 2);
		arzt.setBudget(1000);
		arzt.setRuf(1000);
	}

	/**
	 * 
	 * @param int
	 *            wartende
	 */
	private void erzeugePatienten(int wartende) {

		Random random = new Random();
		for (int i = 0; i < wartende; i++) {
			switch (randomEnum(EGeschlecht.class)) {
			case WEIBLICH:
				wartendePatienten.add(new Patient(lybischeWeiblicheVornamen[random.nextInt(10)],
						afrikanischeNachnamen[random.nextInt(10)], (random.nextInt(21) + 20), EGeschlecht.WEIBLICH,
						randomEnum(EKrankheit.class)));
				break;
			case MAENNLICH:
				wartendePatienten.add(new Patient(lybischeMaenlicheVornamen[random.nextInt(10)],
						afrikanischeNachnamen[random.nextInt(10)], (random.nextInt(21) + 20), EGeschlecht.MAENNLICH,
						randomEnum(EKrankheit.class)));
				break;

			default:
				break;
			}
		}
	}

	/**
	 * Übergebe eine ENUM Klasse um einen zufälligen Wert aus dieser Liste zu
	 * erhalten
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
		Random random = new Random();
		int x = random.nextInt(clazz.getEnumConstants().length);
		return clazz.getEnumConstants()[x];
	}

	public Patient getPatient() {
		Patient pat = null;
		System.out.println("" + wartendePatienten);
		if (inZelt != null) {
			nichtBehandeltePatienten.add(inZelt);
			inZelt = null;
		}
		if (wartendePatienten.size() > 0) {
			pat = wartendePatienten.get(0);
			if (pat != null) {
				wartendePatienten.remove(pat);
				inZelt = pat;
			}
		}
		return pat;
	}

	public Patient getPatient(int ID) {
		if (inZelt.getPatientID() == ID) {
			return inZelt;
		} else {
			return null;
		}

	}

	public Diagnose setDiagnose(int krankheitID) {
		System.out.println("***DIAGNOSE: Vermutung:" + krankheitID);
		System.out.println("***DIAGNOSE: Tatsächlich:" + inZelt.getKrankheit().getKrankheit().getId());
		Diagnose erg = null;
		if (krankheitID == inZelt.getKrankheit().getKrankheit().getId()) {
			inZelt.setDiagnose(EDiagnoseErgebnis.ERFOLGREICH);
			behandeltePatienten.add(inZelt);
			inZelt = null;
			erg = new Diagnose("Erfolgreich", 900, 0);
		} else {
			inZelt.setDiagnose(EDiagnoseErgebnis.NICHT_ERFOLGREICH);
			behandeltePatienten.add(inZelt);
			inZelt = null;
			erg = new Diagnose("NICHT Erfolgreich", 900, 0);
		}
		return erg;
	}

	public String getDiagnose() {
		if (inZelt.getDiagnose() == EDiagnoseErgebnis.ERFOLGREICH) {
			return "Erfolgreich";
		} else if (inZelt.getDiagnose() == EDiagnoseErgebnis.NICHT_ERFOLGREICH) {
			return "NICHT Erfolgreich";
		} else {
			return "Nicht behandelt";
		}
	}

	public String sendeMail(int ID) {
		String absender;
		String mailInhalt;
		if (ID == 1) {
			absender = "" + arzt.vorname + " " + arzt.vorname;
			mailInhalt = EMoeglicheMails.valueOf("" + ID).getMailText();
			System.out.println(absender + ", " + mailInhalt);
			Mail mail = new Mail(absender, mailInhalt);
			arzt.sendeMail(mail);
			if (manager != null) {
				manager.erhalteMail(mail);
			}
			return mail.toString();
		} else {
			return "Mail nicht gesendet";
		}
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("budget", arzt.getBudget());
		objectNode.put("nachricht", this.nachricht);
		objectNode.put("ruf", arzt.getRuf());
		objectNode.put("runde", this.runde);
		objectNode.put("wartendePatienten", this.wartendePatienten.size());

		List<Map<String, Integer>> patienten = new ArrayList<>();
		for (Patient patient : wartendePatienten) {
			Map<String, Integer> map = new HashMap<>();
			map.put("ID", patient.getPatientID());
			map.put("erscheinung", patient.getKrankheit().getErscheinung());
			patienten.add(map);
		}

		objectNode.put("patienten", patienten.toString());
		System.out.println(patienten);
		return objectNode.toString();
	}

	public String getNachricht() {
		return nachricht;
	}

	public void setNachricht(String nachricht) {
		this.nachricht = nachricht;
	}

	public int getRunde() {
		return runde;
	}

	public void setRunde(int runde) {
		this.runde = runde;
	}

	public List<Untersuchungsmethode> getUntersuchungsmethoden() {
		List<Untersuchungsmethode> methoden = new ArrayList<>();
		Iterator<Untersuchungsmethode> it = untersuchungsmethoden.iterator();
		while (it.hasNext()) {
			Untersuchungsmethode m = it.next();
			if (m.isFreigeschaltet()) {
				methoden.add(m);
			}
		}
		return methoden;
	}

}
