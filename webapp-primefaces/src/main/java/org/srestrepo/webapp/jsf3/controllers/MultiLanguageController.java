package org.srestrepo.webapp.jsf3.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SessionScoped
@Named
public class MultiLanguageController implements Serializable {
    private static final long serialVersionUID = 46333248987561L;

    private Locale locale;
    private String language;
    private Map<String, String> supportedLang;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        supportedLang = new HashMap<>();
        supportedLang.put("English", "en");
        supportedLang.put("Español", "es");
    }

    public void changeLanguage(ValueChangeEvent event) {
        String newLanguage = event.getNewValue().toString();
        supportedLang.values().stream()
                .filter(l -> l.equals(newLanguage))
                .findFirst()
                .ifPresent(lang -> {
                    this.locale = new Locale(lang);
                    FacesContext.getCurrentInstance().getViewRoot().setLocale(this.locale);
                });
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Map<String, String> getSupportedLang() {
        return supportedLang;
    }

    public void setSupportedLang(Map<String, String> supportedLang) {
        this.supportedLang = supportedLang;
    }
}
