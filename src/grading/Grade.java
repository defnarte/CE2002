package grading;

import java.util.HashMap;
import java.util.Map;


/**
 * This enumerator holds the possible Grade that can be obtained for a Component Result,
 * as well as their values.
 * 
 * ---Grade Scoring Table---
 * A+	85 to 100	marks	10
 * A 	80 to 84 	marks	9
 * A- 	75 to 79 	marks	8
 * B+ 	70 to 74 	marks	7
 * B 	65 to 69 	marks	6
 * B- 	60 to 64 	marks	5
 * C+ 	55 to 59 	marks	4
 * C 	50 to 54 	marks	3
 * C- 	45 to 49 	marks	2
 * D 	40 to 44 	marks	1	
 * F 	 0 to 39 	marks	0
 *
 */
public enum Grade
{
	F(0),
	D(1), D_PLUS(2),
	C(3), C_PLUS(4),
	B_MINUS(5), B(6), B_PLUS(7),
	A_MINUS(8), A(9), A_PLUS(10),
	TOTAL_NUMBER_OF_GRADES(11);
	
	private Integer value;
	// Create a mapping from a grade to a value (ordering)
	private static Map<Integer,Grade> map = new HashMap<>();
	
	/**
	 * Constructor for grade, which assigns a value (ordering) to a grade.
	 * @param value
	 */
	private Grade(int value) 
	{
		this.value = value;
	}
	// 
    static 
    {
        for (Grade grade : Grade.values()) 
        {
            map.put(grade.value, grade);
        }
    }
    /**
     * Return the grade corresponding to the value.
     * @param The value of the grade.
     * @return Grade corresponding to the value.
     */
    public static Grade valueOf(int grade) 
    {
    	return (Grade) map.get(grade);
    }
    /**
     * Return the value corresponding to the grade.
     * @return The value corresponding to the grade
     */
    public int getValue() 
    {
    	return value;
    }
}