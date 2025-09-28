package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateCriteriaExample {

    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        System.out.println("============ Get all the clients ============");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);

        Root<Client> from = cq.from(Client.class);
        cq.select(from);
        List<Client> c1 = em.createQuery(cq).getResultList();
        c1.forEach(System.out::println);

        System.out.println("============ Get the client with a specified name ============");
        cq = cb.createQuery(Client.class);
        from = cq.from(Client.class);
        ParameterExpression<String> name = cb.parameter(String.class, "name");
        cq.select(from).where(cb.equal(from.get("name"), name));
        c1 = em.createQuery(cq)
                .setParameter("name", "Andres")
                .getResultList();
        c1.forEach(System.out::println);

        System.out.println("============ Get all clients by using a hint name ============");
        cq = cb.createQuery(Client.class);
        from = cq.from(Client.class);
        ParameterExpression<String> nameHint = cb.parameter(String.class, "hint");
        cq.select(from).where(cb.like(cb.upper(from.get("name")), cb.upper(nameHint)));
        c1 = em.createQuery(cq)
                .setParameter("hint", "%lu%")
                .getResultList();
        c1.forEach(System.out::println);

        System.out.println("============ Get all clients with ID between 2 and 6 ============");
        cq = cb.createQuery(Client.class);
        from = cq.from(Client.class);
        cq.select(from).where(cb.between(from.get("id"), 2L, 6L));
        c1 = em.createQuery(cq).getResultList();
        c1.forEach(System.out::println);

        em.close();
    }
}
