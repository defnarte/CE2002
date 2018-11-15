package database;

import java.util.ArrayList;
import universityMembers.Student;

public class StudentDB {
	ArrayList<Student> students;
	
	public StudentDB(ArrayList<Student> students) 
	{
		this.students = students;
	}
	
	public ArrayList<Student> getStudentAl() 
	{
		return students;
	}
	
	public void addStudent(Student student) 
	{
		try 
		{
			for (Student registeredStudent:students) 
			{
				if (registeredStudent.getfullName().equals(student.getfullName()) || registeredStudent.getID().equals(student.getID())) 
				{
					throw new Exception("Student/Matriculation No. already registered!");
				}
			}
			students.add(student);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int checkStudent(String studentID) 
	{
		int index = 0;
		for (Student registeredStudent:students) 
		{
			if (registeredStudent.getID().equals(studentID))
				return index;
			else
				index++;
		}
		return -1;
	}
	
	public void printStudentList() 
	{
		for (Student student:students) 
		{
			System.out.println(student.getfullName() + " " + student.getID());
		}
	}
}
