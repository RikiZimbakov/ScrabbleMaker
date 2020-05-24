
public class Node
{
    private char ch;
    private int howMany;
    public Node next;
   
    public Node(char myCh, int stInt) {
      ch = myCh;
      howMany = stInt;
    }
   
    public char getLetter() {
      return ch;
    }
   
    public int getCount() {
      return howMany;
    }
    public Node getNext() {
	return next;
    }
    
    public boolean setCount(int newCount)
    {
	boolean updated = false;
	if(Integer.signum(newCount) > 0 || Integer.signum(newCount) < 0)
	{
	    howMany = newCount;
	    updated = true;
	}
	return updated;
	
    }
   
    public void setNext(Node next) {
      this.next = next;
    }
    
    public String toString()
    {
	String str = "";
	str = "'" + ch + "' " + howMany;
	return str;
    }

}
