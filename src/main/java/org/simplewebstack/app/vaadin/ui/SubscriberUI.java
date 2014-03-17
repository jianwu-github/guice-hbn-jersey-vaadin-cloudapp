package org.simplewebstack.app.vaadin.ui;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.simplewebstack.app.HibernateUtil;
import org.simplewebstack.app.entity.Subscriber;
import org.simplewebstack.app.vaadin.guice.ui.ScopedUI;

import com.vaadin.data.hbnutil.HbnContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * SubscriberUI displays Subscriber Data loaded from PostgreSQL using HbnContainer
 *
 * @author <a herf="mailto:jian.wu@us.chanjet.com">jianwu</a>
 */
public class SubscriberUI extends ScopedUI implements ClickListener {
    private static final long serialVersionUID = 1L;

    private Table table;
    private SubscriberEditor editor;
    private Button addSubscriberButton;
    private VerticalLayout mainLayout;

    public SubscriberUI() {
    }

    protected void init(VaadinRequest request) {
        // Do nothing
        buildView();
    }

    private void buildView() {
        Panel p = new Panel("Subscriber List");
        p.setStyleName(Reindeer.PANEL_LIGHT);
        setContent(p);

        mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        p.setContent(mainLayout);

        HorizontalLayout ol = new HorizontalLayout();

        addSubscriberButton = new Button("Add Subscriber", this);
        ol.addComponent(addSubscriberButton);
        mainLayout.addComponent(ol);

        // mainLayout.addComponent(new Label("Subscriber List Table"));

        populateAndConfigureTable();

        mainLayout.addComponent(table);

        // make table consume all extra space
        p.setSizeFull();
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(table, 1);
        table.setSizeFull();
    }

    protected void populateAndConfigureTable() {
        table = new Table();

        table.setWidth("100%");
        table.setPageLength(30);
        table.setSelectable(true);
        table.setImmediate(true);
        table.setColumnCollapsingAllowed(true);
        table.setTableFieldFactory(new SubscriberFieldFactory());

        loadSubscribers();
    }

    protected void loadSubscribers() {
        HbnContainer<Subscriber> subscriberContainer = new HbnContainer(Subscriber.class,
                        HibernateUtil.getSessionFactory());

        table.setContainerDataSource(subscriberContainer);

    }

    protected void reloadSubscribers() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currSession = sessionFactory.getCurrentSession();

        // HbnContainer using Criteria API which requires an active transaction
        currSession.beginTransaction();

        HbnContainer<Subscriber> subscriberContainer = new HbnContainer(Subscriber.class, sessionFactory);
        table.setContainerDataSource(subscriberContainer);
    }

    public void buttonClick(ClickEvent event) {
        if (event.getButton() == addSubscriberButton) {
            editor = new SubscriberEditor(this);

            // Set window size.
            editor.setHeight("300px");
            editor.setWidth("400px");

            // Set window position.
            editor.setPositionX(200);
            editor.setPositionY(50);

            addWindow(editor);
        }
    }

}
