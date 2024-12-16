package org.enib.renew.dao.db.impl;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.enib.renew.dao.IDevisesDAO;
import org.enib.renew.dao.db.AbstractDBDAO;
import org.enib.renew.dto.DeviseDTO;
import org.enib.renew.exceptions.DAOException;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

@Repository
@Primary
public class DevisesDAODbImpl extends AbstractDBDAO implements IDevisesDAO {

    private static String REQUETE_GET_ALL_DEVISES = " SELECT code, libelle FROM devises";
    private static String REQUETE_GET_DEVISE = " SELECT code, libelle FROM devises WHERE code = :code";

    protected DevisesDAODbImpl(HikariDataSource dpDataSource) {
        super(dpDataSource);
    }

    private final RowMapper<DeviseDTO> rowMapperDeviseDTO = (rs, rowNum) -> {
        final DeviseDTO fromDB = new DeviseDTO();
        fromDB.setCode(StringUtils.trim(rs.getString("code")));
        fromDB.setLibelle(StringUtils.truncate(StringUtils.trim(rs.getString("libelle")),5));

        return fromDB;
    };

    /**
     * Récupérer une devise par son code
     *
     * @param pCodeDevise le code à requéter
     * @return la devise si trouvée, null sinon
     * @throws DAOException en cas de crash
     */
    @Override
    public DeviseDTO getDeviseByCode(String pCodeDevise) throws DAOException {
        final var parameters = new MapSqlParameterSource()
                .addValue("code", pCodeDevise);

        try {
            final List<DeviseDTO> fromDb = super.getTemplate().query(REQUETE_GET_DEVISE, parameters, rowMapperDeviseDTO);
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
    public List<DeviseDTO> getDevises() throws DAOException {
        try {
            final var parameters = new MapSqlParameterSource();

            return super.getTemplate().query(REQUETE_GET_ALL_DEVISES, parameters, rowMapperDeviseDTO);
        } catch (final DataAccessException pEx ) {
            throw new DAOException(pEx);
        }
    }
}
