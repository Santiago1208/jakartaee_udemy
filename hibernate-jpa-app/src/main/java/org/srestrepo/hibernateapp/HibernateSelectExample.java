package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateSelectExample {

    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        List<Client> clients = em.createQuery("select c from Client c", Client.class).getResultList();
        clients.forEach(System.out::println);
        em.close();
    }
}
