package de.hsmannheim.ss18.gae.imao.model.enums;

/**
 * Enum für Aufgaben, die der Manager erhalten kann.
 * 
 *
 */
public enum EAufgaben {
	INTERVIEW("Sehr geehrter IMAO Vorstand,\n"
			+ "um das Ansehen und die Bekanntheit der Organisation zu steigern ist es essentiell wichtig, dass ein Interview geführt wird. "
			+ "In diesem Interview sollen vor allem die Stärken der Organisation betont werden und bei Problemen immer auf eine schnelle Lösung gedeutet werden."), PRESSEKONFERENZ_VIELETOTE(
					"Sehr geehrter Vorstand von IMAO,\n"
							+ "aufgrund der vielen Todesfälle im Camp muss eine Reaktion Ihrerseits erfolgen. "
							+ "Damit diese Reaktion einen möglichst großen Kreis zieht halten wir somit eine Pressekonferenz für unumgänglich."), PRESSEKONFERENZ_GUTEARBEIT(
									"Sehr geehrter Vorstand von IMAO,\n"
											+ "aufgrund der besonders guten Arbeit der Arztes im Camp muss eine Reaktion Ihrerseits erfolgen. "
											+ "Damit diese Reaktion einen möglichst großen Kreis zieht halten wir somit eine Pressekonferenz für unumgänglich."), PRESSEKONFERENZ_DUERRE(
													"Sehr geehrter Vorstand von IMAO,\n"
															+ "aufgrund der Dürreperiode im Camp muss eine Reaktion Ihrerseits erfolgen. "
															+ "Damit diese Reaktion einen möglichst großen Kreis zieht halten wir somit eine Pressekonferenz für unumgänglich.")
	// , GREAET_KAUFEN("Kaufen Sie ein Gerät für den Arzt")
	, SPONSOR_ANWERBEN("Sehr geehrter IMAO Vorstand,\n" + "Für den Unterhalt und die Gehälter der IMAO Mitarbeiter benötigen wir finanzielle Mittel. " + "Sorgen sie dafür, dass ein Sponsor sich verpflichtend unsere Organisation finanziell zu unterstützen."), ARZT_ABMAHNEN("Sehr geehrter IMAO Vorstand,\n" + "aufgrund mangelnder Erfolgsquote bei den Behandlungen der Patienten im Camp ist es an der Zeit den Arzt abzumahnen. " + "Bei den Fehlbehandlungen scheint es sich ausschließlch um Arztfehler zu handeln und dies wirft ein schlechtes Licht auf die gesamte Organisation.");
	private String aufgabeText;

	private EAufgaben(String aufgabeText) {
		this.aufgabeText = aufgabeText;
	}

	public String getAufgabeText() {
		return aufgabeText;
	}

}
