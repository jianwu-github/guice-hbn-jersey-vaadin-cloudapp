package org.simplewebstack.app;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;

/**
 * HibernateSessionFilter supports Open Session in View Pattern, to simplify the coding, this filter is doing one
 * session one transaction.
 *
 * @author <a herf="mailto:hellojianwu@gmail.com">jianwu</a>
 */
@Singleton
public class HibernateSessionFilter implements Filter, Serializable {
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(HibernateSessionFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Initializing HibernateServletFilter");
    }

    public void destroy() {
        logger.debug("Destroying HibernateServletFilter");
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        if (session.getTransaction().isActive()) {
            logger.debug("Committing the final active transaction");
            session.getTransaction().commit();
        }

        if (session.isOpen()) {
            logger.debug("Closing the final open session");
            session.close();
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
                    ServletException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            logger.debug("Starting a database transaction");
            session.beginTransaction();

            chain.doFilter(request, response);

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            if (session.getTransaction().isActive()) {
                logger.debug("Committing the active database transaction");
                session.getTransaction().commit();
            }
        } catch (StaleObjectStateException e) {
            logger.error(e.toString());

            if (session.getTransaction().isActive()) {
                logger.debug("Rolling back the active transaction");
                session.getTransaction().rollback();
            }

            throw e;
        } catch (Throwable e) {
            logger.error(e.toString());

            if (session.getTransaction().isActive()) {
                logger.debug("Rolling back the active transaction");
                session.getTransaction().rollback();
            }

            throw new ServletException(e);
        }
    }
}
