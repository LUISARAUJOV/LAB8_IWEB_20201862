package com.example.lab8_iweb_2020186.Daos;

import com.example.lab8_iweb_2020186.Beans.libro;
import com.example.lab8_iweb_2020186.Beans.editorial;
import com.example.lab8_iweb_2020186.Beans.genero;

import java.sql.*;
import java.util.ArrayList;

public class LibroDao extends DaoBase {

    public ArrayList<libro> listar() {
        ArrayList<libro> lista = new ArrayList<>();
        String sql = "SELECT l.id, l.titulo, l.autor, l.paginas, l.premios, " +
                     "e.id as editorial_id, e.nombre as editorial_nombre, " +
                     "g.id as genero_id, g.nombre as genero_nombre " +
                     "FROM libro l " +
                     "INNER JOIN editorial e ON l.editorial_id = e.id " +
                     "INNER JOIN genero g ON l.genero_id = g.id";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                libro lib = new libro();
                lib.setId(rs.getInt("id"));
                lib.setTitulo(rs.getString("titulo"));
                lib.setAutor(rs.getString("autor"));
                lib.setPaginas(rs.getInt("paginas"));
                lib.setPremios(rs.getInt("premios"));

                editorial ed = new editorial();
                ed.setId(rs.getInt("editorial_id"));
                ed.setNombre(rs.getString("editorial_nombre"));
                lib.setEditorial(ed);

                genero gen = new genero();
                gen.setId(rs.getInt("genero_id"));
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
        String sql = "SELECT id, nombre FROM editorial";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                editorial ed = new editorial();
                ed.setId(rs.getInt("id"));
                ed.setNombre(rs.getString("nombre"));
                lista.add(ed);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<genero> listarGeneros() {
        ArrayList<genero> lista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM genero";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                genero gen = new genero();
                gen.setId(rs.getInt("id"));
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
        String sql = "INSERT INTO libro (titulo, autor, paginas, premios, genero_id, editorial_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, lib.getTitulo());
            pstmt.setString(2, lib.getAutor());
            pstmt.setInt(3, lib.getPaginas());
            pstmt.setInt(4, lib.getPremios());
            pstmt.setInt(5, lib.getGenero().getId());
            pstmt.setInt(6, lib.getEditorial().getId());

            pstmt.executeUpdate();
        }
    }

    @Override
    public void borrar(int id) throws SQLException {
        String sqlBorrar = "DELETE FROM libro WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlBorrar)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
