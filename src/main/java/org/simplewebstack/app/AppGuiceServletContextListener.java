package org.simplewebstack.app;

import org.simplewebstack.app.guice.jersey.servlet.GuiceJerseyServletModule;
import org.simplewebstack.app.guice.vaadin.servlet.GuiceVaadinServletModule;
import org.simplewebstack.app.vaadin.guice.uiscope.UIScopeModule;
import org.simplewebstack.app.vaadin.ui.SubscriberUI;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class AppGuiceServletContextListener extends GuiceServletContextListener {

    public AppGuiceServletContextListener() {
    }

    protected Injector getInjector() {
        return Guice.createInjector(new UIScopeModule(SubscriberUI.class),
                        new GuiceVaadinServletModule(),
                        new GuiceJerseyServletModule());
    }

}
