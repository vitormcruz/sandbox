package sandbox

import com.querydsl.jpa.codegen.HibernateDomainExporter
import org.hibernate.cfg.Configuration

class QueryClassGenerator {

    public static void main(String[] args) throws Exception {
        HibernateDomainExporter exporter = new HibernateDomainExporter(
                "Q",
                new File("target/generatedQueries"),
                SandboxApplication.addResources(new Configuration()));

        exporter.execute();
    }

}
