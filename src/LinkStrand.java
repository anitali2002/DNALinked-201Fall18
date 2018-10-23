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
	//private LinkedList myInfo;
	//private int myAppends;

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
		Node copy = myFirst;
		while(copy != null) {
			mySize += copy.info.length();
			copy = copy.next;
		}
		
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
		mySize = 0;
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
		//Node newNode = new Node(dna);
		//myFirst.next = newNode;
		myLast.next = new Node(dna);
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
		LinkStrand reverse = new LinkStrand();
		Node copy = myFirst;
		while (copy != null) {
			StringBuilder forNode = new StringBuilder(copy.info);
			String reversed = forNode.reverse().toString();
			//Node newFirst = new Node(reversed);
			reverse.myFirst = new Node(reversed, reverse.myFirst);
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

	@Override
	public char charAt(int index) {
		// TODO Auto-generated method stub
		return 0;
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
