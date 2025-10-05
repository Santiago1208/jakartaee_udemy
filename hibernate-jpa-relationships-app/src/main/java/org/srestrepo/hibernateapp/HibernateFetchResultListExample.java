package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchResultListExample {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        List<Client> clients = em.createQuery("select distinct c from Client c left outer join fetch c.addresses left outer join fetch c.detail", Client.class)
                .getResultList();
        clients.forEach(client -> System.out.println(client.getName() + ", addresses: "  + client.getAddresses()));

        /*
         * In this example we inspected how many queries can be triggered by just doing select * from clients by having more than *Many
         * relationship set up as Eager. Having just 1 eager, if you have 10 clients, you are going to have 11 queries: the main query
         * from .createQuery and 10 more to pull the relationship of each client. Having 2 eager, Hibernate will realize
         * you are trying to get too many records from too many sources, and you will get an error:
         * Exception in thread "main" java.lang.IllegalArgumentException: org.hibernate.loader.MultipleBagFetchException: cannot simultaneously fetch multiple bags: [org.srestrepo.hibernateapp.entity.Client.addresses, org.srestrepo.hibernateapp.entity.Client.invoices]
         *
         * Can you optimize how many queries are performed regardless of the FetchType? Yes, by using JPQL in .createQuery.
         * Add distinct and add JUST 1 left outer join fetch at the time for the *Many relationship you are interested on, if the attributes
         * are backed as List, or get the same error above. If you, on the other hand, are using Set, the amount of left outer join fetch will not matter.
         *
         * The good practice if you want to fetch multiple *Many relationships is having 1 optimized JPQL join fetch query per association.
         * 1 optimized query for Addresses
         * 1 optimized query for Invoices, etc.
         */
        em.close();
    }
}
