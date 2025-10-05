package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Address;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.util.JpaUtil;

public class HibernateOneToManyCreateExample {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Client client = new Client("Cata", "Edu");
            client.setPaymentType("Mercado Pago");

            Address a1 = new Address("El Vergel", 123);
            Address a2 = new Address("Vasco de Gama", 456);
            client.getAddresses().add(a1);
            client.getAddresses().add(a2);
            em.persist(client);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
