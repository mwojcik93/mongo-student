package pl.wspa.mongostudent.marks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wspa.mongostudent.student.Student;
import pl.wspa.mongostudent.student.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userName}/")
public class MarkController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping
    public Student addMarkToStudent(@PathVariable String firstName, @RequestBody Mark mark) {
        Student student = studentRepository.findByFirstName(firstName);
        List<Mark> marks = student.getMarks();
        marks.add(mark);
        student.setMarks(marks);
        return studentRepository.save(student);
    }

}
