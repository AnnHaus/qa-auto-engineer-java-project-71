package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class Json {
  public static String render(Map<String, Map<String, Object>> diffTree) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(diffTree);
  }
}
