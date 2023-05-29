package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VendedorDaoJDBC implements VendedorDao {
    private Connection conn;

    public VendedorDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Vendedor> getAll() {
        return null;
    }

    @Override
    public void insert(Vendedor v) {

    }

    @Override
    public void update(Vendedor v) {

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
                Departamento d = new Departamento();
                d.setId(rs.getInt("DepartamentoId"));
                d.setNome(rs.getString("DepName"));
                Vendedor v = new Vendedor();
                v.setId(rs.getInt("Id"));
                v.setNome(rs.getString("Nome"));
                v.setEmail(rs.getString("Email"));
                v.setSalarioBase(rs.getDouble("salarioBase"));
                v.setDtAniversario(rs.getDate("DtAniversario"));
                v.setDepartamento(d);
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
}
