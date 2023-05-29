package model.dao.impl;

import model.dao.DepartamentoDao;
import model.entities.Departamento;

import java.sql.Connection;
import java.util.List;

public class DepartamentoDaoJDBC  implements DepartamentoDao {
    private Connection conn;
    public DepartamentoDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public List<Departamento> getAll() {
        return null;
    }

    @Override
    public void insert(Departamento d) {

    }

    @Override
    public void update(Departamento d) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Departamento findById(Integer id) {
        return null;
    }
}
