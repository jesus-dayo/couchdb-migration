package com.migration.couchdbmigration.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("couch")
public class CouchConfig {

    private String scheme;
    private String host;
    private String port;
    private String username;
    private String password;
    private String database;
    private CouchDBAPI api;

}
