package zheng.jialin.com.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by jialin.zheng on 2015/12/6.
 */
public class DisplayActivity extends Activity {

    /**
     * 返回码
     */
    public static final int RESULT_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_1);

        TextView display = (TextView) findViewById(R.id.display_1);

        Intent intent = getIntent();
        if (null != intent && null != display) {
            String info = intent.getStringExtra("info");
            if (null == info || info.isEmpty()) {
                Log.e(MainActivity.TAG, "info is null or empty");
            } else {
                display.setText(info);
            }
        }
    }

    /**
     * 监听返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.putExtra("back", "这里是第二个activity的返回值信息");
            setResult(RESULT_CODE, intent);
            finish();// destory activity
        }

        return false;
    }
}
