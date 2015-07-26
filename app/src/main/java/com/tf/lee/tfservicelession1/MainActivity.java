package com.tf.lee.tfservicelession1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener,ServiceConnection {

    private Button startBtnService,stopBtnService,bindServiceBtn,unbindServiceBtn,getCurrentNumBtn;
    //定义一个intent启动serivice
    private Intent serivceIntent;
    /*定义一个临时接受service实例的变量*/
    private TFService tfService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate.............!");

        serivceIntent = new Intent(MainActivity.this,TFService.class);//初始化一个service

        setContentView(R.layout.activity_main);
        startBtnService = (Button)findViewById(R.id.serviceBtn1);
        stopBtnService = (Button)findViewById(R.id.serviceBtn2);
        bindServiceBtn = (Button)findViewById(R.id.bindService);
        unbindServiceBtn = (Button)findViewById(R.id.unbindService);
        getCurrentNumBtn = (Button)findViewById(R.id.getCurrentNumBtn);


        //为两个按钮添加监听
        startBtnService.setOnClickListener(this);//当前类实现onClick接口
        stopBtnService.setOnClickListener(this);
        bindServiceBtn.setOnClickListener(this);
        unbindServiceBtn.setOnClickListener(this);
        getCurrentNumBtn.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onstart.............!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume.............!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause.............!");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop.............!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy.............!");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart.............!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        System.out.print("onCreateOptionsMenu.............!");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        System.out.print("onOptionsItemSelected.............!");

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //启动service
            case R.id.serviceBtn1:
                startService(serivceIntent);
                System.out.println("StartService");
                break;
            //停止service
            case R.id.serviceBtn2:
                stopService(serivceIntent);
                System.out.println("StopService");
                break;
            case R.id.bindService:
                bindService(serivceIntent, this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.unbindService:
                unbindService(this);
                //释放服务位空
                tfService = null;
                System.out.println("null");
                break;
            case R.id.getCurrentNumBtn:
                //做个判断
                if (tfService != null){
                    System.out.printf("当前服务数字是 %d",tfService.getCurrentNum());
                }
        }
    }

    //erviceConnection接口的监听回调
    public void onServiceConnected(android.content.ComponentName componentName, android.os.IBinder iBinder){
    //service成功
          System.out.println("onServiceConnect..........");
          /*绑定成功后，获取service的实例*/
          tfService = ((TFService.TFServiceBinder)iBinder).getServiceInstance();
    }

    public void onServiceDisconnected(android.content.ComponentName componentName){
    //service奔溃触发
        System.out.println("onServiceDisconnected..........");
    }

}
