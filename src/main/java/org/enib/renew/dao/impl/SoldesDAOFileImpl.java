package org.enib.renew.dao.impl;

import org.enib.renew.dao.ISoldesDAO;
import org.enib.renew.dto.SoldeDTO;
import org.enib.renew.exceptions.DAOException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class SoldesDAOFileImpl implements ISoldesDAO {
    /**
     * Récupération d'un solde
     *
     * @param pPersonId la personne à requéter
     * @return le solde si trouvé, null sinon
     * @throws DAOException en cas de crash
     */
    @Override
    public SoldeDTO getSolde(final String pPersonId) throws DAOException {
        return new SoldeDTO().setSolde(123.25).setPersonId(pPersonId).setDeviseCode("Eur");
    }
}
