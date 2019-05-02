package ru.aaxee.homework2.config;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.dialect.H2Dialect;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.function.DefaultReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.function.ReactiveDataAccessStrategy;

@Configuration
public class R2dbcConfig {

    @Bean
    DatabaseClient databaseClient() {

        ConnectionFactory connectionFactory = new H2ConnectionFactory(H2ConnectionConfiguration.builder()
                .inMemory("testdb")
                .username("sa")
                .build());

        return DatabaseClient.builder()
                .connectionFactory(connectionFactory)
                .build();
    }

    @Bean
    ReactiveDataAccessStrategy reactiveDataAccessStrategy() {
        H2Dialect h2Dialect = new H2Dialect();
        return new DefaultReactiveDataAccessStrategy(h2Dialect);
    }

}
