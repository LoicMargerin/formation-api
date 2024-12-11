package org.enib.renew.business.impl;

import org.enib.renew.business.ISoldesBusiness;
import org.enib.renew.business.model.Solde;
import org.enib.renew.dao.IDevisesDAO;
import org.enib.renew.dao.ISoldesDAO;
import org.enib.renew.dto.DeviseDTO;
import org.enib.renew.dto.SoldeDTO;
import org.enib.renew.exceptions.BusinessException;
import org.enib.renew.exceptions.DAOException;
import org.enib.renew.mappers.DTOToAbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class SoldesBusinessImpl implements ISoldesBusiness {

    @Autowired
    protected ISoldesDAO soldesDAO;

    @Autowired
    protected IDevisesDAO devisesDAO;

    protected DTOToAbstractMapper mapper = DTOToAbstractMapper.INSTANCE;


    /**
     * Récupération d'un solde
     *
     * @param pPersonId la personne à requéter
     * @return le solde si trouvé, null sinon
     * @throws BusinessException en cas de crash
     */
    @Override
    public Solde getSolde(String pPersonId) throws BusinessException {
        try {

            final SoldeDTO fromDAOSolde = this.soldesDAO.getSolde(pPersonId);

            // Pas de solde trouvé => on retourne du null
            if (null == fromDAOSolde) {
                return null;
            }

            // Récupération du paramétrage devise associé au solde
            final DeviseDTO fromDAODevise = this.devisesDAO.getDeviseByCode(fromDAOSolde.getDeviseCode());

            // Mapping
            final Solde res = mapper.getFromSoldeDTO(fromDAOSolde);
            res.setDevise(mapper.getFromDeviseDTO(fromDAODevise));

            return res;
        } catch (final DAOException pEx) {
            throw new BusinessException("Erreur technique !", pEx);
        }
    }
}
