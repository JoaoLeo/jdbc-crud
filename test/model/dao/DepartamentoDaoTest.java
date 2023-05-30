package model.dao;

import model.entities.Departamento;
import model.entities.Vendedor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DepartamentoDaoTest {
    private  static  DepartamentoDao departamentoDao = null;
    @BeforeAll
    static void setup(){
        departamentoDao = DaoFactory.criaDepartamentoDao();
    }

    @Test
    void findById(){
        Departamento d =  departamentoDao.findById(4);
        System.out.println("Departamento: " + d.toString());
        assert (d != null);
    }
    @Test
    void getAll(){
        List<Departamento> d = departamentoDao.getAll();
        System.out.println(d.toString());
        assert(d.size() > 0);
    }
}
