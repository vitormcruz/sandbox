package sandbox.payroll.external.config

import org.hibernate.FlushMode
import org.hibernate.HibernateException
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.dao.DataAccessResourceFailureException
import org.springframework.orm.hibernate4.SessionFactoryUtils
import org.springframework.orm.hibernate4.SessionHolder
import org.springframework.transaction.support.TransactionSynchronizationManager

class SpringOpenSessionInTest implements Config{

    SessionFactory sessionFactory = SessionFactory.smartNewFor(SpringOpenSessionInTest);

    @Override
    void configure() {
        Session session = openSession(sessionFactory);
        SessionHolder sessionHolder = new SessionHolder(session);
        TransactionSynchronizationManager.bindResource(sessionFactory, sessionHolder);
    }

    /**
     * Open a Session for the SessionFactory that this filter uses.
     * <p>The default implementation delegates to the {@link SessionFactory#openSession}
     * method and sets the {@link Session}'s flush mode to "MANUAL".
     * @param sessionFactory the SessionFactory that this filter uses
     * @return the Session to use
     * @throws org.springframework.dao.DataAccessResourceFailureException if the Session could not be created
     * @see org.hibernate.FlushMode#MANUAL
     */
    protected Session openSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
        try {
            Session session = sessionFactory.openSession();
            session.setFlushMode(FlushMode.MANUAL);
            return session;
        }
        catch (HibernateException ex) {
            throw new DataAccessResourceFailureException("Could not open Hibernate Session", ex);
        }
    }

    @Override
    void tearDown() {
        SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);
        SessionFactoryUtils.closeSession(sessionHolder.getSession());
    }
}
