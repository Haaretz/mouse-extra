package com.haaretz;

/**
 * Created by elia.grady on 12/02/2017.
 */

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Arrays;

public class HibernateUtil {

  @Autowired
  static Environment env;
  private static SessionFactory sessionFactory;

  private static SessionFactory buildSessionFactory() {
    try {
      // Create the SessionFactory from hibernate.cfg.xml
      Configuration configuration = new Configuration();
      if (env != null) {
        System.out.println(env.toString());
        System.out.println(env.getActiveProfiles());
      }
      if (env != null && Arrays.asList(env.getActiveProfiles()).contains("dev")) {
        configuration.configure("hibernate.cfg.dev.xml");
      } else {
        configuration.configure("hibernate.cfg.xml");
      }
      System.out.println("Hibernate Configuration loaded");

      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
      System.out.println("Hibernate serviceRegistry created");

      SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

      return sessionFactory;
    } catch (Throwable ex) {
      System.err.println("Initial SessionFactory creation failed." + ex);
      ex.printStackTrace();
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) sessionFactory = buildSessionFactory();
    return sessionFactory;
  }
}
