import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class Atten {
    public static void main(String[] args) {
        student[] students = new student[10];

        students[0] = new student(1, "Vinayak");
        students[1] = new student(2, "Darshit");
        students[2] = new student(3, "Zaman");
        students[3] = new student(4, "Uday");
        students[4] = new student(5, "Aditya");
        students[5] = new student(6, "Aman");
        students[6] = new student(7, "Lakshay");
        students[7] = new student(8, "Ayush");
        students[8] = new student(9, "Jyotish");
        students[9] = new student(10, "Lokesh");

        System.out.println("List of students:");
        for (student s : students) {
            System.out.println(s.getId() + " - " + s.getName());
        }

        Scanner sc = new Scanner(System.in);
        boolean[] attendance = new boolean[students.length];

        for (int i = 0; i < students.length; i++) {
            while (true) {
                System.out.println("Mark attendance for " + students[i].getName() + " (P/A):");
                String input = sc.next().trim();

                if (input.equalsIgnoreCase("P") || input.equalsIgnoreCase("Present")) {
                    attendance[i] = true;
                    break;
                } else if (input.equalsIgnoreCase("A") || input.equalsIgnoreCase("Absent")) {
                    attendance[i] = false;
                    break;
                } else {
                    System.out.println("Invalid input, enter P or A");
                }
            }
        }

        System.out.println("\nAttendance Report:");
        int presentCount = 0;
        for (int i = 0; i < students.length; i++) {
            String status = attendance[i] ? "Present" : "Absent";
            if (attendance[i]) presentCount++;
            System.out.println(students[i].getId() + ". " + students[i].getName() + " - " + status);
        }
        int absentCount = students.length - presentCount;

        System.out.println("\nSummary:");
        System.out.println("Total students: " + students.length);
        System.out.println("Present: " + presentCount);
        System.out.println("Absent: " + absentCount);

        sc.close();

        LocalDate today = LocalDate.now();

        // Write to file (overwrite every time → false)
        try (FileWriter fw = new FileWriter("attendance.txt", false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println("Date: " + today);
            for (int i = 0; i < students.length; i++) {
                String status = attendance[i] ? "Present" : "Absent";
                out.println(students[i].getId() + ". " + students[i].getName() + " - " + status);
            }

            out.println("Summary:");
            out.println("Total students: " + students.length);
            out.println("Present: " + presentCount);
            out.println("Absent: " + absentCount);
            out.println("--------------------------------------------------");

        } catch (IOException e) {
            System.out.println("Error writing to file!");
        }

        // Read file
        System.out.println("\n--- Attendance History ---");
        try (BufferedReader br = new BufferedReader(new FileReader("attendance.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file!");
        }
    }
}


