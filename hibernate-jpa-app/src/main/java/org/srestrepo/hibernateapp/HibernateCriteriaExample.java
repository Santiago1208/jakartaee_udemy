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

        System.out.println("============ Get all concatenated client names and surnames ============");
        cq2 = cb.createQuery(String.class);
        from = cq2.from(Client.class);
        cq2.select(cb.concat(cb.concat(from.get("name"), " "), from.get("surname")));
        l = em.createQuery(cq2).getResultList();
        l.forEach(System.out::println);

        System.out.println("============ Get all concatenated client names and surnames, lowercased ============");
        cq2 = cb.createQuery(String.class);
        from = cq2.from(Client.class);
        cq2.select(cb.lower(cb.concat(cb.concat(from.get("name"), " "), from.get("surname"))));
        l = em.createQuery(cq2).getResultList();
        l.forEach(System.out::println);

        System.out.println("============ Get some of the Client fields (Object[]) ============");
        CriteriaQuery<Object[]> cq3 = cb.createQuery(Object[].class);
        from = cq3.from(Client.class);
        cq3.multiselect(from.get("id"), from.get("name"), from.get("surname"));
        List<Object[]> l2 = em.createQuery(cq3).getResultList();
        l2.forEach(o -> System.out.println("id ="+ o[0] + ", name =" + o[1] + ", surname =" + o[2]));

        System.out.println("============ Get some of the Client fields (Object[]) with Where condition ============");
        cq3 = cb.createQuery(Object[].class);
        from = cq3.from(Client.class);
        cq3.multiselect(from.get("id"), from.get("name"), from.get("surname")).where(cb.like(from.get("name"), "%Lu%"));
        l2 = em.createQuery(cq3).getResultList();
        l2.forEach(o -> System.out.println("id ="+ o[0] + ", name =" + o[1] + ", surname =" + o[2]));

        System.out.println("============ Get some of the Client fields (Object[]), single result ============");
        cq3 = cb.createQuery(Object[].class);
        from = cq3.from(Client.class);
        cq3.multiselect(from.get("id"), from.get("name"), from.get("surname")).where(cb.equal(from.get("id"), 5L));
        Object[] o = em.createQuery(cq3).getSingleResult();
        System.out.println("id ="+ o[0] + ", name =" + o[1] + ", surname =" + o[2]);

        System.out.println("============ Count all clients ============");
        CriteriaQuery<Long> cq4 = cb.createQuery(Long.class);
        from = cq4.from(Client.class);
        cq4.select(cb.count(from.get("id")));
        Long theCount = em.createQuery(cq4).getSingleResult();
        System.out.println(theCount);

        System.out.println("============ Sum all the Client IDs ============");
        cq4 = cb.createQuery(Long.class);
        from = cq4.from(Client.class);
        cq4.select(cb.sum(from.get("id")));
        Long theSum = em.createQuery(cq4).getSingleResult();
        System.out.println(theSum);

        System.out.println("============ Find the maximum ID ============");
        cq4 = cb.createQuery(Long.class);
        from = cq4.from(Client.class);
        cq4.select(cb.max(from.get("id")));
        Long theMax = em.createQuery(cq4).getSingleResult();
        System.out.println(theMax);

        System.out.println("============ Find the minimum ID ============");
        cq4 = cb.createQuery(Long.class);
        from = cq4.from(Client.class);
        cq4.select(cb.min(from.get("id")));
        Long theMin = em.createQuery(cq4).getSingleResult();
        System.out.println(theMin);

        System.out.println("============ Get a full report ============");
        cq3 = cb.createQuery(Object[].class);
        from = cq3.from(Client.class);
        cq3.multiselect(cb.count(from.get("id")), cb.max(from.get("id")), cb.min(from.get("id")), cb.avg(from.get("id")));
        o = em.createQuery(cq3).getSingleResult();
        System.out.println("Count =" + o[0] + ", Max =" + o[1] + ", Min =" + o[2] + ", Avg =" + o[3] );

        em.close();
    }
}
