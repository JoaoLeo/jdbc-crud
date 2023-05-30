package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartamentoDao;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartamentoDaoJDBC  implements DepartamentoDao {
    private Connection conn;

    public DepartamentoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Departamento> getAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT * from departamento"
            );
            rs = st.executeQuery();
            List<Departamento> departamentos = new ArrayList<>();
            while(rs.next()){
                Departamento d = instanciaDepartamento(rs);
                departamentos.add(d);
            }
            return departamentos;
        } catch (SQLException err){
            throw new DbException(err.getMessage());
        } finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void insert(Departamento d) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    " insert into departamento (Nome) " +
                            " values(?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1,d.getNome());
            int linhas = st.executeUpdate();
            if(linhas > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    d.setId(id);
                }
                DB.closeResultSet(rs);
            }
            else{
                throw new DbException("Erro inesperado");
            }
        } catch (SQLException err) {
            throw new DbException(err.getMessage());
        }
    }

    @Override
    public void update(Departamento d) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "update departamento " +
                            " set Nome = ?" +
                            " where Id = ? ");
            st.setString(1,d.getNome());
            st.setInt(2,d.getId());
            st.executeUpdate();
        }catch (SQLException err){
            throw new DbException(err.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Departamento findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * from departamento d where id = ?"
            );
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Departamento d = instanciaDepartamento(rs);
                return d;
            }

        } catch (SQLException err) {
            throw new DbException(err.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return null;
    }

    private Departamento instanciaDepartamento(ResultSet rs) throws SQLException {
        Departamento d = new Departamento();
        d.setId(rs.getInt("Id"));
        d.setNome(rs.getString("Nome"));
        return d;
    }
}
