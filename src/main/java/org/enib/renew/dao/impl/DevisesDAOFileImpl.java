package org.enib.renew.dao.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.enib.renew.dao.IDevisesDAO;
import org.enib.renew.dto.DeviseDTO;
import org.enib.renew.exceptions.DAOException;
import org.enib.renew.utils.csv.CSVParser;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Repository
@Primary
public class DevisesDAOFileImpl extends CSVParser<DeviseDTO> implements IDevisesDAO{
    private final Map<String,DeviseDTO> data = new HashMap<>();


    /**
     * Préchargement du fichier de données
     * @param resourceLoader le resource loader donné par Spring
     * @throws DAOException si crash
     */
    protected DevisesDAOFileImpl(ResourceLoader resourceLoader) throws DAOException {
        final List<DeviseDTO> fromFile = super.parseFile(resourceLoader,"csv/devises.csv", (pValues)->{
            if (null != pValues && pValues.length ==2) {
                return new DeviseDTO().setCode(pValues[0]).setLibelle(pValues[1]);
            } else {
                throw new DAOException("Ligne impossible à parser dans le fichier devise !");
            }
        });

        for (final DeviseDTO elt : CollectionUtils.emptyIfNull(fromFile)) {
            data.put(elt.getCode(),elt);
        }
    }

    /**
     * Récupérer une devise par son code
     *
     * @param pCodeDevise le code à requéter
     * @return la devise si trouvée, null sinon
     * @throws DAOException en cas de crash
     */
    @Override
    public DeviseDTO getDeviseByCode(String pCodeDevise) throws DAOException {
        return this.data.get(pCodeDevise);
    }

    /**
     * Récupérer la liste des devises
     *
     * @return la liste des devises
     */
    @Override
    public List<DeviseDTO> getDevises() throws DAOException {
        return MapUtils.emptyIfNull(this.data).values().stream().toList();
    }
}
