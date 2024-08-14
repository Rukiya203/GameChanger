import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManagementSystem {
    /**
     * Represents the total number of available seats in the system.
     * Represents the count of students who have scored greater than 40 marks in Module 1.
     * Represents the count of students who have scored greater than 40 marks in Module 2.
     * Represents the count of students who have scored greater than 40 marks in Module 3.
     */
    public static int global_available_seats = 100;
    public static int module1_greater_than_40_marks  =0;
    public static int  module2_greater_than_40_marks=0;
    public static int  module3_greater_than_40_marks=0;

    /**
     * An array to store the Student objects, initialized with the size of the total available seats.
     */

    public static Student[] students = new Student[global_available_seats];

    /**
     * The main method that serves as the entry point of the Student Management System application.
     *
     * @param args command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        menu();
    }

    /**
     * Displays the menu for the Student Management System and handles user input to navigate through different options.
     * The menu provides options to check available seats, register a student, delete a student, find a student,
     * store student data into a file, load student data from a file, view the list of students based on their names,
     * generate reports, and exit the system.
     */

    public static void menu() {
        do {
            System.out.println("\n============= Student Management System Menu =============");
            System.out.println("1. Check available seats");
            System.out.println("2. Register Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Find Student");
            System.out.println("5. Store student data into file");
            System.out.println("6. Load student data from file");
            System.out.println("7. View the list of students based on their names");
            System.out.println("8.Additional controls ");
            System.out.println("9.Exit");

            int choice = choice_num("Enter the choice");
            switch (choice) {
                case 1:
                    System.out.println("Available seats: " + global_available_seats);
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    studentDelete();
                    break;
                case 4:
                    showStudent();
                    break;
                case 5:
                    fileWrite();
                    break;
                case 6:
                    readFile();
                    break;
                case 7:
                    nameSort();
                    break;
                case 8:
                    reportGenerator();
                    break;
                case 9:
                    System.out.println("System Terminating.....");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 9.");
                    break;
            }

        }
        while (true);
    }

    /**
     * Registers a new student by getting user details such as name, ID, and marks, and adds the student to the main student array.
     * If there are no available seats, it informs the user.
     * It also updates the count of students with marks greater than or equal to 40 for each module.
     */
    public static void register() {
        //Getting user details Name,ID and marks and add it into the main student array.
        if(global_available_seats==0){
            System.out.println("No Spaces Exits");
        }else {
            String name = input_for_name("Enter the Student Name ");

            String studentId = input_for_id_register("Enter the  ID(eg:w2082103) :");
            double [] marks = new double[3];
            for(int h=1;h<4;h++){

                marks[h-1] = marks_validate("Enter the Module "+ h +" mark");
            }
            Module module = new Module(marks[0], marks[1], marks[2]);
            module1_greater_than_40_marks += (marks[0] >= 40) ? 1 : 0;//Used territory operator instead of IF-ELSE
            module2_greater_than_40_marks += (marks[1] >= 40) ? 1 : 0;
            module3_greater_than_40_marks += (marks[2] >= 40) ? 1 : 0;

            Student student = new Student(studentId, name, module); //Construct Student class
            students[100 - global_available_seats] = student;

            global_available_seats--;
            System.out.println("Added  Successfully");



            System.out.println("Remaining seats: " + global_available_seats);
        }
    }

    /**
     * Deletes a student with the given ID.
     * If no students are available to delete, it informs the user.
     * Shifts the elements in the student array to maintain order after deletion.
     */
    public static void studentDelete() {
        boolean found = false;
        if (global_available_seats == 100) {
            System.out.println("No any student record found to delete ");
        } else {
            String value2 = id_validate("Enter the ID (eg:w2082103) : "); // Getting user id to delete
            for (int num = 0; num < 100 - global_available_seats; num++) {
                if (students[num] != null && students[num].getStuid().contains(value2)) {
                    // Shift the elements to the left to remove the null entry
                    for (int g = num; g < 99 - global_available_seats; g++) {
                        students[g] = students[g + 1];
                    }
                    students[99 - global_available_seats] = null; // Clear the last element
                    global_available_seats++;

                    System.out.println("Student removed successfully.");
                    System.out.println("Remaining seats after deletion: " + global_available_seats);
                    found = true;
                    break;
                }
            }
            if (!found)  {
                System.out.println("No such a student found");
            }
        }
    }
    /**
     * Finds and displays the details of a student by their ID.
     * If no students are available or the student with the given ID is not found, it informs the user.
     */
    public static void showStudent() {


        boolean catched = false;
        if(global_available_seats==100){
            System.out.println("No any student record found ");


        }else {
            String value2 = id_validate("Enter the ID(ex: w3456785 : )");
            for (Student student : students) {
                //
                if ((student != null && student.getStuid().equals(value2))) {
                    System.out.println("Student ID: " + student.getStuid());
                    System.out.println("Student Name: " + student.getName());
                    System.out.println("Student Module 1 Mark: " + student.getModule().getS1());
                    System.out.println("Student Module 2 Mark: " + student.getModule().getS2());
                    System.out.println("Student Module 3 Mark: " + student.getModule().getS3());
                    System.out.printf("Module avg: %.3f%n", student.getModule().getAvg());
                    System.out.println("Student  Module Grade : " + student.getModule().getGrade());
                    catched = true;
                    break;
                }


            }
            if(!catched){


                System.out.println("No such a student found");
            }
        }
    }

    public static void fileWrite(){
        //Used print writer to write the dats to  file with comma
        if(global_available_seats==100){
            System.out.println("No any student record found to write");


        }else {

            try (PrintWriter writer = new PrintWriter(new FileWriter("student.txt"))) {

                for (int k = 0; k < 100 - global_available_seats; k++) {
                    writer.println(students[k].getStuid() + ", " + students[k].getName() + ", " + students[k].getModule().getS1() + ", " + students[k].getModule().getS2() + ", " + students[k].getModule().getS3() + ", " + students[k].getModule().getAvg() + ", " + students[k].getModule().getGrade());

                }
                System.out.println("Data Written Successfully");

            }catch (IOException e) {
                System.out.println("An I/O error occurred: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }




    }
    /**
     * Writes the student data to a file named "student.txt".
     * If no student records are available, it informs the user.
     * Uses PrintWriter to write data in a comma-separated format.
     */

    public static void readFile()  {
        //Read file data
        try (Scanner scanner = new Scanner(new File("student.txt"))) {
            int s_index = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); // Update line within the loop
                String[] items = line.split(", ");//split by comma and separate into parts


                if (items.length == 7) {
                    String studentId = items[0];
                    String studentName = items[1];
                    double student_marks1 = Double.parseDouble(items[2]);
                    double student_marks2 = Double.parseDouble(items[3]);
                    double student_marks3 = Double.parseDouble(items[4]);
                    module1_greater_than_40_marks += (student_marks1 >= 40) ? 1 : 0;//Used ternitory operator instead of IF-ELSE
                    module2_greater_than_40_marks += (student_marks2 >= 40) ? 1 : 0;
                    module3_greater_than_40_marks += (student_marks3 >= 40) ? 1 : 0;
                    Module module = new Module(student_marks1,student_marks2,student_marks3);//Add  data to Student array which wriiten in file
                    students[s_index] = new Student(studentId, studentName,module);
                    s_index++;
                    global_available_seats = 100 - s_index;
                }
            }
            System.out.println("Existing Data Read Successfully");

        }catch(Exception Exception) {
            System.out.println("Error Occurred");

        }
    }
    /**
     * Sorts the students by their names in alphabetical order.
     * If no student records are available, it informs the user.
     * Uses a simple bubble sort algorithm to sort the students.
     * Prints the sorted student names.
     */

    public static void nameSort() {
        // Check if there are no student records
        if(global_available_seats == 100) {
            System.out.println("No any student record found to show ");
        } else {
            // Loop through the array for sorting
            for (int value = 0; value < (100 - global_available_seats) - 1; value++) {
                // Nested loop for comparison and swapping
                for (int num = 0; num < 100 - global_available_seats - value - 1; num++) {
                    // Ensure non-null entries before comparison
                    if (students[num] != null && students[num + 1] != null) {

                        if (students[num].getName().compareTo(students[num + 1].getName()) > 0) {
                            // Swap students if they are out of order
                            Student var = students[num];
                            students[num] = students[num + 1];
                            students[num + 1] = var;
                        }
                    } else {

                        break;
                    }
                }
            }
            // Print sorted student names
            for (int h = 0; h < 100 - global_available_seats; h++) {
                System.out.println(students[h].getName());
            }
        }
    }
    public static String input_for_name(String message){
        //validate method for student name
        Scanner input = new Scanner(System.in);
        String name;
        while(true) {


            System.out.println(message);
            name = input.nextLine().toUpperCase();
            if (!name.matches("[A-Z]*$")) { //validated cannot enter number or characters
                System.out.println("Wrong Input try again ");
            } else {

                break;
            }
        }
        return name;
    }
    /**
     * Prompts the user to input a valid student name.
     * Validates that the input contains only uppercase alphabetic characters.
     *
     * @param message The message to display prompting for input.
     * @return The validated student name input in uppercase.
     */

    public static int choice_num(String message) {
        //validate method for user choice
        Scanner input = new Scanner(System.in);
        int choice_number;
        while (true) {
            try {

                System.out.println(message);
                choice_number = input.nextInt();
                input.nextLine();
                return choice_number;


            } catch (InputMismatchException e) {
                System.out.println("Incorrect input , Try again.  ");
                input.nextLine();


            }

        }
    }

    public static double marks_validate(String message){
        //validate module marks
        Scanner input = new Scanner(System.in);
        double  marks_num;
        while(true) {
            try {

                System.out.println(message);
                marks_num = input.nextDouble();
                if( marks_num>100 || marks_num<0){
                    System.out.println("Incorrect input  marks should be  in range 100 and 0");
                }
                else {
                    input.nextLine();
                    return marks_num;
                }

            } catch (InputMismatchException e) {
                System.out.println("Incorrect input , Strings are not allowed  ");
                input.nextLine();
            }

        }

    }
    /**
     * Prompts the user to input and validate module marks.
     * Validates that the input is a valid double within the range of 0 to 100.
     *
     * @param message The message to display prompting for input.
     * @return The validated module mark as a double.
     */
    public static String id_validate(String message){
        //validate student id for other methods except register method
        Scanner input = new Scanner(System.in);
        String stuid;
        while(true) {
            try {

                System.out.println(message);
                stuid = input.nextLine().toLowerCase();


                if(stuid.length() != 8 || (stuid.charAt(0) !='w')){
                    System.out.println("ID must contain 8 characters and first letter should be starting with \"w\" ");

                }else{
                    return stuid;


                }

            } catch (InputMismatchException e) {
                System.out.println("Incorrect input , try again  ");


            }

        }
    }
    /**
     * Prompts the user to input and validate a student ID for registration.
     * Validates that the ID is unique, starts with 'w', and is exactly 8 characters long.
     *
     * @param message The message to display prompting for input.
     * @return The validated student ID as a String.
     */
    public static String input_for_id_register(String message){
        //validated student id repeating
        Scanner input = new Scanner(System.in);
        String stu_id;
        while(true) {
            boolean idExists = false;
            try {

                System.out.println(message);
                stu_id = input.nextLine().toLowerCase();
                for (Student student : students) {
                    if (student != null && student.getStuid().equals(stu_id)) {
                        System.out.println("Cannot proceed , ID already existing  ");
                        idExists = true;
                        break;
                    }
                }
                if(idExists){
                    continue;
                }

                if(stu_id.length() != 8 || (stu_id.charAt(0) !='w')){
                    System.out.println("ID must contain 8 characters and first letter should be starting with \"w\" ");

                }else{
                    return stu_id;
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input , try again  ");
            }
        }
    }
    /**
     * Generates a report showing student details sorted by module average in descending order.
     * Prints student ID, name, module marks, average, and grade.
     * If no student records are available, it prints a message indicating so.
     */

    public static void systemReport() {
        // Outer loop for sorting students based on module average
        for (int value = 0; value < 100 - global_available_seats; ++value) {
            // Inner loop for comparing and swapping students
            for (int num = value + 1; num < (100 - global_available_seats); ++num) {
                // handled null pointer exception
                if (students[value] != null && students[num] != null) {

                    if (students[value].getModule().getAvg() < students[num].getModule().getAvg()) {
                        // Swap students if they are out of order
                        Student var = students[num];
                        students[num] = students[value];
                        students[value] = var;
                    }
                } else {

                    break;
                }
            }
        }
        boolean data_availble=false;


        for (Student student : students) {
            if (student != null) {
                data_availble=true;
                System.out.println();
                System.out.println("ID: " + student.getStuid());
                System.out.println("Name: " + student.getName());
                System.out.println("Module 1 Mark: " + student.getModule().getS1());
                System.out.println("Module 2 Mark: " + student.getModule().getS2());
                System.out.println("Module 3 Mark: " + student.getModule().getS3());
                System.out.printf("Module avg: %.3f%n", student.getModule().getAvg());
                System.out.println("Module Grade: " + student.getModule().getGrade());
            }

        }
        if(!data_availble){
            System.out.println("No student record available");

        }

    }

    /**
     * Displays system status including total student registrations and counts of students
     * scoring greater than 40 marks in each module.
     */
    public static void systemStatus(){
        System.out.println("------System Full analysis------");
        System.out.println("Total student registration is " + (100-global_available_seats));

        System.out.println("Module 1: Students scored greater than 40 marks: "+module1_greater_than_40_marks);
        System.out.println("Module 2: Students scored greater than 40 marks: "+module2_greater_than_40_marks);
        System.out.println("Module 3: Students scored greater than 40 marks: "+module3_greater_than_40_marks);
    }

    /**
     * Generates a full report of the system including a summary of system status or
     * detailed student records. Allows the user to choose between these options.
     * Exits upon user request.
     */

    public static void reportGenerator(){

        System.out.println("------System Full Report------");

        do{
            System.out.println();
            System.out.println("1.Enter 'c' to generate a summary.");
            System.out.println("2.Enter 'd' to generate full report of student records.");
            System.out.println("3.Enter 'e' to Exit to Main manu.");
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the choice :");
            String choice = input.nextLine().toLowerCase();//whatever user inputted converted to lowercase
            switch (choice) {
                case "c":
                    systemStatus();
                    break;
                case "d":
                    systemReport();
                    break;

                case "e":
                    System.out.println("Returning to Main Menu");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a choice 'c' or 'd' or 'e' only .");
                    break;
            }

        }
        while (true);

    }

}