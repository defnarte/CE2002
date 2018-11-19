package database;

import java.util.ArrayList;

import universityMembers.FacultyMember;

public class FacultyMemberDB {
	private ArrayList<FacultyMember> faculty;
	public static final String SEPARATOR = "|";
			
	/**
	 * Constructor for faculty database.
	 * @param faculty The ArrayList of all faculty members.
	 */
	public FacultyMemberDB(ArrayList<FacultyMember> faculty)
	{
		this.faculty = faculty;
	}
	/**
	 * Get the ArrayList containing all faculty members.
	 * @return The ArrayList containing all faculty members.
	 */
	public ArrayList<FacultyMember> getFacultyAl() 
	{
		return faculty;
	}
	/**
	 * Get the faculty member whose ID corresponds to the requested ID.
	 * @param facultyMemberID The ID of the faculty member.
	 * @return The faculty member whose ID corresponds to the requested ID.
	 */
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
