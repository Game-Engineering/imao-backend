package de.hsmannheim.ss18.gae.imao.model;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Spielrunde {

	private String nachricht;
	private long budget;
	private long ruf;
	private int runde;
	private Patient inZelt;
	private List<Patient> wartendePatienten = new ArrayList<>();
	private List<Patient> behandeltePatienten = new ArrayList<>();

	String[] lybischeWeiblicheVornamen = { "Aya", "Reem", "Mona", "Nesreen", "Ranai", "Hoda", "Fatima", "Sarah",
			"Marwa", "Eisha" };
	String[] lybischeMaenlicheVornamen = { "Mohammed", "Hamza", "Abdallah", "Khaled", "Ahmeed", "Ibrahim", "Tareq",
			"Ali", "Mahmoud", "Hassan" };
	String[] afrikanischeNachnamen = { "Azikiwe", "Awolowo", "Bello", "Balewa", "Akintola", "Okotie-Eboh", "Nzeogwu",
			"Onwuatuegwu", "Okafor", "Okereke", "Okeke", "Okonkwo", "Okoye", "Okorie", "Obasanjo", "Babangida",
			"Buhari", "Dimka", "Diya", "Odili" };

	/**
	 * 
	 */
	public Spielrunde() {
	}

	/**
	 * 
	 * @param runde
	 */
	public Spielrunde(int runde) {
		erzeugeNeueRunde(runde);
	}

	/**
	 * 
	 * @param runde
	 */
	private void erzeugeNeueRunde(int runde) {
		nachricht = "Runde " + runde + " wurde gestartet.";
		erzeugeNeueRunde(runde + 3);
		budget = budget + 100;
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
		Patient pat = wartendePatienten.get(0);
		wartendePatienten.remove(0);
		inZelt = pat;
		return pat;
	}

	public void setDiagnose(String diagnose) {
		inZelt.setDiagnose(diagnose.equals(inZelt.getKrankheit()));
	}

	public String getDiagnose() {
		if (inZelt.isDiagnose()) {
			return "Erfilgreich";
		} else {
			return "NICHT Erfolgreich";
		}
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

	@Override
	public String toString() {
		return "Spielrunde [Message=" + nachricht + ", Budget=" + budget + ", Ruf=" + ruf + ", WartendePatienten="
				+ wartendePatienten + ", Runde=" + runde + "]";
	}
}
