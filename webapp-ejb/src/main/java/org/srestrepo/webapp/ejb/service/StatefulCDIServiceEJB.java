package org.srestrepo.webapp.ejb.service;

import jakarta.ejb.Stateful;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
@Stateful
public class StatefulCDIServiceEJB {

    private int counter;

    public String greeting(String name) {
        System.out.println("Printing from inside ServiceEJB: " + this);
       /*
        * Request 1.1: Printing from inside ServiceEJB: org.srestrepo.webapp.ejb.service.StatefulCDIServiceEJB@5e27a787
        * Request 1.2: Printing from inside ServiceEJB: org.srestrepo.webapp.ejb.service.StatefulCDIServiceEJB@5e27a787
        *
        * Request 2.1: Printing from inside ServiceEJB: org.srestrepo.webapp.ejb.service.StatefulCDIServiceEJB@7fb43dfc
        * Request 2.2: Printing from inside ServiceEJB: org.srestrepo.webapp.ejb.service.StatefulCDIServiceEJB@7fb43dfc
        * Since this bean is RequestScoped, the clients will be using one single object per request. The objects are
        * going to be renewed when a new request starts. You can have SessionScoped, ApplicationScoped and ConversationScoped
        * beans depending on your needs.
        * */

        counter++;
        System.out.println("Counter: " + counter);
       /*
        * Request 1.1: Counter: 1
        * Request 1.2: Counter: 2
        *
        * Request 2.1: Counter: 1
        * Request 2.2: Counter: 2
        * The attribute values of this bean will be stored in memory as long as the request keeps going. Once the
        * request ends, the bean will be destroyed and the memory is going to be empty.
        * */

        return "Hello " + name;
    }
}
