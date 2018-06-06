package de.hsmannheim.ss18.gae.imao.model.enums;

/**
 * Enum für Aufgaben, die der Manager erhalten kann.
 * 
 *
 */
public enum EAufgaben {
	INTERVIEW("Geben Sie ein Interview"), PRESSEKONFERENZ_VIELETOTE(
			"Halten Sie eine Pressekonferenz zu den Todesfällen"), PRESSEKONFERENZ_GUTEARBEIT(
					"Halten Sie eine Pressekonferenz zur guten Arbeit"), PRESSEKONFERENZ_DUERRE(
							"Halten Sie eine Pressekonferenz zu Dürreperiode"), GREAET_KAUFEN(
									"Kaufen Sie ein Gerät für den Arzt"), SPONSOR_ANWERBEN(
											"Werben Sie einen neuen Sponsor an"), ARZT_ABMAHNEN(
													"Senden Sie eine Abmahmung an den Arzt");
	private String aufgabeText;

	private EAufgaben(String aufgabeText) {
		this.aufgabeText = aufgabeText;
	}

	public String getAufgabeText() {
		return aufgabeText;
	}

}
