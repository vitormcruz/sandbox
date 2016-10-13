package sandbox.payroll.external.config

import com.querydsl.jpa.hibernate.HibernateQueryFactory
import org.hibernate.SessionFactory
import org.springframework.transaction.support.TransactionTemplate
import sandbox.payroll.IntegrationTestBase
import sandbox.payroll.external.interfaceAdapter.persistence.querydsl.entity.QEmployee

class DatabaseCleaner {

    private SessionFactory sessionFactory = SessionFactory.smartNewFor(IntegrationTestBase)
    private TransactionTemplate transactionTemplate = TransactionTemplate.smartNewFor(IntegrationTestBase)
    private def entitiesToDelete = [QEmployee.employee]


    public void cleanDatabase(){
        transactionTemplate.execute{
            def queryFactory = new HibernateQueryFactory(this.sessionFactory.getCurrentSession())
            entitiesToDelete.forEach {queryFactory.delete(it).execute()}
        }
    }


}
