package de.hsmannheim.ss18.gae.imao.model.enums;

public enum EWikiKathegorie {
    KRANKHEITEN(0),SPIELINTERNES(1), AUSZEICHNUNGEN(2);

    private int id;

    EWikiKathegorie(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
