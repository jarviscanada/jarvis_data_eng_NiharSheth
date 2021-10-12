package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {

  /**
   * Top level search workflow.
   *
   * @throws IOException
   */
  void process() throws IOException;

  /**
   * Traverse a given directory and return all files.
   *
   * @param rootDir input directory
   * @return files under the rootDir
   */
  List<File> listFiles(String rootDir);

  /**
   * Read a file and return all the lines.
   *
   * @param inputFile file to be read
   * @return lines
   * @throws IllegalArgumentException if a given inputFile is not a file
   */
  List<String> readLines(File inputFile) throws IllegalArgumentException;

  /**
   * Check if a line contains the regex pattern (passed by user).
   *
   * @param line input string
   * @return true if there is a match
   */
  boolean containsPattern(String line);

  /**
   * Write lines to a file.
   *
   * @param lines matched line
   * @throws IOException if write failed
   */
  void writeToFile(List<String> lines) throws IOException;

  // Getter/setter methods

  /**
   * Get the root path.
   *
   * @return root path
   */
  String getRootPath();

  /**
   * Set the root path.
   *
   * @param rootPath path to set
   */
  void setRootPath(String rootPath);

  /**
   * Get the regex pattern.
   *
   * @return regex pattern
   */
  String getRegex();

  /**
   * Set the regex pattern.
   *
   * @param regex regex pattern to set
   */
  void setRegex(String regex);

  /**
   * Get output file.
   *
   * @return output file
   */
  String getOutFile();

  /**
   * Set output file.
   *
   * @param outFile output file to set
   */
  void setOutFile(String outFile);

}
