package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.entity.Invoice;
import org.srestrepo.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchManyToOneCriteriaExample {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Invoice> cq = cb.createQuery(Invoice.class);
        Root<Invoice> root = cq.from(Invoice.class);

        Join<Invoice, Client> client = (Join) root.fetch("client", JoinType.LEFT);
        client.fetch("detail", JoinType.LEFT);

        cq.select(root).where(cb.equal(client.get("id"), 1L));
        List<Invoice> invoices = em.createQuery(cq).getResultList();
        invoices.forEach(i -> System.out.println(i.getDescription() + ", client: " + i.getClient().getName()));

        em.close();
    }
}
