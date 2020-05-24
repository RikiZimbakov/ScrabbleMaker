import java.io.*;

public class Dictionary
{
    private int numWords;
    private String[] allMyWords;

    public Dictionary(int howManyWords)
    {
	numWords = 0;
	allMyWords = new String[howManyWords];
    }

    public void fill(String filename, int num)
    {
	// use search method in fill to determine if all words are unique
	int fillWords = num;
	BufferedReader in;
	String str;
	
	int current = 0;
	String compareTo;
	if (allMyWords.length < fillWords)
	{
	    allMyWords = new String[allMyWords.length * 2];
	}
	System.out.println("The size of the array is" + allMyWords.length);
	
	try
	{
	    in = new BufferedReader(new FileReader(filename));
	    if(numWords == 0) //no words in dictionary 
	    {
		for (int i = 0; i < fillWords; i++)
		{
		    if ((str = in.readLine()) != null)
		    {

			allMyWords[numWords++] = str;
			System.out.println(allMyWords[numWords - 1]);

		    } else
		    {
			
			break;
		    }
		}
	    }
	    else
	    {
		for (int i = 0; i < fillWords; i++)
		{
		    if ((compareTo = in.readLine()) != null)
		    {
			while (current < numWords && allMyWords[current].compareToIgnoreCase(compareTo) <= 0)
			{
			    System.out.println(current);
			    if (allMyWords[current].compareToIgnoreCase(compareTo) == 0) // if word is the same
			    {
				break;
			    }			    
			    current++;
			}
			if (current == numWords)
			{
			    if (allMyWords[current-1].compareToIgnoreCase(compareTo) != 0) // word does not match up
			    {
				numWords++;
				for (int j = numWords; j > current; j--)
				{
				    allMyWords[j] = allMyWords[j - 1];
				}
				allMyWords[current] = compareTo;
			    }
			}
			else
			{
			    if (allMyWords[current].compareToIgnoreCase(compareTo) != 0) // word does not match up
			    {
				numWords++;
				for (int j = numWords; j > current; j--)
				{
				    allMyWords[j] = allMyWords[j - 1];
				}
				allMyWords[current] = compareTo;
			    }
			}
			
			current = 0;
		    } else
		    {
			System.out.println("\n" + allMyWords[0]);
			System.out.println("filled array with all " + numWords + " words");
			break;
		    }
		    
		}
	    }
	    
	} catch (IOException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
    }

    public boolean search(String word)
    {
	return recFind(word, 0, numWords);
    }

    // -----------------------------------------------------------
    private boolean recFind(String word, int lowerBound, int upperBound)
    {
	int pos;
	pos = (lowerBound + upperBound) / 2;
	// System.out.println(allMyWords[pos]);
	if (allMyWords[pos].equals(word))
	    return true; // found it
	else if (lowerBound > upperBound)
	    return false; // can’t find it
	else // divide range
	{
	    if (allMyWords[pos].compareTo(word) < 0) // it’s in upper half
		return recFind(word, pos + 1, upperBound);
	    else // it’s in lower half
		return recFind(word, lowerBound, pos - 1);
	} // end else divide range
    } // end recFind()
      // -----------------------------------------------------------

   
    
  
}
