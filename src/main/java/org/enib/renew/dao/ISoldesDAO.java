package org.enib.renew.dao;

import org.enib.renew.dto.SoldeDTO;
import org.enib.renew.exceptions.DAOException;

public interface ISoldesDAO {
    /**
     * Récupération d'un solde
     * @param pPersonId la personne à requéter
     * @return le solde si trouvé, null sinon
     * @throws DAOException en cas de crash
     */
    SoldeDTO getSolde(final String pPersonId) throws DAOException;
}
