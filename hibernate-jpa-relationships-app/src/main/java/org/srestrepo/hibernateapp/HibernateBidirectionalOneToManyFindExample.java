package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.entity.Invoice;
import org.srestrepo.hibernateapp.util.JpaUtil;

public class HibernateBidirectionalOneToManyFindExample {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Client client = em.find(Client.class, 1L);
            Invoice i1 = new Invoice("Compras de supermercado", 5000L);
            Invoice i2 = new Invoice("Compras de tecnología", 7000L);

            client.addInvoice(i1)
                  .addInvoice(i2);

            em.getTransaction().commit();
            System.out.println(client);

            em.getTransaction().begin();
            // Invoice i3 = em.find(Invoice.class, 1L); This is option 1

            // This is option 2: you will need to override equals in Invoice
            // since you are creating a new instance and .remove matches the objects being in the same memory space.
            Invoice i3 = new Invoice("Compras de supermercado", 5000L);
            i3.setId(1L);
            client.removeInvoice(i3);
            em.getTransaction().commit();
            System.out.println(client);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
