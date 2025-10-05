package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Student;
import org.srestrepo.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchManyToManyExample {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        List<Student> students = em.createQuery("select distinct s from Student s left outer join fetch s.courses", Student.class)
                .getResultList();
        students.forEach(s -> System.out.println(s.getName() + " , courses: " + s.getCourses()));
        em.close();

        // This behaves and brings up concerns exactly the same as HibernateFetchResultListExample.
    }
}
