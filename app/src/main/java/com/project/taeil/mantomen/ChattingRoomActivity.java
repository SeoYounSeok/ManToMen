package com.project.taeil.mantomen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.project.taeil.mantomen.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ChattingRoomActivity extends AppCompatActivity {

    // private Socket mSocket;
    ListView chatlist;
    EditText editText;
    Button sendbutton;
    Button resetbutton;
    Variable variable;
    VariableOfClass variableOfClass;
    ChatData chatData;
    public static ChatDataListAdapter chatDataListAdapter;  // 퍼블릭 스태틱으로 선언해서 서비스에서 이용가능하게 만듦

    @Override
    protected void onResume() {
        super.onResume();
        // chatDataListAdapter.clearAllitems();  // 클리어
        // chatDataListAdapter.upDateItemList(ChatDataList);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_room);
        // mSocket = MyService.getmSocket();  // 소켓생성


        chatlist = findViewById(R.id.chatlist);
        editText = findViewById(R.id.edittext);
        sendbutton = findViewById(R.id.sendbutton);
        resetbutton = findViewById(R.id.resetbutton);

        // ChatDataList =   //저장된 컬랙션호출

        chatDataListAdapter = new ChatDataListAdapter(this, variableOfClass.getChatData());   // 처음으로 호출되는
        chatlist.setAdapter(chatDataListAdapter);  // 어댑터연결

        sendbutton.setOnClickListener(new View.OnClickListener() {  //센드 버튼이자나
            @Override
            public void onClick(View v) {
                JSONObject data = new JSONObject();
                try {
                    data.put("userid", variable.getUserID());
                    // data.put("message", editText.getText().toString());
                    // MyService.getmSocket().emit("send", data);  // 이밋이 보낸느거 온이 받는거 서비스관련안한다
                    Log.d("소켓", data.toString());
                    chatData = new ChatData(variable.getUserID(), editText.getText().toString());
                    variableOfClass.getChatData().add(chatData);
                    // chatDataListAdapter.clearAllitems();  // 클리어
                    //chatDataListAdapter.addItems(chatData);
                    //ChattingRoomActivity.this.runOnUiThread(updateUI);
                    chatDataListAdapter.upDateItemList(variableOfClass.getChatData()); // 이 부분입니다.
                    chatDataListAdapter.notifyDataSetChanged();
                    Log.d("태일3", String.valueOf(variableOfClass.getChatData()));
                    Log.d("태일3", String.valueOf(chatDataListAdapter.getCount()));
                    editText.setText("");

                    // textView.setText(textView.getText().toString() + "\n" + editText.getText().toString());  // 내용을 추가

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                variableOfClass.getChatData().clear();
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
