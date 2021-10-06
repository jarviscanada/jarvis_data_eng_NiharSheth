package ca.jrvs.apps.practice;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExcImp implements RegexExc {

  private Pattern pattern;
  private Matcher matcher;
  private boolean stringMatched = false;
  private final Logger logger = LoggerFactory.getLogger(RegexExcImp.class);

  @Override
  public boolean matchJpeg(String filename) {
    pattern = Pattern.compile("\\.jpe?g$");
    matcher = pattern.matcher(filename);
    stringMatched = matcher.find();

    if (stringMatched) {
      logger.debug("matchJpeg() matched with " + filename);
    }
    return stringMatched;
  }

  @Override
  public boolean matchIp(String ip) {
    pattern = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+");
    matcher = pattern.matcher(ip);
    stringMatched = matcher.find();

    if (stringMatched) {
      logger.debug("matchIp() matched with " + ip);
    }
    return stringMatched;
  }

  // Need to fix
  @Override
  public boolean isEmptyLine(String line) {
    pattern = Pattern.compile("\\s?");
    matcher = pattern.matcher(line);
    stringMatched = matcher.matches();

    if (stringMatched) {
      logger.debug("isEmptyLine() matched with " + line);
    }
    return stringMatched;
  }
}
