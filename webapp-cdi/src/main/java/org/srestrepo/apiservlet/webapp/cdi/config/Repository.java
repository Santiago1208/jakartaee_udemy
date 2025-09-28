package org.srestrepo.apiservlet.webapp.cdi.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Stereotype
@ApplicationScoped
@Named
@Retention(RUNTIME)
@Target(TYPE)
public @interface Repository {
}
