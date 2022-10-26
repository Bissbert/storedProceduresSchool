package ch.bissbert.storedprocedures;

import ch.bissbert.storedprocedures.data.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;

@RestController
public class SQLRequests {

    @Autowired
    private StudentRepo studentRepo;


    /**
     * Select a single student from a table
     * @param id the id of the student
     * @return the student
     */
    @GetMapping("/student")
    public Student getStudent(int id) {
        return studentRepo.getStudentPlain(id);
    }

    /**
     * Select a single student from a table with a prepared statement
     * @param name the name of the student
     * @return the student
     */
    @GetMapping("/studentPrepared")
    public Student getStudentPrepared(String name) {
        return studentRepo.getStudentPrepared(name);
    }

    /**
     * Select a single student from a table with a stored procedure
     * @param id the id of the student
     * @return the student
     */
    @GetMapping("/studentStoredProcedure")
    public Student getStudentStoredProcedure(int id) {
        return studentRepo.getStudentStoredProcedure(id);
    }
}
