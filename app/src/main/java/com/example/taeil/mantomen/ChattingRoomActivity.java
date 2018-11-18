package com.example.taeil.mantomen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.List;

public class ChattingRoomActivity extends AppCompatActivity {

    // private Socket mSocket;
    ListView chatlist;
    EditText editText;
    Button button;
    Variable variable;
    VariableOfClass variableOfClass;
    ChatData chatData;
    ChatDataListAdapter chatDataListAdapter;
    List<ChatData> ChatDataList;  //리스트뷰와 어댑터 초기화

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_room);
        // mSocket = MyService.getmSocket();  // 소켓생성

        ListView chatlistView = findViewById(R.id.chatlist);


        chatlist = findViewById(R.id.chatlist);
        editText = findViewById(R.id.edittext);
        button = findViewById(R.id.sendbutton);


        if (variableOfClass.getChatData() == null) {

        } else {
            // List<AllClass> AllClassList;  //리스트뷰와 어댑터 초기화

            ChatDataList = variableOfClass.getChatData();  //저장된 컬랙션호출
            chatDataListAdapter = new ChatDataListAdapter(this, ChatDataList);
            chatlist.setAdapter(chatDataListAdapter);  // 어댑터연결
            chatDataListAdapter.notifyDataSetChanged();

            //리스트뷰를 펼치기위해 높이를 설정한 메서드를 실행한것
            // listViewHeightSet(allClassListAdapter, fitlistView);

        }





        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000); //2초에 한번씩검사
                        // textView.setText(variableOfClass.getChatObjectContents());
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
                    MyService.getmSocket().emit("login", data);  // 이밋이 보낸느거 온이 받는거
                    Log.d("소켓",data.toString());
                    chatData = new ChatData(variable.getUserID(),"내용");
                    variableOfClass.getChatData().add(chatData);


                    editText.setText("");


                    // textView.setText(textView.getText().toString() + "\n" + editText.getText().toString());  // 내용을 추가

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }


//    private Emitter.Listener onNewMessage = new Emitter.Listener() {
//        @Override
//        public void call(final Object... args) {
//            ChattingRoomActivity.this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    JSONObject data = (JSONObject) args[0];
//                    String username;
//                    String message;
//                    try {
//                        //username = data.getString("userID");
//                        message = data.getString("msg");
//                        textView.setText(message);
//                        Log.d("소켓", message);
//                    } catch (JSONException e) {
//                        return;
//                    }
//                    // 메시지를 받으면 data에 담고,
//                    // username와 message라는 키값으로 들어왔다는 가정으로 작성된 코드다.
//                    // addMessage(username, message); 이런 식으로 코드를 실행시키면 addMessage 쪽으로 인자를 담아 보내니 화면에 노출하게 만들면 될 것이다.
//
//                }
//            });
//        }
//    };


}
