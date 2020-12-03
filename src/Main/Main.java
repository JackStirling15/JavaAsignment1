package Main;

import uk.ac.uos.i2p.assignment.*;

import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // create object from contact tracing library
        ContactTracing contactTracingInstance = new ContactTracingImpl();
        createData(contactTracingInstance);

        // create user object, and run load functions
        StudentDetails user = new StudentDetails();
        user.loadStudentName();
        user.loadStudentNumber();
        user.loadCovidResult();

        // only run code if the user reports they have covid
        if (user.covidResult){
            System.out.println("User Has COVID - Reporting Results");

            // return info about students in possible contact
            List<String> emailList = contactTracingInstance.contactTracing(user.studentNumber);

            // print results to console and write to file
            user.writeUserDetails();

            // if there are any effected students
            if (emailList.size() != 0) {
                user.writeCovidDetails(emailList);
                System.out.println("Effected Students:");
                System.out.println(emailList);
            } else {
                System.out.println("No Students Were In Contact.");
            }

        } else {
            System.out.println("User Does Not Have COVID: No Action Needed");
        }
    }

    private static void createData(ContactTracing CTinstance){
        // insert data into the ContactTracing Object
        Map<String, String> studentEntry = new HashMap<>();
        studentEntry.put("s101", "clint eastwood");
        studentEntry.put("s102", "jamie foxx");
        studentEntry.put("s103", "olivia wilde");
        studentEntry.put("s104", "test name 1");
        studentEntry.put("s105", "test name 2");
        studentEntry.put("s106", "test name 3");
        CTinstance.loadStudentList(studentEntry);

        Map<String, String> studentEmail = new HashMap<>();
        studentEmail.put("s101", "abc@uos.ac.uk");
        studentEmail.put("s102", "xyz@uos.ac.uk");
        studentEmail.put("s103", "klm@uos.ac.uk");
        studentEmail.put("s104", "test1@uos.ac.uk");
        studentEmail.put("s105", "test2@uos.ac.uk");
        studentEmail.put("s106", "test3@uos.ac.uk");
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
        studentCourseList.put("s104", "se01");
        studentCourseList.put("s105", "se01");
        studentCourseList.put("s106", "ne02");
        CTinstance.loadStudentCourseList(studentCourseList);
    }
}

class StudentDetails {
    // load scanner for reading cli input
    private Scanner scanner = new Scanner(System.in);
    private String studentName;
    String studentNumber;
    boolean covidResult;

    // directory variable, from project root
    String fileDir = System.getProperty("user.dir") + "/src/Main/outputFiles/";


    void loadStudentName(){
        // input name
        System.out.println("Enter Name: ");
        studentName = scanner.nextLine();
    }
    void loadStudentNumber(){
        // input number
        System.out.println("Enter Student Number: ");
        studentNumber = scanner.nextLine();
    }
    void loadCovidResult() {
        // input covid result
        System.out.println("Enter Covid Result: (y/n) ");
        // ensure value is yes to change result value
        if ("y".equals(scanner.nextLine())) {
            covidResult = true;
        }
    }
    void writeUserDetails(){
        // write output to file, catch exceptions
        try {
            System.out.println("Input Positive Result File Name:");
            String fileName = scanner.nextLine();
            while (fileName.equals("")){
                System.out.println("Input valid File Name");
                fileName = scanner.nextLine();
            }
            FileWriter fileWriter = new FileWriter(fileDir + fileName, true);
            BufferedWriter positiveResultFile = new BufferedWriter(fileWriter);
            positiveResultFile.write(studentNumber + " - " + studentName);
            positiveResultFile.newLine();
            positiveResultFile.close();
        } catch (IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    void writeCovidDetails(List<String> emailList){
        // write output to file, catch exceptions
        try {
            System.out.println("Input Contact Traced File Name:");
            String fileName = scanner.nextLine();
            while (fileName.equals("")){
                System.out.println("Input valid File Name");
                fileName = scanner.nextLine();
            }
            FileWriter fileWriter = new FileWriter(fileDir + fileName);
            fileWriter.write(String.join("\n", emailList));
            fileWriter.close();
        } catch (IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
