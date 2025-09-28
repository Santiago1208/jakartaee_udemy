package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.util.JpaUtil;

import javax.swing.*;

public class HibernateMergeExample {

    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Long id = Long.valueOf(JOptionPane.showInputDialog("Please enter client ID"));
            Client client = em.find(Client.class, id);

            String name = JOptionPane.showInputDialog("Please enter client name", client.getName());
            String surname = JOptionPane.showInputDialog("Please enter client surname" , client.getSurname());
            String paymentType = JOptionPane.showInputDialog("Please enter client payment type", client.getPaymentType());

            em.getTransaction().begin();
            client.setName(name);
            client.setSurname(surname);
            client.setPaymentType(paymentType);
            em.merge(client);
            em.getTransaction().commit();

            System.out.println(client);
        } catch (Exception e) {
            em.getTransaction().begin();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
