import java.util.*;

class Student {
    String name;
    int score;

    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

public class GradeTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter student name: ");
            String name = sc.next();
            System.out.print("Enter score: ");
            int score = sc.nextInt();
            students.add(new Student(name, score));
        }

        int sum = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (Student s : students) {
            sum += s.score;
            max = Math.max(max, s.score);
            min = Math.min(min, s.score);
        }

        double avg = (double) sum / n;

        System.out.println("\n--- Report ---");
        for (Student s : students) {
            System.out.println(s.name + " : " + s.score);
        }
        System.out.println("Average Score: " + avg);
        System.out.println("Highest Score: " + max);
        System.out.println("Lowest Score: " + min);
    }
}
