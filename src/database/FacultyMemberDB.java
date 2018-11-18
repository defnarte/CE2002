package database;

import java.util.ArrayList;

import universityMembers.FacultyMember;

public class FacultyMemberDB {
	private ArrayList<FacultyMember> faculty;
	public static final String SEPARATOR = "|";
			
	public FacultyMemberDB(ArrayList<FacultyMember> faculty)
	{
		this.faculty = faculty;
	}
	public ArrayList<FacultyMember> getFacultyAl() 
	{
		return faculty;
	}
//	public FacultyMember getFacultyMember()
//	{
//		ArrayList<String> facultyList = read(facultyFilename);
//		for (int i=0; i< facultyList.size(); i++)
//		{
//			String st = (String) facultyList.get(i);
//			StringTokenizer facultyStar = new StringTokenizer(st, SEPARATOR);
//			String name = facultyStar.nextToken().trim();
//			String id = facultyStar.nextToken().trim();
//			FacultyMember facultyMem = new FacultyMember(id,name);
//			faculty.add(facultyMem);
//		}
//		return faculty;
//	}
	
	public FacultyMember getFacultyMember(String facultyMemberID)
	{
		for(FacultyMember facultyMem: faculty)
		{
			if(facultyMem.getID().equals(facultyMemberID))
				return facultyMem;
		}
		
		return null;
	}
}
