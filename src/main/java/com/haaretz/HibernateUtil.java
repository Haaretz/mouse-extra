package com.haaretz;

/**
 * Created by elia.grady on 12/02/2017.
 * Used to create a session holder for connecting to MySQL
 */

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@org.springframework.context.annotation.Configuration
@PropertySource("classpath:application.properties")
public class HibernateUtil implements EnvironmentAware {


  private static SessionFactory sessionFactory;
  private static Environment env;

  private static SessionFactory buildSessionFactory() {
    try {
      // Create the SessionFactory from hibernate.cfg.xml
      Configuration configuration = new Configuration();
      if (env != null) {
        System.out.println(env.toString());
        System.out.println(env.getActiveProfiles().toString());
      }
      if (env != null && Arrays.asList(env.getActiveProfiles()).contains("dev")) {
        configuration.configure("hibernate.cfg.dev.xml");
      } else {
        configuration.configure("hibernate.cfg.xml");
      }
      System.out.println("Hibernate Configuration loaded");

      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
      System.out.println("Hibernate serviceRegistry created");

      return configuration.buildSessionFactory(serviceRegistry);
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

  @Override
  public void setEnvironment(final Environment environment) {
    this.env = environment;
  }
}
