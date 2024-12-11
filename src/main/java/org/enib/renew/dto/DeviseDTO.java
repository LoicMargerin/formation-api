package org.enib.renew.dto;

public class DeviseDTO {
    private String code;
    private String libelle;

    public String getCode() {
        return code;
    }

    public DeviseDTO setCode(String code) {
        this.code = code;
        return this;
    }

    public String getLibelle() {
        return libelle;
    }

    public DeviseDTO setLibelle(String libelle) {
        this.libelle = libelle;
        return this;
    }
}
