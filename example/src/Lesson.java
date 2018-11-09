import java.util.ArrayList;

public class Lesson {
	private String lessonName;
	private String lessonID;
	private ArrayList<LessonEnrollment> StudentsEnrolled;
	
	public Lesson(String lessonName, String lessonID) {
		this.lessonName = lessonName;
		this.lessonID = lessonID;
		this.StudentsEnrolled = new ArrayList<LessonEnrollment>();
	}
	public void printStudentsEnrolled() {
		for (int i = 0; i < StudentsEnrolled.size(); i++) {
			System.out.println(StudentsEnrolled.get(i).getStudent().getStudentName());
		}
	}
	public String getLessonName() {
		return lessonName;
	}
	public String getLessonID() {
		return lessonID;
	}
	public void addStudentsEnrolled(LessonEnrollment studentEnrol) {
		StudentsEnrolled.add(studentEnrol);
	}
}
