package ca.jrvs.apps.grep;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaGrepImp implements JavaGrep {

  private final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex, rootPath, outFile;

  public static void main(String[] args) {
    verifyArgs(args);

    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch (IOException e) {
      javaGrepImp.logger.error("ERROR: Unable to process.", e);
    }
  }

  public static void verifyArgs(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: regex rootPath outFile\n"
          + "- regex: a special text string for describing a search pattern\n"
          + "- rootPath: root directory path\n"
          + "- outFile: output file name");
    }
  }

  @Override
  public void process() throws IOException {
    List<String> lines = readLines(new File("C:\\Users\\Nihar\\Documents\\dev\\jarvis_data_eng_NiharSheth\\core_java\\grep\\data\\txt\\shakespeare.txt"));
    List<String> matchedLines = new ArrayList<>();
    for (String line : lines) {
      if (containsPattern(line)) {
        matchedLines.add(line);
      }
    }
    writeToFile(matchedLines);
  }

  /*
  @Override
  public List<File> listFiles(String rootDir) {
    File directory = new File(rootDir);
    List<File> fileList = new ArrayList<File>();

    for(File file : directory.listFiles()) {
      if (file.isFile()) {
        fileList.add(file);
      }
      else if (file.isDirectory()) {

      }
    }

    return fileList;
  }*/

  @Override
  public List<String> readLines(File inputFile) throws IllegalArgumentException {
    List<String> linesList = new ArrayList<>();
    try {
      FileReader fileReader = new FileReader(inputFile);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      String line = bufferedReader.readLine();
      while (line != null) {
        linesList.add(line);
        line = bufferedReader.readLine();
      }
      bufferedReader.close();
    } catch (IOException e) {
      System.err.println(e);
    }

    return linesList;
  }

  @Override
  public boolean containsPattern(String line) {
    Pattern pattern = Pattern.compile(getRegex());
    Matcher matcher = pattern.matcher(line);
    return matcher.find();
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    File outFile = new File(this.getOutFile() + "\\out.txt");
    if (outFile.exists()) {
      outFile.delete();
    }
    outFile.createNewFile();
    FileWriter fileOutputStream = new FileWriter(outFile);
    for (String line : lines) {
      fileOutputStream.write(line + "\n");
    }
    fileOutputStream.flush();
    fileOutputStream.close();
  }

  // Getter/setter methods
  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}
