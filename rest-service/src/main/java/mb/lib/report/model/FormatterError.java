package mb.lib.report.model;

public class FormatterError
{
  private final String message;

  public FormatterError(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
