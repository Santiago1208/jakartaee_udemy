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

        System.out.println("============ Get all the clients ordered by name asc and surname desc ============");
        cq = cb.createQuery(Client.class);
        from = cq.from(Client.class);
        cq.select(from).orderBy(cb.asc(from.get("name")), cb.desc(from.get("surname")));
        c1 = em.createQuery(cq).getResultList();
        c1.forEach(System.out::println);

        System.out.println("============ Find single client by ID ============");
        cq = cb.createQuery(Client.class);
        from = cq.from(Client.class);
        ParameterExpression<Long> id = cb.parameter(Long.class, "id");
        cq.select(from).where(cb.equal(from.get("id"), id));
        Client cl = em.createQuery(cq)
                .setParameter("id", 1L)
                .getSingleResult();
        System.out.println(cl);

        System.out.println("============ Get all client names ============");
        CriteriaQuery<String> cq2 = cb.createQuery(String.class);
        from = cq2.from(Client.class);
        cq2.select(from.get("name"));
        List<String> l = em.createQuery(cq2).getResultList();
        l.forEach(System.out::println);

        System.out.println("============ Get all distinct client names, uppercased ============");
        cq2 = cb.createQuery(String.class);
        from = cq2.from(Client.class);
        cq2.select(cb.upper(from.get("name"))).distinct(true);
        l = em.createQuery(cq2).getResultList();
        l.forEach(System.out::println);

        em.close();
    }
}
