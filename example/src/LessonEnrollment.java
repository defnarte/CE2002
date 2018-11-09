public class LessonEnrollment {
	private Student student;
	private Lesson lesson;
	
	public LessonEnrollment(Student student, Lesson lesson) {
		this.student = student;
		this.lesson = lesson;
	}
	public Student getStudent() {
		return student;
	}
	public Lesson getLesson() {
		return lesson;
	}
}
