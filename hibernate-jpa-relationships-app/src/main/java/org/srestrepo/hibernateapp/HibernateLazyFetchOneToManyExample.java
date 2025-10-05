package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.util.JpaUtil;

public class HibernateLazyFetchOneToManyExample {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        Client client = em.find(Client.class, 1L);
       /*
        * Output: 1 query from clients LEFT JOIN client_details (default fetch type as Eager)
        * Hibernate: select c1_0.id,c1_0.created_at,c1_0.updated_at,d1_0.id,d1_0.prime,d1_0.score_total,c1_0.name,c1_0.payment_type,c1_0.surname from clients c1_0 left join client_details d1_0 on c1_0.id=d1_0.id_client where c1_0.id=?
        * */

        // System.out.println(client.getAddresses());
       /*
        * Output: 2 queries, 1 from clients LEFT JOIN client_details (eager) and 1 from tbl_client_addresses JOIN addresses (lazy after invoking getter)
        * Hibernate: select c1_0.id,c1_0.created_at,c1_0.updated_at,d1_0.id,d1_0.prime,d1_0.score_total,c1_0.name,c1_0.payment_type,c1_0.surname from clients c1_0 left join client_details d1_0 on c1_0.id=d1_0.id_client where c1_0.id=?
        * Hibernate: select a1_0.id_client,a1_1.id,a1_1.number,a1_1.street from tbl_clients_addresses a1_0 join addresses a1_1 on a1_1.id=a1_0.id_address where a1_0.id_client=?
        * */
        em.close();

        // System.out.println(client.getAddresses());
       /*
        * Output: LazyInitializationException. Can not query the associated objects because there is no JDBC session.
        * Exception in thread "main" org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: org.srestrepo.hibernateapp.entity.Client.addresses: could not initialize proxy - no Session
        * */
    }
}
