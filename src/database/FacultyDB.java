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
		
	}
	
}
