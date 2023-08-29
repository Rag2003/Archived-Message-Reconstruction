package edu.iastate.cs228.hw4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


/**
 * 
 * @author Raghuram Guddati
 *
 */

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner scnr=new Scanner(System.in);
		System.out.print("Please enter filename to decode: ");
		String name=scnr.nextLine();
		scnr.close();
		String s=new String(Files.readAllBytes(Paths.get(name))).trim();
		//reading the file
		int position=s.lastIndexOf('\n');
		//position of the last index
		String b=s.substring(position).trim(); 
		String p=s.substring(0,position); 
		String ch=new String();
		//created a new string
		for (char c : p.toCharArray()) {
			if (c!='^') {
				ch+=c;
			}
		}
		MsgTree root=new MsgTree(p);
		System.out.println("character code");
		System.out.println("---------------------");
		//printing the starting of the code for user input
		MsgTree.printCodes(root,ch);
		//prints the binary code
		root.decode(root,b);//decodes the message
	}
}