package org.simplewebstack.app;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HibernateUtil is responsible to initialize Hibernate Session Factory
 *
 *  @author <a herf="mailto:hellojianwu@gmail.com">jianwu</a>
 */
public class HibernateUtil {
    private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
    private static SessionFactory sessionFactory;

    static {
        try {
            logger.debug("Initializing HibernateUtil");

            final Configuration configuration = new Configuration();
            configuration.configure();

            final ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder();

            final ServiceRegistry serviceRegistry = serviceRegistryBuilder
                            .applySettings(configuration.getProperties())
                            .buildServiceRegistry();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable e) {
            logger.error(e.toString());
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
