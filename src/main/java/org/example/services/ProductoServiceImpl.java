package org.example.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.example.entities.Producto;
import org.example.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Stateless
public class ProductoServiceImpl implements ProductoService{

    @Inject
    CrudRepository<Producto> repository;

    @Override
    public List<Producto> listar() {
        return repository.listar();
    }

    @Override
    public Optional<Producto> porId(Long id) {
            return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void guardar(Producto producto) {
        repository.guardar(producto);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}
