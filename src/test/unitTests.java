package test;
import org.junit.*;
import uk.ac.uos.i2p.assignment.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class unitTests {
    public void loadData(ContactTracing CTinstance){
        Map<String, String> studentEntry = new HashMap<>();
        studentEntry.put("s101", "clint eastwood");
        studentEntry.put("s102", "jamie foxx");
        studentEntry.put("s103", "olivia wilde");
        CTinstance.loadStudentList(studentEntry);

        Map<String, String> studentEmail = new HashMap<>();
        studentEmail.put("s101", "abc@uos.ac.uk");
        studentEmail.put("s102", "xyz@uos.ac.uk");
        studentEmail.put("s103", "klm@uos.ac.uk");
        CTinstance.loadEmailList(studentEmail);

        Map<String, String> courseList = new HashMap<>();
        courseList.put("se01", "software");
        courseList.put("ne02", "networks");
        courseList.put("cs03", "security");
        CTinstance.loadCourseList(courseList);

        Map<String, String> studentCourseList = new HashMap<>();
        studentCourseList.put("s101", "se01");
        studentCourseList.put("s102", "ne02");
        studentCourseList.put("s103", "se01");
        CTinstance.loadStudentCourseList(studentCourseList);
    }

    @Test
    public void testReturnEmails_1(){
        ContactTracing CTinstance = new ContactTracingImpl();
        loadData(CTinstance);
        List<String> exposedStudents = CTinstance.contactTracing("s101");
        System.out.println(exposedStudents);
        Assert.assertEquals(1, exposedStudents.size());
    }

    @Test
    public void testReturnEmails_2(){
        ContactTracing CTinstance = new ContactTracingImpl();
        loadData(CTinstance);
        List<String> exposedStudents = CTinstance.contactTracing("s102");
        System.out.println(exposedStudents);
        Assert.assertEquals(0, exposedStudents.size());
    }

    @Test
    public void testReturnEmails_3(){
        ContactTracing CTinstance = new ContactTracingImpl();
        loadData(CTinstance);
        List<String> exposedStudents = CTinstance.contactTracing("s103");
        System.out.println(exposedStudents);
        Assert.assertEquals(1, exposedStudents.size());
    }

    @Test
    public void testFindMatchingCourses(){
        ContactTracing CTinstance = new ContactTracingImpl();
        loadData(CTinstance);
        List<String> courses = CTinstance.findMatchingCourses("s103");
        System.out.println(courses);
        Assert.assertEquals(courses.get(0), "se01");
    }

    @Test
    public void testFindMatchingCoursesFail(){
        ContactTracing CTinstance = new ContactTracingImpl();
        loadData(CTinstance);
        List<String> courses = CTinstance.findMatchingCourses("s103sdfas");
        System.out.println(courses);
        Assert.assertEquals(courses.size(), 0);
    }

    @Test
    public void testLoadStudents(){
        ContactTracing CTinstance = new ContactTracingImpl();
        Map<String, String> studentEntry = new HashMap<>();
        studentEntry.put("s101", "clint eastwood");
        studentEntry.put("s102", "jamie foxx");
        studentEntry.put("s103", "olivia wilde");
        CTinstance.loadStudentList(studentEntry);
    }

    @Test
    public void testLoadEmails(){
        ContactTracing CTinstance = new ContactTracingImpl();
        Map<String, String> studentEmail = new HashMap<>();
        studentEmail.put("s101", "abc@uos.ac.uk");
        studentEmail.put("s102", "xyz@uos.ac.uk");
        studentEmail.put("s103", "klm@uos.ac.uk");
        CTinstance.loadEmailList(studentEmail);
    }

    @Test
    public void testLoadCourse(){
        ContactTracing CTinstance = new ContactTracingImpl();
        Map<String, String> studentEmail = new HashMap<>();
        studentEmail.put("s101", "abc@uos.ac.uk");
        studentEmail.put("s102", "xyz@uos.ac.uk");
        studentEmail.put("s103", "klm@uos.ac.uk");
        CTinstance.loadEmailList(studentEmail);
    }

    @Test
    public void testLoadStudentCourseList(){
        ContactTracing CTinstance = new ContactTracingImpl();
        Map<String, String> studentCourseList = new HashMap<>();
        studentCourseList.put("s101", "se01");
        studentCourseList.put("s102", "ne02");
        studentCourseList.put("s103", "se01");
        CTinstance.loadStudentCourseList(studentCourseList);
    }


}
