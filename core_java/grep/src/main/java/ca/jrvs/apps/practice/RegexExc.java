package ca.jrvs.apps.practice;

public interface RegexExc {

  /**
   * Return true if the filename extension is jpeg or jpg, case-insensitive.
   * @param filename
   * @return
   */
  public boolean matchJpeg(String filename);

  /**
   * Return true if IP address format is valid (from 0.0.0.0 to 999.999.999.999)
   * @param ip
   * @return
   */
  public boolean matchIp(String ip);

  /**
   * Return true if the line is empty or contains only whitespace.
   * @param line
   * @return
   */
  public boolean isEmptyLine(String line);
}
