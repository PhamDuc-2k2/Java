package com.ducpham.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
    private final static SessionFactory SESSION_FACTORY = buildSessionFactory();

    private HibernateUtil() {
    }

    /**
     * This is the first way to create instance SessionFactory
     *
     * @author  DucPham at 02 / 01 / 2025
     * @return  SessionFactory
     */
    private static SessionFactory buildSessionFactory() {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    /**
     * This is the second way to create instance SessionFactory
     *
     * @author  DucPham at 02 / 01 / 2025
     * @return  SessionFactory
     */
    private static SessionFactory buildSessionFactorySecondWay() {
        return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

}
