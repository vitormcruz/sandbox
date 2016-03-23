package sandbox.payroll.interfaceAdapter.persistence.hibernate.repository

import com.querydsl.jpa.hibernate.HibernateQuery
import com.querydsl.jpa.hibernate.HibernateQueryFactory
import org.hibernate.SessionFactory
import org.springframework.transaction.support.TransactionTemplate
import sandbox.payroll.business.entity.Employee
import sandbox.payroll.business.entity.repository.EmployeeRepository
import sandbox.payroll.business.entity.repository.entityQuery.QEmployee

class HibernateEmployeeRepository implements EmployeeRepository{

    private SessionFactory sessionFactory = SessionFactory.smartNewFor(HibernateEmployeeRepository)
    private TransactionTemplate transactionTemplate = TransactionTemplate.smartNewFor(HibernateEmployeeRepository)
    private pending = []

    @Override
    Employee get(id) {
        transactionTemplate.execute {
            return sessionFactory.getCurrentSession().get(Employee, id)
        }
    }

    @Override
    public void update(Employee employee) {
        pending.add({
            sessionFactory.getCurrentSession().merge(employee)
        })
    }

    @Override
    Employee find(Closure closure) {
        def qEmployee = QEmployee.employee
        transactionTemplate.execute {
            HibernateQuery<Employee> employeeQuery = new HibernateQueryFactory(this.sessionFactory.getCurrentSession()).from(qEmployee)
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
    Iterator<Employee> iterator() {
        //TODO use pagination
        Collection<Employee> employees
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
    boolean add(Employee employee) {
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
    boolean addAll(Collection<? extends Employee> c) {
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
