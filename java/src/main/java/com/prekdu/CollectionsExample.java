package com.prekdu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/*
 * Create a basic Java Program that takes 10 strings as input and adds them to an ArrayList and
 * HashSet. Also, create a HashMap by populating the words as key and frequency as the value in the
 * map. Iterate the list, set and map and print the content of the collection.
 */

public final class CollectionsExample {

  public static void main(String[] args) {
    // Intializing Collection
    ArrayList<String> arrayList = new ArrayList<String>(10);
    HashSet<String> hashSet = new HashSet<String>();
    HashMap<String, Integer> hashMap = new HashMap<>();

    Scanner in = new Scanner(System.in);

    System.out.println("Enter 10 String:");
    for (int i = 0; i < 10; i++) {
      String s = in.nextLine();
      arrayList.add(s);
      hashSet.add(s);
      hashMap.put(s, hashMap.getOrDefault(s, 0) + 1);
    }

    // printing content of collection
    System.out.println("\nContents of List:");
    for (String i : arrayList) {
      System.out.println(i);
    }
    System.out.println("\nContents of Set:");
    for (String i : hashSet) {
      System.out.println(i);
    }
    System.out.println("\nContents of Map:");
    hashMap.forEach((Key, value) -> System.err.println(Key + "->" + value));
  }
}
