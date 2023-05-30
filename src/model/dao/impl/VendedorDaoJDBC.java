package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendedorDaoJDBC implements VendedorDao {
    private Connection conn;

    public VendedorDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Vendedor> getAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT vendedor.*,departamento.Nome as DepName " +
                            " FROM vendedor INNER JOIN departamento" +
                            " ON vendedor.DepartamentoId = departamento.Id"
            );
            rs = st.executeQuery();
            List<Vendedor> vendedores = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();
            while(rs.next()){

                Departamento d = map.get(rs.getInt("departamentoId"));
                if(d == null) {
                    d = instanciaDepartamento(rs);
                    map.put(rs.getInt("DepartamentoId"), d);
                }
                Vendedor v = instanciaVendedor(rs,d);
                vendedores.add(v);
                }
            return vendedores;
            } catch (SQLException err){
                throw new DbException(err.getMessage());
        } finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        }


    @Override
    public void insert(Vendedor v) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                   " insert into vendedor (Nome,Email,DtAniversario,salarioBase,DepartamentoId) " +
                    " values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1,v.getNome());
            st.setString(2,v.getEmail());
            st.setDate(3, new Date(v.getDtAniversario().getTime()));
            st.setDouble(4, v.getSalarioBase());
            st.setInt(5, v.getDepartamento().getId());
            int linhas = st.executeUpdate();
            if(linhas > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    v.setId(id);
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
    public void update(Vendedor v) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "update vendedor " +
                            " set Nome = ?, Email = ?, DtAniversario= ? ,salarioBase= ? , DepartamentoId = ? " +
                            " where Id = ? ");
            st.setString(1,v.getNome());
            st.setString(2,v.getEmail());
            st.setDate(3, new Date(v.getDtAniversario().getTime()));
            st.setDouble(4, v.getSalarioBase());
            st.setInt(5, v.getDepartamento().getId());
            st.setInt(6,v.getId());
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
    public Vendedor findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT vendedor.*,departamento.Nome as DepName " +
                    " FROM vendedor INNER JOIN departamento" +
                    " ON vendedor.DepartamentoId = departamento.Id" +
                    " WHERE vendedor.Id = ?"
            );
            st.setInt(1, id); //Seta qu o primeiro parametro "?" receba o valor id
            rs = st.executeQuery();
            if(rs.next()){
                Departamento d = instanciaDepartamento(rs);
                Vendedor v = instanciaVendedor(rs, d);
                return v;
            }
            return null;
        } catch (SQLException err){
            throw new DbException(err.getMessage());
        } finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Vendedor instanciaVendedor(ResultSet rs, Departamento d) throws SQLException {
        Vendedor v = new Vendedor();
        v.setId(rs.getInt("Id"));
        v.setNome(rs.getString("Nome"));
        v.setEmail(rs.getString("Email"));
        v.setSalarioBase(rs.getDouble("salarioBase"));
        v.setDtAniversario(rs.getDate("DtAniversario"));
        v.setDepartamento(d);
        return v;
    }

    private Departamento instanciaDepartamento(ResultSet rs) throws SQLException {
        Departamento d = new Departamento();
        d.setId(rs.getInt("DepartamentoId"));
        d.setNome(rs.getString("DepName"));
        return d;
    }
}
