import java.util.ArrayList;

public class Student {
	private String studentName;
	private String studentID;
	private ArrayList<LessonEnrollment> LessonsEnrolled;
	
	public Student(String studentName, String studentID) {
		this.studentName = studentName;
		this.studentID = studentID;
		this.LessonsEnrolled = new ArrayList<LessonEnrollment>();
	}
	public void printLessonEnrolled() {
		for (int i = 0; i < LessonsEnrolled.size(); i++) {
			System.out.println(LessonsEnrolled.get(i).getLesson().getLessonID());
		}
	}
	public String getStudentName() {
		return studentName;
	}
	public String getStudentID() {
		return studentID;
	}
	public void addLessonsEnrolled(LessonEnrollment lessonEnrol) {
		LessonsEnrolled.add(lessonEnrol);
	}
}
