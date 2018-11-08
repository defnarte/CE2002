import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SCRAMEApp
{
	public static void main(String args[]) throws IOException
	{

		String filename = "Students.txt";
		ArrayList<Student> students = StudentDB.readStudents(filename);
		// ArrayList CourseAl = CourseDB.readCourses(filename);

		System.out.println("Welcome to SCRAME");
		
		Scanner sc = new Scanner(System.in);
		int choice = 0;

		do
		{
			printMenu();
			
			do
			{
				System.out.println("Select an option:");
				
				if(sc.hasNextInt())
					choice = sc.nextInt();
			} while(choice < 1 || choice > 11);
			
			switch (choice)
			{
				case 1:
					// Error check
					System.out.println("Enter the name of the student: ");
					String name = sc.next();
					Pattern p = Pattern.compile("^[ A-Za-z]+$");
					Matcher m = p.matcher(name);
					boolean b = m.matches();
					if (!b)
					{
						System.out.println("Error! Please enter only letters and spaces.");
						break;
					}
					// Error check
					System.out.println("Enter the student's ID: ");
					String matriculationNumber = sc.next();
					Student stu = new Student(name, matriculationNumber);
					students.add(stu);
					StudentDB.saveStudents("Students.txt", students);

					System.out.println("List of all students:");
					for (int i = 0; i < students.size(); i++)
					{
						Student stu2 = (Student) students.get(i);
						System.out.print(stu2.getName());
						System.out.println(" " + stu2.getID());
					}
					break;
				case 2:
					break;
				case 3:
					System.out.println("Enter the student's ID: ");
					String studentID = sc.next();
					System.out.println("Enter courseID to be added: ");
					String courseID = sc.next();
					for (int i = 0; i < students.size(); i++)
					{
						Student stu3 = (Student) students.get(i);
						if (studentID.equals(stu3.getID()))
						{
							stu3.addCourse(courseID);
							StudentDB.saveStudents("Students.txt", students);
							System.out.println("Course added!");

							break;
						}
					}
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
				case 10:
				default:
					break;
			}
			

		} while (choice != 11);
		
		sc.close();
	}
	
	public static void printMenu()
	{
		System.out.println(	"Select an option:\n" +
							"1.  Add a student\n" +
							"2.  Add a course\n" + 
							"3.  Register student for a course (this include registering for Tutorial/Lab classes)\n" + 
							"4.  Check available slot in a class (vacancy in a class)\n" + 
							"5.  Print student list by lecture, tutorial or laboratory session for a course.\n" + 
							"6.  Enter course assessment components weightage\n" + 
							"7.  Enter coursework mark – inclusive of its components.\n" + 
							"8.  Enter exam mark\n" + 
							"9.  Print course statistics\n" + 
							"10. Print student transcript.\n" +
							"11. Quit");
	}
}
