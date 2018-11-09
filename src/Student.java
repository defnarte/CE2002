import java.util.ArrayList;
public class Student
{
	private String name;
	private String matriculationNumber;
	private ArrayList<Course> courses;
	private ArrayList<ArrayList<String>> lessons;

	// Used when creating adding a new student
	Student(String name, String matriculationNumber)
	{
		this.name = name;
		this.matriculationNumber = matriculationNumber;
		this.courses = new ArrayList<Course>();
		// this.courses = new ArrayList<String>();
	}

	// Used when loading StudentDB
	Student(String name, String matriculationNumber, ArrayList<Course> courses, ArrayList<ArrayList<String>> lessons)
	{
		this.name = name;
		this.matriculationNumber = matriculationNumber;
		this.courses = courses;
		this.lessons = lessons;
	}

	public String getName()
	{
		return name;
	}

	public String getID()
	{
		return matriculationNumber;
	}

	public ArrayList<Course> getCourses()
	{
		return courses;
	}

	public void addCourse(Course course)
	{
		this.courses.add(course);
	}

	public ArrayList<ArrayList<String>> getLessons()
	{
		return lessons;
	}
}
