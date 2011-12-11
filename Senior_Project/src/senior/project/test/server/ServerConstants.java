package senior.project.test.server;

/**
 *
 * @author Paul Jones
 */
public interface ServerConstants {
  
  public enum GoalType { LOSE_WEIGHT, MAINTAIN_WEIGHT, BUILD_MUSCLE }
  
  public enum MealType { BREAKFAST, DINNER, LUNCH, SNACK }
  
  public static final int UNKNOWN_ERROR = 1;
  public static final int INVALID_USER = 2;
  public static final int INVALID_ITEM = 3;
  public static final int INVALID_DATE = 4;
  public static final int BAD_DATE_RANGE = 5;
  public static final int INVALID_MEAL = 6;
  public static final int INVALID_AMOUNT = 7;
  public static final int INVALID_TIME = 8;
  public static final int INVALID_WEIGHT = 9;
  public static final int INVALID_INTENSITY = 10;
  public static final int INVALID_USERNAME = 11;
  public static final int INVALID_PASSWORD = 12;
  public static final int INVALID_PASSWORD_MATCH = 13;
  public static final int INVALID_EMAIL = 14;
  public static final int INVALID_EMAIL_MATCH = 15;
  public static final int INVALID_GENDER = 16;
  public static final int INVALID_HEIGHT = 17;
  public static final int INVALID_UNIT = 18;
  public static final int USERNAME_REGD = 19;
  public static final int EMAIL_REGD = 20;
  public static final int BAD_INSERT = 200;
  public static final int BAD_RETRIEVAL = 201;
  public static final int BAD_LOGIN = 300;
  public static final int CANT_LOGIN = 301;
  public static final int INVALID_KEY = 302;
}
