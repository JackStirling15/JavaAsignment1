package uk.ac.uos.i2p.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContactTracingImpl implements ContactTracing {
    // create empty attributes
    private Map<String, String> studentList;
    private Map<String, String> emailList;
    private Map<String, String> courseList;
    private Map<String, String> studentCourseList;
    // id of vulnerable students
    private List<String> exposedStudents = new ArrayList();
    // create list to return of courses the student matches
    private List<String> selectedCourseList = new ArrayList();
    private String currentStudentNumber;

    public void loadStudentList(Map<String, String> studentEntry){
        // <id, name>
        studentList = studentEntry;
    }
    public void loadCourseList(Map<String, String> courseEntry){
        // <id, name>
        courseList = courseEntry;
    }
    public void loadEmailList(Map<String, String> emailEntry){
        // <id, email>
        emailList = emailEntry;
    }
    public void loadStudentCourseList(Map<String, String> studentCourseEntry){
        // <studentId, courseId>
        studentCourseList = studentCourseEntry;
    }
    public List<String> findMatchingCourses(String inputStudentNumber){
        this.currentStudentNumber = inputStudentNumber;
        // loop through stored list of courses
        studentCourseList.forEach((studentID, linkedCourseID)->{
            // if selected student matches course, add to the list
            if (studentID.equals(inputStudentNumber)){
                selectedCourseList.add(linkedCourseID);
            }
        });
        return selectedCourseList;
    }
    public List<String> findMatchingStudents(List<String> matchingCourseList){
        // loop through courses and loop through all students assigned to it
        matchingCourseList.forEach((course)-> studentCourseList.forEach((studentID, courseID)->{
            if (course.equals(courseID) && !(studentID.equals(this.currentStudentNumber))){
                // add to list
                exposedStudents.add(studentID);
            }
        }));
        return exposedStudents;
    }
    public List<String> contactTracing(String inputStudentNumber){
        List<String> studentDetails = new ArrayList();
        List<String> courseList = findMatchingCourses(inputStudentNumber);
        List<String> exposedStudentList = findMatchingStudents(courseList);
        // find associate emails for each id
        exposedStudentList.forEach((studentID)-> studentDetails.add(studentID + " " + emailList.get(studentID) + " " + studentList.get(studentID)));
       // exposedStudentList.forEach((studentID)-> studentDetails.add(emailList.get(studentID)));
        return studentDetails;
    }
}
