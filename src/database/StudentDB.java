package database;

import java.util.ArrayList;
import universityMembers.Student;

public class StudentDB {
	ArrayList<Student> students;
	
	public ArrayList<Student> getStudentAl() 
	{
		return students;
	}
	public void addStudent(Student student) 
	{
		students.add(student);
	}
	public void printStudentList() 
	{
		for (Student student:students) 
		{
			System.out.println(student.getfullName() + student.getID());
		}
	}
}
