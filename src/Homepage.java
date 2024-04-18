import java.util.*;

public class Homepage {
    ArrayList<HashMap<String, HashMap<String, Integer>>> studentList = new ArrayList<>();
    
    public static void main(String[] args) {
        Homepage homepage = new Homepage();
        homepage.chooseOption();
    }  
    
    public void chooseOption() {
        Scanner homePageSnr = new Scanner(System.in);
        System.out.println("\n" +
                "╔═══════════════════════════════════════╗\n" +
                "║                                       ║\n" +
                "║   Add student details   - Press (1)   ║\n" +
                "║                                       ║\n" +
                "║   View student details  - Press (2)   ║\n" +
                "║                                       ║\n" +
                "║   Update student mark   - Press (3)   ║\n" +
                "║                                       ║\n" +
                "║   Add subject           - Press (4)   ║\n" +
                "║                                       ║\n" +
                "║   Delete student        - Press (5)   ║\n" +
                "║                                       ║\n" +
                "╚═══════════════════════════════════════╝\n\n");

        int option = homePageSnr.nextInt();

        switch (option) {
            case 1:
                addStudentDetails();
                break;
            case 2:
                viewStudentDetails();
                chooseOption();
                break;
            case 3:
                chooseStudentToUpdate();
                break;
            case 4:
            	chooseStudentToAddSubject();
            case 5:
                chooseStudentToDelete();
                break;
        }
    }
            
    public void addStudentDetails() {
        Scanner addStudentsSnr = new Scanner(System.in);
        System.out.println("\n" +
                "╔═══════════════════════════════════════╗\n" +
                "║                                       ║\n" +
                "║   Add student           - Press (1)   ║\n" +
                "║                                       ║\n" +
                "║   Exit                  - Press (2)   ║\n" +
                "║                                       ║\n" +
                "╚═══════════════════════════════════════╝\n\n");

        int option = addStudentsSnr.nextInt();
     
        switch(option) {
        case 1:
            System.out.println("Enter the student name");
            String name = addStudentsSnr.next();
            String exit = "";
              HashMap<String, HashMap<String, Integer>> student = new HashMap<>();
              HashMap<String, Integer> studentDetails = new HashMap<>();
                    while(!exit.equals("exit")) {
                        System.out.println("Enter 1 to add subject details or enter (exit) to exit");
                        exit= addStudentsSnr.next();
                        if(!exit.equals("exit")) {
                            System.out.println("Enter the subject name");
                            String subjectName = addStudentsSnr.next();
                            System.out.println("Enter the subject mark");
                            int mark = addStudentsSnr.nextInt();
                            studentDetails.put(subjectName, mark);
                        }
                        else {
                            break;
                        }
                    student.put(name, studentDetails);
            }
            studentList.add(student);
            addStudentDetails();
            break;
        case 2:
            chooseOption();
            break;
        }
    }
    
    public void viewStudentDetails() {
    	if(studentList.isEmpty()) {
    		System.out.println("No students!!!");
    		System.out.println("Please add students details");
    		chooseOption();
    	}
    	else {
        int index = 1;
        for (HashMap<String, HashMap<String, Integer>> student : studentList) {
            for (Map.Entry<String, HashMap<String, Integer>> entry : student.entrySet()) {
                String studentName = entry.getKey();
                HashMap<String, Integer> subjectMarks = entry.getValue();
                System.out.println("\n" + index + ". " + "Student Name: " + studentName);
                for (Map.Entry<String, Integer> subjectDetails : subjectMarks.entrySet()) {
                    String subject = subjectDetails.getKey();
                    int mark = subjectDetails.getValue();
                    System.out.println("Subject: " + subject + " - Mark: " + mark);
                }
            }
            index++;
        }
    	}
    
    }

    public void chooseStudentToUpdate() {
        viewStudentDetails();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the index of the student whose mark you want to update:");
        int studentIndex = scanner.nextInt();
        updateStudentMark(studentIndex);
    }

    public void updateStudentMark(int studentIndex) {
        HashMap<String, HashMap<String, Integer>> student = studentList.get(studentIndex-1);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the subject name:");
        String subjectToUpdate = scanner.next();
        HashMap<String, Integer> subjectDetails = student.get(student.keySet().iterator().next());
        if (!subjectDetails.containsKey(subjectToUpdate)) {
            System.out.println("Subject not found for the selected student. Mark not updated.");
            chooseOption();
            return;
        }
        System.out.println("Enter the new mark:");
        int newMark = scanner.nextInt();
        subjectDetails.put(subjectToUpdate, newMark);
        System.out.println("Mark updated successfully!");
        chooseOption();
    }

    public void chooseStudentToDelete() {
    	if(studentList.isEmpty()) {
    		System.out.println("No students!!!");
    		System.out.println("Please add students details");
    		chooseOption();
    	}
    	else {
        viewStudentDetails();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the index of the student you want to delete:");
        int studentIndex = scanner.nextInt();
        deleteStudent(studentIndex);
    	}
    }

    public void deleteStudent(int studentIndex) {
        studentList.remove(studentIndex - 1);
        System.out.println("Student deleted successfully!");
        chooseOption();
    }
    
    public void  chooseStudentToAddSubject() {
    	  viewStudentDetails();
          Scanner scanner = new Scanner(System.in);
          System.out.println("Enter the index of the student that you want to add more subject:");
          int studentIndex = scanner.nextInt();
          addSubject(studentIndex);
    }
    
    public void addSubject(int studentIndex) {
    	 HashMap<String, HashMap<String, Integer>> student = studentList.get(studentIndex-1);
         Scanner addSubjectSnr = new Scanner(System.in);
         System.out.println("Enter the subject name to add:");
         String subjectToAdd = addSubjectSnr.next();
         System.out.println("Enter the mark");
         int markToAdd = addSubjectSnr.nextInt();
         HashMap<String, Integer> subjectDetails = student.get(student.keySet().iterator().next());
         subjectDetails.put(subjectToAdd, markToAdd);
         System.out.println("Subject added successfully");
         chooseOption();
    }
}
