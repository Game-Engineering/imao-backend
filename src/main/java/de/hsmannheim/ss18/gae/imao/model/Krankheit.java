package de.hsmannheim.ss18.gae.imao.model;

public class Krankheit {
	private EKrankheit krankheit;
	private Ultraschall ultraschall;
	private Roentgen roentgen;
	private int erscheinung;
	private Blutbild blutbild;

	public Krankheit(EKrankheit krankheit) {
		super();
		this.krankheit = krankheit;
	}

	public EKrankheit getKrankheit() {
		return krankheit;
	}

	public Ultraschall getUltraschall() {
		return ultraschall;
	}

	public Roentgen getRoentgen() {
		return roentgen;
	}

	public int getErscheinung() {
		return erscheinung;
	}

	public Blutbild getBlutbild() {
		return blutbild;
	}

}
