/**
     * ScrabbleMaker
     * @author       Risto Zimbakov
     * @version      6/6/2018
     *
     * PURPOSE: writing a program that allows a user to 
     * enter 7 letters (representing the letter tiles they hold), 
     * plus an 8th letter (representing the letter they would like 
     * to use on the board). Your program will output all valid words
     *  that can be made from the entered letters and then print the
     *  word with the highest point value
     */
import java.util.*;
import java.io.*;

public class ScrabbleMaker
{
    /**
     * Dictionary
     *
     * Risto Zimbakov, 
     *
     * PURPOSE: to create a class that can store a string array of words
     * in alphabetical order and be searched to find how many words
     * we can make from the users entered letters
     */
    public static class Dictionary
    {
	private int numWords;
	private String[] allMyWords;

	/**
	 * Creates a constructor that takes in how many spots we have in our dictionary
	 * and ensures user is creating valid dictionary
	 *
	 * @param howManyWords
	 *            the starting size of our dictionary will later be doubled
	 *            when too small
	 * 
	 * 
	 * @return nothing is returned
	 */
	public Dictionary(int howManyWords)
	{
	    numWords = 0;//initializes how many words in dictionary to 0
	    if(howManyWords >= 0)
	    {
		allMyWords = new String[howManyWords];
	    }
	    else
	    {
		System.out.println("enter valid size for dictionary");
	    }
	}

	/**
	 * creates a method that will fill dictionary with words but if there are
	 * duplicates will not add those words and also makes sure that list stays in
	 * order when adding words. also doubles the size of the array if the array is 
	 * too small to fill all words
	 *
	 * @param fileName
	 *            the file we are trying to read fill our dictionary with.
	 * @param num
	 *            the amount of words we want copied over from the dictionary
	 * 
	 * 
	 * @return nothing is returned
	 */
	public void fill(String filename, int num)
	{
	    int fillWords = num;// how many words we want to add
	    BufferedReader in;//needed to read in words from file
	    String str;//the string that will be compared to all the words in the list already
	    int current = 0;//the position that we are comparing to str

	    while(allMyWords.length < fillWords)// checks if the size of the array is big enough to store all words
	    {
		allMyWords = new String[allMyWords.length * 2];// if not doubles size
	    }
	    System.out.println("The size of the array is" + allMyWords.length);

	    try
	    {
		in = new BufferedReader(new FileReader(filename));// reads in from file
		for (int i = 0; i < fillWords; i++)//will run the amount of words we are trying to add from the dictionary
		{
		    if ((str = in.readLine()) != null)//will read as long as more words to read in file
		    {
			if (numWords == 0)//base case if there are no words in the file
			{
			    allMyWords[numWords++] = str;//adds the first word to the first spot in the array
			}
			while (current < numWords && allMyWords[current].compareToIgnoreCase(str) <= 0)//important loop that keeps track of where in the list we have to add new word
			{
			    if (allMyWords[current].equalsIgnoreCase(str)) // if word is the same get out of loop because we do not have to add that word
			    {
				break;
			    }
			    current++;//changes what position we are looking at
			}
			if (current == numWords)//special case if second list is larger then first list we are adding to
			{
			    if (!allMyWords[current-1].equalsIgnoreCase(str)) // have to check current-1 because current is a position not a size variable so end case does not exist
			    {
				numWords++;//makes extra spot on end of array for new word
				for (int j = numWords; j > current; j--)
				{
				    allMyWords[j] = allMyWords[j - 1];//shifts how many words over to make room for the new word at the correct position
				}
				allMyWords[current] = str;//finally adds the new word to the correct position
			    }
			} else
			{
			    if (!allMyWords[current].equalsIgnoreCase(str))//every other case that is not at the end can compare correct words
			    {
				numWords++;//adds the new word in
				for (int j = numWords; j > current; j--)
				{
				    allMyWords[j] = allMyWords[j - 1];//shifts how many words over to make room for the new word at the correct position
				}
				allMyWords[current] = str;//finally adds the new word to the correct position
			    }
			}

			current = 0;//resets the position so we begin our check anew
		    } else//no more words to read
		    {

			System.out.println("filled array with all " + numWords + " words");
			break;//gets out of for loop because done reading words
		    }
		    
		}
		//in.close();//closes the reader to be used again and no memory leaks
	    } catch (IOException e)//catches exception if something goes wrong while reading
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	}

