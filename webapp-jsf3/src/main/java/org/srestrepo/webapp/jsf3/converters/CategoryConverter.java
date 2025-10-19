package org.srestrepo.webapp.jsf3.converters;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.srestrepo.webapp.jsf3.entities.Category;
import org.srestrepo.webapp.jsf3.services.ProductService;

import java.util.Optional;

@RequestScoped
@Named
public class CategoryConverter implements Converter<Category> {

    @Inject
    private ProductService productService;

    @Override
    public Category getAsObject(FacesContext context, UIComponent component, String id) {
        System.out.println(id);
        if (id == null) {
            return null;
        }
        Optional<Category> categoryOptional = productService.getCategory(Long.valueOf(id));
        return categoryOptional.orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Category category) {
        if (category == null) {
            return "0";
        }
        return category.getId().toString();
    }
}
