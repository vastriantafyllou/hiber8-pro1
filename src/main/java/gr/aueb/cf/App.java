package gr.aueb.cf;

import gr.aueb.cf.model.Course;
import gr.aueb.cf.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {

    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("school8PU1");
    private final static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {


        em.getTransaction().begin();

        Teacher teacher = em.find(Teacher.class,1L);
        teacher.setFirstname("Αθ.");
        em.merge(teacher);

        Course course = em.find(Course.class, 1L);
        em.remove(course);
        course.setTeacher(null);
        em.remove(teacher);

        em.persist(teacher);
        em.persist(course);
        teacher.addCourse(course);

        em.remove(course);


        em.getTransaction().commit();

        em.close();
        emf.close();

    }
}
