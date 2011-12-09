package senior.project.test.server.errors;

/**
 *
 * @author Paul Jones
 */
public class ServerCantLoginException extends RuntimeException {

  public static final int CODE = 301;
  
  public ServerCantLoginException(String msg) {
    super(msg);
  }
}
