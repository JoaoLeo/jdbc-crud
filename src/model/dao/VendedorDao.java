package model.dao;

import model.entities.Departamento;
import model.entities.Vendedor;

import java.util.List;

public interface VendedorDao {
    List<Vendedor> getAll();
    void insert(Vendedor v);
    void update(Vendedor v);
    void delete(Integer id);
    Vendedor findById(Integer id);
}
