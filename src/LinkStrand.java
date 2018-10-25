/**
 * 
 * @author al367
 *
 */

import java.util.*;

public class LinkStrand implements IDnaStrand {
	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
		
		public Node(String s, Node nextNode) {
			info = s;
			next = nextNode;
		}
	}
	private Node myFirst, myLast;
	private long mySize;
	private int myAppends;
	private Node myCurrent;
	private int myGlobalIndex = 0; //overall index
	private int myLocalIndex = 0;


	/**
	 * default constructor
	 */
	public LinkStrand() {
		this("");
	}

	/**
	 * create a strand representing s. 
	 * 
	 * no error checking is done to see if s represents 
	 * valid genomic/DNA data.
	 * 
	 * @param s
	 */
	public LinkStrand(String s) {
		initialize(s);
	}

	/**
	 * @return total number of characters stored in all nodes together
	 */
	@Override
	public long size() {
//		Node copy = myFirst;
//		while(copy != null) {
//			mySize += copy.info.length();
//			copy = copy.next;
//		}
		
		return mySize;
	}

	/**
	 * initialize this strand so that it represents the value of source.
	 * 
	 * @param String source (of enzyme)
	 */
	@Override
	public void initialize(String source) {
		myFirst = new Node(source);
		myLast = myFirst;
		myAppends = 0;
		mySize = myFirst.info.length();
		myCurrent = myFirst; //current node in the iteration
	}

	/**
	 * return new instance of LinkStrand
	 * 
	 * @param String source (of enzyme)
	 * @return LinkStrand object
	 */
	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	/**
	 * create one new node.
	 * 
	 * update instance variable to maintain class invariants.
	 * 
	 * @param String dna (new node)
	 * @return LinkStrand 
	 */
	@Override
	public IDnaStrand append(String dna) {
//		Node newNode = new Node(dna);
		//myFirst.next = newNode;
		//myLast = new Node(myLast.info, new Node(dna));
//		myLast.next = new Node(dna);
//		myLast = myLast.next;
		
		Node copy = myFirst;
		
		while (copy != null) {
			copy = copy.next;
		}
		
		copy = new Node(dna);
		
		myLast.next = copy;
		myLast = myLast.next;

		mySize += dna.length();
		myAppends += 1;
		
		return this;
	}

	/**
	 * return a new LinkStrand object that's reverse of the object
	 * on what it's called 
	 * 
	 * creates new LinkStrand (NOT mutator)
	 * 
	 * 
	 */
	@Override
	public IDnaStrand reverse() {
		LinkStrand reverse = new LinkStrand(new StringBuilder(myFirst.info).reverse().toString());
		Node copy = myFirst;
//		while (copy.next != null) {
//			StringBuilder forNode = new StringBuilder(copy.next.info);
//			String reversed = forNode.reverse().toString();
//			Node newFirst = new Node(reversed, reverse.myFirst);
//		//	Node rev = reverse.myFirst;
//			//reverse.myFirst = newFirst;
//			reverse.append(reversed);
//	//		reverse.myFirst.next = rev;
////			reverse.myFirst = new Node(reversed, reverse.myFirst);
//			
//			copy = copy.next;
//		}
		
		while (copy.next!= null) {
//			StringBuilder forNode = new StringBuilder(copy.info);
//			String reversed = forNode.reverse().toString();
//			reverse.myFirst = new Node(reversed);
			reverse.myFirst = new Node(copy.info);
			reverse.myFirst = new Node(copy.info, reverse.myFirst);
			StringBuilder forNode = new StringBuilder(copy.next.info);
			forNode.reverse();
			reverse.myFirst.info = forNode.toString();
			reverse.mySize += forNode.toString().length();
			copy = copy.next;
		}
		
		return reverse;
	}

	/**
	 * return number of appends.
	 * 
	 * @return int appendCount
	 */
	@Override
	public int getAppendCount() {
		return myAppends;
	}
	
	/**
	 * myIndex is the value of the parameter in the last call to charAt
	 * 
	 * myLocalIndex is the value of the index within the string stored 
	 * in the node last-referenced by charAt when the method finishes
	 * 
	 * myCurrent is the node of the internal list referenced in the last
	 * call to charAt 
	 * 
	 * @param int index
	 * @return char at the index
	 */
	@Override
	public char charAt(int index) {
		//int totalIndex; //total indicies 
		//int myLocalIndex; //current index in that node
//		Node myCurrent; //current node in the iteration
		char ch = ' ';
		
		if (index >= this.size() || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		if (index < myGlobalIndex) {
			myCurrent = myFirst;
			myLocalIndex = index;
		}
		else {
			myLocalIndex = index - myGlobalIndex + myLocalIndex;
		}	
		
		while (myCurrent != null) {
			if (myCurrent.info.length() <= myLocalIndex) {
				myLocalIndex -= myCurrent.info.length();
				myCurrent = myCurrent.next;
			}
			
			else {
				ch = myCurrent.info.charAt(myLocalIndex);
				break;
			}
		}
	
		myGlobalIndex = index;	
		return ch;
	}

	/**
	 * returns String representation of LinkStrand
	 * loops over nodes and appends value to a StringBuilder object
	 * 
	 * @return String repOfLinkStrand
	 */
	@Override
	public String toString() {
		StringBuilder stringRep = new StringBuilder();
		Node copy = myFirst;
		
		while (copy != null) {
			stringRep.append(copy.info);
			copy = copy.next;
		}
		
		return stringRep.toString();
	}



}
