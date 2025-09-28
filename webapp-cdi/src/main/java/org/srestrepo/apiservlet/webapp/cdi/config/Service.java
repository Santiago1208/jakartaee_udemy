package org.srestrepo.apiservlet.webapp.cdi.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;
import org.srestrepo.apiservlet.webapp.cdi.interceptors.Logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Stereotype
@ApplicationScoped
@Logging
@Named
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Service {
}
