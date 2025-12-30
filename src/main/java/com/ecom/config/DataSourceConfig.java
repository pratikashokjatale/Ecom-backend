package com.ecom.config;

import java.net.URI;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    private Environment env;

    @Bean
    @ConditionalOnMissingBean(DataSource.class)
    public DataSource dataSource() {
        String springUrl = env.getProperty("spring.datasource.url");
        if (springUrl != null && !springUrl.isBlank()) {
            log.debug("spring.datasource.url is set, leaving auto-configuration to Spring Boot");
            return null; // allow default autoconfiguration to proceed
        }

        String databaseUrl = env.getProperty("DATABASE_URL");
        if (databaseUrl == null || databaseUrl.isBlank()) {
            log.warn("No spring.datasource.url or DATABASE_URL found; no manual DataSource created");
            return null;
        }

        try {
            // Example DATABASE_URL: mysql://user:pass@host:port/dbname?ssl-mode=REQUIRED
            URI uri = new URI(databaseUrl);

            String userInfo = uri.getUserInfo();
            String username = null;
            String password = null;
            if (userInfo != null) {
                String[] parts = userInfo.split(":", 2);
                username = parts[0];
                if (parts.length > 1) {
                    password = parts[1];
                }
            }

            String host = uri.getHost();
            int port = uri.getPort();
            String path = uri.getPath();
            String db = (path != null && path.length() > 1) ? path.substring(1) : "";
            String query = uri.getQuery();

            StringBuilder jdbc = new StringBuilder();
            jdbc.append("jdbc:mysql://").append(host);
            if (port != -1) {
                jdbc.append(":").append(port);
            }
            if (!db.isBlank()) {
                jdbc.append("/").append(db);
            }
            if (query != null && !query.isBlank()) {
                jdbc.append("?").append(query);
            }

            String jdbcUrl = jdbc.toString();
            log.info("Mapping DATABASE_URL to JDBC URL: {}", jdbcUrl);

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(jdbcUrl);
            if (username != null) {
                config.setUsername(username);
            }
            if (password != null) {
                config.setPassword(password);
            }

            // allow optional override via spring properties
            String driver = env.getProperty("spring.datasource.driver-class-name");
            if (driver != null) {
                config.setDriverClassName(driver);
            }

            String maxPool = env.getProperty("spring.datasource.hikari.maximum-pool-size");
            if (maxPool != null) {
                config.setMaximumPoolSize(Integer.parseInt(maxPool));
            }

            return new HikariDataSource(config);
        } catch (Exception e) {
            log.error("Failed to configure DataSource from DATABASE_URL", e);
            throw new IllegalStateException("Unable to configure DataSource", e);
        }
    }
}
