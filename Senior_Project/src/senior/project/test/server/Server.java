package senior.project.test.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.json.*;
import senior.project.test.server.errors.ServerBadLoginException;
import senior.project.test.server.errors.ServerCantLoginException;
import senior.project.test.server.errors.ServerConnectionException;



/**
 *
 * @author Paul Jones
 */
public class Server {
  
  private static final String SERVER_BASE_URL =
          "http://ashley.versvik.net/capstone/request.php";
  
  private static String userID = null, auth = null;
  
  /**
   * A convenience method to get a URLConnection. Every communication with the
   * server will require one, so it makes sense to streamline the operation
   * @return The configured connection
   * @throws MalformedURLException If the internally stored URL is incorrect
   * @throws IOException upon any I/O error
   */
  private static URLConnection getConnection() throws MalformedURLException,
          IOException {
    
    URLConnection conn = new URL(SERVER_BASE_URL).openConnection();
    conn.setDoInput(true);
    conn.setDoOutput(true);
    conn.setUseCaches(false);
    conn.setRequestProperty("Content-Type",
            "application/x-www-form-urlencoded");
    return conn;
  }

  @Deprecated
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
      return null;
    }
    catch (JSONException ex) {
      System.err.println("JSON exception during getTest");
      return null;
    }
    return result;
  }

  /**
   * Logs a user into the server. This is required before performing most other
   * server tasks
   * @param userName
   * @param password
   * @throws ServerConnectionException If an error occurs while connecting to
   * the server
   * @throws ServerBadLoginException If the provided user name is invalid
   * @throws JSONException If the JSON object returned by the server was of an
   * un expected format
   */
  public static void login(String userName, String password)
          throws ServerConnectionException, ServerBadLoginException,
          JSONException {
    PostBuilder post = new PostBuilder();
    post.add("request", "login");
    post.add("username", "noidea");
    post.add("password", "evenlessidea");
    JSONObject result = runTransaction(post);
    if( ((String)result.get("response")).equals("ERROR") ) {
      if( ((Integer)result.get("code")).equals(ServerBadLoginException.CODE))
        throw new ServerBadLoginException(((String)result.get("msg")));
      if( ((Integer)result.get("code")).equals(ServerCantLoginException.CODE))
        throw new ServerCantLoginException(((String)result.get("msg")));
    }
    userID = (String)result.get("userid");
    auth = (String)result.get(auth);
  }
  
  private static JSONObject runTransaction(PostBuilder post)
          throws ServerConnectionException {
    URLConnection conn = null;
    OutputStreamWriter out = null;
    BufferedReader in = null;
    String responseStr = "", buf;
    JSONObject responseObj = null;
    try {
      conn = getConnection();
      out = new OutputStreamWriter(conn.getOutputStream());
      in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      out.write(post.data);
      while( (buf = in.readLine()) != null)
        responseStr += buf;
      responseObj = new JSONObject(responseStr);
      in.close();
      out.close();
    } catch (JSONException ex) {
      throw new ServerConnectionException("Bad JSON response");
    } catch (MalformedURLException ex) {
      throw new ServerConnectionException("URL Error");
    } catch (IOException ex) {
      throw new ServerConnectionException("I/O Error");
    }
    return responseObj;
  }
  
  /**
   * This class exists because I'm lazy. It generates an encoded string for use
   * in a HTTP POST
   */
  private static class PostBuilder {
    public String data = null;
    
    public void add(String key, String value) {
      if(data != null)
        data += "&";
      else
        data = "";
      try {
        data += URLEncoder.encode(key, "UTF-8");
        data += "=";
        data += URLEncoder.encode(value, "UTF-8");
      } catch (UnsupportedEncodingException ex) {
        // Should never happen
      }
    }
    
    @Override
    public String toString() {
      return data;
    }
  }
}

