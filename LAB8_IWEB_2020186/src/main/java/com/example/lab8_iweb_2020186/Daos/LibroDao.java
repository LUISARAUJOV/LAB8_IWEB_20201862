package com.example.lab8_iweb_2020186.Daos;

import com.example.lab8_iweb_2020186.Beans.libro;
import com.example.lab8_iweb_2020186.Beans.editorial;
import com.example.lab8_iweb_2020186.Beans.genero;

import java.sql.*;
import java.util.ArrayList;

public class LibroDao extends DaoBase {

    public ArrayList<libro> listar() {
        ArrayList<libro> lista = new ArrayList<>();
        String sql = "SELECT l.idlibro, l.nombre as titulo, l.autor, l.precio, l.fechaPublicacion, " +
                     "e.ideditorial, e.nombre as editorial_nombre, e.direccion, " +
                     "g.idgenero, g.nombre as genero_nombre " +
                     "FROM libro l " +
                     "INNER JOIN editorial e ON l.editorial_ideditorial = e.ideditorial " +
                     "INNER JOIN genero g ON l.genero_idgenero = g.idgenero";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                libro lib = new libro();
                lib.setId(rs.getInt("idlibro"));
                lib.setTitulo(rs.getString("titulo"));
                lib.setAutor(rs.getString("autor"));
                lib.setPrecio(rs.getDouble("precio"));
                lib.setFechaPublicacion(rs.getDate("fechaPublicacion"));

                editorial ed = new editorial();
                ed.setId(rs.getInt("ideditorial"));
                ed.setNombre(rs.getString("editorial_nombre"));
                ed.setDireccion(rs.getString("direccion"));
                lib.setEditorial(ed);

                genero gen = new genero();
                gen.setId(rs.getInt("idgenero"));
                gen.setNombre(rs.getString("genero_nombre"));
                lib.setGenero(gen);

                lista.add(lib);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<editorial> listarEditoriales() {
        ArrayList<editorial> lista = new ArrayList<>();
        String sql = "SELECT ideditorial, nombre, direccion FROM editorial";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                editorial ed = new editorial();
                ed.setId(rs.getInt("ideditorial"));
                ed.setNombre(rs.getString("nombre"));
                ed.setDireccion(rs.getString("direccion"));
                lista.add(ed);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<genero> listarGeneros() {
        ArrayList<genero> lista = new ArrayList<>();
        String sql = "SELECT idgenero, nombre FROM genero";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                genero gen = new genero();
                gen.setId(rs.getInt("idgenero"));
                gen.setNombre(rs.getString("nombre"));
                lista.add(gen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void crear(Object entidad) throws SQLException {
        libro lib = (libro) entidad;
        String sql = "INSERT INTO libro (nombre, autor, precio, fechaPublicacion, editorial_ideditorial, genero_idgenero) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, lib.getTitulo());
            pstmt.setString(2, lib.getAutor());
            pstmt.setDouble(3, lib.getPrecio());
            pstmt.setDate(4, lib.getFechaPublicacion());
            pstmt.setInt(5, lib.getEditorial().getId());
            pstmt.setInt(6, lib.getGenero().getId());

            pstmt.executeUpdate();
        }
    }

    @Override
    public void borrar(int id) throws SQLException {
        String sqlVerificar = "SELECT COUNT(*) as total FROM premio WHERE libro_idlibro = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmtVerificar = conn.prepareStatement(sqlVerificar)) {

            pstmtVerificar.setInt(1, id);
            ResultSet rs = pstmtVerificar.executeQuery();

            if (rs.next() && rs.getInt("total") > 0) {
                throw new SQLException("No se puede borrar el libro porque tiene premios asociados");
            }

            String sqlBorrar = "DELETE FROM libro WHERE idlibro = ?";
            try (PreparedStatement pstmtBorrar = conn.prepareStatement(sqlBorrar)) {
                pstmtBorrar.setInt(1, id);
                pstmtBorrar.executeUpdate();
            }
        }
    }
}
