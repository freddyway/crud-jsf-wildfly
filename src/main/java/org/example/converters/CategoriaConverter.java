package org.example.converters;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.entities.Categoria;
import org.example.services.ProductoService;

import java.util.Optional;

@RequestScoped
@Named("categoriaConverter")
public class CategoriaConverter implements Converter<Categoria> {
    @Inject
    private ProductoService service;
    @Override
    public Categoria getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        if(id == null) {
            return null;
        }

        Optional<Categoria> optionalCategoria = service.porIdCategoria(Long.valueOf(id));
        return optionalCategoria.orElse(null);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Categoria categoria) {
        if(categoria == null){
            return "0";
        }
        return categoria.getId().toString();
    }
}
