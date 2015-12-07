package zheng.jialin.com.until;

import android.app.Application;
import android.util.Log;

import zheng.jialin.com.helloandroid.MainActivity;

/**
 * Created by jialin.zheng on 2015/12/6.
 */
public class App extends Application {

    private String shareData = "default";

    public void setShareData(String shareData) {
        this.shareData = shareData;
    }

    public String getShareData() {
        return shareData;
    }

    @Override
    public void onCreate() {
        Log.d(MainActivity.TAG,"this is a global oncreate msg");
        super.onCreate();
    }
}
