package it.java.progettoPSSS.server.db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

//private static SessionFactory sessionFactory= initHibernateUtil();
	

 protected static SessionFactory sessionFactory;

	public void setup() {
		
		
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
		        .configure() // configures settings from hibernate.cfg.xml
		        .build();
		try {
		    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception ex) {
		    StandardServiceRegistryBuilder.destroy(registry);
		}

	}
	
//	private static SessionFactory initHibernateUtil() {
//        try {
//            return new Configuration().configure().buildSessionFactory();
//        } catch (HibernateException ex) {
//            throw new ExceptionInInitializerError(ex);
//        }
//    }

	public void exit() {
		sessionFactory.close();
	}
	

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}