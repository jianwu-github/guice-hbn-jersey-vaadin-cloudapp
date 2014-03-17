package org.simplewebstack.app.guice.jersey.servlet;

import org.simplewebstack.app.HibernateSessionFilter;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class GuiceJerseyServletModule extends ServletModule {

    protected void configureServlets() {
        final ResourceConfig rc = new PackagesResourceConfig("org.simplewebstack.app.rest");
        for (Class<?> resource : rc.getClasses()) {
            System.out.println("Binding resource: " + resource.getName());
            bind(resource);
        }

        filter("/rest/*").through(HibernateSessionFilter.class);
        serve("/rest/*").with(GuiceContainer.class);
    }
}
