package sandbox.payroll.external.interfaceAdapter.persistence.hibernate.repository

import com.querydsl.jpa.hibernate.HibernateQuery
import com.querydsl.jpa.hibernate.HibernateQueryFactory
import org.hibernate.SessionFactory
import org.springframework.transaction.support.TransactionTemplate
import sandbox.payroll.EmployeeRepository
import sandbox.payroll.external.interfaceAdapter.persistence.querydsl.entity.QEmployeeImp
import sandbox.payroll.imp.EmployeeImp

class HibernateEmployeeRepository implements EmployeeRepository{

    private SessionFactory sessionFactory = SessionFactory.smartNewFor(HibernateEmployeeRepository)
    private TransactionTemplate transactionTemplate = TransactionTemplate.smartNewFor(HibernateEmployeeRepository)
    private pending = []

    @Override
    EmployeeImp get(id) {
        transactionTemplate.execute {
            return sessionFactory.getCurrentSession().get(EmployeeImp, id)
        }
    }

    @Override
    public void update(EmployeeImp employee) {
        pending.add({
            sessionFactory.getCurrentSession().merge(employee)
        })
    }

    @Override
    EmployeeImp find(Closure closure) {
        def qEmployee = QEmployeeImp.employeeImp
        transactionTemplate.execute {
            HibernateQuery<EmployeeImp> employeeQuery = new HibernateQueryFactory(this.sessionFactory.getCurrentSession()).from(qEmployee)
            closure(employeeQuery, qEmployee)
            return employeeQuery.fetchOne()
        }
    }

    public void executeAllPending(){
        pending.each {it()}
        pending.clear()
    }

    @Override
    int size() {
        return 0
    }

    @Override
    boolean isEmpty() {
        return false
    }

    @Override
    boolean contains(Object o) {
        return false
    }

    @Override
    Iterator<EmployeeImp> iterator() {
        //TODO use pagination
        Collection<EmployeeImp> employees
        transactionTemplate.execute{
            employees = new HibernateQueryFactory(this.sessionFactory.getCurrentSession()).selectFrom(QEmployee.employee).fetch()
        }

        return employees.iterator()
    }

    @Override
    Object[] toArray() {
        return new Object[0]
    }

    @Override
    def <T> T[] toArray(T[] a) {
        return null
    }

    @Override
    boolean add(EmployeeImp employee) {
        pending.add({
            sessionFactory.getCurrentSession().persist(employee)
        })
        return false
    }

    @Override
    boolean remove(employee) {
        transactionTemplate.execute {
            sessionFactory.getCurrentSession().delete(employee)
            return true
        }
    }

    @Override
    boolean containsAll(Collection<?> c) {
        return false
    }

    @Override
    boolean addAll(Collection<? extends EmployeeImp> c) {
        return false
    }

    @Override
    boolean removeAll(Collection<?> c) {
        return false
    }

    @Override
    boolean retainAll(Collection<?> c) {
        return false
    }

    @Override
    void clear() {

    }
}
