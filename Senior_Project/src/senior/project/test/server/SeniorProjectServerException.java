package senior.project.test.server;

/**
 * This will encompass all exceptions/errors that the server can throw during
 * communication
 * @author Paul Jones
 */
public class SeniorProjectServerException extends Exception {

  private int code;
  
  /**
   * Constructs an instance of <code>SeniorProjectServerException</code> with the specified detail message.
   * @param msg the detail message.
   */
  public SeniorProjectServerException(int code, String msg) {
    super("" + code + ": ");
    this.code = code;
  }
  
  public int getErrorCode() {
    return code;
  }
}
