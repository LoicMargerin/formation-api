package org.enib.renew.business;

import org.enib.renew.business.model.Devise;
import org.enib.renew.business.model.Solde;
import org.enib.renew.exceptions.BusinessException;

import java.util.List;

public interface IDevisesBusiness {

    /**
     * Récupération d'une devise
     * @param pCode la devise à requéter
     * @return le solde si trouvé, null sinon
     * @throws BusinessException en cas de crash
     */
    Devise getDevise(final String pCode) throws BusinessException;

    /**
     * Récupération de toutes les devises existantes
     * @return la liste si trouvée, null sinon
     * @throws BusinessException en cas de crash
     */
    List<Devise> getDevises() throws BusinessException;

}
