package app.task.com.softTsk.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Raos on 6/16/2017.
 */
public class NetworkStatus {
    private static ConnectivityManager connectivityManager =null;
    private static  NetworkInfo[] networkInfos = null;

    private static int TYPE_WIFI = -1;
    private static int TYPE_MOBILE = 2;
    private static int TYPE_NOT_CONNECTED = 0;
    /**
     * This method checks the internet availability
     * @param context
     * @return boolean
     */
    public static boolean isConnected(Context context){
        connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager!=null){
           networkInfos =  connectivityManager.getAllNetworkInfo();
            if(networkInfos!=null){
                for (int i=0;i<networkInfos.length;i++){
                    if(networkInfos[i].getState() == NetworkInfo.State.CONNECTED){
                        return true;
                    }
                }
            }

        }
        return false;
    }

    /*
    This method is used to identify whether device is connected to mobile data or wifi
    @param context
    @return int -1 for wifi,2- mobile,0-not connected
     */
    public static  int getNetworkType(Context context){
        if(connectivityManager!=null){
            connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;

    }

}
