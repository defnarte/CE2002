package database;

import java.util.ArrayList;
import courses.Course;

public class CourseDB {
	ArrayList<Course> courses;
	
	public ArrayList<Course> getCourseAl() 
	{
		return courses;
	}
	public void addCourse(Course course) 
	{
		courses.add(course);
	}
}
