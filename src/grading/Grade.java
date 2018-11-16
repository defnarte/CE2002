package grading;

import java.util.HashMap;
import java.util.Map;

//Grade Scoring Table
//A+	85 to 100	marks	10
//A 	80 to 84 	marks	9
//A- 	75 to 79 	marks	8
//B+ 	70 to 74 	marks	7
//B 	65 to 69 	marks	6
//B- 	60 to 64 	marks	5
//C+ 	55 to 59 	marks	4
//C 	50 to 54 	marks	3
//C- 	45 to 49 	marks	2
//D 	40 to 44 	marks	1	
//F 	 0 to 39 	marks	0
public enum Grade
{
	F(0),
	D(1), D_PLUS(2),
	C(3), C_PLUS(4),
	B_MINUS(5), B(6), B_PLUS(7),
	A_MINUS(8), A(9), A_PLUS(10);
	
	private Integer value;
	private static Map<Integer,Grade> map = new HashMap<>();
	
	private Grade(int value) 
	{
		this.value = value;
	}
    static 
    {
        for (Grade grade : Grade.values()) 
        {
            map.put(grade.value, grade);
        }
    }
    public static Grade valueOf(int grade) 
    {
    	return (Grade) map.get(grade);
    }
    public int getValue() 
    {
    	return value;
    }
}