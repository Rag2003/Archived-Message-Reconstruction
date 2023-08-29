package edu.iastate.cs228.hw4;

import java.util.Stack;

/**
 * 
 * @author Raghuram Guddati
 *
 */
public class MsgTree {
	public MsgTree left;
	public MsgTree right;
	public char payloadChar;
	private static String c;

	
	/*
	 * Constructor building the tree from a string
	 */
	public MsgTree(String encodingString) {
		Stack<MsgTree> stack = new Stack<>();
		String last="in";
		int index=0;
		if (encodingString.length()<=1||encodingString==null) {
			return;
		}
		this.payloadChar=encodingString.charAt(index++);
		stack.push(this);
		//pushes the stack
		MsgTree current=this;
		while (index<encodingString.length()) {
			MsgTree n=new MsgTree(encodingString.charAt(index++));
			if (last.equals("in")) {
				current.left=n;
				if (n.payloadChar=='^') {
					current=stack.push(n);
					last="in";
				} 
				else {
					if (stack.empty()==false) {
						current=stack.pop();
					    last="out";
					}
					else {
		
					}
				}
			} 
			else { 
				current.right=n;
				if (n.payloadChar=='^') {
					current=stack.push(n);
					last="in";
				} 
				else {
					if (stack.empty()==false) {
						current=stack.pop();
					    last="out";
					}
				}
			}
		}
	}

	/**
	 * Constructor for a single node with null children
	 * 
	 * @param payloadChar
	 */
	public MsgTree(char payloadChar) {
		this.left=null;
		this.right=null;
		this.payloadChar=payloadChar;
	}
	
	/**
	 * created a helper method which uses recursion to find the code of the alphabets
	 * @param root
	 * @param chr
	 * @param x
	 * @return
	 */
	private static boolean coder(MsgTree root,char chr,String x) {
		boolean y=false;
		if (root!=null) {
			if (root.payloadChar==chr) {
				c=x;
				y=true;
			}
			return coder(root.left,chr,x+"0")||coder(root.right,chr,x+"1"); //recursive call
		}
		return y;
	}

	/**
	 * 
	 * method to print characters and their binary codes
	 * @param root
	 * @param code
	 */
	public static void printCodes(MsgTree root,String code) {
		for (char ch : code.toCharArray()) {
			coder(root, ch, c = "");
			//calling the helper method created
			System.out.println("    " + (ch == '\n' ? "\\n" : ch + " ") + "    " + c);
		}
	}
		
	


	/**
	 * 
	 * decodes the message to readable form
	 * @param codes
	 * @param msg
	 */
	public void decode(MsgTree codes, String msg) {
		System.out.println("MESSAGE:");
		MsgTree current1=codes;
		int i=0;
		StringBuilder s=new StringBuilder();
		for (i=0;i<msg.length();i++) {
			char ch=msg.charAt(i);
			current1=(ch=='0' ? current1.left : current1.right);
			if (current1.payloadChar!='^') {
				coder(codes,current1.payloadChar,c="");
				s.append(current1.payloadChar);
				current1=codes;
			}
		}
		System.out.println(s.toString());
		statistc(msg,s.toString());
	}

	/**
	 * extra credit
	 * prints the specs: Avg bits, Total Characters and Space Saving with correct spacing in between
	 * @param encodeStr
	 * @param decodeStr
	 */
	private void statistc(String encodeStr,String decodeStr) {
		System.out.println("STATISTICS:");
		System.out.println(String.format("Avg bits/char:        %.1f",encodeStr.length()/((double)decodeStr.length())));
		System.out.println("Total Characters:     "+decodeStr.length());
		System.out.println(String.format("Space Saving:         %.1f%%",(1d-decodeStr.length()/(double)encodeStr.length())*100));
	}
}
