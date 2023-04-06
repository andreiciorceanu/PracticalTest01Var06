package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

public class ProcessingThread extends Thread {
    private Context context = null;
    private boolean isRunning = true;
    private int scor;

    public ProcessingThread(Context context, int a) {
        this.context = context;
        scor = a;
    }

    @Override
    public void run() {
        Log.d("PracticalTest01Var03Service", "Thread has started!");
        while (isRunning) {
            if(scor > 300) {
                sleep();
                sendMessage1();
                stopThread();
            } else {
                stopThread();
            }
        }
        Log.d("PracticalTest01Var03Service", "Thread has stopped!");
    }

    private void sendMessage1() {
        Intent intent = new Intent();
        intent.setAction("calculareScor");
        //System.out.println("a trecut pe aici");
        intent.putExtra("message",
                new Date(System.currentTimeMillis()) + " " + scor);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }

}
