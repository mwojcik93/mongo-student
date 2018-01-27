package pl.wspa.mongostudent.student.get;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.wspa.mongostudent.student.Student;
import pl.wspa.mongostudent.student.StudentRepository;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class GetStudentFromPostgresService {

    private final String POSTGRESQL_URL = "http://localhost:8081/student";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StudentRepository studentRepository;


    @Scheduled(initialDelay = 1000)
    public void saveStudentFromPostgresInMongo(){
        configure();
        List<Student> students = Arrays.asList(restTemplate.getForEntity(POSTGRESQL_URL, Student[].class)
                                                           .getBody());
        studentRepository.save(students);
    }


    private void configure() {
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("mongo",
                                                                             "mongoPassword"));
    }
}
