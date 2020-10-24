public interface ContactTracing {
    public void loadStudentList(Map<String, String> studentEntry);
    public void loadCourseList(Map<String, String> courseEntry);
    public void loadEmailList(Map<String, String> emailEntry);
    public void loadStudentCourseList(Map<String, String> studentCourseEntry);
    public List<String> findMatchingCourses(String inputStudentNumber);
    public List<String> findMatchingStudents(List<String> matchingCourseList);
    public List<String> contactTracing(String inputStudentNumber);
}

public class ContactTracingFunctions implements ContactTracing {
    public void loadStudentList(Map<String, String> studentEntry){
        String[] loadStudentList = {};
        
    }
    public void loadCourseList(Map<String, String> courseEntry){

    }
    public void loadEmailList(Map<String, String> emailEntry){

    }
    public void loadStudentCourseList(Map<String, String> studentCourseEntry){

    }
    public List<String> findMatchingCourses(String inputStudentNumber){

    }
    public List<String> findMatchingStudents(List<String> matchingCourseList){

    }
    public List<String> contactTracing(String inputStudentNumber){

    }
}
