package de.hsmannheim.ss18.gae.imao.model;

public enum EWikiKathegorie {
    KRANKHEITEN(0), SONSTIGES(10);

    private int id;

    EWikiKathegorie(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
