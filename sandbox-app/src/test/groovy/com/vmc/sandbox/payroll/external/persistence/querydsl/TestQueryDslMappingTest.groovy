package com.vmc.sandbox.payroll.external.persistence.querydsl

import com.mchange.v2.c3p0.ComboPooledDataSource
import com.querydsl.sql.Configuration
import com.querydsl.sql.HSQLDBTemplates
import com.querydsl.sql.SQLQueryFactory
import com.vmc.sandbox.payroll.external.persistence.querydsl.entity.EmployeeEntity
import com.vmc.sandbox.payroll.external.persistence.querydsl.queryEntity.QEmployee
import org.flywaydb.core.Flyway
import org.junit.Test

import javax.sql.DataSource

class TestQueryDslMappingTest {
    static private DataSource dataSource
    private static Configuration configuration

    static{
        dataSource = new ComboPooledDataSource()
        dataSource.setDriverClass("org.hsqldb.jdbcDriver")
        dataSource.setJdbcUrl("jdbc:hsqldb:mem:tsg")
        dataSource.setUser("sa")
        dataSource.setPassword("")
        Flyway flyway = new Flyway()
        flyway.setDataSource(TestQueryDslMappingTest.dataSource)
        flyway.setLocations("classpath:/databaseScripts/migrations")
        flyway.migrate()

        def templates = new HSQLDBTemplates()
        configuration = new Configuration(templates);
        configuration.

    }

    @Test
    def void "asasasas"(){
        SQLQueryFactory queryFactory = new SQLQueryFactory(configuration, dataSource);
        QEmployee employee = QEmployee.employee
        def entity = new EmployeeEntity()
        entity.setName("test")
        entity.setAddress("asas")
        entity.setEmail("asas")
        queryFactory.insert(employee).populate(entity).execute()
        List<EmployeeEntity> employees = queryFactory.select(employee).from(employee).where(employee.name.eq("test")).fetch()
        employees.each {println(it.name)}
    }
}
