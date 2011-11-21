package senior.project.test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.*;



/**
 *
 * @author Paul Jones
 */
public class ServerSession implements ServerConnection {
  
  private static final String SERVER_BASE_URL =
          "http://kawaii-rage.com/project/";
  
  public JSONObject getTest(int id) {
    URL url = null;
    HttpURLConnection conn = null;
    try {
      url = new URL(SERVER_BASE_URL + "nutrition_get.html?id=" + id);
      conn = (HttpURLConnection)url.openConnection();
    }
    catch (MalformedURLException ex) {
      System.err.println("Malformed URL in getTest");
      return null;
    }
    catch (IOException ex) {
      System.err.println("IOException during getTest");
      ex.printStackTrace();
      return null;
    }
    return null;
  }
  
}
