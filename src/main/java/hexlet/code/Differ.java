package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

  public static String generate(String filePath1, String filePath2) throws Exception {
    return generate(filePath1, filePath2, "stylish");
  }

  public static String generate(String filePath1, String filePath2, String formatName)
      throws Exception {
    Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
    Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();

    String content1 = Files.readString(path1);
    String content2 = Files.readString(path2);

    String format1 = getFileExtension(filePath1);
    String format2 = getFileExtension(filePath2);

    Map<String, Object> map1 = Parser.parse(content1, format1);
    Map<String, Object> map2 = Parser.parse(content2, format2);

    Map<String, Map<String, Object>> diffTree = Tree.build(map1, map2);

    return Formatter.render(diffTree, formatName);
  }

  private static String getFileExtension(String filePath) {
    int index = filePath.lastIndexOf('.');
    if (index > 0) {
      return filePath.substring(index + 1);
    }
    return "";
  }
}
