/**
 * This boundary class takes in input and prints output via the console.
 * 
 * last-updated: 2018-11-09
 * @author Jason
 *
 */
public class ConsoleIO
{
	public static void printStudentTranscript(Student student)
	{
		System.out.println("Name: " + student.getName() + "\tMatriculation number: " + student.getID());
		System.out.println("Course\t\t\tGrade\n" + 
							"----------------------------------------------");
		
//		for(Course course: student.getCourses())
//		{
//			System.out.println(course.getCourseID() + "\t" + course.getCourseName() + "\t\t" /*+ course.getGrade*/);
//		}
		
	}
}
