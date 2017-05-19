package com.vmc.sandbox.payroll.external.config.tools.codeGen

import com.mchange.v2.c3p0.ComboPooledDataSource
import com.querydsl.codegen.BeanSerializer
import com.querydsl.sql.codegen.MetaDataExporter
import com.vmc.sandbox.payroll.external.config.tools.migration.PayrollDatabaseMigration

import javax.sql.DataSource

class QueryDslClassGenerator {

    public static void main(String[] args) throws Exception {

        DataSource dataSource = new ComboPooledDataSource()
        dataSource.setDriverClass("org.hsqldb.jdbcDriver")
        dataSource.setJdbcUrl("jdbc:hsqldb:mem:tsg")
        dataSource.setUser("sa")
        dataSource.setPassword("")
        new PayrollDatabaseMigration().migratePayroll(dataSource)

        MetaDataExporter exporter = new MetaDataExporter()
        exporter.setBeanSerializerClass(BeanSerializer)
        exporter.setTargetFolder(new File("target/querydsl/"))
        exporter.setPackageName("payroll.external.persistence.querydsl.queryEntity");
        exporter.setBeanPackageName("payroll.external.persistence.querydsl.entity")
        exporter.setBeanSuffix("Entity")
        exporter.setSourceEncoding("UTF-8")
        def connection = dataSource.getConnection()
        try {
            exporter.export(connection.getMetaData())
            connection.close()
        } finally {
            try {
                connection.close()
            } catch (Exception e) {
                //Close Quietly. Apache Commons and groovy don't help me here
            }
        }
    }


}
