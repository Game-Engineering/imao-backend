package de.hsmannheim.ss18.gae.imao.model;

public enum EAufgaben {
	INTERVIEW("Geben Sie ein Interview"), PRESSEKONFERENZ("Halten Sie eine Pressekonferenz"), GREAET_KAUFEN(
			"Kaufen Sie ein Gerät für den Arzt"), SPONSOR_ANWERBEN(
					"Werben Sie einen neuen Sponsor an"), ARZT_ABMAHNEN("Senden Sie eine Abmahmung an den Arzt");
	private String aufgabeText;

	private EAufgaben(String aufgabeText) {
		this.aufgabeText = aufgabeText;
	}

	public String getAufgabeText() {
		return aufgabeText;
	}

}
