package com.codigonline;

public class Main {

    public static void main(String[] args) {
        /**
         * Creación de la instancia de MYSQL
         */
        MySQL mySQL = new MySQL();
        /**
         * Llamada al método de connexión
         */
        mySQL.connect();
        mySQL.createUsuario();
        Usuario usuario = new Usuario("lkjhgfd", "lkjhg@gmail.com", "fdghjkl");
        mySQL.addUsuario(usuario);
        mySQL.mostrarUsuarios();
    }
}
