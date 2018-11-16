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
			
	public FacultyDB(ArrayList<FacultyMember> faculty)
	{
		this.faculty = faculty;
	}
	public ArrayList<FacultyMember> getFacultyAl() 
	{
		return faculty;
	}
	public FacultyMember getFacultyMember()
	{
		ArrayList<String> facultyList = read(facultyFilename);
		for (int i=0; i< facultyList.size(); i++)
		{
			String st = (String) facultyList.get(i);
			StringTokenizer facultyStar = new StringTokenizer(st, SEPARATOR);
			String name = facultyStar.nextToken().trim();
			String id = facultyStar.nextToken().trim();
			FacultyMember facultyMem = new FacultyMember(id,name);
			faculty.add(facultyMem);
		}
		return faculty;
	}
	
	public FacultyMember getFacultyMember(String facultyID)
	{
		for(FacultyMember facultyMem: faculty)
		{
			if(facultyMem.getID().equals(facultyID))
				return facultyMem;
		}
		
		return null;
	}
	
	public ArrayList<FacultyMember> getAllFacultyMembers()
	{
		return faculty;
	}
}
