
public class LinkedList
{
    private Node first;
    // -------------------------------------------------------------
    public LinkedList() // constructor
    {
	first = null;
    }

    // -------------------------------------------------------------
    public boolean isEmpty() // true if no links
    {
	return (first == null);
    }

    // -------------------------------------------------------------
    public void insert(char letter, int howMany) // insert in order
    {
	Node newLink = new Node(letter, howMany); // make new link
	Node previous = null; // start at first
	Node current = first;
	// until end of list,
	while (current != null && letter > current.getLetter())
	{ // or letter > current,
	    previous = current;
	    current = current.getNext(); // go to next item
	}
	
	if (previous == null) // at beginning of list
	{
	    //System.out.println("should only print once");
	    first = newLink; // first --> newLink
	}	
	else
	{
	    // not at beginning	    
	    Node tempPrev = previous.getNext();	    
	    tempPrev = newLink;// old prev --> newLink
	    previous.setNext(tempPrev);
	}
	Node newLink2 = newLink.getNext();
	newLink2= current; // newLink --> old current
	newLink.setNext(newLink2);
	
    } // end insert()

    public Node remove() // return & delete first link
    { // (assumes non-empty list)
	Node temp = first; // save first
	first = first.getNext(); // delete first
	return temp; // return value
    }
    // -------------------------------------------------------------
    public void incrementCount(char newCh)
    {
	Node current = first;
	boolean foundNew = true;
	//System.out.println(newCh);
	while(current != null)
	{
	    if(current.getLetter() == newCh)
	    {
		current.setCount(current.getCount()+1);
		foundNew = false;
	    }
	    current = current.getNext();
	}
	if(foundNew)
	{
	    insert(newCh, 1);
	}
    }
    
    public String toString() 
    {
        String result = "";
        Node current = first;
        while(current != null)
        {            
            result += current.toString() + "\n";
            current = current.getNext();
        }
        return result;
    }
    
    public int GrandTotal()
    {
	int total = 0;
        Node current = first;
        while(current != null)
        {            
            total += current.getCount();
            current = current.getNext();
        }
        return total;
    }
}
