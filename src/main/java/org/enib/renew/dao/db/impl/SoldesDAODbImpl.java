package org.enib.renew.dao.db.impl;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.enib.renew.dao.ISoldesDAO;
import org.enib.renew.dao.db.AbstractDBDAO;
import org.enib.renew.dto.DeviseDTO;
import org.enib.renew.dto.SoldeDTO;
import org.enib.renew.exceptions.DAOException;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class SoldesDAODbImpl extends AbstractDBDAO implements ISoldesDAO {

    private final static String REQUETE_GET_ALL_PERSONNES = " SELECT id, montant, devise FROM soldes";
    private final static String REQUETE_GET_SOLDE = " SELECT id, montant, devise FROM soldes WHERE id = :id";

    protected SoldesDAODbImpl(HikariDataSource dpDataSource) {
        super(dpDataSource);
    }

    private final RowMapper<SoldeDTO> rowSoldeDeviseDTO = (rs, rowNum) -> {
        final SoldeDTO fromDB = new SoldeDTO();
        fromDB.setPersonId(StringUtils.trim(rs.getString("id")));
        fromDB.setSolde(rs.getDouble("montant"));
        fromDB.setDeviseCode(StringUtils.trim(rs.getString("devise")));

        return fromDB;
    };

    /**
     * Récupérer une devise par son code
     *
     * @param pPersonId le code à requéter
     * @return la devise si trouvée, null sinon
     * @throws DAOException en cas de crash
     */
    @Override
    public SoldeDTO getSolde(String pPersonId) throws DAOException {
        final var parameters = new MapSqlParameterSource()
                .addValue("id", pPersonId);

        try {
            final List<SoldeDTO> fromDb = super.getTemplate().query(REQUETE_GET_SOLDE, parameters, rowSoldeDeviseDTO);
            if (CollectionUtils.isNotEmpty(fromDb)) {

                return fromDb.getFirst();
            } else {
                return null;
            }

        } catch (final DataAccessException pEx ) {
            throw new DAOException(pEx);
        }
    }

    /**
     * Récupérer la liste des devises
     *
     * @return la liste des devises
     */
    @Override
    public List<String> getPersonIds() throws DAOException {
        try {
            final var parameters = new MapSqlParameterSource();

            return CollectionUtils.emptyIfNull(super.getTemplate().query(REQUETE_GET_ALL_PERSONNES, parameters, rowSoldeDeviseDTO)).stream().map(SoldeDTO::getPersonId).toList();
        } catch (final DataAccessException pEx ) {
            throw new DAOException(pEx);
        }
    }
}
