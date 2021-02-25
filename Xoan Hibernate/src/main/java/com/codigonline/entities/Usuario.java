package com.codigonline.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "usuarios_xoan")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30)
    private String nombre;
    @Column(length = 20, nullable = false, unique = true)
    private String email;
    @Column(length = 8)
    private String password;
    private Date fechaNacimiento;

    public Usuario() {
    }

    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) {
        if (email.length()==0){
            System.out.println("El email debe de tener +de 0 caracteres");
            return;
        }
        this.email = email;
    }
}
