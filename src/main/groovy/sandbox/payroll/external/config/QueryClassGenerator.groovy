package sandbox.payroll.external.config

import com.querydsl.jpa.codegen.HibernateDomainExporter
import org.hibernate.cfg.Configuration

/**
 * This class is just a helper with a main that creates all the equivalent Q classes from hibernate xml mapping, by
 * the time this comment was written QueryDSL just creates Q classes automatically from annotations.
 */
class QueryClassGenerator {

    public static void main(String[] args) throws Exception {
        HibernateDomainExporter exporter = new HibernateDomainExporter(
                "Q",
                new File("target/generatedQueries"),
                {new Configuration().addResource(HibernateResources.listOfMappings())}())

        exporter.execute();
    }

}
