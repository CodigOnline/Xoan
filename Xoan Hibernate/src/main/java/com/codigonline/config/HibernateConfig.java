package com.codigonline.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Error al iniciarlizar la configuración de Hibernate");
            System.err.println(ex.toString());
            throw new ExceptionInInitializerError("Error al inciarlizar");
        }
    }

    public static SessionFactory get() {
        return sessionFactory;
    }


}
