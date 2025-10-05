package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.util.JpaUtil;

public class HibernateLazyJoinFetchOneToManyExample {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        Client client = em.createQuery("select c from Client c left outer join fetch c.addresses left join fetch c.detail where c.id = :id", Client.class)
                .setParameter("id", 1L)
                .getSingleResult();
       /*
        * Output: 1 big query left joining tbl_clients_addresses join address, left joined client_details
        * Hibernate: select c1_0.id,a1_0.id_client,a1_1.id,a1_1.number,a1_1.street,c1_0.created_at,c1_0.updated_at,d1_0.id,d1_0.prime,d1_0.score_total,c1_0.name,c1_0.payment_type,c1_0.surname from clients c1_0 left join (tbl_clients_addresses a1_0 join addresses a1_1 on a1_1.id=a1_0.id_address) on c1_0.id=a1_0.id_client left join client_details d1_0 on c1_0.id=d1_0.id_client where c1_0.id=?
        * getAddresses and getDetail can be called outside the persistence context, after the entity manager closed.
        * */

        System.out.println(client.getAddresses());
       /*
        * Output: No extra query
        * */

        System.out.println(client.getDetail());
       /*
        * Output: No extra query
        * */

        em.close();
    }
}
