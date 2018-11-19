package database;

import java.util.ArrayList;

import universityMembers.Student;

public class StudentDB
{
	ArrayList<Student> students;

	/**
	 * Constructor for student database.
	 * @param students The ArrayList containing all students.
	 */
	public StudentDB(ArrayList<Student> students)
	{
		this.students = students;
	}

	/**
	 * Get the student ArrayList i.e complete database.
	 * @return The ArrayList containing all students.
	 */
	public ArrayList<Student> getStudentAl()
	{
		return students;
	}

	/**
	 * Add a student to the database.
	 * @param student The student.
	 */
	public void addStudent(Student student)
	{
		try
		{
			for (Student registeredStudent : students)
			{
				if (registeredStudent.getfullName().equals(student.getfullName())
						|| registeredStudent.getID().equals(student.getID()))
				{
					throw new Exception("Student/Matriculation No. already registered!");
				}
			}
			students.add(student);
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Check if a student exists in the database.
	 * @param studentID The ID of the student.
	 * @return Boolean as to whether the student exists in the database.
	 */
	public boolean checkStudentIDExists(String studentID)
	{
		for (Student registeredStudent : students)
		{
			if (registeredStudent.getID().equals(studentID))
				return true;
		}
		return false;
	}
	
	/**
	 * Get the student whose ID corresponds to the requested ID.
	 * @param studentID The ID of the student.
	 * @return Student whose ID corresponds to the requested ID.
	 */
	public Student getStudent(String studentID)
	{
		for(Student student: students)
		{
			if(student.getID().equals(studentID))
				return student;
		}
		
		return null;
	}
	/**
	 * Print all students, their name and their ID.
	 */
	public void printStudentList()
	{
		for (Student student : students)
		{
			System.out.println(student.getfullName() + " " + student.getID());
		}
	}
}
