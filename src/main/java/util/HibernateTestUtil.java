package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by 2ssoon on 5/1/15.
 */
public class HibernateTestUtil {
    private static SessionFactory sessionFactory;
    private static String configFile = "hibernate.cfg.xml";


    public static SessionFactory getSessionFactory(Class<?> clazz) {
        if (sessionFactory == null) {
            try {
                configFile = "/" + clazz.getPackage().getName().replace('.', '/') + "/" + configFile;
                Configuration configuration = new Configuration().configure(configFile);
                StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
                standardServiceRegistryBuilder.applySettings(configuration.getProperties());
                StandardServiceRegistry standardServiceRegistry = standardServiceRegistryBuilder.build();
                sessionFactory = configuration.buildSessionFactory(standardServiceRegistry);
            } catch (Throwable th) {
                System.err.println("Init SessionFactory creation failed" + th);
                throw new ExceptionInInitializerError(th);
            }
        }
        return sessionFactory;
    }

    public void shutdown() {
        sessionFactory.close();
    }
}
