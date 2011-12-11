package senior.project.test.server.errors;

/**
 * This error is generated when providing a bad user name to login()
 * @author Paul Jones
 */
public class ServerBadLoginException extends Exception {

  public ServerBadLoginException(String msg) {
    super(msg);
  }
}
