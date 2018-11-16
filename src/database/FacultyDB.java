package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import universityMembers.FacultyMember;

public class FacultyDB {
	private ArrayList<FacultyMember> faculty;
	public static final String SEPARATOR = "|";
			
	public FacultyDB()
	{
		this.faculty = new ArrayList<FacultyMember>();
	}
	
	public static ArrayList<String> read(String filename) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new FileInputStream(filename));
		ArrayList<String> data = new ArrayList<String>();
		try
		{
			while (sc.hasNextLine())
			{
				data.add(sc.nextLine());
			}
		} finally
		{
			sc.close();
		}
		return data;
	}
	
	public ArrayList<FacultyMember> readFacultyDB(String facultyFilename) throws FileNotFoundException 
	{
		ArrayList<String> facultyList = read(facultyFilename);
		for (int i=0; i< facultyList.size(); i++)
		{
			String st = (String) facultyList.get(i);
			StringTokenizer facultyStar = new StringTokenizer(st, SEPARATOR);
			String name = facultyStar.nextToken().trim();
			String id = facultyStar.nextToken().trim();
			FacultyMember facultymem = new FacultyMember(id,name);
			faculty.add(facultymem);
		}
		return faculty;
	}
	
}
