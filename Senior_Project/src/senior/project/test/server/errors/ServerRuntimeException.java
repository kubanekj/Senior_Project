package senior.project.test.server.errors;

/**
 * A ServerRuntimeException is an exception that is unlikely to be generated,
 * therfore catching it is optional
 * @author Paul Jones
 */
public class ServerRuntimeException extends RuntimeException {

  public ServerRuntimeException(String msg) {
    super(msg);
  }
}
