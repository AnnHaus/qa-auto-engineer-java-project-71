package hexlet.code;

import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
    name = "gendiff",
    mixinStandardHelpOptions = true,
    version = "gendiff 1.0",
    description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
  @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
  private String filepath1;

  @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
  private String filepath2;

  @Option(
      names = {"-f", "--format"},
      paramLabel = "format",
      description = "output format [default: stylish]",
      defaultValue = "stylish")
  private String format = "stylish";

  @Override
  public Integer call() throws Exception {
    if (filepath1 != null && filepath2 != null) {
      String result = Differ.generate(filepath1, filepath2);
      System.out.println(result);
    }
    return 0;
  }

  public static void main(String[] args) {
    int exitCode = new CommandLine(new App()).execute(args);
    System.exit(exitCode);
  }
}
