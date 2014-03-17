package org.simplewebstack.app.guice.vaadin.servlet;

import org.simplewebstack.app.HibernateSessionFilter;

import com.google.inject.servlet.ServletModule;

public class GuiceVaadinServletModule extends ServletModule {

    public GuiceVaadinServletModule() {
    }

    protected void configureServlets() {
        filter("/ui/*").through(HibernateSessionFilter.class);
        serve("/ui/*").with(GuiceVaadinServlet.class);
        serve("/VAADIN/*").with(GuiceVaadinServlet.class);
    }

}
