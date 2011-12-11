package senior.project.test.server.errors;

/**
 *
 * @author Paul Jones
 */
public class ServerCantLoginException extends RuntimeException {

  public ServerCantLoginException(String msg) {
    super(msg);
  }
}
