import java.util.*;

public class Homepage {
    HashMap<String, HashMap<String, ArrayList<Integer>>> sList = new HashMap<>();
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
            HashMap<String, ArrayList<Integer>> subjectDetails;
            if(sList.containsKey(name)) {
            subjectDetails = sList.get(name);
                addingSubjectDetails(subjectDetails);
            }
            else {
              subjectDetails = new HashMap<>();
            addingSubjectDetails(subjectDetails);
                sList.put(name, subjectDetails);
            }
            addStudentDetails();
            break;
        case 2:
            chooseOption();
            break;
        }
    }
    
    
    public void addingSubjectDetails(HashMap<String, ArrayList<Integer>> subjectDetails) {
         Scanner addStudentsSnr = new Scanner(System.in);
           String exit = "";
        while(!exit.equals("exit")) {
          
            System.out.println("Enter 1 to add subject details or enter (exit) to exit");
            exit= addStudentsSnr.next();
            if(!exit.equals("exit")) {
                System.out.println("Enter the subject name");
                String subjectName = addStudentsSnr.next();
                System.out.println("Enter the subject mark");
                int mark = addStudentsSnr.nextInt();
                if(subjectDetails.containsKey(subjectName)) {
                    ArrayList<Integer> marksArray = subjectDetails.get(subjectName);
                    marksArray.add(mark);
                }
                else {
                ArrayList<Integer> marks = new ArrayList<>();
                marks.add(mark);
                subjectDetails.put(subjectName, marks);
                }
            }
            else {
                break;
            }
          
}
      
    }
    
    public void viewStudentDetails() {
        if(sList.isEmpty()) {
            System.out.println("No students!!!");
            System.out.println("Please add students details");
            chooseOption();
        }
        else {
        int index = 1;
            for (Map.Entry<String, HashMap<String, ArrayList<Integer>>> entry : sList.entrySet()) {
                String studentName = entry.getKey();
                HashMap<String, ArrayList<Integer>> subjectMarks = entry.getValue();
                System.out.println("\n" + index + ". " + "Student Name: " + studentName);
                for (Map.Entry<String, ArrayList<Integer>> subjectDetails : subjectMarks.entrySet()) {
                    String subject = subjectDetails.getKey();
                    ArrayList<Integer> marks = subjectDetails.getValue();
                    System.out.print("Subject: " + subject + " - Mark: ");
                    for (int mark : marks) {
                        System.out.print(mark + " ");
                    }
                    System.out.println(); 
                }
                index++;
            }
           
        }
        }
    
}