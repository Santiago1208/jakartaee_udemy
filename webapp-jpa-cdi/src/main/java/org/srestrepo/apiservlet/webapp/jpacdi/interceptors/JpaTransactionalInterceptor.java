package org.srestrepo.apiservlet.webapp.jpacdi.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import org.srestrepo.apiservlet.webapp.jpacdi.services.JdbcServiceException;

import java.util.logging.Logger;

@JpaTransactional
@Interceptor
public class JpaTransactionalInterceptor {

    @Inject
    private EntityManager em;
    @Inject
    private Logger log;

    @AroundInvoke
    public Object transactional(InvocationContext invocation) throws Throwable {
        try {
            em.getTransaction().begin();
            log.info("-------> JPA Transaction Begins " + invocation.getMethod().getName() + " of " + invocation.getMethod().getDeclaringClass().getName());
            Object result = invocation.proceed();
            em.getTransaction().commit();
            log.info("-------> JDBC Transaction Ended " + invocation.getMethod().getName() + " of " + invocation.getMethod().getDeclaringClass().getName());

            return result;
        } catch (JdbcServiceException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
