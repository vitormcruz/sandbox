package com.vmc.sandbox.payroll.external.config.tools.migration

import com.mchange.v2.c3p0.ComboPooledDataSource
import org.flywaydb.core.Flyway


class PayrollDatabaseMigration {

    public void migratePayroll(ComboPooledDataSource dataSource) {
        Flyway flyway = new Flyway()
        flyway.setDataSource(dataSource)
        flyway.setLocations("classpath:/databaseScripts/migrations")
        flyway.migrate()
    }
}