	/**
	 * manipulated code from textbook
	 * user friendly method that takes in a word and calls the recursive binary
	 * search below
	 *
	 * @param word
	 *            the word we are trying to find in our dictionary
	 * 
	 * 
	 * @return boolean to checks if we found a word that matches up in dictionary
	 */
	public boolean search(String word)
	{
	    return recFind(word, 0, numWords);
	}

	
	/**
	 * getter that returns the value of numWords
	 *
	 * @param none
	 * 
	 * @return how many words in list
	 */
	public int getNumWords()
	{
	    return numWords;
	}
	
	/**
	 * manipulated code from textbook
	 * recursive binary search that finds if there are any matches for
	 * the letters we have in our hand to the words in our dictionary
	 *
	 * @param word
	 *            the word we are trying to find in our dictionary
	 * @param lowerBound
	 *            the lower limit of what we have left to look through in our dictionary
	 * @param upperBound
	 *            the upper limit of what we have left to look through in our dictionary
	 * 
	 * @return boolean to checks if we found a word that matches up in dictionary
	 */
	private boolean recFind(String word, int lowerBound, int upperBound)
	{
	    int pos;//the position we are looking at in this run through
	    pos = (lowerBound + upperBound) / 2;//the half way point of what is left of the dictionary 
	    if (allMyWords[pos].equalsIgnoreCase(word))//if found word return true(base case)
		return true; // found it
	    else if (lowerBound > upperBound)//if our variables cross means word is not in list so we can stop(base case)
		return false; // can’t find it
	    else // divide range
	    {
		if (allMyWords[pos].compareToIgnoreCase(word) < 0) //if its within the upper half of the list
		    return recFind(word, pos + 1, upperBound);//now will only look from halfway to end divides where we have to look in half 
		else // it’s in lower half
		    return recFind(word, lowerBound, pos - 1);//now will only look from beginning to halfway divides where we have to look in half 
	    } // end else divide range
	}
	
	
    }
    
    /**
     * letters
     *
     * Risto Zimbakov, 
     *
     * PURPOSE: to create a letters class that will keep track
     * of the value of each letter. It was recommended to use node and linked list
     * but I implemented simplyArray because prof said this was okay as well
     */
    
    public static class Letters
    {
	private int[] Points =
	{ 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };
	private char Letters[] =
	{ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
		'w', 'x', 'y', 'z' };

	/**
	 * gets the value of whatever letter the user is checking
	 *
	 * @param letter
	 *            the letter we are trying to find the value of
	 * 
	 * @return the  integer value of the letter we are looking at
	 */
	public int getValue(char letter)
	{
	    int value = 0;
	    for (int i = 0; i < Points.length; i++)
	    {
		if (Letters[i] == (letter))
		{
		    value = Points[i];//both arrays in same order so if find letter find number
		}
	    }
	    return value;
	}
    }

