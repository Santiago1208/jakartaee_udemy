package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.util.JpaUtil;

import java.util.Scanner;

public class HibernateSelectWhereExample {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery("select c from Client c where c.paymentType = ?1", Client.class);
        System.out.println("Type in a Payment Type: ");
        String paymentType = scanner.next();
        query.setParameter(1, paymentType);
        Client client = (Client) query.getSingleResult();
        System.out.println(client);
        em.close();
    }
}
