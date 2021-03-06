package de.hsmannheim.ss18.gae.imao.model.medizin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.hsmannheim.ss18.gae.imao.model.Spielrunde;
import de.hsmannheim.ss18.gae.imao.model.enums.EDiagnoseErgebnis;
import de.hsmannheim.ss18.gae.imao.model.enums.EGeschlecht;
import de.hsmannheim.ss18.gae.imao.model.enums.EKrankheit;
import de.hsmannheim.ss18.gae.imao.model.enums.EMoeglicheMails;
import de.hsmannheim.ss18.gae.imao.model.wirtschaft.Mail;
import de.hsmannheim.ss18.gae.imao.model.wirtschaft.Manager;

import java.util.*;

public class SpielrundeMedizin extends Spielrunde {

	private String nachricht;
	private Patient inZelt;
	private List<Patient> wartendePatienten = new ArrayList<>();
	private List<Patient> behandeltePatienten = new ArrayList<>();
	private List<Patient> nichtBehandeltePatienten = new ArrayList<>();

	private String[] lybischeWeiblicheVornamen = { "Aya", "Reem", "Mona", "Nesreen", "Ranai", "Hoda", "Fatima", "Sarah",
			"Marwa", "Eisha" };
	private String[] lybischeMaenlicheVornamen = { "Mohammed", "Hamza", "Abdallah", "Khaled", "Ahmeed", "Ibrahim", "Tareq",
			"Ali", "Mahmoud", "Hassan" };
	private String[] afrikanischeNachnamen = { "Azikiwe", "Awolowo", "Bello", "Balewa", "Akintola", "Okotie-Eboh", "Nzeogwu",
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
	 */
	@Override
	protected void erzeugeNeueRunde() {
		nachricht = "Runde " + runde + " wurde gestartet.";
		erzeugePatienten(runde + 2);
		arzt.setBudget(1000);
		arzt.setRuf(50);
	}

    /**
     *
     * @param wartende Anzahl der Patienten
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

    /**
     *
     * @param krankheitID
     * @return
     */
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

    /**
     *
     * @return
     */
	public String getDiagnose() {
		if (inZelt.getDiagnose() == EDiagnoseErgebnis.ERFOLGREICH) {
			return "Erfolgreich";
		} else if (inZelt.getDiagnose() == EDiagnoseErgebnis.NICHT_ERFOLGREICH) {
			return "NICHT Erfolgreich";
		} else {
			return "Nicht behandelt";
		}
	}

    /**
     *
     * @param ID
     * @return
     */
	public String sendeMail(String ID) {
		String absender = "" + arzt.vorname + " " + arzt.vorname;
		String betreff;
		String mailInhalt;
		switch (ID.toUpperCase()) {
		case "LOB":
			mailInhalt = EMoeglicheMails.LOB.getMailText();
			betreff = "Lob";
			break;
		case "ABMAHNUNG":
			mailInhalt = EMoeglicheMails.ABMAHNUNG.getMailText();
			betreff = "Abmahnung";
			break;
//		case "GERAET_GEKAUFT":
//			mailInhalt = EMoeglicheMails.GERAET_GEKAUFT.getMailText();
//			betreff = "Gerät gekauft";
//			break;
		default:
			mailInhalt = "Mail konnte nicht gesendet werden.";
			betreff = "Fehler";
		}

		System.out.println(absender + ", " + betreff + ", " + mailInhalt);
		Mail mail = new Mail(absender, betreff, mailInhalt);
		arzt.sendeMail(mail);
		if (manager != null) {
			manager.erhalteMail(mail);
		}
		return mail.toString();

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

    /**
     *
     * @return
     */
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
