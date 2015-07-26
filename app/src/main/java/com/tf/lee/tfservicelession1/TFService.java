package com.tf.lee.tfservicelession1;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

public class TFService extends Service {
    public TFService() {

    }

    /**/
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        System.out.println("onBind!!!!");
//        throw new UnsupportedOperationException("Not yet implemented");
        return  serviceBinder;//坑啊 o(╯□╰)o 这里需要返回一个值，不然没有绑定
    }

    //声明一个对象
    private  final  TFServiceBinder serviceBinder = new TFServiceBinder();
    //定义一个binder类
    public  class  TFServiceBinder extends Binder {
          //取得service实例方法
        public  TFService  getServiceInstance(){
            return TFService.this;
        }
    }

    //获取当前服务的数字
    public  int getCurrentNum() {
          return i;
    }

    //重写onCreat方法和onDestroy方法

    @Override
    public void onCreate() {
        System.out.println("onCreatService");
        super.onCreate();
        //执行开启计时器
        startTimer();
    }

    @Override
    public void onDestroy() {
        System.out.println("onDestroyService");
        super.onDestroy();
        //停止计时器
        stopTimer();
    }

    private Timer timer = null;
    private  TimerTask taskTimer = null;
    private int i = 0;

      /*开始计时器*/
    public  void  startTimer(){
      /*初始化为null*/
        if (timer == null){
            timer = new Timer();
            taskTimer = new TimerTask() {
                @Override
                public void run() {
                   i++;
                    System.out.println(i);
                }
            };
        }
        timer.schedule(taskTimer,1000,1000);
    }
       /*停止计时器*/
    public  void  stopTimer(){
          if (timer != null){
              taskTimer.cancel();
              timer.cancel();
          }
    }
}
