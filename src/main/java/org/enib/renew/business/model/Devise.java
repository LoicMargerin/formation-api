package org.enib.renew.business.model;

public class Devise {
    private String code;
    private String libelle;

    public String getCode() {
        return code;
    }

    public Devise setCode(String code) {
        this.code = code;
        return this;
    }

    public String getLibelle() {
        return libelle;
    }

    public Devise setLibelle(String libelle) {
        this.libelle = libelle;
        return this;
    }
}
