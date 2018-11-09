import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class StudentDB {
	private ArrayList<Student> students;
	public static final String SEPARATOR = "|";
	public static final String SEPARATOR2 = ",";

	public StudentDB() {
		students = new ArrayList<Student>();
	}
	public void addStudent(Student student) {
		students.add(student);
	}
	public Student getStudent(int index){
		return students.get(index);
	}
	public void printStudentList() {
		for (int i = 0; i < students.size(); i++)
		{
			Student student = (Student) students.get(i);
			System.out.print(student.getStudentName());
			System.out.println(" " + student.getStudentID());
		}
	}
	public boolean checkStudentName(String name) {
		for (int i=0; i<students.size(); i++) {
			if (students.get(i).getStudentName().equals(name)) 
				return true;
		}
		return false;
	}
	public boolean checkStudentMatNo(String MatNo) {
		for (int i=0; i<students.size(); i++) {
			if (students.get(i).getStudentID().equals(MatNo)) 
				return true;
		}
		return false;
		
	}
	public void printListCourseID(String courseID){
		// For each student
		for (int i=0; i<students.size(); i++) {
			// Check the student's registered courses
			for (int j=0; j<students.get(i).getStudentCourses().size(); j++) {
				if (students.get(i).getStudentCourses().get(j).equals(courseID)) {
					System.out.println(students.get(i).getStudentName() + " " + students.get(i).getStudentID());
				}
			}
		}
	}
	public void printListLessonID(String lessonID){
		// For each student
		for (int i=0; i<students.size(); i++) {
			// Check the student's registered indexes
			for (int j=0; j<students.get(i).getStudentLessons().size(); j++) {
				for (int k=0; k<students.get(i).getStudentLessons().get(j).size(); k++) {
					if (students.get(i).getStudentLessons().get(j).get(k).equals(lessonID)) {
						System.out.println(students.get(i).getStudentName() + " " + students.get(i).getStudentID());
					}
				}
			}
		}
	}
	
	// Read the textfile as a stringArray
	public static List read(String filename) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new FileInputStream(filename));
		List data = new ArrayList();
		try
		{
			while (sc.hasNextLine())
			{
				data.add(sc.nextLine());
			}
		} finally
		{
			sc.close();
		}
		return data;
	}

	// Create the database
	public void readStudents(String filename) throws IOException
	{
		ArrayList stringArray = (ArrayList) read(filename);
		ArrayList alr = new ArrayList();

		for (int i = 0; i < stringArray.size(); i++)
		{
			String st = (String) stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, SEPARATOR);

			String name = star.nextToken().trim();
			String matriculationNumber = star.nextToken().trim();
			ArrayList<String> courses = new ArrayList<String>(Arrays.asList(star.nextToken().trim().split("\\s*,\\s*")));
			ArrayList<ArrayList<String>> lessons = stringsplit(star.nextToken().trim(), "_");

			Student stu = new Student(name, matriculationNumber, courses, lessons);
			students.add(stu);
		}
	}

	public void saveStudents(String filename) throws IOException
	{
		List alw = new ArrayList();
		for (int i = 0; i < students.size(); i++)
		{
			Student stu = (Student) students.get(i);
			StringBuilder st = new StringBuilder();
			st.append(stu.getStudentName().trim());
			st.append(SEPARATOR);
			st.append(stu.getStudentID().trim());
			st.append(SEPARATOR);
			ArrayList<String> courses = stu.getStudentCourses();
			for (int j = 0; j < courses.size(); j++)
			{
				st.append(courses.get(j));
				if (j != courses.size() - 1)
					st.append(SEPARATOR2);
			}
			alw.add(st.toString());
		}
		write(filename, alw);
	}

	public static void write(String fileName, List data) throws IOException
	{
		PrintWriter out = new PrintWriter(new FileWriter(fileName));
		try
		{
			for (int i = 0; i < data.size(); i++)
			{
				out.println((String) data.get(i));
			}
		} finally
		{
			out.close();
		}
	}

	public static ArrayList<ArrayList<String>> stringsplit(String st, String SEP)
	{
		StringTokenizer star = new StringTokenizer(st, SEP);
		ArrayList<ArrayList<String>> studentList = new ArrayList<ArrayList<String>>();
		int n = star.countTokens();
		for (int j = 0; j < n; j++)
		{
			ArrayList<String> arrlist = new ArrayList<String>(
					Arrays.asList(star.nextToken().trim().split("\\s*,\\s*")));
			studentList.add(arrlist);
		}
		return studentList;
	}
}
