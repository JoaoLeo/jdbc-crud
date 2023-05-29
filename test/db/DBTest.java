package db;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class DBTest {
    @AfterAll
    static void disconnect(){
        System.out.println("Fechando conexão");
        DB.disconnect();
    }
    @Test
    void testConnection(){
        System.out.println("Abrindo conexão");
        Connection conn = DB.connect();
        assert (conn != null);
    }
}
