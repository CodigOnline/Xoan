package com.codigonline;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

/**
 * Funcionalidad de MYSQL dentro de nuestro proyecto JAVA
 */
public class MySQL {

    private Connection connection;

    /**
     * Función para connectarnos a la BD DESEADA
     */
    public void connect() {
        /**
         * Registrar el connector en nuestro programa
         */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("No se ha podido registrar el connector");
            System.err.println(ex.toString());
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/xoan", "root", "secret");
            System.out.println("Connexión realizada correctamente");
        } catch (SQLException ex) {
            System.err.println("No nos hemos podido connectar la BD: LOCALHOST:3306");
            System.err.println(ex.toString());
        }


    }

    public void createUsuario() {
        String sql = "CREATE TABLE IF NOT EXISTS Usuarios(id INTEGER PRIMARY KEY AUTO_INCREMENT,nombre VARCHAR(150), email VARCHAR(50), password VARCHAR(20));";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Tabla creada correctamente");
        } catch (SQLException ex) {
            System.err.println("Error al creat la tabla usuarios");
        }
    }

    public void mostrarUsuarios() {
        String sql = "SELECT * FROM Usuarios";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            /**
             * Recorre las filas recuperadas
             */
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String nombre = resultSet.getString(2);
                String email = resultSet.getString(3);
                String password = resultSet.getString(4);

                Usuario usuario = new Usuario(id, nombre, email, password);
                System.out.println(usuario.toString());

            }

        } catch (SQLException ex) {
            System.err.println("No hemos podido recuperar los usuarios");
            System.err.println(ex.toString());
        }


    }

    public void addUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuarios (nombre,email,password) VALUES (?,?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getPassword());

            preparedStatement.executeUpdate();


            System.out.println("Usuario introducido correctamente");
        } catch (SQLException ex) {
            System.err.println("No hemos podido insertar el usuario");
            System.err.println(ex.toString());
        }
    }



/*

    deleteUsuario();
    getUsuario();

    */
}
