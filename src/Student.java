import java.util.ArrayList;
public class Student
{
	private String name;
	private String matriculationNumber;
	private ArrayList<String> courses;
	private ArrayList<ArrayList<String>> lessons; // a collection of collection of lessons

	// Used when creating adding a new student
	Student(String name, String matriculationNumber)
	{
		this.name = name;
		this.matriculationNumber = matriculationNumber;
		this.courses = new ArrayList<String>();
	}

	// Used when loading StudentDB
	Student(String name, String matriculationNumber, ArrayList<String> courses, ArrayList<ArrayList<String>> lessons)
	{
		this.name = name;
		this.matriculationNumber = matriculationNumber;
		this.courses = courses;
		this.lessons = lessons;
	}

	public String getStudentName()
	{
		return name;
	}

	public String getStudentID()
	{
		return matriculationNumber;
	}

	public ArrayList<String> getStudentCourses()
	{
		return courses;
	}

	public void enrolCourse(String course)
	{
		this.courses.add(course);
	}

	public ArrayList<ArrayList<String>> getStudentLessons()
	{
		return lessons;
	}
}
