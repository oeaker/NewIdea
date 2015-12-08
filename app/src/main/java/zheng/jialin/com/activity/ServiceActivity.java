package zheng.jialin.com.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import zheng.jialin.com.helloandroid.R;
import zheng.jialin.com.service.MyService;

/**
 * Created by jialin.zheng on 2015/12/7.
 */
public class ServiceActivity extends Activity implements View.OnClickListener, ServiceConnection {


    private Intent intent;

    private MyService.Mybinder binder;

    private EditText editText;

    private Button unbindBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_layout);

        intent = new Intent(this, MyService.class);
        editText = (EditText) findViewById(R.id.edit_txt);
        findViewById(R.id.startService_Btn).setOnClickListener(this);
        findViewById(R.id.stopService_Btn).setOnClickListener(this);
        findViewById(R.id.bindService_Btn).setOnClickListener(this);

        unbindBtn = (Button) findViewById(R.id.UnbindService_Btn);

        // 默认为不可点击
        unbindBtn.setEnabled(false);
        unbindBtn.setOnClickListener(this);
        findViewById(R.id.syncData_Btn).setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.startService_Btn:
                startService(intent);
                break;
            case R.id.stopService_Btn:
                stopService(intent);
                break;
            case R.id.bindService_Btn:
                bindService(intent, this, BIND_AUTO_CREATE);
                // 绑定之后才能点击
                unbindBtn.setEnabled(true);
                break;
            case R.id.UnbindService_Btn:
                unbindService(this);
                // 解除绑定之后按钮不可点击，直到下一次绑定完成
                unbindBtn.setEnabled(false);
                break;
            case R.id.syncData_Btn:
                if (binder != null) {
                    binder.setDate(getEditTextValue());
                }
                break;
        }
    }

    /**
     * Called when a connection to the Service has been established, with
     * the {@link IBinder} of the communication channel to the
     * Service.
     *
     * @param name    The concrete component name of the service that has
     *                been connected.
     * @param service The IBinder of the Service's communication channel,
     */
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        binder = (MyService.Mybinder) service;
        System.out.println("this service is binging...");
    }

    /**
     * Called when a connection to the Service has been lost.  This typically
     * happens when the process hosting the service has crashed or been killed.
     * This does <em>not</em> remove the ServiceConnection itself -- this
     * binding to the service will remain active, and you will receive a call
     * to {@link #onServiceConnected} when the Service is next running.
     *
     * @param name The concrete component name of the service whose
     *             connection has been lost.
     */
    @Override
    public void onServiceDisconnected(ComponentName name) {
        System.out.println("service connection is disconnected");
    }

    private String getEditTextValue() {
        if (editText != null) {
            return editText.getText().toString();
        } else {
            return "000";
        }
    }
}
