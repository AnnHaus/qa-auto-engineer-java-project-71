package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.Map;

public class Parser {

  public static Map<String, Object> parse(String content, String format) throws Exception {
    if (format.equalsIgnoreCase("json")) {
      ObjectMapper jsonMapper = new ObjectMapper();
      return jsonMapper.readValue(content, new TypeReference<Map<String, Object>>() {});
    } else if (format.equalsIgnoreCase("yaml") || format.equalsIgnoreCase("yml")) {
      YAMLMapper yamlMapper = new YAMLMapper();
      return yamlMapper.readValue(content, new TypeReference<Map<String, Object>>() {});
    }
    throw new IllegalArgumentException("Unknown format: " + format);
  }
}
