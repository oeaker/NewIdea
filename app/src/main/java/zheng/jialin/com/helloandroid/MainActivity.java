package zheng.jialin.com.helloandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    /**
     * 提示信息
     */
    public final static String TAG = "zjl";

    /**
     * 请求码
     */
    public final static int REQUEST_CODE = 1;

    /**
     * default timezone is china
     */
    public final static String TIME_ZONE = "Asia/Shanghai";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "这里是提示信息");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView display = (TextView) findViewById(R.id.hehe);

        Button btn = (Button) findViewById(R.id.testbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "这里是提示信息", Toast.LENGTH_LONG).show();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                //  http://www.joda.org/joda-time/userguide.html
                // 获取时间
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
                display.setText("当前时间是" + simpleDateFormat.format(new Date()));
                display.setTextColor(Color.RED);

                // 使用joda - time进行时间操作
                DateTime dateTime = new DateTime(DateTimeZone.forID("Asia/Shanghai"));
                Log.d(TAG, "joda设置时间" + dateTime.toString("yyyy-MM-dd HH:mm:ss"));
                DateTime newyear = new DateTime(2016, 1, 1, 0, 0, 0);
                Log.d(TAG, "距离元旦还有的天数" + Days.daysBetween(dateTime, newyear));

                // 时间比较
                Log.d(TAG, "compare with now" + dateTime.isBeforeNow());
            }
        });

        Button nextBtn = (Button) findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                intent.putExtra("info", "time is " + new DateTime(DateTimeZone.forID(TIME_ZONE)).toString("yyyy-MM-dd HH:mm:ss"));
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    /**
     * catch activity result
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == DisplayActivity.RESULT_CODE) {
            Log.d(TAG, "get result from displayActivity");
            Bundle bundle = data.getExtras();
            if (null != bundle) {
                Log.d(TAG, "result is " + bundle.getString("result"));
                Log.d(TAG, "back is" + bundle.getString("back"));
                Log.d(TAG, "这行代码是二次提交的");
            } else {
                Log.e(TAG, "this is no result");
            }
        } else {
            Log.d(TAG, "catch result,but is need,requestcode is " + resultCode + ",resultcode is" +
                    resultCode);
        }
    }
}

