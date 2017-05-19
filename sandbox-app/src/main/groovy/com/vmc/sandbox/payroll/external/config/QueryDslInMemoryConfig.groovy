package com.vmc.sandbox.payroll.external.config

import com.mchange.v2.c3p0.ComboPooledDataSource
import org.flywaydb.core.Flyway

import javax.sql.DataSource

class QueryDslInMemoryConfig implements Config{

    @Override
    void configure() {

        DataSource dataSource = new ComboPooledDataSource()
        dataSource.setDriverClass("org.hsqldb.jdbcDriver")
        dataSource.setJdbcUrl("jdbc:hsqldb:mem:tsg")
        dataSource.setUser("sa")
        dataSource.setPassword("")
        Flyway flyway = new Flyway()
        flyway.setDataSource(dataSource)
        flyway.setLocations("classpath:/databaseScripts/migrations")
        flyway.migrate()

//        def templates = new H2Templates()
//        templates.
//        Configuration configuration = new Configuration(templates)
//        configuration.jd

    }

    @Override
    void tearDown() {

    }
}
