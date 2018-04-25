package de.hsmannheim.ss18.gae.imao.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Spielrunde {
	protected List<Untersuchungsmethode> untersuchungsmethoden = new ArrayList<>();
	protected int runde;

	public Spielrunde(int runde) {
		super();
		this.runde = runde;
		untersuchungsmethoden.add(new Untersuchungsmethode("Anamnese", 0, 0, true));
		untersuchungsmethoden.add(new Untersuchungsmethode("Blutbild", 10, 0, true));
		untersuchungsmethoden.add(new Untersuchungsmethode("Ultraschall", 50, 1000, false));
		untersuchungsmethoden.add(new Untersuchungsmethode("Roentgen", 50, 1000, false));
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

	public List<Untersuchungsmethode> getKatalog() {
		return untersuchungsmethoden;
	}

	protected abstract void erzeugeNeueRunde();

}
