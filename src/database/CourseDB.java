package database;

import java.util.ArrayList;
import courses.Course;

public class CourseDB {
	ArrayList<Course> courses;
	
	public CourseDB(ArrayList<Course> courses) 
	{
		this.courses = courses;
	}
	
	public ArrayList<Course> getCourseAl() 
	{
		return courses;
	}
	
	public void addCourse(Course course) 
	{
		try 
		{
			for (Course registeredCourse:courses) 
			{
				if (registeredCourse.getName().equals(course.getName()) || registeredCourse.getCourseCode().equals(course.getCourseCode())) 
				{
					throw new Exception("Course Name/Code. already registered!");
				}
			}
			courses.add(course);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int checkCourse(String courseCode) 
	{
		int index = 0;
		for (Course registeredCourse:courses) 
		{
			if (registeredCourse.getCourseCode().equals(courseCode))
				return index;
			else
				index++;
		}
		return -1;
	}
	
	public void printCourseList() {
		for (Course registeredCourse:courses) 
		{
			System.out.println(registeredCourse.getName() + " " + registeredCourse.getCourseCode());
		}
	}
}
