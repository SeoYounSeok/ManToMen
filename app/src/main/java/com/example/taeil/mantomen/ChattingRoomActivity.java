package com.example.taeil.mantomen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class ChattingRoomActivity extends AppCompatActivity {

    // private Socket mSocket;
    private static TextView textView;
    EditText editText;
    Button button;
    Variable variable;
    VariableOfClass variableOfClass;
    Thread thread;

//    {
//        try {
//            mSocket = IO.socket("http://ec2-54-180-106-61.ap-northeast-2.compute.amazonaws.com");
//            mSocket.connect();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_room);
        // mSocket = MyService.getmSocket();  // 소켓생성


        textView = findViewById(R.id.text);
        editText = findViewById(R.id.edittext);
        button = findViewById(R.id.sendbutton);

        // textView.setText(textView.getText() + "\n" + variableOfClass.getChatObjectContents()); // 상대채팅추가

//        MyService.getmSocket().on("receive", onNewMessage);
//        MyService.getmSocket().connect();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000); //5초씩 쉰다.

                        textView.setText(textView.getText().toString() + "\n" + variableOfClass.getChatObjectContents());

                    } catch (Exception e) {
                    }
                }

            }
        }).start();

        button.setOnClickListener(new View.OnClickListener() {  //센드 버튼이자나
            @Override
            public void onClick(View v) {
                JSONObject data = new JSONObject();
                try {
                    data.put("userid", variable.getUserID());
                    // data.put("message", editText.getText().toString());
                    editText.setText("");
                    MyService.getmSocket().emit("login", data);  // 이밋이 보낸느거 온이 받는거
                    Log.d("소켓", data.toString());

                    textView.setText(textView.getText().toString() + "\n" + editText.getText().toString());  // 내용을 추가

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            ChattingRoomActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    String message;
                    try {
                        //username = data.getString("userID");
                        message = data.getString("msg");
                        textView.setText(message);
                        Log.d("소켓", message);
                    } catch (JSONException e) {
                        return;
                    }
                    // 메시지를 받으면 data에 담고,
                    // username와 message라는 키값으로 들어왔다는 가정으로 작성된 코드다.
                    // addMessage(username, message); 이런 식으로 코드를 실행시키면 addMessage 쪽으로 인자를 담아 보내니 화면에 노출하게 만들면 될 것이다.

                }
            });
        }
    };


}
