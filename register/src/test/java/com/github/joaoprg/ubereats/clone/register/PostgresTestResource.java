package com.github.joaoprg.ubereats.clone.register;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.Map;

public class PostgresTestResource implements QuarkusTestResourceLifecycleManager {

    public static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:13.11");

    @Override
    public Map<String, String> start() {
        POSTGRES.start();
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("quarkus.datasource.jdbc.url", POSTGRES.getJdbcUrl());
        properties.put("quarkus.datasource.username", POSTGRES.getUsername());
        properties.put("quarkus.datasource.password", POSTGRES.getPassword());
        return properties;
    }

    @Override
    public void stop() {
        if (POSTGRES.isRunning()) {
            POSTGRES.stop();
        }
    }
}