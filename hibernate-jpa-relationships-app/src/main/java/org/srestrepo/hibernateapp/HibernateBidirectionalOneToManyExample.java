package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.entity.Invoice;
import org.srestrepo.hibernateapp.util.JpaUtil;

public class HibernateBidirectionalOneToManyExample {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Client client = new Client("Cata", "Edu");
            client.setPaymentType("PayPal");
            Invoice i1 = new Invoice("Compras de supermercado", 5000L);
            Invoice i2 = new Invoice("Compras de tecnología", 7000L);

            client.addInvoice(i1)
                  .addInvoice(i2);

            em.persist(client);
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
