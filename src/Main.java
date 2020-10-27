import uk.ac.uos.i2p.assignment.*;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ContactTracing CTinstance = new ContactTracingImpl();
        Map<String, String> studentEntry = new HashMap<>();
        studentEntry.put("Jack", "s210887");
        studentEntry.put("Dave", "s5429384");
        CTinstance.loadCourseList(studentEntry);

        Map<String, String> studentEmail = new HashMap<>();

    }
}
