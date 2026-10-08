package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DifferTest {

  private static String expected;
  private static final String FIXTURES_PATH = "src/test/resources/fixtures/";

  private static Path getFixturePath(String fileName) {
    return Paths.get(FIXTURES_PATH + fileName).toAbsolutePath().normalize();
  }

  @BeforeAll
  public static void beforeAll() throws Exception {
    expected = Files.readString(getFixturePath("result.txt")).replaceAll("\\r\\n", "\n").trim();
  }

  @Test
  public void testGenerate() throws Exception {
    String path1 = getFixturePath("file1.json").toString();
    String path2 = getFixturePath("file2.json").toString();

    String actual = Differ.generate(path1, path2).replaceAll("\\r\\n", "\n").trim();

    assertThat(actual).isEqualTo(expected);
  }
}
