package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.util.JpaUtil;

import java.util.Scanner;

public class HibernateRemoveExample {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the client you want to remove");
        Long id = scanner.nextLong();
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Client client = em.find(Client.class, id);
            em.getTransaction().begin();
            em.remove(client);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
