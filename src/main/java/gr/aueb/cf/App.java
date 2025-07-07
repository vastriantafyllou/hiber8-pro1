package gr.aueb.cf;

import gr.aueb.cf.model.Course;
import gr.aueb.cf.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

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

//        JPQL (Java Persistence Query Language)
        String sql = "SELECT t FROM Teacher t";
        List<Teacher> teachers = em.createQuery(sql, Teacher.class).getResultList();
        teachers.forEach(System.out::println);

        String sql2 = "SELECT c FROM Course c WHERE c.teacher.lastname = :lastname";
        TypedQuery<Course> courseTypedQuery = em.createQuery(sql2, Course.class);
        List<Course> courses = courseTypedQuery.setParameter("lastname", "Ανδρούτσος").getResultList();
        courses.forEach(System.out::println);

        String sql3 = "select t from Teacher t where id = :id";
        TypedQuery<Teacher> typedQuery = em.createQuery(sql3, Teacher.class);
        List<Teacher> teacherList = typedQuery.setParameter("id", 4).getResultList();
        teacherList.forEach(System.out::println);

        String sql4 = "select t from Teacher t JOIN t.courses c where c.title = :title";
        TypedQuery<Teacher> typedQuery1 = em.createQuery(sql4, Teacher.class);
        List<Teacher> teachers1 = typedQuery1.setParameter("title", "SQL").getResultList();
        teachers1.forEach(System.out::println);

        String sql5 = "select t, c from Teacher t Join t.courses c";
        List<Object[]> teachersCourses = em.createQuery(sql5, Object[].class).getResultList();
        for (Object[] objectArr : teachersCourses) {
            System.out.println("Teacher: " + objectArr[0] + "\nCourse: " + objectArr[1]);
        }

        String sql6 = "SELECT c, t FROM Course c JOIN c.teacher t";
        List<Object[]> coursesTeachers = em.createQuery(sql6, Object[].class).getResultList();
        for (Object[] objectsArr : coursesTeachers) {
            System.out.println("Courses: " + objectsArr[0] + "\nTeachers: " + objectsArr[1]);
        }


        em.getTransaction().commit();

        em.close();
        emf.close();

    }
}
