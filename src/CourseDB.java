import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CourseDB {
	private ArrayList<Course> courses;
	public static final String SEPARATOR = "|";
	
	public CourseDB(){
		courses = new ArrayList<Course>();
	}
	public void addCourse(Course course) {
		courses.add(course);
	}
	public Course getCourse(int index) {
		return courses.get(index);
	}
	public int getMarkIndex(String courseID, String matriculationNumber) {
		for (int i = 0; i < courses.size(); i++)
		{
			// If the course matches the given courseID
			if (courses.get(i).getCourseID().equals(courseID)) {
				
				for (int j=0; j<courses.get(i).getstudentsMatricNumbers().size() ; j++) {
					// If one of the matriculation numbers in the course matches the given matriculation number
					if (courses.get(i).getstudentsMatricNumbers().get(j).equals(matriculationNumber)) {
						// Return the index (Recall matriculation number and courseMark share a 1 to 1 correspondence)
						return j;
					}
				}
			}
		}
		return -1;
	}
	public void printCourseList() {
		for (int i = 0; i < courses.size(); i++)
		{
			Course course = (Course) courses.get(i);
			System.out.print(course.getCourseName());
			System.out.println(" " + course.getCourseID());
		}
	}
	public boolean checkCourseName(String name) {
		for (int i=0; i<courses.size(); i++) {
			if (courses.get(i).getCourseName().equals(name)) 
				return true;
		}
		return false;
	}
	public boolean checkCourseID(String courseID) {
		for (int i=0; i<courses.size(); i++) {
			if (courses.get(i).getCourseID().equals(courseID)) 
				return true;
		}
		return false;
		
	}
	// Read the textfile as a stringArray
	public static List read(String filename) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream(filename));
		List data = new ArrayList();
	    try {
	        while (sc.hasNextLine()){
	          data.add(sc.nextLine());
	        }
	      }
	      finally{
	        sc.close();
	      }
	    return data;
	}
	// Create the database
	public void readCourses(String filename) throws IOException {
		ArrayList stringArray = (ArrayList) read(filename);
		ArrayList alr = new ArrayList();
		
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st,SEPARATOR);
			
			String courseID = star.nextToken().trim();
			String courseName = star.nextToken().trim();
			ArrayList<String> matNos= new ArrayList<String>(Arrays.asList(star.nextToken().trim().split("\\s*,\\s*")));
			
			ArrayList<String> weightageString = new ArrayList<String>(Arrays.asList(star.nextToken().trim().split("\\s*,\\s*")));
			int x = weightageString.size();
			double weightage[] = new double[x];
			for (int k = 0; k < x; k++) {
				weightage[k] = Double.parseDouble(weightageString.get(k));
			}
			ArrayList<ArrayList<String>> marksString = stringsplit(star.nextToken().trim(),"_");
			// 80,60    50,50
			ArrayList<CourseWork[]> courseWorkMarks = new ArrayList<CourseWork[]>();
			int y = marksString.size();
			for (int l = 0; l < y; l++) {
				double[] marksStudent = new double[x];
				CourseWork[] courseWorkFull = new CourseWork[x];
				for (int m = 0; m < x; m++) {
					marksStudent[m] = Double.parseDouble(marksString.get(l).get(m));
					CourseWork courseWorkIndividual = new CourseWork(weightage[m],marksStudent[m]);
					courseWorkFull[m] = courseWorkIndividual;
				}
				courseWorkMarks.add(courseWorkFull);
			}
			ArrayList<String> lessonIDs = new ArrayList<String>(Arrays.asList(star.nextToken().trim().split("\\s*,\\s*")));
			ArrayList<String> lessonTypes = new ArrayList<String>(Arrays.asList(star.nextToken().trim().split("\\s*,\\s*")));
			ArrayList<String> totalSize = new ArrayList<String>(Arrays.asList(star.nextToken().trim().split("\\s*,\\s*")));
			ArrayList<String> vacancy = new ArrayList<String>(Arrays.asList(star.nextToken().trim().split("\\s*,\\s*")));
			
			ArrayList<Lesson> lessons = new ArrayList<Lesson>();
			int u = lessonIDs.size();
			for (int p = 0; p < u; p++) {
				Lesson lesson = new Lesson(lessonIDs.get(p),lessonTypes.get(p),Integer.parseInt(totalSize.get(p)),Integer.parseInt(vacancy.get(p)));
				lessons.add(lesson);
			}
			Course course = new Course(courseID, courseName, matNos, courseWorkMarks, lessons);
			courses.add(course);
		}
	}
	public static ArrayList<ArrayList<String>> stringsplit(String st,String SEP) {
		StringTokenizer star = new StringTokenizer(st,SEP);
		ArrayList<ArrayList<String>> studentList = new ArrayList<ArrayList<String>>();
		int n = star.countTokens();
		for (int j = 0; j < n; j++) {
			ArrayList<String> arrlist = new ArrayList<String>(Arrays.asList(star.nextToken().trim().split("\\s*,\\s*")));
			studentList.add(arrlist);
		}
		return studentList;
	}
}