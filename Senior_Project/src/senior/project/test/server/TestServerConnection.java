package senior.project.test.server;

import org.json.JSONObject;

/**
 * This class is only used for testing the ServerConnection class
 * @author Paul Jones
 */
public class TestServerConnection {
  public static void main(String[] args) {
    Server test = new Server();
    JSONObject testValue = test.getTest(1);
    System.out.println(testValue);
  }
}
