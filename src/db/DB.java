package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    private static Connection conn = null;

    //conecta ao banco de dados
    public static Connection connect(){
        if(conn == null) {
            try {
                Properties properties = loadProperties();
                conn = DriverManager.getConnection(properties.getProperty("dburl"), properties);
            } catch(SQLException err){
                throw new DbException(err.getMessage());
            }
        }
        return conn;
    }

    //fechar conexão com banco de dados
    public static void disconnect() {
        if(conn != null){
            try {
                conn.close();
            } catch(SQLException err){
                throw new DbException(err.getMessage());
            }
        }
    }
    // lê o arquivo db.properties que esta na raiz e carrega pro objeto properties
    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties properties = new Properties();
            properties.load(fs);
            return properties;
        } catch (IOException err){
            throw new DbException(err.getMessage());
        }
    }
}
