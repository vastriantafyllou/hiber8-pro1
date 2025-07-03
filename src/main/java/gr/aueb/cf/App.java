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

//        em.find(teacher.getClass(),2);
//        em.remove(teacher);
//        Course course = em.find(Course.class, 2L);
//        em.remove(course);
//        course.setTeacher(null);
        Teacher teacher = em.find(Teacher.class, 2L);
        em.remove(teacher);




        em.getTransaction().commit();

        em.close();
        emf.close();

    }
}
