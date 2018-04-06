package de.gae.imao.model;

public class Untersuchungsmethode {
	private String name;
	private long behandlungsKosten;
	private long anschaffungsKosten;
	private boolean freigeschaltet;

	public Untersuchungsmethode() {
	}

	public Untersuchungsmethode(String name, int behandlungsKosten, int anschaffungsKosten, boolean freigeschaltet) {
		this.name = name;
		this.behandlungsKosten = behandlungsKosten;
		this.anschaffungsKosten = anschaffungsKosten;
		this.freigeschaltet = freigeschaltet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getBehandlungsKosten() {
		return behandlungsKosten;
	}

	public void setBehandlungsKosten(long kosten) {
		this.behandlungsKosten = kosten;
	}

	public long getAnschaffungsKosten() {
		return anschaffungsKosten;
	}

	public void setAnschaffungsKosten(long anschaffungsKosten) {
		this.anschaffungsKosten = anschaffungsKosten;
	}

	public boolean isFreigeschaltet() {
		return freigeschaltet;
	}

	public void setFreigeschaltet(boolean freigeschaltet) {
		this.freigeschaltet = freigeschaltet;
	}

}
