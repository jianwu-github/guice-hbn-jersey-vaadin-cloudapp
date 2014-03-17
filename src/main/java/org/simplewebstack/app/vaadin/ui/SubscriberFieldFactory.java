package org.simplewebstack.app.vaadin.ui;

import com.vaadin.data.Container;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

public class SubscriberFieldFactory extends DefaultFieldFactory {
    private static final long serialVersionUID = 1L;

    public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
        final Field<?> f = super.createField(container, itemId, propertyId, uiContext);
        if (f != null) {
            if (f instanceof TextField) {
                TextField tf = (TextField) f;
                tf.setWidth("100%");
            }
        }
        return f;
    }

}
