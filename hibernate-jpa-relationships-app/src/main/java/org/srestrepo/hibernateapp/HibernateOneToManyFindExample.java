package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Address;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.util.JpaUtil;

public class HibernateOneToManyFindExample {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Client client = em.find(Client.class, 2L);

            Address a1 = new Address("El Vergel", 123);
            Address a2 = new Address("Vasco de Gama", 456);
            client.getAddresses().add(a1);
            client.getAddresses().add(a2);
            em.merge(client);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
