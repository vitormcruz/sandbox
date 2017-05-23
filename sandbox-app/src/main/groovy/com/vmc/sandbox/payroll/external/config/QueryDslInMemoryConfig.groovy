package com.vmc.sandbox.payroll.external.config

import com.mchange.v2.c3p0.ComboPooledDataSource
import com.querydsl.sql.Configuration
import com.querydsl.sql.HSQLDBTemplates
import com.querydsl.sql.SQLQueryFactory
import com.vmc.sandbox.payroll.external.persistence.querydsl.queryEntity.QEmployee
import org.flywaydb.core.Flyway

import javax.sql.DataSource

class QueryDslInMemoryConfig implements Config{
    private static DataSource dataSource
    private static Configuration configuration

    @Override
    void configure() {

        dataSource = new ComboPooledDataSource()
        dataSource.setDriverClass("org.hsqldb.jdbcDriver")
        dataSource.setJdbcUrl("jdbc:hsqldb:mem:tsg")
        dataSource.setUser("sa")
        dataSource.setPassword("")
        Flyway flyway = new Flyway()
        flyway.setDataSource(dataSource)
        flyway.setLocations("classpath:/databaseScripts/migrations")
        flyway.migrate()

        def templates = new HSQLDBTemplates()
        configuration = new Configuration(templates);
    }

    @Override
    void tearDown() {
        SQLQueryFactory queryFactory = new SQLQueryFactory(configuration, dataSource);
        QEmployee employee = QEmployee.employee
        queryFactory.w

    }
}
