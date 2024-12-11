package org.enib.renew.dao.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.enib.renew.dao.ISoldesDAO;
import org.enib.renew.dto.DeviseDTO;
import org.enib.renew.dto.SoldeDTO;
import org.enib.renew.exceptions.DAOException;
import org.enib.renew.utils.csv.CSVParser;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Primary
public class SoldesDAOFileImpl extends CSVParser<SoldeDTO> implements ISoldesDAO {
    private final Map<String,SoldeDTO> data = new HashMap<>();


    /**
     *
     * Préchargement du fichier de données
     * @param resourceLoader le resource loader donné par Spring
     * @throws DAOException si crash
     */
    protected SoldesDAOFileImpl(ResourceLoader resourceLoader) throws DAOException {
        final String errorMessage = "Ligne impossible à parser dans le fichier soldes !";

        try {
            final List<SoldeDTO> fromFile = super.parseFile(resourceLoader, "csv/soldes.csv", (pValues) -> {
                if (null != pValues && pValues.length == 3) {
                    return new SoldeDTO().setPersonId(pValues[0]).setSolde(Double.parseDouble(pValues[1])).setDeviseCode(pValues[2]);
                } else {
                    throw new DAOException(errorMessage);
                }
            });

            for (final SoldeDTO elt : CollectionUtils.emptyIfNull(fromFile)) {
                data.put(elt.getPersonId(),elt);
            }
        } catch (final NumberFormatException pEx) {
            throw new DAOException(errorMessage);
        }
    }


    /**
     * Récupération d'un solde
     *
     * @param pPersonId la personne à requéter
     * @return le solde si trouvé, null sinon
     * @throws DAOException en cas de crash
     */
    @Override
    public SoldeDTO getSolde(final String pPersonId) throws DAOException {
        return this.data.get(pPersonId);
    }

    /**
     * Récupération de tous les ID de personnes existants
     *
     * @return la liste si trouvée, null sinon
     * @throws DAOException en cas de crash
     */
    @Override
    public List<String> getPersonIds() throws DAOException {
        return MapUtils.emptyIfNull(this.data).keySet().stream().toList();
    }
}
