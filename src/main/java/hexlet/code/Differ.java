package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

  public static String generate(String filePath1, String filePath2) throws Exception {
    Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
    Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();

    String content1 = Files.readString(path1);
    String content2 = Files.readString(path2);

    String format1 = getFileExtension(filePath1);
    String format2 = getFileExtension(filePath2);

    Map<String, Object> map1 = Parser.parse(content1, format1);
    Map<String, Object> map2 = Parser.parse(content2, format2);

    Set<String> keys = new TreeSet<>();
    keys.addAll(map1.keySet());
    keys.addAll(map2.keySet());

    StringBuilder result = new StringBuilder("{\n");

    for (String key : keys) {
      Object value1 = map1.get(key);
      Object value2 = map2.get(key);

      if (map1.containsKey(key) && !map2.containsKey(key)) {
        result.append("  - ").append(key).append(": ").append(value1).append("\n");
      } else if (!map1.containsKey(key) && map2.containsKey(key)) {
        result.append("  + ").append(key).append(": ").append(value2).append("\n");
      } else if (Objects.equals(value1, value2)) {
        result.append("    ").append(key).append(": ").append(value1).append("\n");
      } else {
        result.append("  - ").append(key).append(": ").append(value1).append("\n");
        result.append("  + ").append(key).append(": ").append(value2).append("\n");
      }
    }

    result.append("}");
    return result.toString();
  }

  private static String getFileExtension(String filePath) {
    int index = filePath.lastIndexOf('.');
    if (index > 0) {
      return filePath.substring(index + 1);
    }
    return "";
  }
}
