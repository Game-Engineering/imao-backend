package de.hsmannheim.ss18.gae.imao.model;

import java.util.List;
import java.util.Random;

import org.eclipse.persistence.annotations.ReturnUpdate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.Iterator;

public class Spielrunde {

	private String nachricht;
	private long budget;
	private long ruf;
	private int runde;
	private Patient inZelt;
	private List<Patient> wartendePatienten = new ArrayList<>();
	private List<Patient> behandeltePatienten = new ArrayList<>();
	private List<Untersuchungsmethode> untersuchungsmethoden = new ArrayList<>();

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
	public Spielrunde(int runde) {
		this.runde = runde;
		untersuchungsmethoden.add(new Untersuchungsmethode("Anamnese", 0, 0, true));
		untersuchungsmethoden.add(new Untersuchungsmethode("Blutbild", 10, 0, true));
		untersuchungsmethoden.add(new Untersuchungsmethode("Ultraschall", 50, 1000, false));
		untersuchungsmethoden.add(new Untersuchungsmethode("Roentgen", 50, 1000, false));
		erzeugeNeueRunde();
	}

	/**
	 * 
	 * @param runde
	 */
	private void erzeugeNeueRunde() {
		nachricht = "Runde " + runde + " wurde gestartet.";
		erzeugePatienten(runde + 2);
		budget = 1000;
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
		if (wartendePatienten.get(0) != null) {
			pat = wartendePatienten.get(0);
			wartendePatienten.remove(0);
			inZelt = pat;
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

	public Diagnose setDiagnose(String diagnose) {
		Diagnose erg = null;
		if (diagnose.equals(inZelt.getKrankheit())) {
			inZelt.setDiagnose(EDiagnoseErgebniss.ERFOLGREICH);
			behandeltePatienten.add(inZelt);
			inZelt = null;
			erg = new Diagnose("Erfolgreich", 900, 0);
		} else {
			inZelt.setDiagnose(EDiagnoseErgebniss.NICHT_ERFOLGREICH);
			behandeltePatienten.add(inZelt);
			inZelt = null;
			erg = new Diagnose("NICHT Erfolgreich", 900, 0);
		}
		return erg;
	}

	public String getDiagnose() {
		if (inZelt.getDiagnose() == EDiagnoseErgebniss.ERFOLGREICH) {
			return "Erfolgreich";
		} else if (inZelt.getDiagnose() == EDiagnoseErgebniss.NICHT_ERFOLGREICH) {
			return "NICHT Erfolgreich";
		} else {
			return "Nicht behandelt";
		}
	}

	public List<Untersuchungsmethode> kaufeGeraet(String geraet) {
		Iterator<Untersuchungsmethode> iterator = untersuchungsmethoden.iterator();
		while (iterator.hasNext()) {
			Untersuchungsmethode methode = iterator.next();
			if (geraet.equals(methode.getName())) {
				methode.setFreigeschaltet(true);
			}
		}
		return untersuchungsmethoden;
	}

	public String getNachricht() {
		return nachricht;
	}

	public void setNachricht(String nachricht) {
		this.nachricht = nachricht;
	}

	public long getBudget() {
		return budget;
	}

	public void setBudget(long budget) {
		this.budget = budget;
	}

	public long getRuf() {
		return ruf;
	}

	public void setRuf(long ruf) {
		this.ruf = ruf;
	}

	public int getRunde() {
		return runde;
	}

	public void setRunde(int runde) {
		this.runde = runde;
	}

	public List<Untersuchungsmethode> getKatalog() {
		return untersuchungsmethoden;
	}

	public List<Untersuchungsmethode> getUntersuchungsmethoden() {
		List<Untersuchungsmethode> methoden = new ArrayList<>();
		Iterator<Untersuchungsmethode> it = methoden.iterator();
		while (it.hasNext()) {
			Untersuchungsmethode m = it.next();
			if (m.isFreigeschaltet()) {
				methoden.add(m);
			}
		}
		return methoden;
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("budget", this.budget);
		objectNode.put("nachricht", this.nachricht);
		objectNode.put("ruf", this.ruf);
		objectNode.put("runde", this.runde);
		objectNode.put("wartendePatienten", this.wartendePatienten.size());

		return objectNode.toString();
	}
}
