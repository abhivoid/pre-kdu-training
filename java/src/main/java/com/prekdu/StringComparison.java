package com.prekdu;

import java.util.Scanner;

/*
 * Create a basic Java Program that takes two strings as input and produces the following output.
 * Print the length of the first string
 * Print the length of the second string
 * Print if the length matches or not
 * Print if the two strings are the same
 */

public class StringComparison {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.print("Enter First String:");
    String w1 = in.nextLine();
    System.out.print("Enter Second String:");
    String w2 = in.nextLine();

    int l1 = w1.length(), l2 = w2.length();
    System.err.println("Length of First String:" + l1);
    System.err.println("Length of First String:" + l2);
    if (l1 == l2) System.out.println("Length matches");
    else System.out.println("Length dosen't match");

    if (w1.equals(w2)) {
      System.out.println("The strings are equal.");
    } else {
      System.out.println("The strings are not equal.");
    }
  }
}
