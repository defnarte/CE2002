package database;

import java.util.ArrayList;
import courses.Course;
import universityMembers.Student;

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
		courses.add(course);
	}
}
