package sandbox.payroll.external.config.querydsl

import com.querydsl.jpa.codegen.HibernateDomainExporter
import org.hibernate.cfg.Configuration
import sandbox.payroll.external.config.persistence.hibernate.HibernateResources

class QueryClassGenerator {

    public static void main(String[] args) throws Exception {
        HibernateDomainExporter exporter = new HibernateDomainExporter(
                "Q",
                new File("target/generatedQueries"),
                {new Configuration().addResource(HibernateResources.listOfMappings())}())

        exporter.execute();
    }

}
