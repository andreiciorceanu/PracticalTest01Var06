package ro.pub.cs.systems.eim.practicaltest01var06;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var06Service extends Service {
    private ProcessingThread processingThread;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("onStartCommand", "startService() method was invoked");
        int scor = intent.getIntExtra("scorTotal", 0);
        //System.out.println("scor din serviceeee");
        processingThread = new ProcessingThread(this, scor);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d("PracticalTest01Var06Service", "Service has stopped!");
        processingThread.stopThread();
    }
}