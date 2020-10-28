import uk.ac.uos.i2p.assignment.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ContactTracing CTinstance = new ContactTracingImpl();
        Map<String, String> studentEntry = new HashMap<>();
        studentEntry.put("clint eastwood", "s101");
        studentEntry.put("jamie foxx", "s102");
        studentEntry.put("olivia wilde", "s103");
        CTinstance.loadCourseList(studentEntry);

        Map<String, String> studentEmail = new HashMap<>();
        studentEmail.put("s101", "abc@uos.ac.uk");
        studentEmail.put("s102", "xyz@uos.ac.uk");
        studentEmail.put("s103", "klm@uos.ac.uk");
        CTinstance.loadEmailList(studentEmail);

        Map<String, String> courseList = new HashMap<>();
        courseList.put("software", "se01");
        courseList.put("networks", "ne02");
        courseList.put("security", "cs03");
        CTinstance.loadCourseList(courseList);

        Map<String, String> studentCourseList = new HashMap<>();
        studentCourseList.put("s101", "se01");
        studentCourseList.put("s102", "ne02");
        studentCourseList.put("s103", "se01");
        CTinstance.loadStudentCourseList(studentCourseList);

        List<String> exposedStudents = CTinstance.contactTracing("s103");
        System.out.println(exposedStudents);

    }
}
