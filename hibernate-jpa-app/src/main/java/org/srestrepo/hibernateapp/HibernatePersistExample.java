package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.util.JpaUtil;

import javax.swing.*;

public class HibernatePersistExample {

    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String name = JOptionPane.showInputDialog("Enter a name");
            String surname = JOptionPane.showInputDialog("Enter a surname");
            String paymentType = JOptionPane.showInputDialog("Enter a payment type");

            em.getTransaction().begin();
            Client client = new Client(null, name, surname, paymentType);
            em.persist(client);
            em.getTransaction().commit();

            System.out.println("New ID generated: " + client.getId());
            client = em.find(Client.class, client.getId());
            System.out.println(client);
        } catch  (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
