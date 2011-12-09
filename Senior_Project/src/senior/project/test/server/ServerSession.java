package senior.project.test.server;

import senior.project.test.server.ServerConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    String strResponse ;
    JSONObject result;
    try {
      url = new URL(SERVER_BASE_URL + "nutrition_get.php?id=" + id);
      conn = (HttpURLConnection)url.openConnection();
      strResponse = new BufferedReader(new
              InputStreamReader(conn.getInputStream())).readLine();
      result = (JSONObject)(new JSONTokener(strResponse).nextValue());
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
    catch (JSONException ex) {
      System.err.println("JSON exception during getTest");
      ex.printStackTrace();
      return null;
    }
    return result;
  }
  
}
