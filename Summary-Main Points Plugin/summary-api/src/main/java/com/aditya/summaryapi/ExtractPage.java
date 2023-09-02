package com.aditya.summaryapi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ExtractPage {

    public static String getContent(String tabUrl) {
        try {
            // Connect to the webpage and get its HTML content
            Document document = Jsoup.connect(tabUrl).get();

            // Find all <p> elements within the document
            Elements paragraphElements = document.select("p");

            // Initialize a StringBuilder to store the extracted plain text
            StringBuilder plainTextContent = new StringBuilder();

            // Iterate through <p> elements and append their plain text to the StringBuilder
            for (Element paragraph : paragraphElements) {
                // Exclude anchor (<a>) elements within paragraphs
                //paragraph.select("a").remove();
                plainTextContent.append(paragraph.text());
                plainTextContent.append("\n"); // Add a line break after each paragraph
            }

            // Return the extracted plain text content
            return plainTextContent.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return "Error check log";
        }
    }
}