package model.dao;

import model.entities.Vendedor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class VendedorDaoTest {
    private  static VendedorDao vendedorDao = null;
    @BeforeAll
    static void setup(){
        vendedorDao = DaoFactory.criaVendedorDao();
    }
    @Test
    void findById(){
        Vendedor v  = vendedorDao.findById(3);
        System.out.println("Vendedor: " + v.toString());
        assert (v != null);
    }

    @Test
    void getAll(){
        List<Vendedor> v = vendedorDao.getAll();
        System.out.println(v.toString());
        assert(v.size() > 0);
    }
}
