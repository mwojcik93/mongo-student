package pl.wspa.mongostudent.student;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.wspa.mongostudent.marks.Mark;

import java.util.ArrayList;
import java.util.List;

@Data
public class Student {

    @Indexed(unique = true)
    private String firstName;
    private String lastName;
    @Id
    private String id;
    private String indexNumber;

    private List<Mark> marks = new ArrayList<>();

}
