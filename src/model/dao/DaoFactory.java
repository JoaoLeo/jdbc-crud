package model.dao;

import db.DB;
import model.dao.impl.DepartamentoDaoJDBC;
import model.dao.impl.VendedorDaoJDBC;

public class DaoFactory {
    public static VendedorDao criaVendedorDao(){
        return new VendedorDaoJDBC(DB.connect());
    }

    public static DepartamentoDao criaDepartamentoDao(){
        return new DepartamentoDaoJDBC(DB.connect());
    }
}
