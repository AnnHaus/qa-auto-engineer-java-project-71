package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DifferTest {

  private static String expectedStylish;
  private static String expectedPlain;
  private static final String FIXTURES_PATH = "src/test/resources/fixtures/";

  private static Path getFixturePath(String fileName) {
    return Paths.get(FIXTURES_PATH + fileName).toAbsolutePath().normalize();
  }

  @BeforeAll
  public static void beforeAll() throws Exception {
    expectedStylish =
        Files.readString(getFixturePath("result_stylish.txt")).replaceAll("\\R", "\n").trim();
    expectedPlain =
        Files.readString(getFixturePath("result_plain.txt")).replaceAll("\\R", "\n").trim();
  }

  @ParameterizedTest
  @ValueSource(strings = {"json", "yml"})
  public void testGenerateStylish(String format) throws Exception {
    String path1 = getFixturePath("file1." + format).toString();
    String path2 = getFixturePath("file2." + format).toString();

    String actual = Differ.generate(path1, path2, "stylish").replaceAll("\\R", "\n").trim();
    assertThat(actual).isEqualToNormalizingNewlines(expectedStylish);
  }

  @ParameterizedTest
  @ValueSource(strings = {"json", "yml"})
  public void testGeneratePlain(String format) throws Exception {
    String path1 = getFixturePath("file1." + format).toString();
    String path2 = getFixturePath("file2." + format).toString();

    String actual = Differ.generate(path1, path2, "plain").replaceAll("\\R", "\n").trim();
    assertThat(actual).isEqualToNormalizingNewlines(expectedPlain);
  }
}
