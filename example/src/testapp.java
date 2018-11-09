import java.util.ArrayList;
import java.util.Scanner;

public class testapp {
	public static void main(String args[]) {
		
		ArrayList<LessonEnrollment> enrollmentList = new ArrayList<LessonEnrollment>();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter name of the lesson: ");
		String lessonName = sc.next();
		System.out.println("Enter ID of the lesson: ");
		String lessonID = sc.next();
		Lesson lesson = new Lesson(lessonName,lessonID);
		
		for (int i = 0; i < 2; i++) {
			System.out.println("Enter name of the student: ");
			String studentName = sc.next();
			System.out.println("Enter ID of the student: ");
			String studentID = sc.next();
			
			Student student = new Student(studentName,studentID);
			LessonEnrollment enrollment = new LessonEnrollment(student,lesson);
			student.addLessonsEnrolled(enrollment);
			lesson.addStudentsEnrolled(enrollment);
		}
		System.out.println("Students enrolled in " + lesson.getLessonName() + " :");
		lesson.printStudentsEnrolled();
	}
}
