import java.util.ArrayList;
import java.util.Scanner;

public class Course {
	private String courseName;
	private String courseID;
	private ArrayList<String> matNos;
	private ArrayList<CourseWork[]> marks;
	private ArrayList<Lesson> lessons;
	
	public Course(String courseID, String courseName, ArrayList<String> matNos, ArrayList<CourseWork[]> marks, ArrayList<Lesson> lessons) {
		this.courseName = courseName;
		this.courseID = courseID;
		this.matNos = matNos;
		this.marks = marks;
		this.lessons = lessons;
	}
	public Course(String courseName, String courseID) {
		this.courseName = courseName;
		this.courseID = courseID;	
	}
	public String getCourse() {
		return courseName;
	}
	public String getCourseID() {
		return courseID;
	}
	public ArrayList<String> getmatNos(){
		return matNos;
	}
	public ArrayList<CourseWork[]> getMarks(){
		return marks;
	}
	public ArrayList<Lesson> getLessons(){
		return lessons;
	}
	
	public int searchIndex(String Index, ArrayList<String> lecIndex) {
		for (int i = 0; i < lecIndex.size(); i++) {
			if (lecIndex.get(i).equals(Index)) {
				return i;
			}
		}
		return -1;
	}
	public void addIndex(ArrayList<String> lecIndex) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Add an index for lecture:");
		String index = sc.next();
		lecIndex.add(index);
		sc.close();
	}
	public void printList(String Index, ArrayList<String> arrIndex, ArrayList<ArrayList<String>> arrStudentList) {
		int i = searchIndex(Index,arrIndex);
		if (i >= 0) {
			ArrayList<String> studentList = arrStudentList.get(i);
			for (int j = 0; j < studentList.size(); j++) {
				System.out.println(studentList.get(j));
			}
		}
	}
}
