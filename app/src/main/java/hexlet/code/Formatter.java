package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import java.util.Map;

public class Formatter {
  public static String render(Map<String, Map<String, Object>> diffTree, String format)
      throws Exception {
    switch (format.toLowerCase()) {
      case "stylish":
        return Stylish.render(diffTree);
      case "plain":
        return Plain.render(diffTree);
      case "json":
        return Json.render(diffTree);
      default:
        throw new IllegalArgumentException("Unknown format: " + format);
    }
  }
}
