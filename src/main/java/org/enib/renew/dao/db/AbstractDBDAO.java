package org.enib.renew.dao.db;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Superclasse des DAO PostgreSQL
 */
public class AbstractDBDAO {
    protected final HikariDataSource dataSource;
    protected final NamedParameterJdbcTemplate template;

    protected AbstractDBDAO(@Qualifier("DataBaseDatasource") HikariDataSource dpDataSource) {
        this.dataSource = dpDataSource;
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    protected NamedParameterJdbcTemplate getTemplate() {
        return this.template;
    }
}
