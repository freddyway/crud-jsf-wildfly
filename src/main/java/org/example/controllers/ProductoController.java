package org.example.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.entities.Categoria;
import org.example.entities.Producto;
import org.example.services.ProductoService;

import java.util.Arrays;
import java.util.List;


//@RequestScoped
//@Named(value="producto")

//@Model incluye a @@RequestScoped y @Named
@Model
public class ProductoController {

    private Long id;
    private Producto producto;
    @Inject
    private ProductoService service;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Produces
    @Model
    public String titulo(){
        return "CRUD Java Server Faces 3.0";
    }

    @Produces
    @RequestScoped
    @Named("listado")
    public List<Producto> findAll(){
        //return Arrays.asList(new Producto("peras"),new Producto("limones"),new Producto("naranjas"));
        return service.listar();
    }

    @Produces
    @Model
    public Producto producto(){
        this.producto = new Producto();
        if(id != null && id > 0){
            service.porId(id).ifPresent(p -> {
                this.producto = p;
            });
        }
        return producto;
    }

    @Produces
    @Model
    public List<Categoria> categorias(){
        return service.listarCategorias();
    }
    public String guardar(){
        service.guardar(producto);
        return "index.xhtml?faces-redirect=true";
    }

    public String eliminar(Long id){
        service.eliminar(id);
        return "index.xhtml?faces-redirect=true";
    }
    public String editar(Long id){
        this.id = id;
        return "form.xhtml";
    }
}
