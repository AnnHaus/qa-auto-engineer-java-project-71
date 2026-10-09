package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {
  public static String render(Map<String, Map<String, Object>> diffTree) {
    StringBuilder result = new StringBuilder();

    for (Map.Entry<String, Map<String, Object>> entry : diffTree.entrySet()) {
      String key = entry.getKey();
      Map<String, Object> info = entry.getValue();
      String type = (String) info.get("type");

      switch (type) {
        case "deleted":
          result.append("Property '").append(key).append("' was removed\n");
          break;
        case "added":
          result
              .append("Property '")
              .append(key)
              .append("' was added with value: ")
              .append(stringify(info.get("newValue")))
              .append("\n");
          break;
        case "changed":
          result
              .append("Property '")
              .append(key)
              .append("' was updated. From ")
              .append(stringify(info.get("oldValue")))
              .append(" to ")
              .append(stringify(info.get("newValue")))
              .append("\n");
          break;
        case "unchanged":
          break;
        default:
          throw new IllegalArgumentException("Unknown type: " + type);
      }
    }

    return result.toString().trim();
  }

  private static String stringify(Object value) {
    if (value == null) {
      return "null";
    }
    if (value instanceof String) {
      return "'" + value + "'";
    }
    if (value instanceof Map || value instanceof List) {
      return "[complex value]";
    }
    return value.toString();
  }
}
