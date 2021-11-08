package ca.jrvs.apps.twitter.dao;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

public class JsonUtil {

  /**
   * Convert a Java object to a JSON string.
   *
   * @param object Input Object
   * @return JSON string
   * @throws JsonProcessingException
   */
  public static String toJson(Object object, boolean prettyJson, boolean includeNullValues)
      throws JsonProcessingException {
    ObjectMapper m = new ObjectMapper();

    if (!includeNullValues) {
      m.setSerializationInclusion(Include.NON_NULL);
    }
    if (prettyJson) {
      m.enable(SerializationFeature.INDENT_OUTPUT);
    }
    return m.writeValueAsString(object);
  }

  /**
   * Parse JSON string to an Object.
   *
   * @param json JSON string
   * @param clas Object class
   * @param <T>  Type
   * @return Object
   * @throws IOException
   */
  public static <T> T toObjectFromJson(String json, Class clas) throws IOException {
    ObjectMapper m = new ObjectMapper();
    return (T) m.readValue(json, clas);
  }
}
