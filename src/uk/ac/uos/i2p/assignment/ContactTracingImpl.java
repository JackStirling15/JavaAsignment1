package uk.ac.uos.i2p.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContactTracingImpl implements ContactTracing {
    private Map<String, String> studentList;
    private Map<String, String> emailList;
    private Map<String, String> courseList;
    private Map<String, String> studentCourseList;

    public void loadStudentList(Map<String, String> studentEntry){
        studentList = studentEntry;
    }
    public void loadCourseList(Map<String, String> courseEntry){
        courseList = courseEntry;
    }
    public void loadEmailList(Map<String, String> emailEntry){
        emailList = emailEntry;
    }
    public void loadStudentCourseList(Map<String, String> studentCourseEntry){
        studentCourseList = studentCourseEntry;
    }
    public List<String> findMatchingCourses(String inputStudentNumber){
        List<String> selectedCourseList = new ArrayList();
        studentCourseList.forEach((studentID, linkedCourseID)->{
            if (studentID.equals(inputStudentNumber)){
//                courseList.forEach((courseName, id)->{
//                    if (linkedCourseID.equals(id)){
//                        selectedCourseList.add(courseName);
//                    }
//                });
                selectedCourseList.add(linkedCourseID);
            }
        });

        return selectedCourseList;
    }
    public List<String> findMatchingStudents(List<String> matchingCourseList){
        List<String> exposedStudents = new ArrayList();
        matchingCourseList.forEach((course)->{
            studentCourseList.forEach((studentID, courseID)->{
                if (course.equals(courseID)){
                    exposedStudents.add(studentID);
                }
            });
        });
        return exposedStudents;
    }

    public List<String> contactTracing(String inputStudentNumber){
        List<String> emails = new ArrayList();

        List<String> courseList = findMatchingCourses(inputStudentNumber);
        List<String> studentList = findMatchingStudents(courseList);
        studentList.forEach((studentID)-> emails.add(emailList.get(studentID)));
        return emails;
    }
}
