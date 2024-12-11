package org.enib.renew.exceptions;

public class BusinessException extends Exception {
    public String getSpecificMessage() {
        return specificMessage;
    }

    private final String specificMessage;


    /**
     * Encapsulation d'une erreur DAO
     * @param pSpecificMessage le message spécifique
     * @param pEx l'exception encapsulée
     */
    public BusinessException(final String pSpecificMessage, final DAOException pEx) {
        super(pEx);

        this.specificMessage = pSpecificMessage;
    }

    /**
     * Encapsulation d'une erreur DAO
     * @param pSpecificMessage le message spécifique
     */
    public BusinessException(final String pSpecificMessage) {

        this.specificMessage = pSpecificMessage;
    }

}
