package org.enib.renew.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.enib.renew.exceptions.DAOException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "datasource.db")
@Configuration
public class DatasourceConfiguration {
    protected String driverClassName;
    protected String url;
    protected String username;
    protected String password;
    protected int maximumPoolSize;
    protected String connectionTestQuery;
    protected int idleTimeout;
    protected int leakDetectionThreshold;


    @Bean(name = "DataBaseDatasource")
    public HikariDataSource getDataSources() throws DAOException {
        final var hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(maximumPoolSize);
        hikariConfig.setConnectionTestQuery(connectionTestQuery);
        hikariConfig.setConnectionTimeout(idleTimeout);
        hikariConfig.setIdleTimeout(idleTimeout);
        hikariConfig.setLeakDetectionThreshold(leakDetectionThreshold);

        return new HikariDataSource(hikariConfig);
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public DatasourceConfiguration setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public DatasourceConfiguration setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public DatasourceConfiguration setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DatasourceConfiguration setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public DatasourceConfiguration setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
        return this;
    }

    public String getConnectionTestQuery() {
        return connectionTestQuery;
    }

    public DatasourceConfiguration setConnectionTestQuery(String connectionTestQuery) {
        this.connectionTestQuery = connectionTestQuery;
        return this;
    }

    public int getIdleTimeout() {
        return idleTimeout;
    }

    public DatasourceConfiguration setIdleTimeout(int idleTimeout) {
        this.idleTimeout = idleTimeout;
        return this;
    }

    public int getLeakDetectionThreshold() {
        return leakDetectionThreshold;
    }

    public DatasourceConfiguration setLeakDetectionThreshold(int leakDetectionThreshold) {
        this.leakDetectionThreshold = leakDetectionThreshold;
        return this;
    }
}
