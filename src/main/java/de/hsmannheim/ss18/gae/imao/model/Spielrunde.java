package de.hsmannheim.ss18.gae.imao.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Spielrunde {
	protected List<Untersuchungsmethode> untersuchungsmethoden = new ArrayList<>();
	protected int runde;
	protected Manager manager;
	protected Arzt arzt;
	protected String nachricht;

	public Spielrunde(int runde, Manager manager, Arzt arzt) {
		super();
		this.runde = runde;
		this.manager = manager;
		this.arzt = arzt;
		untersuchungsmethoden.add(new Untersuchungsmethode("Anamnese", 0, 0, 0, true));
		untersuchungsmethoden.add(new Untersuchungsmethode("Blutbild", 10, 0, 100, true));
		untersuchungsmethoden.add(new Untersuchungsmethode("Ultraschall", 50, 1000, 500, false));
		untersuchungsmethoden.add(new Untersuchungsmethode("Roentgen", 50, 1000, 500, false));
	}

	public List<Untersuchungsmethode> getKatalog() {
		return untersuchungsmethoden;
	}

	protected abstract void erzeugeNeueRunde();

}
