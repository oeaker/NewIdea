package zheng.jialin.com.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import zheng.jialin.com.helloandroid.MainActivity;

/**
 * Created by jialin.zheng on 2015/12/7.
 */
public class MyService extends Service {


    private boolean serviceRunning = false;

    private String str = "default";

    /**
     * 这是参数的值
     *
     * @param str
     */
    public void setStr(String str) {
        this.str = str;
    }

    /**
     * Return the communication channel to the service.  May return null if
     * clients can not bind to the service.  The returned
     * {@link IBinder} is usually for a complex interface
     * that has been <a href="{@docRoot}guide/components/aidl.html">described using
     * aidl</a>.
     * <p/>
     * <p><em>Note that unlike other application components, calls on to the
     * IBinder interface returned here may not happen on the main thread
     * of the process</em>.  More information about the main thread can be found in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html">Processes and
     * Threads</a>.</p>
     *
     * @param intent The Intent that was used to bind to this service,
     *               as given to {@link Context#bindService
     *               Context.bindService}.  Note that any extras that were included with
     *               the Intent at that point will <em>not</em> be seen here.
     * @return Return an IBinder through which clients can call on to the
     * service.
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Mybinder();
    }

    public class Mybinder extends Binder {

        public void setDate(String string) {
            str = string;
        }
    }

    @Override
    public void onCreate() {
        new Thread() {
            @Override
            public void run() {
                while (serviceRunning) {
                    System.out.println(str);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        serviceRunning = false;
        System.out.println("this service is stop...");
        super.onDestroy();
    }

    /**
     * Each Time ths service start this method will be execute
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        serviceRunning = true;
        str = intent.getStringExtra("data");
        System.out.println("start command is done and data is " + str);
        return super.onStartCommand(intent, flags, startId);
    }
}