package org.srestrepo.apiservlet.webapp.cdi.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;

@Logging
@Interceptor
public class LoggingInterceptor {

    @Inject
    private Logger log;

    @AroundInvoke
    public Object log(InvocationContext invocation) throws Exception {
        log.info("****** Before invoking " + invocation.getMethod().getName() + " of " + invocation.getMethod().getDeclaringClass().getName());

        Object returnValue = invocation.proceed();

        log.info("****** After invoking " + invocation.getMethod().getName() + " of " + invocation.getMethod().getDeclaringClass().getName());
        return returnValue;
    }
}
