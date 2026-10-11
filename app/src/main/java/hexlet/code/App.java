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

  @Parameters(index = "0", description = "path to first file")
  private String path1;

  @Parameters(index = "1", description = "path to second file")
  private String path2;

  @Option(
      names = {"-f", "--format"},
      defaultValue = "stylish",
      description = "output format [default: stylish]")
  private String format;

  @Override
  public Integer call() throws Exception {
    String result = Differ.generate(path1, path2, format);
    System.out.println(result);
    return 0;
  }

  public static void main(String[] args) {
    int exitCode = new CommandLine(new App()).execute(args);
    System.exit(exitCode);
  }
}
