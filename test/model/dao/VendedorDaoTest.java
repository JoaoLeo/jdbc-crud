package model.dao;

import model.entities.Vendedor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class VendedorDaoTest {
    private  static VendedorDao vendedorDao = null;
    private static DepartamentoDao departamentoDao = null;
    @BeforeAll
    static void setup(){
        vendedorDao = DaoFactory.criaVendedorDao();
        departamentoDao = DaoFactory.criaDepartamentoDao();
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

    @Test
    void insert(){
        Vendedor v = new Vendedor();
        v.setNome("João");
        v.setEmail("joaoleonardo9921@gmail.com");
        v.setDtAniversario(new Date());
        v.setSalarioBase(1000.0);
        v.setDepartamento(departamentoDao.findById(1));
        vendedorDao.insert(v);
        Vendedor v2 = vendedorDao.findById(v.getId());
        System.out.println(v2.toString());
        assert (v2 != null);
    }

    @Test
    void update(){
        Vendedor v = vendedorDao.findById(8);
        v.setNome("João ");
        v.setEmail("teste@gmail.com");
        v.setDtAniversario(new Date());
        v.setSalarioBase(660.0);
        v.setDepartamento(departamentoDao.findById(2));
        vendedorDao.update(v);
        Vendedor v2 = vendedorDao.findById(8);
        System.out.println(v2.toString());
        assert (v.equals(v2)); //Se for true que dizer que o objeto foi alterado no banco de acordo com os sets acima
    }

    @Test
    void delete(){
        int idParaDeletar = 9;
        System.out.println("Deletando registro de numero " + idParaDeletar);
        vendedorDao.delete(idParaDeletar);
        assert (vendedorDao.findById(idParaDeletar) == null);
    }
}
