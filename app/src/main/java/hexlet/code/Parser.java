package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.HashMap;
import java.util.Map;

public class Parser {

  public static Map<String, Object> parse(String content, String format) throws Exception {
    if (content == null || content.trim().isEmpty()) {
      return new HashMap<>();
    }

    ObjectMapper mapper = getMapper(format);
    return mapper.readValue(content, new TypeReference<Map<String, Object>>() {});
  }

  private static ObjectMapper getMapper(String format) {
    if (format.equalsIgnoreCase("yml") || format.equalsIgnoreCase("yaml")) {
      return new YAMLMapper();
    }
    return new ObjectMapper();
  }
}
