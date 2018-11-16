package consoleIO;

import java.util.ArrayList;

import universityMembers.FacultyMember;

public class ConsoleDisplay
{
	public static void printMainMenu()
	{
		System.out.println("Select an option:\n" + "1.  Add a student\n" + "2.  Add a course\n"
				+ "3.  Register student for a course (this include registering for Tutorial/Lab classes)\n"
				+ "4.  Check available slot in a class (vacancy in a class)\n"
				+ "5.  Print student list by lecture, tutorial or laboratory session for a course.\n"
				+ "6.  Enter course assessment components weightage\n"
				+ "7.  Enter coursework mark inclusive of its components.\n" + "8.  Enter exam mark\n"
				+ "9.  Print course statistics\n" + "10. Print student transcript.\n" + "11. Quit");
	}
	
	public static void displayFacultyMembers(ArrayList<FacultyMember> faculty)
	{
		for(FacultyMember facultyMem: faculty)
			System.out.println(facultyMem);
	}
}
