package zheng.jialin.com.until;

import android.app.Application;

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
}
