package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Course;
import org.srestrepo.hibernateapp.entity.Student;
import org.srestrepo.hibernateapp.util.JpaUtil;

public class HibernateUnidirectionalManyToManyExample {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Student s1 = new Student("Cata", "Edu");
            Student s2 = new Student("Jano", "Fernan");
            Course c1 = new Course("Java", "Andrés");
            Course c2 = new Course("Hibernate & JPA", "Andrés");

            s1.getCourses().add(c1);
            s1.getCourses().add(c2);

            s2.getCourses().add(c1);

            em.persist(s1);
            em.persist(s2);
            em.getTransaction().commit();
            System.out.println(s1);
            System.out.println(s2);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
