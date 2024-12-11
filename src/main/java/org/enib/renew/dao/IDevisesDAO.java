package org.enib.renew.dao;

import org.enib.renew.dto.DeviseDTO;
import org.enib.renew.exceptions.DAOException;

import java.util.List;

public interface IDevisesDAO {
    /**
     * Récupérer une devise par son code
     * @param pCodeDevise le code à requéter
     * @return la devise si trouvée, null sinon
     * @throws DAOException en cas de crash
     */
    DeviseDTO getDeviseByCode(final String pCodeDevise) throws DAOException;

    /**
     * Récupérer la liste des devises
     * @return la liste des devises
     */
    List<DeviseDTO> getDevises() throws DAOException;
}
