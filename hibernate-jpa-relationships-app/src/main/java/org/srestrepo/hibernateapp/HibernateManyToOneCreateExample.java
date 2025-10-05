package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.entity.Invoice;
import org.srestrepo.hibernateapp.util.JpaUtil;

public class HibernateManyToOneCreateExample {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Client client = new Client("Cata", "Edu");
            client.setPaymentType("Credito");
            em.persist(client);

            Invoice invoice = new Invoice("Compras de oficina", 1000L);
            invoice.setClient(client);
            em.persist(invoice);

            System.out.println(invoice);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
