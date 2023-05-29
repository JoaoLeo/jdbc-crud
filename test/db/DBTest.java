package db;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Properties;

public class DBTest {
    @AfterAll
    static void disconnect(){
        System.out.println("Fechando conexão");
        DB.disconnect();
    }
    @Test
    void testConnection(){
        System.out.println("Abrindo conexão");
        assert (DB.connect() != null);
    }

    @Test
    void loadProperties(){
        System.out.println("Abrindo conexão");
        DB.connect();
        System.out.println("Validando propriedades do banco");
        Properties p = DB.loadProperties();
        System.out.println("Propriedades: " + p.toString());
        assert (p != null);
    }
}
