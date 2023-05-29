package model.dao;

import model.entities.Vendedor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
}
