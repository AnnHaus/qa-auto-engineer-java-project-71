package hexlet.code.formatters;

import java.util.Map;

public class Stylish {
  public static String render(Map<String, Map<String, Object>> diffTree) {
    StringBuilder result = new StringBuilder("{\n");

    for (Map.Entry<String, Map<String, Object>> entry : diffTree.entrySet()) {
      String key = entry.getKey();
      Map<String, Object> info = entry.getValue();
      String type = (String) info.get("type");

      switch (type) {
        case "deleted":
          result.append("  - ").append(key).append(": ").append(info.get("oldValue")).append("\n");
          break;
        case "added":
          result.append("  + ").append(key).append(": ").append(info.get("newValue")).append("\n");
          break;
        case "unchanged":
          result.append("    ").append(key).append(": ").append(info.get("oldValue")).append("\n");
          break;
        case "changed":
          result.append("  - ").append(key).append(": ").append(info.get("oldValue")).append("\n");
          result.append("  + ").append(key).append(": ").append(info.get("newValue")).append("\n");
          break;
        default:
          throw new IllegalArgumentException("Unknown type: " + type);
      }
    }

    result.append("}");
    return result.toString();
  }
}
