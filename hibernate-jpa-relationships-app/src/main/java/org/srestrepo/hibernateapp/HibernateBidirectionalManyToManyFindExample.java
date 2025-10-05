package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Course;
import org.srestrepo.hibernateapp.entity.Student;
import org.srestrepo.hibernateapp.util.JpaUtil;

public class HibernateBidirectionalManyToManyFindExample {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Student s1 = em.find(Student.class, 1L);
            Student s2 = em.find(Student.class, 2L);
            Course c1 = em.find(Course.class, 1L);
            Course c2 = em.find(Course.class, 2L);

            s1.addCourse(c1);
            s1.addCourse(c2);

            s2.addCourse(c1);

            em.getTransaction().commit();
            System.out.println(s1);
            System.out.println(s2);

            em.getTransaction().begin();
            // Course c3 = em.find(Course.class, 2L); Option 1, without equals

            // Option 2, with equals
            Course c3 = new Course();
            c3.setId(2L);
            s1.removeCourse(c3);
            em.getTransaction().commit();
            System.out.println(s1);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
