package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.util.JpaUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HibernateCriteriaDynamicQueryExample {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name to filter:");
        String nameHint =  scanner.nextLine();

        System.out.println("Enter the surname to filter:");
        String surnameHint =  scanner.nextLine();

        System.out.println("Enter the payment type to filter:");
        String paymentTypeHint =  scanner.nextLine();

        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> from = cq.from(Client.class);
        List<Predicate> predicates = new ArrayList<>();

        if (nameHint != null && !nameHint.isBlank()) {
            predicates.add(cb.equal(from.get("name"), nameHint));
        }
        if (surnameHint != null && !surnameHint.isBlank()) {
            predicates.add(cb.equal(from.get("surname"), surnameHint));
        }
        if (paymentTypeHint != null && !paymentTypeHint.isBlank()) {
            predicates.add(cb.equal(from.get("paymentType"), paymentTypeHint));
        }

        cq.select(from).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        List<Client> clients = em.createQuery(cq).getResultList();
        clients.forEach(System.out::println);

        em.close();
    }
}
