package com.example.taeil.mantomen;

import android.os.Handler;

public class ServiceThread extends Thread{
    Handler handler;
    boolean isRun = true;

    public ServiceThread(Handler handler){
        this.handler = handler;
    }

    public void stopForever(){
        synchronized (this) {
            this.isRun = false;
        }
    }

    public void run(){
        //반복적으로 수행할 작업을 한다. // 아 이걸 그러면 리시브가 있을 때만 true로 해두고
        while(MyService.recieveflag){  //리시브 플래그가 트루일때만
        handler.sendEmptyMessage(0);//쓰레드에 있는 핸들러에게 메세지를 보냄 //10초마다 검사자나
        try{
            Thread.sleep(1000); // 1초씩 검사.
        }catch (Exception e) {}
    }
}




}
