package com.youli.testwifi2017_9_25;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * 作者: zhengbin on 2017/9/25.
 * <p>
 * 邮箱:2381447237@qq.com
 * <p>
 * github:2381447237
 */

public class WifiService extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        Log.e("2017/9/25","===onBind===");

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("2017/9/25","===onStartCommand===");

        intentFilter=new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver=new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);


        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        unregisterReceiver(networkChangeReceiver);
    }

    private IntentFilter intentFilter;

    private NetworkChangeReceiver networkChangeReceiver;


    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager cManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            Log.e("2017/10/30","瓜牛大师");

            NetworkInfo networkInfo=cManager.getActiveNetworkInfo();
            WifiManager     wifiManager=(WifiManager)getSystemService(Context.WIFI_SERVICE);
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(context,"wifi可用",Toast.LENGTH_SHORT).show();
              //  wifiManager.setWifiEnabled(false);
            }else{
                Toast.makeText(context,"wifi不可用",Toast.LENGTH_SHORT).show();
            }

        }

    }
}
