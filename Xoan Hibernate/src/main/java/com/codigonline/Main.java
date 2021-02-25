package com.codigonline;

import com.codigonline.config.HibernateConfig;
import com.codigonline.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando config de hibernate");
        SessionFactory sessionFactory = HibernateConfig.get();
        System.out.println("Abriendo sesión");
        Session session = sessionFactory.openSession();
        Usuario usuario = new Usuario("alvaro", "asd@gmail.com", "asdasd");

        session.beginTransaction();
        session.save(usuario);
        session.getTransaction().commit();

        session.beginTransaction();
        usuario.setEmail("alortegama@gmail.com");
        session.update(usuario);
        session.getTransaction().commit();

        session.beginTransaction();
        Long userId = usuario.getId();
        Query<Usuario> query = session.createQuery("from usuarios_xoan as u where u.id = :userId");
        query.setParameter("userId", userId);
        Usuario usuario1 = query.getSingleResult();
        System.out.println(usuario1);
        session.getTransaction().commit();


        session.beginTransaction();
        session.delete(usuario);
        session.getTransaction().commit();


        session.close();
        System.out.println("Cerrando sesión");
        sessionFactory.close();
        System.out.println("Cerrando conexión");
    }
}