
public class Letters
{
    private int[] Points= {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
    private char Letters[] = {'a','b','c','d','e','f','g','h',
	    		'i','j','k','l','m','n','o','p','q',
	    		'r','s','t','u','v','w','x','y','z'};
    
    public int getValue(char letter)
    {
	int value = 0;
	for(int i = 0; i < Points.length; i++)
	{
	    if(Letters[i] == (letter))
	    {
		value = Points[i];
	    }
	}
	return value;
    }
}
