package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

  @ParameterizedTest
  @ValueSource(strings = {"json", "yml", "yaml"})
  public void testGenerate(String format) throws Exception {
    String currentFormat = format.equals("yaml") ? "yml" : format;

    String path1 = getFixturePath("file1." + currentFormat).toString();
    String path2 = getFixturePath("file2." + currentFormat).toString();

    String actual = Differ.generate(path1, path2).replaceAll("\\r\\n", "\n").trim();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testParserException() throws Exception {
    var map = Parser.parse("timeout: 20", "yaml");
    assertThat(map).containsKey("timeout");

    assertThatThrownBy(() -> Parser.parse("some content", "txt"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Unknown format: txt");
  }
}
