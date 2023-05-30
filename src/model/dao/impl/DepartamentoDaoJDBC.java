package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartamentoDaoJDBC  implements DepartamentoDao {
    private Connection conn;

    public DepartamentoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Departamento> getAll() {
        return null;
    }

    @Override
    public void insert(Departamento d) {

    }

    @Override
    public void update(Departamento d) {

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
