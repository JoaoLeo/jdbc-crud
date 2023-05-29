package model.dao;

import org.junit.jupiter.api.Test;

public class VendedorDaoTest {
    @Test
    void getAll(){
        VendedorDao vendedorDao = DaoFactory.criaVendedorDao();
        assert (vendedorDao.getAll().size() != 0);
    }
}
