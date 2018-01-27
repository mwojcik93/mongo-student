package pl.wspa.mongostudent.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wspa.mongostudent.marks.Mark;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity<List<Mark>> getAllMarks(@PathVariable String studentName) {
        List<Mark> marks = studentRepository.findByFirstName(studentName)
                                            .getMarks();

        return marks.isEmpty() ?
               ResponseEntity.noContent()
                             .build() :
               ResponseEntity.ok(marks);
    }

    @GetMapping("/{studentName}/{markId}")
    public ResponseEntity<Mark> getMarkById(@PathVariable String studentName, @PathVariable int markId) {
        try {
            return ResponseEntity.ok(studentRepository.findByFirstName(studentName)
                                                      .getMarks()
                                                      .get(markId));
        } catch (IndexOutOfBoundsException ex) {
            return ResponseEntity.noContent()
                                 .build();
        }
    }

    @PostMapping
    public Mark addMark(@PathVariable String studentName, @RequestBody Mark mark) {
        Student student = studentRepository.findByFirstName(studentName);
        List<Mark> marks = student.getMarks();
        marks.add(mark);
        student.setMarks(marks);
        studentRepository.save(student);
        return mark;
    }

    @PutMapping("/{markId}")
    public Mark updateMark(@PathVariable String userName, @PathVariable int receiptId, @RequestBody Mark mark) {
        Student student = studentRepository.findByFirstName(userName);
        List<Mark> marks = student.getMarks();
        marks.get(receiptId)
             .setShopName(mark.getShopName());
        student.setMarks(marks);
        studentRepository.save(student);
        return marks.get(receiptId);
    }

    @DeleteMapping
    public ResponseEntity<List<Mark>> removeMark(@PathVariable String studentName, @RequestBody Mark mark) {
        Student student = studentRepository.findByFirstName(studentName);
        List<Mark> marks = student.getMarks();

        if (marks.remove(mark)) {
            student.setMarks(marks);
            studentRepository.save(student);

            return ResponseEntity.ok(marks);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                             .body(marks);
    }

}
