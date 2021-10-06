package ca.jrvs.apps.practice;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

public class JavaRegex {

  public static void main(String[] args) {
    RegexExcImp regexExcImp = new RegexExcImp();
    Logger logger = LoggerFactory.getLogger(JavaRegex.class);

    logger.debug("Checking matchJpeg() method.");
    System.out.println("image.jpg: " + regexExcImp.matchJpeg("image.jpg") + "\n" +
        "image.jpeg: " + regexExcImp.matchJpeg("image.jpeg") + "\n" +
        "image.png: " + regexExcImp.matchJpeg("image.png") + "\n"); // return true

    logger.debug("Checking matchIp() method.");
    System.out.println("192.16.0.1: " + regexExcImp.matchIp("192.16.0.1") + "\n" +
        "192.168: " + regexExcImp.matchIp("192.168") + "\n"); //return true

    logger.debug("Checking isEmptyLine() method.");
    System.out.println("Empty line: " + regexExcImp.isEmptyLine("") + "\n" +
        "Spaces only: " + regexExcImp.isEmptyLine("     ") + "\n" +
        "Whitespace with a letter: " + regexExcImp.isEmptyLine("   a"));
  }
}
