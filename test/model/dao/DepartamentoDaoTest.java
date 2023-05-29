package model.dao;

import model.entities.Departamento;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DepartamentoDaoTest {

    @Test
    void getAll(){
        DepartamentoDao departamentoDao = DaoFactory.criaDepartamentoDao();
        assert (departamentoDao.getAll().size() != 0);
    }
}
