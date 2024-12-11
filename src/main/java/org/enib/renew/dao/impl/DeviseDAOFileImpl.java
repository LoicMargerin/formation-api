package org.enib.renew.dao.impl;

import org.enib.renew.dao.IDevisesDAO;
import org.enib.renew.dto.DeviseDTO;
import org.enib.renew.exceptions.DAOException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class DeviseDAOFileImpl implements IDevisesDAO {

    /**
     * Récupérer une devise par son code
     *
     * @param pCodeDevise le code à requéter
     * @return la devise si trouvée, null sinon
     * @throws DAOException en cas de crash
     */
    @Override
    public DeviseDTO getDeviseByCode(String pCodeDevise) throws DAOException {
        return new DeviseDTO().setCode("EUR").setLibelle("Euro");
    }
}
