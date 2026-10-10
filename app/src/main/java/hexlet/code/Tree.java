package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Tree {
  public static Map<String, Map<String, Object>> build(
      Map<String, Object> map1, Map<String, Object> map2) {
    Map<String, Map<String, Object>> result = new LinkedHashMap<>();
    Set<String> keys = new TreeSet<>(map1.keySet());
    keys.addAll(map2.keySet());

    for (String key : keys) {
      Map<String, Object> info = new LinkedHashMap<>();
      if (map1.containsKey(key) && !map2.containsKey(key)) {
        info.put("type", "deleted");
        info.put("oldValue", map1.get(key));
      } else if (!map1.containsKey(key) && map2.containsKey(key)) {
        info.put("type", "added");
        info.put("newValue", map2.get(key));
      } else if (Objects.equals(map1.get(key), map2.get(key))) {
        info.put("type", "unchanged");
        info.put("oldValue", map1.get(key));
      } else {
        info.put("type", "changed");
        info.put("oldValue", map1.get(key));
        info.put("newValue", map2.get(key));
      }
      result.put(key, info);
    }
    return result;
  }
}
