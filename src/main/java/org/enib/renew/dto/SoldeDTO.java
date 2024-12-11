package org.enib.renew.dto;

public class SoldeDTO {
    private String personId;
    private Double solde;
    private String deviseCode;

    public String getPersonId() {
        return personId;
    }

    public SoldeDTO setPersonId(String personId) {
        this.personId = personId;
        return this;
    }

    public Double getSolde() {
        return solde;
    }

    public SoldeDTO setSolde(Double solde) {
        this.solde = solde;
        return this;
    }

    public String getDeviseCode() {
        return deviseCode;
    }

    public SoldeDTO setDeviseCode(String deviseCode) {
        this.deviseCode = deviseCode;
        return this;
    }
}
