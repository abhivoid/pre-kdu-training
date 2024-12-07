package com.prekdu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * Create a basic Java Program that reads a CSV file and prints the top 3 repeated words in the file.
 * The CSV file is in the reosurces folder and the file name is input.csv.
 */

public class CSVWordFrequency {
  public static void main(String[] args) {
    Map<String, Integer> wordFreq = new HashMap<>();

    // reading the file
    try (BufferedReader file =
        new BufferedReader(
            new FileReader(
                "C:\\cygwin64\\home\\Abhishek\\pre-kdu-training\\java\\src\\main\\resources\\input.csv"))) {
      String line;
      while ((line = file.readLine()) != null) {
        // split line into word by comma or space
        String[] words = line.split("[,\\s]+");
        for (String s : words) {
          wordFreq.put(s, wordFreq.getOrDefault(s, 0) + 1);
        }
      }
    } catch (IOException e) {
      System.err.println("Error reading the file: " + e.getMessage());
      return;
    }

    // intitialise priority queue to get top 3 repeated words
    PriorityQueue<Map.Entry<String, Integer>> pq =
        new PriorityQueue<>((a, b) -> b.getValue().compareTo(a.getValue()));

    pq.addAll(wordFreq.entrySet());

    // Printing top 3 repeated words
    System.out.println("Top 3 repeated words:");
    for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
      Map.Entry<String, Integer> entry = pq.poll();
      System.out.println(entry.getKey());
    }
  }
}
