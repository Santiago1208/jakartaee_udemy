package org.srestrepo.webapp.ejb.service;

import jakarta.ejb.Stateless;

@Stateless
public class StatelessServiceEJB {

    private int counter;

    public String greeting(String name) {
        System.out.println("Printing from inside ServiceEJB: " + this);
       /*
        * Request 1: Printing from inside ServiceEJB: org.srestrepo.webapp.ejb.service.StatelessServiceEJB@7c65c742
        * Request 2: Printing from inside ServiceEJB: org.srestrepo.webapp.ejb.service.StatelessServiceEJB@7c65c742
        * Same object for request 1 and request 2 if they are made sequentially. If made in parallel, the objects
        * are going to be different, since the first one is buzzy so the second one will be pulled from the EJB pool.
        * */

        counter++;
        System.out.println("Counter: " + counter);
       /*
        * Request 1: Counter: 1
        * Request 2: Counter: 2
        * Any attributes inside a stateless bean are going to be shared along all the clients using it. Requests being
        * done sequentially are going to increment the counter for subsequent ones. Request being done in parallel are
        * going to restart the counter caused by the new instance provided by the EJB pool. As long as the first instance
        * is still buzzy, the counter on the second instance will increase as soon as it gets free and buzzy again.
        * */

        return "Hello " + name;
    }
}
