package org.simplewebstack.app.guice.vaadin.servlet;

import org.simplewebstack.app.vaadin.guice.ui.ScopedUIProvider;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinServlet;

@Singleton
public class GuiceVaadinServlet extends VaadinServlet implements SessionInitListener {

    private static final long serialVersionUID = 1L;

    /**
     * Cannot use constructor injection. Container expects servlet to have no-arg public constructor
     */

    private ScopedUIProvider applicationProvider;

    @Inject
    public GuiceVaadinServlet(ScopedUIProvider applicationProvider) {
        this.applicationProvider = applicationProvider;
    }

    @Override
    protected void servletInitialized() {
        getService().addSessionInitListener(this);
    }

    @Override
    public void sessionInit(SessionInitEvent event) throws ServiceException {
        event.getSession().addUIProvider(applicationProvider);
    }
}
