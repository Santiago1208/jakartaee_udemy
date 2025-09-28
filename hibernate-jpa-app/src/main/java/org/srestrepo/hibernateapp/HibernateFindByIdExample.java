package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.util.JpaUtil;

import java.util.Scanner;

public class HibernateFindByIdExample {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = JpaUtil.getEntityManager();

        System.out.println("Type in an ID: ");
        Long id = scanner.nextLong();
        Client client = em.find(Client.class, id);
        System.out.println(client);

        em.close();
    }
}
