import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAMySQL");
        EntityManager em = emf.createEntityManager();

        try {

            Group group1 = new Group(126);
            Group group2 = new Group(228);
            Group group3 = new Group(426);
            Student student;

            em.getTransaction().begin();

            try {
                em.persist(group1);
                em.persist(group2);
                em.persist(group3);

                for (int i = 0; i < 5; i++) {
                    student = new Student("name " + i, "surname " + i, group1);
                    em.persist(student);
                }

                for (int i = 0; i < 7; i++) {
                    student = new Student("name " + i, "surname " + i, group2);
                    em.persist(student);
                }

                for (int i = 0; i < 15; i++) {
                    student = new Student("name " + i, "surname " + i, group3);
                    em.persist(student);
                }

                em.getTransaction().commit();
            } catch (Exception e) {

                em.getTransaction().rollback();
            }

            Query querySt = em.createQuery("FROM Student ", Student.class);

            Query queryGr = em.createQuery("FROM Group");

            List<Student> studentList = querySt.getResultList();
            List<Group> studentGrop = queryGr.getResultList();

            for (Group group : studentGrop) {
                int count = 0;
                Group groupValue = group;

                for (Student studen : studentList) {
                    if (studen.getGroup().equals(groupValue)) {
                        count++;
                    }
                }

                System.out.println("The " + groupValue.getNumber() + " group has " + count + " students");
            }
        } finally {

            em.close();
            emf.close();
        }


    }
}
