package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.domain.ClientDTO;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateQLExample {

    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        System.out.println("============ Find All ============");
        List<Client> clients = em.createQuery("select c from Client c", Client.class).getResultList();
        clients.forEach(System.out::println);

        System.out.println("============ Find by ID ============");
        Client c1 = em.createQuery("select c from Client c where c.id = :clientId", Client.class)
                .setParameter("clientId", 1L)
                .getSingleResult();
        System.out.println(c1);

        System.out.println("============ Find Name by ID ============");
        String n = em.createQuery("select c.name from Client c where c.id = :clientId", String.class)
                .setParameter("clientId", 1L)
                .getSingleResult();
        System.out.println(n);

        System.out.println("============ Find by ID, custom fields result ============");
        Object[] obj = em.createQuery("select c.id, c.name, c.surname from Client c where c.id = :id", Object[].class)
                .setParameter("id", 1L)
                .getSingleResult();
        Long id = (Long) obj[0];
        String name = (String) obj[1];
        String surname = (String) obj[2];
        System.out.println("id =" + id + ", name =" + name + ", surname =" + surname);

        System.out.println("============ Find All, custom fields result ============");
        List<Object[]> objs = em.createQuery("select c.id, c.name, c.surname from Client c", Object[].class)
                .getResultList();
        for (Object[] o : objs) {
            Long id2 = (Long) o[0];
            String name2 = (String) o[1];
            String surname2 = (String) o[2];
            System.out.println("id =" + id2 + ", name =" + name2 + ", surname =" + surname2);
        }

        System.out.println("============ Find all, client and payment type, Object[] ============");
        List<Object[]> objs2 = em.createQuery("select c, c.paymentType from Client c", Object[].class)
                .getResultList();
        objs2.forEach(o -> {
            Client c =  (Client) o[0];
            String paymentType = (String) o[1];
            System.out.println("Payment type =" + paymentType + ", " + c);
        });

        System.out.println("============ Find all clients, new Entity object ============");
        List<Client> c2 = em.createQuery("select new Client(c.name, c.surname) from Client c", Client.class)
                        .getResultList();
        c2.forEach(System.out::println);

        System.out.println("============ Find all clients, new DTO object ============");
        List<ClientDTO> c3 = em.createQuery("select new org.srestrepo.hibernateapp.domain.ClientDTO(c.name, c.surname) from Client c", ClientDTO.class)
                .getResultList();
        c3.forEach(System.out::println);

        System.out.println("============ Find all client names ============");
        List<String> ns = em.createQuery("select c.name from Client c", String.class)
                        .getResultList();
        ns.forEach(System.out::println);

        System.out.println("============ Find all client names, distinct ============");
        List<String> ns2 = em.createQuery("select distinct c.name from Client c", String.class)
                .getResultList();
        ns2.forEach(System.out::println);

        System.out.println("============ Find all payment types, distinct ============");
        List<String> ps = em.createQuery("select distinct c.paymentType from Client c", String.class)
                .getResultList();
        ps.forEach(System.out::println);

        System.out.println("============ Count distinct payment types ============");
        Long c = em.createQuery("select count(distinct(c.paymentType)) from Client c", Long.class)
                .getSingleResult();
        System.out.println(c);

        em.close();
    }
}
