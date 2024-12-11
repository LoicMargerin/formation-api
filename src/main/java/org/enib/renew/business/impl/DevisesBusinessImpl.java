package org.enib.renew.business.impl;

import org.enib.renew.business.IDevisesBusiness;
import org.enib.renew.business.model.Devise;
import org.enib.renew.business.model.Solde;
import org.enib.renew.dao.IDevisesDAO;
import org.enib.renew.dto.DeviseDTO;
import org.enib.renew.exceptions.BusinessException;
import org.enib.renew.exceptions.DAOException;
import org.enib.renew.utils.mappers.DTOToAbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class DevisesBusinessImpl implements IDevisesBusiness {
    protected DTOToAbstractMapper mapper = DTOToAbstractMapper.INSTANCE;

    @Autowired
    protected IDevisesDAO devisesDAO;

    /**
     * Récupération d'une devise
     *
     * @param pCode la devise à requéter
     * @return le solde si trouvé, null sinon
     * @throws BusinessException en cas de crash
     */
    @Override
    public Devise getDevise(String pCode) throws BusinessException {
        try {
            // Récupération du paramétrage devise associé au solde
            final DeviseDTO fromDAODevise = this.devisesDAO.getDeviseByCode(pCode);

            // Mapping
            return(mapper.getFromDeviseDTO(fromDAODevise));
        } catch (final DAOException pEx) {
            throw new BusinessException("Erreur technique sur la récupération d'une devise !", pEx);
        }

    }

    /**
     * Récupération de toutes les devises existantes
     *
     * @return la liste si trouvée, null sinon
     * @throws BusinessException en cas de crash
     */
    @Override
    public List<Devise> getDevises() throws BusinessException {
        try {
            // Récupération du paramétrage devise associé au solde
            final List<DeviseDTO> fromDAODevise = this.devisesDAO.getDevises();

            // Mapping
            return(mapper.getFromDevisesListDTO(fromDAODevise));
        } catch (final DAOException pEx) {
            throw new BusinessException("Erreur technique sur la récupération d'une devise !", pEx);
        }
    }
}
