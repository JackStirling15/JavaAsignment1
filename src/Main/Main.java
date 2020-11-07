package Main;

import uk.ac.uos.i2p.assignment.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // load data
        ContactTracing contactTracingInstance = new ContactTracingImpl();
        createData(contactTracingInstance);

        StudentDetails user = new StudentDetails();
        user.loadStudentName();
        user.loadStudentNumber();
        user.loadCovidResult();

        if (user.covidResult){
            System.out.println("User Has COVID - Reporting Results");
            user.writeUserDetails();
            List<String> emailList = contactTracingInstance.contactTracing(user.studentNumber);
            List<String> exposedStudents =  contactTracingInstance.
        } else {
            System.out.println("User Does Not Have COVID");

        }





    }

    private static void createData(ContactTracing CTinstance){
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
}

class StudentDetails {
    private Scanner scanner = new Scanner(System.in);

    private String studentName;
    String studentNumber;
    boolean covidResult;
    private FileWriter positiveResultFile;
    private FileWriter contactTracedFile;


    void loadStudentName(){
        System.out.println("Enter Name: ");
        studentName = scanner.nextLine();
    }
    void loadStudentNumber(){
        System.out.println("Enter Student Number: ");
        studentNumber = scanner.nextLine();
    }
    void loadCovidResult() {
        System.out.println("Enter Covid Result: (y/n) ");
        if ("y".equals(scanner.nextLine())) {
            covidResult = true;
        }
    }
    void writeUserDetails(){
        try {
            positiveResultFile = new FileWriter("outputFiles/positiveResult.txt");
            positiveResultFile.write(studentNumber + "-" + studentName);
        } catch (IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
