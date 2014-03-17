package org.simplewebstack.app.vaadin.ui;

import org.hibernate.Session;
import org.simplewebstack.app.HibernateUtil;
import org.simplewebstack.app.entity.Subscriber;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

/**
 * SubscriberEditor allows user to add Subscriber through UI
 *
 * @author <a herf="mailto:jian.wu@us.chanjet.com">jianwu</a>
 */
public class SubscriberEditor extends Window implements ClickListener {
    private static final long serialVersionUID = 1L;

    private Subscriber subscriber;
    private SubscriberUI subscriberUI;

    private TextField nameField = new TextField("NAME");
    private TextField ageField = new TextField("AGE");
    private TextField facebookIdField = new TextField("FACEBOOK ID");

    private Button saveButton = new Button("SAVE");

    public SubscriberEditor(SubscriberUI subscriberUI) {
        super("Add New Subscriber");

        this.subscriberUI = subscriberUI;

        Layout main = new VerticalLayout();
        setContent(main);
        main.setSizeUndefined();
        main.setStyleName(Reindeer.PANEL_LIGHT);

        FormLayout form = new FormLayout();
        form.setSizeUndefined();

        form.addComponent(nameField);
        form.addComponent(ageField);
        form.addComponent(facebookIdField);
        main.addComponent(form);

        HorizontalLayout actions = new HorizontalLayout();
        actions.addComponent(saveButton);

        saveButton.addListener(this);
        main.addComponent(actions);
    }

    public void buttonClick(ClickEvent event) {
        if (event.getButton() == saveButton) {
            Subscriber newSubscriber = new Subscriber();
            newSubscriber.setName(nameField.getValue());
            newSubscriber.setAge(Integer.parseInt(ageField.getValue()));
            newSubscriber.setFacebookId(facebookIdField.getValue());

            Session currSession = HibernateUtil.getSessionFactory().getCurrentSession();
            if (currSession.getTransaction() == null || currSession.getTransaction().wasCommitted()
                            || currSession.getTransaction().wasRolledBack()) {
                currSession.beginTransaction();
            }

            currSession.save(newSubscriber);
            currSession.getTransaction().commit();

            subscriberUI.reloadSubscribers();

            if (getParent() != null) {
                getParent().getUI().removeWindow(this);
            }
        }
    }

}
