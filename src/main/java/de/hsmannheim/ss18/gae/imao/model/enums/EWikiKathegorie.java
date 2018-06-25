package de.hsmannheim.ss18.gae.imao.model.enums;

public enum EWikiKathegorie {
    KRANKHEITEN(0),SPIELINTERNES(1), SONSTIGES(10);

    private int id;

    EWikiKathegorie(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
