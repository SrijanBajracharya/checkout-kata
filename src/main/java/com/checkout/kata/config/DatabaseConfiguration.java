package com.checkout.kata.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.Location;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager",
        basePackages = {"com.checkout"}
)
public class DatabaseConfiguration {

    @Primary
    @Bean
    @ConditionalOnProperty(name="spring.datasource.primary.jdbc-url")
    @ConfigurationProperties(ConfigurationKey.PRIMARY_DATASOURCE)
    public HikariDataSource primaryDatasource(){
        HikariDataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
        dataSource.setMinimumIdle(15);
        dataSource.setMaximumPoolSize(15);
        return dataSource;
    }

    @Bean
    public Flyway flywayPrimary(@Qualifier("primaryDatasource") DataSource dataSource) {
        ClassicConfiguration configuration = getConfiguration(dataSource, List.of("classpath:/config/flyway/db/migration"));
        var flyway = new Flyway(configuration);
        flyway.migrate();
        return flyway;
    }

    private ClassicConfiguration getConfiguration(DataSource dataSource, List<String> locations) {
        var configuration = new ClassicConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setLocations(locations.stream().map(Location::new).toArray(Location[]::new));
        configuration.setOutOfOrder(true);
        return configuration;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("primaryDatasource") DataSource primaryDataSource) {
        return builder.dataSource(primaryDataSource).packages("").build();
    }

    @Primary
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryEntityManagerFactory") EntityManagerFactory primaryEntityManagerFactory) {
        return new JpaTransactionManager(primaryEntityManagerFactory);
    }


}
