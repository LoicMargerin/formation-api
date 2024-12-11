package org.enib.renew.business;

import org.enib.renew.business.model.Solde;
import org.enib.renew.exceptions.BusinessException;
import org.enib.renew.exceptions.DAOException;

public interface ISoldesBusiness {

    /**
     * Récupération d'un solde
     * @param pPersonId la personne à requéter
     * @return le solde si trouvé, null sinon
     * @throws BusinessException en cas de crash
     */
    Solde getSolde(final String pPersonId) throws BusinessException;

}
