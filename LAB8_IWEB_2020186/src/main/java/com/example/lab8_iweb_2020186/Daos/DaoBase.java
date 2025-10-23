package com.example.lab8_iweb_2020186.Daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DaoBase {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/libreria?serverTimezone=America/Lima&useUnicode=true&characterEncoding=UTF-8";
        return DriverManager.getConnection(url, user, password);
    }

    public abstract void crear(Object entidad) throws SQLException;
    public abstract void borrar(int id) throws SQLException;
}