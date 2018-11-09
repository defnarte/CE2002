import java.util.*;

enum Grade
{
	APLUS, A, AMINUS,
	BPLUS, B, BMINUS,
	CPLUS, C,
	DPLUS, D,
	F,
}

/**
 * This entity class holds information regarding a particular Course.
 * 
 * last-updated: 2018-11-09
 * @author DongJoo
 *
 */
public class Course
{
	private String courseID;
	private String courseName;
	
	private Faculty courseCoordinator;
	private ArrayList<String> studentsMatricNumbers;
	// originally named marks; Why is a collection of CourseWorks named marks?
	private ArrayList<CourseWork[]> courseWorkMarks; 
	private ArrayList<Lesson> lessons;
	
	private int vacancy/* = Lesson.getVacancy()*/;
	private double totalMark;
	Scanner scan = new Scanner(System.in); // Why is there input in an entity class?

	public Course(String courseID, String courseName, Faculty courseCoordinator, ArrayList<String> studentsMatricNumbers, ArrayList<CourseWork[]> courseWorkMarks,
			ArrayList<Lesson> lessons)
	{
		this.courseName = courseName;
		this.courseID = courseID;
		this.courseCoordinator = courseCoordinator;
		this.studentsMatricNumbers = studentsMatricNumbers;
		this.courseWorkMarks = courseWorkMarks;
		this.lessons = lessons;
	}
	public Course(String courseName, String courseID)
	{
		this.courseName = courseName;
		this.courseID = courseID;
	}

	public String getCourseID()
	{
		return courseID;
	}
	public String getCourseName()
	{
		return courseName;
	}
	
	public Faculty getCourseCoordinator()
	{
		return courseCoordinator;
	}
	public void setCourseCoordinator(Faculty courseCoordinator)
	{
		this.courseCoordinator = courseCoordinator;
	}

	public ArrayList<String> getstudentsMatricNumbers()
	{
		return studentsMatricNumbers;
	}

	public ArrayList<CourseWork[]> getcourseWorkMarks()
	{
		return courseWorkMarks;
	}

	public ArrayList<Lesson> getLessons()
	{
		return lessons;
	}

	// what is this method for?
	public int searchIndex(String targetIndex, ArrayList<String> lectureIndex)
	{
		for (int i = 0; i < lectureIndex.size(); i++)
		{
			if (lectureIndex.get(i).equals(targetIndex))
			{
				return i;
			}
		}
		return -1;
	}

	// it is NOT the responsibility of this class to do IO
	public void addIndex(ArrayList<String> lectureIndex)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Add an index for lecture:");
		String index = sc.next();
		lectureIndex.add(index);
		sc.close();
	}

	// registration of Student for a Lesson should be the responsibility of Lesson, not Course
	public void registerStudent(String matricNumber, Lesson lesson)
	{
		if (lessons.indexOf(matricNumber) != -1)
			System.out.println("Already registered!");
		else if (lesson.getVacancy() == 0)
			System.out.println("Not Available!");
		else
			studentsMatricNumbers.add(matricNumber);
		
		lesson.addStudent(matricNumber);
		vacancy--;
	}

	public void deleteStudent(String matricNumber, Lesson lesson)
	{
		if (lessons.size() == 0)
			System.out.println("Error input!");
		else if (lessons.indexOf(matricNumber) == -1)
			System.out.println("There is no such Student!");
		else
			studentsMatricNumbers.remove(matricNumber);
		lessons.remove(matricNumber);
		vacancy++;
	}

	
	public void printList(String Index, ArrayList<String> arrIndex, ArrayList<ArrayList<String>> arrStudentList)
	{
		int i = searchIndex(Index, arrIndex);
		if (i >= 0)
		{
			ArrayList<String> studentList = arrStudentList.get(i);
			for (int j = 0; j < studentList.size(); j++)
			{
				System.out.println(studentList.get(j));
			}
		}
	}

	// it is NOT the Course's responsibility to PRINT its stats
	// Course shd have a getStats() method that a boundary class will call to print its stats
	public void printCourseStats(String courseID)
	{
		for (int i = 0; i < 3; i++)
		{
			int a = scan.nextInt();
			int b = scan.nextInt();
			int c = scan.nextInt();
			int d = scan.nextInt();
			int f = scan.nextInt();
			if (a + b + c + d + f != 100 || a <= 0 || b <= 0 || c <= 0 || d <= 0 || f <= 0)
				System.out.println("Error inputs!");
			else
				System.out.println("For grade percentage : ");
			System.out.println("A is upper " + a + "%");
			System.out.println("B is next " + b + "%");
			System.out.println("C is next " + c + "%");
			System.out.println("D is next " + d + "%");
			System.out.println("F is next " + f + "%");
		}
	}

	public double computeOverallcourseWorkMarks()
	{
		return 0 /*CourseWork.getCourseWorkMark()*/;
	}
}