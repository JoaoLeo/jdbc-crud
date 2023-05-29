package model.dao;

import model.entities.Departamento;

import java.util.List;

public interface DepartamentoDao {

    List<Departamento> getAll();
    void insert(Departamento d);
    void update(Departamento d);
    void delete(Integer id);
    Departamento findById(Integer id);

}
