package database;

import java.util.ArrayList;

import consoleIO.ConsoleDisplay;
import courses.Course;
import lessons.Lesson;

/**
 * This class hold all the Courses.
 *
 */
public class CourseDB
{
	private ArrayList<Course> courses;

	/**
	 * Constructor for CourseDB.
	 * 
	 * @param courses
	 */
	public CourseDB(ArrayList<Course> courses)
	{
		this.courses = courses;
	}

	/**
	 * This method gets all Courses stored in the Course database in the form of an ArrayList.
	 * 
	 * @return ArrayList of courses stored in database
	 */
	public ArrayList<Course> getCourseAl()
	{
		return courses;
	}

	/**
	 * This method adds a new course into the Course database.
	 * 
	 * @param course
	 */
	public void addCourse(Course course)
	{
		try
		{
			for (Course registeredCourse : courses)
			{
				if (registeredCourse.getName().equals(course.getName())
						|| registeredCourse.getCourseCode().equals(course.getCourseCode()))
				{
					throw new Exception("Course Name/Code. already registered!");
				}
			}
			courses.add(course);
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Check a course code has already been registered in the course database.
	 * @param courseCode The course code of the course.
	 * @return Boolean whether the course code has already been registered in the course database.
	 */
	public boolean checkCourseCodeExists(String courseCode)
	{
		for (Course registeredCourse : courses)
		{
			if (registeredCourse.getCourseCode().equals(courseCode))
				return true;
		}
		return false;
	}
	/**
	 * 
	 * @param lessonID The ID of the lesson.
	 * @return Boolean whether the lesson exists in any one of the courses in the course database.
	 */
	public boolean checkLessonExists(int lessonID)
	{
		for (Course registeredCourse:courses)
		{
			ArrayList<Lesson> lessons = registeredCourse.getLessons();
			for (Lesson lesson:lessons)
			{
				if(lesson.getLessonID() == lessonID)
					return true;
			}
		}
		return false;	
	}
	/**
	 * Get the course whose course code corresponds to the requested course code.
	 * @param courseCode The course code of the course.
	 * @return The course whose course code corresponds to the requested course code.
	 */
	public Course getCourse(String courseCode)
	{
		for (Course registeredCourse : courses)
		{
			if (registeredCourse.getCourseCode().equals(courseCode))
				return registeredCourse;
		}
		
		return null;
	}
	/**
	 * Print the list of all courses in the course database.
	 */
	public void printCourseList()
	{
		ConsoleDisplay.displayCourses(courses);
	}
	/**
	 * Print the list of all courses in the course database, together with the coordinator of the course.
	 */
	public void printCourseListWithCoordinator()
	{
		ConsoleDisplay.displayCoursesWithCoordinators(courses);
	}
}
