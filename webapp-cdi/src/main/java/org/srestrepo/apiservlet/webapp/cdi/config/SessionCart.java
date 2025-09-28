package org.srestrepo.apiservlet.webapp.cdi.config;

import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

import static  java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.*;

@Stereotype
@SessionScoped
@Named
@Retention(RUNTIME)
@Target(TYPE)
public @interface SessionCart {
}
