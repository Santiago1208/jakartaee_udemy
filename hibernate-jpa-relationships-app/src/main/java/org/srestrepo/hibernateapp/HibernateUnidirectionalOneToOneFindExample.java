package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.entity.ClientDetail;
import org.srestrepo.hibernateapp.util.JpaUtil;

public class HibernateUnidirectionalOneToOneFindExample {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Client client = em.find(Client.class, 2L);

            ClientDetail detail = new ClientDetail(true, 5000L);
            em.persist(detail);
            client.setDetail(detail);
            em.getTransaction().commit();
            System.out.println(client);
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
