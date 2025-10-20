package org.srestrepo.webapp.jsf3;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.context.FacesContext;

@ApplicationScoped
public class FlashMessageProducer {

    @Produces
    @RequestScoped
    public FacesContext produceFacesContext() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        return facesContext;
    }
}
