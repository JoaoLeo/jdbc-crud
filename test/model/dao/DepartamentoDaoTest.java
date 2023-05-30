package model.dao;

import model.entities.Departamento;
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
    @Test
    void insert(){
        Departamento d = new Departamento();
        d.setNome("DEV");
        departamentoDao.insert(d);
        System.out.println(d);
        assert (departamentoDao.findById(d.getId()) != null);
    }
    @Test
    void update(){
            Departamento d= departamentoDao.findById(3);
            d.setNome("backend");
            departamentoDao.update(d);
            Departamento d2 = departamentoDao.findById(3);
            System.out.println(d2.toString());
            assert (d.equals(d2)); //Se for true que dizer que o objeto foi alterado no banco de acordo com os sets acima
        }
    @Test
    void delete(){
        int idParaDeletar = 4;
        System.out.println("Deletando registro de numero " + idParaDeletar);
        departamentoDao.delete(idParaDeletar);
        assert (departamentoDao.findById(idParaDeletar) == null);
    }
    }
