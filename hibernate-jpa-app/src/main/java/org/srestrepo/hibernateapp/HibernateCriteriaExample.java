package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.util.JpaUtil;

import java.util.Arrays;
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

        System.out.println("============ Get all clients with ID in a fixed list ============");
        cq = cb.createQuery(Client.class);
        from = cq.from(Client.class);
        ParameterExpression<List> names = cb.parameter(List.class, "names");
        cq.select(from).where(from.get("name").in(names));
        c1 = em.createQuery(cq)
                .setParameter("names", Arrays.asList("Andres", "John", "Lou"))
                .getResultList();
        c1.forEach(System.out::println);

        System.out.println("============ Get all clients with ID greater and equal than 2  ============");
        cq = cb.createQuery(Client.class);
        from = cq.from(Client.class);
        cq.select(from).where(cb.ge(from.get("id"), 2L));
        c1 = em.createQuery(cq).getResultList();
        c1.forEach(System.out::println);

        System.out.println("============ Get all clients with name length greater than 5 ============");
        cq = cb.createQuery(Client.class);
        from = cq.from(Client.class);
        cq.select(from).where(cb.gt(cb.length(from.get("name")), 5L));
        c1 = em.createQuery(cq).getResultList();
        c1.forEach(System.out::println);

        System.out.println("============ Get all clients with name length less than 5 ============");
        cq = cb.createQuery(Client.class);
        from = cq.from(Client.class);
        cq.select(from).where(cb.lt(cb.length(from.get("name")), 5L));
        c1 = em.createQuery(cq).getResultList();
        c1.forEach(System.out::println);

        System.out.println("============ Get all the clients with ID greater and equal than 3, are named Andres or the Payment Type is Debito ============");
        cq = cb.createQuery(Client.class);
        from = cq.from(Client.class);
        Predicate byName = cb.equal(from.get("name"),  "Andres");
        Predicate byPaymentType = cb.equal(from.get("paymentType"), "Debito");
        Predicate byId = cb.ge(from.get("id"), 3L);
        cq.select(from).where(cb.and(byId, cb.or(byName, byPaymentType)));
        c1 = em.createQuery(cq).getResultList();
        c1.forEach(System.out::println);

        em.close();
    }
}
