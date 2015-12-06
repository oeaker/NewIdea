package zheng.jialin.com.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jialin.zheng on 2015/12/6.
 */
public class ClockActivity extends Activity implements View.OnClickListener {

    /**
     * 申明展示框
     */
    private TextView timerDisplay = null;

    Handler handler = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (timerDisplay != null) {
                    timerDisplay.setText(new DateTime(DateTimeZone.forID(MainActivity.TIME_ZONE)).toString("yyyy-MM-dd HH:mm:ss"));
                }
                super.handleMessage(msg);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_layout);

        timerDisplay = (TextView) findViewById(R.id.clock_txt);
        final DateTime time = new DateTime(DateTimeZone.forID(MainActivity.TIME_ZONE));

        if (null != timerDisplay) {
            timerDisplay.setText(time.toString("yyyy-MM-dd HH:mm:ss"));
        } else {
            Log.e(MainActivity.TAG, "get timer display text error");
        }


        Button timerBtn = (Button) findViewById(R.id.timer_Btn);
        timerBtn.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.timer_Btn:
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = 1;
                        handler.sendMessage(msg);
                    }
                };

                Timer timer = new Timer(true);
                timer.schedule(task, 1000, 1000);
                break;
            default:
                break;
        }
    }
}
