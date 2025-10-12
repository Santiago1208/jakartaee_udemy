package org.srestrepo.webapp.ejb.service;

import jakarta.ejb.Stateful;

@Stateful
public class StatefulServiceEJB {

    private int counter;

    public String greeting(String name) {
        System.out.println("Printing from inside ServiceEJB: " + this);
       /*
        * Request 1.1: Printing from inside ServiceEJB: org.srestrepo.webapp.ejb.service.StatefulServiceEJB@199b3ac9
        * Request 1.2: Printing from inside ServiceEJB: org.srestrepo.webapp.ejb.service.StatefulServiceEJB@7cb25e66
        *
        * Request 2.1: Printing from inside ServiceEJB: org.srestrepo.webapp.ejb.service.StatefulServiceEJB@199b3ac9
        * Request 2.2: Printing from inside ServiceEJB: org.srestrepo.webapp.ejb.service.StatefulServiceEJB@7cb25e66
        * Since this bean is being used twice from different attributes in the servlets, the objects are different.
        * However, starting a new request will mean that those exact objects are going to be used again, since they
        * are specific to the clients. This is a weird behavior because we are mixing ApplicationScoped servlet
        * with Dependent beans.
        * */

        counter++;
        System.out.println("Counter: " + counter);
       /*
        * Request 1.1: Counter: 1
        * Request 1.2: Counter: 1
        *
        * Request 2.1: Counter: 2
        * Request 2.2: Counter: 2
        * All the attribute values inside each bean instance are going to be stored in memory. Whoever client
        * uses the bean, is going to increment the counter and preserved until the next client uses it. Still this is
        * a weird behavior because only 2 beans are going to store all the values for all the clients, caused from the
        * Dependant context they belong.
        * */

        return "Hello " + name;
    }
}
