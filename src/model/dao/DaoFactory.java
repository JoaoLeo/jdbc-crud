package model.dao;

import model.dao.impl.DepartamentoDaoJDBC;
import model.dao.impl.VendedorDaoJDBC;

public class DaoFactory {
    public static VendedorDao criaVendedorDao(){
        return new VendedorDaoJDBC();
    }

    public static DepartamentoDao criaDepartamentoDao(){
        return new DepartamentoDaoJDBC();
    }
}
