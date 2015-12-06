package zheng.jialin.com.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import zheng.jialin.com.helloandroid.R;
import zheng.jialin.com.until.App;

/**
 * Created by jialin.zheng on 2015/12/6.
 */
public class ContentActivity extends Activity {

    /**
     * 文本信息
     */
    private TextView tv = null;

    private ImageView imageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tv = new TextView(this);
        tv.setText(((App) getApplicationContext()).getShareData());
        setContentView(tv);

        ((App) getApplicationContext()).setShareData("进入的页面是"+this);
        imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher);
//        setContentView(imageView);
    }
}
