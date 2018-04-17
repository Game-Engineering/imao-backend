package de.hsmannheim.ss18.gae.imao.model;

public enum EKrankheit {
	MASERN(1), CHOLERA(2), BILHARZIOSE(3), HIV(4), HEP_A(5), HEP_B(6), TETANUS(7), GELBFIEBER(8), DENGUE_FIEBER(
			9), HAUTLEISHMANIASIS(10);

	private int id;

	EKrankheit(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
