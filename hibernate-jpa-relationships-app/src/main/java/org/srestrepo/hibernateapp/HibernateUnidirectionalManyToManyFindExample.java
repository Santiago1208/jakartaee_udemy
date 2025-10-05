package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Course;
import org.srestrepo.hibernateapp.entity.Student;
import org.srestrepo.hibernateapp.util.JpaUtil;

public class HibernateUnidirectionalManyToManyFindExample {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Student s1 = em.find(Student.class, 1L);
            Student s2 = em.find(Student.class, 2L);
            Course c1 = em.find(Course.class, 1L);
            Course c2 = em.find(Course.class, 2L);

            s1.getCourses().add(c1);
            s1.getCourses().add(c2);

            s2.getCourses().add(c1);

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
