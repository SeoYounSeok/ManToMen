package com.example.taeil.mantomen;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class MyService extends Service {
    NotificationManager Notifi_M;
    ServiceThread thread;
    Notification Notifi;
    private static Socket mSocket;
    Variable variable;
    VariableOfClass variableOfClass;
    ChatData chatData;

    public static Socket getmSocket() {
        return mSocket;
    }

    public void setmSocket(Socket mSocket) {
        this.mSocket = mSocket;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            mSocket = IO.socket(variable.HttpAddres);
            mSocket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        JSONObject data = new JSONObject();  // 최초 생성시에 아이디 보내준다.
        try {
            data.put("userid",variable.getUserID());
            // data.put("message", editText.getText().toString());
            // editText.setText("");
            mSocket.emit("login", data);  // 이밋이 보낸느거 온이 받는거
            Log.d("소켓",data.toString());
        } catch(JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notifi_M = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        myServiceHandler handler = new myServiceHandler();
        thread = new ServiceThread(handler);
        thread.start();

        return START_STICKY;
    }

    //서비스가 종료될 때 할 작업
    public void onDestroy() {
        thread.stopForever();
        thread = null;//쓰레기 값을 만들어서 빠르게 회수하라고 null을 넣어줌.
    }

    class myServiceHandler extends Handler {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void handleMessage(android.os.Message msg) {
            Intent intent = new Intent(MyService.this, LoginActivity.class);  // 로그인액티비티로 이동
            final PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


            mSocket.on("receive", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    String message;
                    try {
                        message = data.getString("msg");
                        //message = data.getString("message");
                        Log.d("로그2", message);

                        variableOfClass.setChatObjectContents(message);  // 상대방 채팅을 저장
                        chatData = new ChatData("상대아이디",message);
                        variableOfClass.getChatData().add(chatData);  // 컬렉션에 채팅을 저장

                        Notifi = new Notification.Builder(getApplicationContext())
                                .setContentTitle("맨투맨 알림")
                                .setContentText("확인해봐")
                                .setTicker("알림!!!")
                                .setSmallIcon(R.drawable.logo)
                                .setContentIntent(pendingIntent)
                                .build();
                        //소리추가
                        Notifi.defaults = Notification.DEFAULT_SOUND;
                        //알림 소리를 한번만 내도록
                        Notifi.flags = Notification.FLAG_ONLY_ALERT_ONCE;
                        //확인하면 자동으로 알림이 제거 되도록
                        Notifi.flags = Notification.FLAG_AUTO_CANCEL;

                        Notifi_M.notify(777, Notifi);
                        //토스트 띄우기
                        Toast.makeText(MyService.this, "메시지도착", Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        return;
                    } catch (RuntimeException e){
                        return;
                    }
                    // 메시지를 받으면 data에 담고,
                    // username와 message라는 키값으로 들어왔다는 가정으로 작성된 코드다.
                    // addMessage(username, message); 이런 식으로 코드를 실행시키면 addMessage 쪽으로 인자를 담아 보내니 화면에 노출하게 만들면 될 것이다.

                }
            });

            mSocket.connect();




        }
    }

}
