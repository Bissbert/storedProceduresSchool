package ch.bissbert.storedprocedures;

import ch.bissbert.storedprocedures.data.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class StudentRepo {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Select a single student from a table with plain SQL query by id
     *
     * @param id the id of the student
     * @return the student
     */
    public Student getStudentPlain(int id) {
        Student student = null;
        Query query = entityManager.createQuery("SELECT * FROM students WHERE idstudents = " + id);
        student = (Student) query.getSingleResult();
        return student;
    }

    /**
     * Select a single student from a table with a prepared statement by id
     *
     * @param name the name of the student
     * @return the student
     */
    public Student getStudentPrepared(String name) {
        Student student = null;
        Query query = entityManager.createNativeQuery("SELECT * FROM students WHERE name = ?", Student.class);
        query.setParameter(1, name);
        student = (Student) query.getSingleResult();
        return student;
    }

    /**
     * Select a single student from a table with a stored procedure by id
     *
     * @param id the id of the student
     * @return the student
     */
    public Student getStudentStoredProcedure(int id) {
        Student student = null;
        Query query = entityManager.createStoredProcedureQuery("CALL getStudentById(?)", Student.class);
        query.setParameter(1, id);
        student = (Student) query.getSingleResult();
        return student;
    }
}