    /**
     * builds a dictionary from the  file words alpha.txt",
     * builds the list containing the points value for each letter,
     * prompt a user to enter their letters (7 letters representing the
     * letter tiles they hold, plus an 8th letter representing the letter
     * they would like to use on the board),recursively generates all
     * possible combinations of the user's letters ,print the points 
     * value for any words that are found in dictionary, and also prints
     * the word with highest point value that also contains the letter from 
     * the board
     *
     * @param args
     *            default method that can be used to test code
     *
     * @return nothing is returned
     */
    public static void main(String[] parms)
    {
	//testingSomething();
	final int SIZE_OF_HAND = 7;
	Dictionary builder = new Dictionary(20);//creates a new dictionary with an arbitrary size because will grow if needed
	builder.fill("words_alpha.txt", 10000);//fills the dictionary with the amount of words specified and reads in from the file

	final int AMT_LETTERS = 26;
	for (int i = 97; i < AMT_LETTERS; i++)
	{
	    int valueOfLetter = i;
	    char letter = (char) valueOfLetter;

	}
	//scanner that takes in input from the user
	Scanner objScanner = new Scanner(System.in);
	int letter = 0;//integer that keeps track of how many letters taken in
	String storeHand = "";//string we will add to make all our combinations
	while (letter != SIZE_OF_HAND)//only runs as many letters as the user can hold
	{
	    System.out.print("Enter letter " + (letter + 1) + ": ");
	    char c = objScanner.next().charAt(0);//only takes in the first letter entered by the user on that line
	    storeHand += c;//adds that letter to a string
	    letter++;//increments count
	}
	System.out.print("Please enter the letter you would like to use on the board: ");
	char onBoard = objScanner.next().charAt(0);//still only takes in the first letter entered
	storeHand += onBoard;//adds that last letter to the string
	//substrings(storeHand,0,1);	
	allCombos(storeHand, builder);//will generate all combinations of the word recursively

    }
    
    /**
     * user friendly method that calls a recursive method
     * that finds every combination of the words
     *
     * @param mixUp
     *            takes in the string that we are trying to find the combos for
     * @param find
     *            the dictionary we will be searching to find all the letters
     *
     * @return nothing is returned
     */
    public static void allCombos(String mixUp, Dictionary find)
    {
	//permutation("", mixUp, find);
	permute("",mixUp);
    }

    /**
     * recursively finds every combination of the word and prints out
     * if the word was found in the dictionary
     *
     * @param str
     *            the string we are manipulating until our base case where there is nothing left
     *            to manipulate
     * @param find
     *            the dictionary we will be searching to find all the letters
     *
     * @return nothing is returned
     */
    public static void permutation(String prefix, String str, Dictionary find)
    {
	int n = str.length();//length of letters we are shifting
	if (n == 0)//only time true if unique permutation created
	{
	    for (int i = 0; i < prefix.length(); i++)//once all permutations are found find if substrings of those by search
	    {;
		if (find.search(prefix.substring(0, (prefix.length() - i))))//checks smaller and smaller perm every time
		{
		    
		    int worth = 0;//how much each found word is worth
		    Letters myLetter = new Letters();//creates new instance of letters
		    char[] value = (prefix.substring(0, (prefix.length() - i))).toCharArray();//converts found word into char array
		    for (int j = 0; j < value.length; j++)
		    {
			worth += myLetter.getValue(value[j]);//gets value of every letter of char array
		    }
		    //System.out.println((prefix.substring(0, (prefix.length() - i))) +" " + worth);//prints out word and value
		}
	    }
	} else//most important part of recursion returns a different string then the one started that has letters
	    //slightly more different then the time before it 
	{
	    for (int i = 0; i < n; i++)
		permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), find);
	}
    }
    public static void permute(String toPermute, String soFar) {

	  // Base case
	  if (toPermute.length() <= 1)
	    System.out.println(toPermute + soFar);

	  else {
	    // Recursive case
	    for (int i = 0; i < toPermute.length(); i++) {

	      char L = toPermute.charAt(i);

	      // Remove the letter L from toPermute, creating a new String
	      String toPermuteWithoutL = "";
	      if (i > 0)
	        toPermuteWithoutL = toPermuteWithoutL + toPermute.substring(0, i);
	      if (i < (toPermute.length() - 1))
	        toPermuteWithoutL = toPermuteWithoutL + toPermute.substring(i + 1);

	      // Recursive call
	      permute(toPermuteWithoutL, L + soFar);
	    }
	  }
	}
    
}