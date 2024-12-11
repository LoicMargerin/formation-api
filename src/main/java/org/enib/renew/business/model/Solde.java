package org.enib.renew.business.model;

public class Solde {
    private String personId;
    private Double solde;
    private Devise devise;

    public String getPersonId() {
        return personId;
    }

    public Solde setPersonId(String personId) {
        this.personId = personId;
        return this;
    }

    public Double getSolde() {
        return solde;
    }

    public Solde setSolde(Double solde) {
        this.solde = solde;
        return this;
    }

    public Devise getDevise() {
        return devise;
    }

    public Solde setDevise(Devise devise) {
        this.devise = devise;
        return this;
    }
}
