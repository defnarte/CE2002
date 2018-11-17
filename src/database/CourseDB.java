package database;

import java.util.ArrayList;

import consoleIO.ConsoleDisplay;
import courses.Course;
import lessons.Lesson;

/**
 * This class hold all the Courses.
 * 
 * @version 1.1
 * @since 2018/11/16
 * @author Isaac
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
	 * Checks if a Course is in the Course database base on the Course code,
	 * and return the index of the Course in the ArrayList in which it is stored.
	 * -1 is returned if the Course code cannot found in the ArrayList.
	 * 
	 * @param courseCode
	 * @return index
	 */
	
	public boolean checkCourseCode(String courseCode)
	{
		for (Course registeredCourse : courses)
		{
			if (registeredCourse.getCourseCode().equals(courseCode))
				return true;
		}
		return false;
	}
	
	public boolean checkCourseName(String courseName)
	{
		for (Course registeredCourse : courses)
		{
			if (registeredCourse.getName().equals(courseName))
				return true;
		}
		return false;
	}
	
//	public int checkCourse(String courseCode)
//	{
//		int index = 0;
//		for (Course registeredCourse : courses)
//		{
//			if (registeredCourse.getCourseCode().equals(courseCode))
//				return index;
//			else
//				index++;
//		}
//		return -1;
//	}
	
	public boolean checkLesson(String lessonID)
	{
		for (Course registeredCourse:courses)
		{
			ArrayList<Lesson> lessons = registeredCourse.getLessons();
			for (Lesson lesson:lessons)
			{
				if(lesson.getLessonID().equals(lessonID))
					return true;
			}
		}
		return false;	
	}
	
	public Course getCourse(String courseCode)
	{
		for (Course registeredCourse : courses)
		{
			if (registeredCourse.getCourseCode().equals(courseCode))
				return registeredCourse;
		}
		
		return null;
	}

	public void printCourseList()
	{
		ConsoleDisplay.displayCourses(courses);
	}
}
