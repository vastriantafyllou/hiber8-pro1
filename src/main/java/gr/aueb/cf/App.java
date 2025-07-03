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

//        Teacher teacher = new Teacher(null, "Μάκης", "Καπέτης");
//        Course course = new Course(null, "Databases");
//        teacher.addCourse(course);

        em.getTransaction().begin();

//        Teacher teacher = em.find(Teacher.class,1L);
//        teacher.setFirstname("Αθ.");
//        em.merge(teacher);

//        Course course = em.find(Course.class, 2L);
//        em.remove(course);
//        course.setTeacher(null);
//        Teacher teacher = em.find(Teacher.class, 2L);
//        em.remove(teacher);

//        em.persist(teacher);
//        em.persist(course);
//        teacher.addCourse(course);
        Teacher teacher = em.find(Teacher.class, 3L);
        em.remove(teacher);

//        Course course = em.find(Course.class, 3L);
//        em.remove(course);





        em.getTransaction().commit();

        em.close();
        emf.close();

    }
}
