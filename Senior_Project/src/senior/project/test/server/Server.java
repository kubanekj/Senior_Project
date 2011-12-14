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
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import senior.project.test.server.errors.*;



/**
 *
 * @author Paul Jones
 */
public class Server implements ServerConstants {
  
  private static final String SERVER_BASE_URL =
          "http://ashley.versvik.net/capstone/request.php";
  
  private static final SimpleDateFormat DATE_FORMAT =
          new SimpleDateFormat("yyyy-MM-dd");
  
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
   * This is the request you would use to retrieve the complete fitness table.
   * @return The fitness table, encoded into a JSONObject
   * @throws ServerConnectionException If an error occurs during communication
   * with the server
   * @throws ServerBadRetrievalException If a server-side error occurs during
   * the transaction
   * @throws JSONException Upon receipt of A JSONObject with unexpected format
   */
  public static JSONObject listFitness() throws ServerConnectionException,
          ServerBadRetrievalException, JSONException {
    PostBuilder post = new PostBuilder();
    post.add("request", "list_fitness");
    JSONObject response = runTransaction(post);
    if( ((String)response.get("response")).equals("ERROR") ) {
      throw new ServerBadRetrievalException((String)response.get("msg"));
    }
    return response;
  }
  
  /**
   * This is the request you would use to retrieve the complete fitness table
   * @return The fitness table, encoded as a JSONObject
   * @throws ServerConnectionException If an error occurs during communication
   * with the server
   * @throws ServerBadRetrievalException If a server-side error occurs during
   * the transaction
   * @throws JSONException Upon receipt of A JSONObject with unexpected format
   */
  public static JSONObject listNutrition() throws ServerConnectionException,
          JSONException, ServerBadRetrievalException {
    PostBuilder post = new PostBuilder();
    post.add("request", "list_nutrition");
    JSONObject response = runTransaction(post);
    if( ((String)response.get("response")).equals("ERROR") ) {
      throw new ServerBadRetrievalException((String)response.get("msg"));
    }
    return response;
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
      if( ((Integer)result.get("code")).equals(BAD_LOGIN))
        throw new ServerBadLoginException(((String)result.get("msg")));
      if( ((Integer)result.get("code")).equals(CANT_LOGIN))
        throw new ServerCantLoginException(((String)result.get("msg")));
    }
    userID = (String)result.get("userid");
    auth = (String)result.get(auth);
  }
  
  /**
   * Registers a new user
   * @param userName the desired user name
   * @param password1 the desired password
   * @param password2 the desired password again, for confirmation
   * @param email1 the user's email address
   * @param email2 the user's email address again for confirmation
   * @param gender the user's gender
   * @param mesUnit the user's preferred measurement system
   * @param weight the user's weight, in units indicated by mesUnit
   * @param height the user's height, formatted as appropriate for mesUnit
   * @param birthday the user's date of birth
   * @throws ServerConnectionException
   * @throws JSONException
   * @throws ServerInvalidPasswordException
   * @throws ServerInvalidUserException
   * @throws ServerInvalidEmailException
   * @throws ServerInvalidGenderException
   * @throws ServerInvalidDateException
   * @throws ServerInvalidUnitException
   * @throws ServerInvalidWeightException
   * @throws ServerInvalidHeightException 
   */
  public static void register(String userName, String password1,
          String password2, String email1, String email2, Gender gender,
          MeasurementSystem mesUnit, double weight, String height, Date birthday)
          throws ServerConnectionException, JSONException,
          ServerInvalidPasswordException, ServerInvalidUserException,
          ServerInvalidEmailException, ServerInvalidWeightException,
          ServerInvalidHeightException, ServerInvalidDateException {
    PostBuilder post = new PostBuilder();
    post.add("request", "register");
    post.add("username", userName);
    post.add("password_1", password1);
    post.add("password_2", password2);
    post.add("email_1", email1);
    post.add("email_2", email2);
    switch(gender) {
      case MALE:
        post.add("gender", "M");
        break;
      case FEMALE:
        post.add("gender", "F");
        break;
    }
    switch(mesUnit) {
      case US:
        post.add("unit", "us");
        break;
      case METRIC:
        post.add("unit", "me");
        break;
    }
    post.add("weight", "" + weight);
    post.add("height", height);
    post.add("birthday", DATE_FORMAT.format(birthday));
    JSONObject response = runTransaction(post);
    if(((String)response.get("response")).equals("ERROR")) {
      switch((Integer)response.get("code")) {
        case INVALID_USER:
        case USERNAME_REGD:
          throw new ServerInvalidUserException((String)response.get("msg"));
        case INVALID_PASSWORD:
        case INVALID_PASSWORD_MATCH:
          throw new ServerInvalidPasswordException((String)response.get("msg"));
        case INVALID_EMAIL:
        case INVALID_EMAIL_MATCH:
        case EMAIL_REGD:
          throw new ServerInvalidEmailException((String)response.get("msg"));
        case INVALID_GENDER:
          throw new ServerInvalidGenderException((String)response.get("msg"));
        case INVALID_DATE:
          throw new ServerInvalidDateException((String)response.get("msg"));
        case INVALID_UNIT:
          throw new ServerInvalidUnitException((String)response.get("msg"));
        case INVALID_WEIGHT:
          throw new ServerInvalidWeightException((String)response.get("msg"));
        case INVALID_HEIGHT:
          throw new ServerInvalidHeightException((String)response.get("msg"));
        case BAD_INSERT:
          throw new ServerConnectionException((String)response.get("msg"));
      }
    }
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
   * Retrieves the dietary consumption of the user over the given time range
   * @param start
   * @param end
   * @return
   * @throws ServerConnectionException
   * @throws JSONException
   * @throws ServerInvalidUserException
   * @throws ServerInvalidDateException
   * @throws ServerBadDateRangeException
   * @throws ServerInvalidKeyException 
   */
  public static JSONObject trackDiet(Date start, Date end) throws
          ServerConnectionException, JSONException, ServerInvalidUserException,
          ServerInvalidDateException, ServerBadDateRangeException,
          ServerInvalidKeyException {
    if(userID == null || auth == null)
      throw new ServerConnectionException("trackDiet requires login");
    PostBuilder post = new PostBuilder();
    post.add("request", "track_diet");
    post.add("userid", userID);
    post.add("auth", auth);
    post.add("startdate", DATE_FORMAT.format(start));
    post.add("enddate", DATE_FORMAT.format(end));
    JSONObject response = runTransaction(post);
    if(((String)response.get("response")).equals("ERROR")) {
      switch((Integer)response.get("code")) {
        case INVALID_USER:
          throw new ServerInvalidUserException((String)response.get("msg"));
        case INVALID_DATE:
          throw new ServerInvalidDateException((String)response.get("msg"));
        case BAD_DATE_RANGE:
          throw new ServerBadDateRangeException((String)response.get("msg"));
        case BAD_RETRIEVAL:
          throw new ServerConnectionException((String)response.get("msg"));
        case INVALID_KEY:
          throw new ServerInvalidKeyException((String)response.get("msg"));
      }
    }
    return response;
  }
  
  /**
   * Retrieves the exercise events of the user over the given time range
   * @param start
   * @param end
   * @return
   * @throws ServerConnectionException
   * @throws JSONException
   * @throws ServerInvalidUserException
   * @throws ServerInvalidDateException
   * @throws ServerBadDateRangeException
   * @throws ServerInvalidKeyException 
   */
  public static JSONObject trackFitness(Date start, Date end) throws
          ServerConnectionException, JSONException, ServerInvalidUserException,
          ServerInvalidDateException, ServerBadDateRangeException,
          ServerInvalidKeyException {
    if(userID == null || auth == null)
      throw new ServerConnectionException("trackFitness requires login");
    PostBuilder post = new PostBuilder();
    post.add("request", "track_fitness");
    post.add("userid", userID);
    post.add("auth", auth);
    post.add("startdate", DATE_FORMAT.format(start));
    post.add("enddate", DATE_FORMAT.format(end));
    JSONObject response = runTransaction(post);
    if(((String)response.get("response")).equals("ERROR")) {
      switch((Integer)response.get("code")) {
        case INVALID_USER:
          throw new ServerInvalidUserException((String)response.get("msg"));
        case INVALID_DATE:
          throw new ServerInvalidDateException((String)response.get("msg"));
        case BAD_DATE_RANGE:
          throw new ServerBadDateRangeException((String)response.get("msg"));
        case BAD_RETRIEVAL:
          throw new ServerConnectionException((String)response.get("msg"));
        case INVALID_KEY:
          throw new ServerInvalidKeyException((String)response.get("msg"));
      }
    }
    return response;
  }
  
  /**
   * Retrieves the user's changes in weight over the provided time range
   * @param start
   * @param end
   * @return
   * @throws ServerConnectionException
   * @throws JSONException
   * @throws ServerInvalidUserException
   * @throws ServerInvalidDateException
   * @throws ServerBadDateRangeException
   * @throws ServerInvalidKeyException 
   */
  public static JSONObject trackWeight(Date start, Date end) throws
          ServerConnectionException, JSONException, ServerInvalidUserException,
          ServerInvalidDateException, ServerBadDateRangeException,
          ServerInvalidKeyException {
    if(userID == null || auth == null)
      throw new ServerConnectionException("trackWeight requires login");
    PostBuilder post = new PostBuilder();
    post.add("request", "track_weight");
    post.add("userid", userID);
    post.add("auth", auth);
    post.add("startdate", DATE_FORMAT.format(start));
    post.add("enddate", DATE_FORMAT.format(end));
    JSONObject response = runTransaction(post);
    if(((String)response.get("response")).equals("ERROR")) {
      switch((Integer)response.get("code")) {
        case INVALID_USER:
          throw new ServerInvalidUserException((String)response.get("msg"));
        case INVALID_DATE:
          throw new ServerInvalidDateException((String)response.get("msg"));
        case BAD_DATE_RANGE:
          throw new ServerBadDateRangeException((String)response.get("msg"));
        case BAD_RETRIEVAL:
          throw new ServerConnectionException((String)response.get("msg"));
        case INVALID_KEY:
          throw new ServerInvalidKeyException((String)response.get("msg"));
      }
    }
    return response;
  }
  
  /**
   * Adds a meal to the user's history.
   * @param date
   * @param id
   * @param amount
   * @param type
   * @throws ServerConnectionException
   * @throws JSONException
   * @throws ServerInvalidUserException
   * @throws ServerInvalidItemException
   * @throws ServerInvalidDateException
   * @throws ServerInvalidMealException
   * @throws ServerInvalidAmountException
   * @throws ServerInvalidKeyException 
   */
  public static void updateDiet(Date date, int id, double amount,
          MealType type) throws ServerConnectionException, JSONException,
          ServerInvalidUserException, ServerInvalidItemException,
          ServerInvalidDateException, ServerInvalidMealException,
          ServerInvalidAmountException, ServerInvalidKeyException {
    if(userID == null || auth == null)
      throw new ServerConnectionException("updateDiet requires login");
    PostBuilder post = new PostBuilder();
    post.add("request", "update_diet");
    post.add("userid", userID);
    post.add("auth", auth);
    post.add("date", DATE_FORMAT.format(date));
    post.add("id", "" + id);
    post.add("amount", "" + amount);
    switch(type) {
      case BREAKFAST:
        post.add("meal", "breakfast");
        break;
      case DINNER:
        post.add("meal", "dinner");
        break;
      case LUNCH:
        post.add("meal", "lunch");
        break;
      case SNACK:
        post.add("meal", "snack");
        break;
    }
    JSONObject response = runTransaction(post);
    if(((String)response.get("response")).equals("ERROR")) {
      switch((Integer)response.get("code")) {
        case INVALID_USER:
          throw new ServerInvalidUserException((String)response.get("msg"));
        case INVALID_ITEM:
          throw new ServerInvalidItemException((String)response.get("msg"));
        case INVALID_DATE:
          throw new ServerInvalidDateException((String)response.get("msg"));
        case INVALID_MEAL:
          throw new ServerInvalidMealException((String)response.get("msg"));
        case INVALID_AMOUNT:
          throw new ServerInvalidAmountException((String)response.get("msg"));
        case BAD_INSERT:
          throw new ServerConnectionException((String)response.get("msg"));
        case INVALID_KEY:
          throw new ServerInvalidKeyException((String)response.get("msg"));
      }
    }
  }
  
  /**
   * Adds an exercise session to the user's history
   * @param date
   * @param id
   * @param lengthInMinutes
   * @param amount
   * @param intensity
   * @throws ServerConnectionException
   * @throws JSONException
   * @throws ServerInvalidUserException
   * @throws ServerInvalidItemException
   * @throws ServerInvalidDateException
   * @throws ServerInvalidTimeException
   * @throws ServerInvalidAmountException
   * @throws ServerInvalidIntensityException
   * @throws ServerInvalidKeyException 
   */
  public static void updateFitness(Date date, int id, int lengthInMinutes,
          int amount, ExerciseIntensity intensity) throws
          ServerConnectionException, JSONException, ServerInvalidUserException,
          ServerInvalidItemException, ServerInvalidDateException,
          ServerInvalidTimeException, ServerInvalidAmountException,
          ServerInvalidKeyException {
    if(userID == null || auth == null)
      throw new ServerConnectionException("updateFitness requires login");
    PostBuilder post = new PostBuilder();
    post.add("request", "update_fitness");
    post.add("userid", userID);
    post.add("auth", auth);
    post.add("date", DATE_FORMAT.format(date));
    post.add("id", "" + id);
    post.add("time", "" + lengthInMinutes);
    post.add("amount", "" + amount);
    switch(intensity) {
      case LIGHT:
        post.add("intensity", "light");
        break;
      case MODERATE:
        post.add("intensity", "moderate");
        break;
      case INTENSE:
        post.add("intensity", "intense");
        break;
    }
    JSONObject response = runTransaction(post);
    if(((String)response.get("response")).equals("ERROR")) {
      switch((Integer)response.get("code")) {
        case INVALID_USER:
          throw new ServerInvalidUserException((String)response.get("msg"));
        case INVALID_ITEM:
          throw new ServerInvalidItemException((String)response.get("msg"));
        case INVALID_DATE:
          throw new ServerInvalidDateException((String)response.get("msg"));
        case INVALID_AMOUNT:
          throw new ServerInvalidAmountException((String)response.get("msg"));
        case INVALID_TIME:
          throw new ServerInvalidTimeException((String)response.get("msg"));
        case INVALID_INTENSITY:
          throw new ServerInvalidIntensityException((String)response.get("msg"));
        case BAD_INSERT:
          throw new ServerConnectionException((String)response.get("msg"));
        case INVALID_KEY:
          throw new ServerInvalidKeyException((String)response.get("msg"));
      }
    }
  }
  
  /**
   * Add a goal to the user's account
   * @param type The type of goal. Currently meaningless
   * @param start The date the goal should start
   * @param finish The date the goal should be finished.
   * @param weight The desired weight.
   */
  public static void updateGoal(GoalType type, Date start, Date finish,
          int weight) throws ServerConnectionException, JSONException,
          ServerInvalidUserException, ServerInvalidDateException,
          ServerInvalidWeightException, ServerBadDateRangeException,
          ServerInvalidKeyException {
    if(userID == null || auth == null)
      throw new ServerConnectionException("updateGoal requires login");
    PostBuilder post = new PostBuilder();
    post.add("request", "update_goal");
    post.add("userid", userID);
    post.add("auth", auth);
    int typeCode;
    // These aren't accurate. I'll fix them when they are
    switch(type) {
      case LOSE_WEIGHT:
        typeCode = 1;
        break;
      case MAINTAIN_WEIGHT:
        typeCode = 2;
        break;
      default:
        typeCode = 3;
        break;
    }
    post.add("id", "" + typeCode);
    post.add("startdate", DATE_FORMAT.format(start));
    post.add("enddate", DATE_FORMAT.format(finish));
    post.add("weight", "" + weight);
    JSONObject response = runTransaction(post);
    if(((String)response.get("response")).equals("ERROR")) {
      switch((Integer)response.get("code")) {
        case INVALID_USER:
          throw new ServerInvalidUserException((String)response.get("msg"));
        case INVALID_DATE:
          throw new ServerInvalidDateException((String)response.get("msg"));
        case INVALID_ITEM:
          throw new ServerInvalidGoalTypeException((String)response.get("msg"));
        case INVALID_WEIGHT:
          throw new ServerInvalidWeightException((String)response.get("msg"));
        case BAD_DATE_RANGE:
          throw new ServerBadDateRangeException((String)response.get("msg"));
        case BAD_INSERT:
          throw new ServerConnectionException((String)response.get("msg"));
        case INVALID_KEY:
          throw new ServerInvalidKeyException((String)response.get("msg"));
      }
    }
  }
  
  /**
   * Updates the user's weight as of the specified date
   * @param date
   * @param weight
   * @throws ServerConnectionException
   * @throws JSONException
   * @throws ServerInvalidUserException
   * @throws ServerInvalidDateException
   * @throws ServerInvalidWeightException
   * @throws ServerInvalidKeyException 
   */
  public static void updateWeight(Date date, int weight) throws ServerConnectionException, JSONException, ServerInvalidUserException, ServerInvalidDateException, ServerInvalidWeightException, ServerInvalidKeyException {
    if(userID == null || auth == null)
      throw new ServerConnectionException("updateWeight requires login");
    PostBuilder post = new PostBuilder();
    post.add("request", "update_weight");
    post.add("userid", userID);
    post.add("auth", auth);
    post.add("date", DATE_FORMAT.format(date));
    post.add("weight", "" + weight);
    JSONObject response = runTransaction(post);
    if( ((String)response.get("response")).equals("ERROR") ) {
      switch( (Integer)response.get("code")) {
        case INVALID_USER:
          throw new ServerInvalidUserException((String)response.get("msg") );
        case INVALID_DATE:
          throw new ServerInvalidDateException((String)response.get("msg") );
        case INVALID_WEIGHT:
          throw new ServerInvalidWeightException((String)response.get("msg"));
        case BAD_INSERT:
          throw new ServerConnectionException((String)response.get("msg") );
        case INVALID_KEY:
          throw new ServerInvalidKeyException((String)response.get("msg"));
      }
    }
  }
  
  /**
   * This request gives you all of the relevant current user data such as
   * join-date, gender, birth date, height, and current weight.
   * @return
   * @throws ServerConnectionException
   * @throws JSONException
   * @throws ServerInvalidUserException
   * @throws ServerInvalidKeyException 
   */
  public static JSONObject userData() throws ServerConnectionException,
          JSONException, ServerInvalidUserException, ServerInvalidKeyException {
    if(userID == null || auth == null)
      throw new ServerConnectionException("userData requires login");
    PostBuilder post = new PostBuilder();
    post.add("request", "user_data");
    post.add("userid", userID);
    post.add("auth", auth);
    JSONObject response = runTransaction(post);
    if(((String)response.get("response")).equals("ERROR")) {
      switch((Integer)response.get("code")) {
        case INVALID_USER:
          throw new ServerInvalidUserException((String)response.get("msg"));
        case INVALID_KEY:
          throw new ServerInvalidKeyException((String)response.get("msg"));
        case BAD_INSERT:
          throw new ServerConnectionException((String)response.get("msg"));
      }
    }
    return response;
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

