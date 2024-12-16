package org.enib.renew.dao.db;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Superclasse des DAO PostgreSQL
 */
public class AbstractDBDAO {
    protected final HikariDataSource dataSource;

    protected AbstractDBDAO(@Qualifier("DataBaseDatasource") HikariDataSource dpDataSource) {
        this.dataSource = dpDataSource;
    }
}
