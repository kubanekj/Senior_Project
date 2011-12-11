package senior.project.test.server.errors;

/**
 *
 * @author Paul Jones
 */
public class ServerInvalidEmailException extends Exception {

  public ServerInvalidEmailException(String msg) {
    super(msg);
  }
}
