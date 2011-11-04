package senior.project.test;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


public class Connection {


    private static Connection instance = new Connection();
    ConnectivityManager connectivityManager;
    NetworkInfo wifiInfo, mobileInfo;
    static Context context;
    boolean connected = false;


    public static Connection getInstance(Context ctx) {

      context = ctx;
            return instance;
    }



public Boolean isOnline(Context con) {


try {
        connectivityManager = (ConnectivityManager) con
                        .getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo networkInfo = connectivityManager
                        .getActiveNetworkInfo();
        connected = networkInfo != null
                        && networkInfo.isAvailable() && networkInfo.isConnected();
        return connected;


} catch (Exception e) {
        System.out.println("CheckConnectivity Exception: " + e.getMessage());
        Log.v("connectivity", e.toString());
}


return connected;
 }
}
