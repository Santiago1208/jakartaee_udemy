package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchOneToManyCriteriaExample {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> root = cq.from(Client.class);

        root.fetch("addresses", JoinType.LEFT);
        root.fetch("detail", JoinType.LEFT);

        cq.select(root).distinct(true);
        List<Client> clients = em.createQuery(cq).getResultList();
        clients.forEach(c -> System.out.println(c.getName() + ", addresses: " + c.getAddresses() + ", detail: " + c.getDetail()));
        em.close();
    }
}
