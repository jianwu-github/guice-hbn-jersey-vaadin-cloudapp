package org.simplewebstack.app.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.hibernate.Session;
import org.simplewebstack.app.HibernateUtil;
import org.simplewebstack.app.entity.Subscriber;

@Path("/subscriber")
public class SubscriberResource {

    public SubscriberResource() {
    }

    @GET
    @Path("{id}")
    @Produces("application/xml")
    public SubscriberBean getSubscriberBean(@PathParam("id") long id) {
        SubscriberBean subscriberBean = new SubscriberBean();

        Subscriber subscriber = findSubscriberById(id);
        if (subscriber != null) {
            subscriberBean.setId(subscriber.getId());
            subscriberBean.setAge(subscriber.getAge());
            subscriberBean.setName(subscriber.getName());
            subscriberBean.setFacebookId(subscriber.getFacebookId());
        }

        return subscriberBean;
    }

    private Subscriber findSubscriberById(long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        if (session != null) {
            Subscriber subscriber = (Subscriber) session.get(Subscriber.class, Long.valueOf(id));

            return subscriber;
        }

        return null;
    }

}
