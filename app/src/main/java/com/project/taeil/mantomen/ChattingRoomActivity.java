package com.project.taeil.mantomen;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.project.taeil.mantomen.firebase.ChatModel;
import com.project.taeil.mantomen.firebase.UserData;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

public class ChattingRoomActivity extends AppCompatActivity {

    // private Socket mSocket;
    String Token = null;
    RecyclerView recyclerView;
    EditText editText;
    Button sendbutton;
    Button resetbutton;
    Variable variable;
    VariableOfClass variableOfClass;

    private String uid; //채팅하는 ID
    private  String chatRoomUid= null;
    private String destinatonUid; //채팅 당하는 ID
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    public UserData user;
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

        uid = Variable.getUserID();
        if(getIntent().getStringExtra("destinationUid")==null){
            destinatonUid = VariableOfClass.getClassTutorID();
        }else{
            destinatonUid = getIntent().getStringExtra("destinationUid");
        }
        recyclerView = findViewById(R.id.recyclerView);
        editText = findViewById(R.id.edittext);
        sendbutton = findViewById(R.id.sendbutton);

        // ChatDataList =   //저장된 컬랙션호출

/*        chatDataListAdapter = new ChatDataListAdapter(this, variableOfClass.getChatData());   // 처음으로 호출되는
        chatlist.setAdapter(chatDataListAdapter);  // 어댑터연결*/

        sendbutton.setOnClickListener(new View.OnClickListener() {  //센드 버튼이자나
            @Override
            public void onClick(View v) {
                ChatModel chatModel = new ChatModel();
                chatModel.users.put(uid,true);
                chatModel.users.put(destinatonUid,true);

                if(chatRoomUid == null){
                    sendbutton.setEnabled(false);
                    FirebaseDatabase.getInstance().getReference().child("chatrooms").push().setValue(chatModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            checkChatRoom();
                        }
                    });
                }
                else{
                    ChatModel.Comment comment = new ChatModel.Comment();
                    comment.uid = uid;
                    comment.message = editText.getText().toString();
                    comment.timestamp = ServerValue.TIMESTAMP;
                    FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chatRoomUid).child("comments").push().setValue(comment);
                }
                sendPostToFCM(editText.getText().toString());
                editText.setText("");

                /*  JSONObject data = new JSONObject();
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
                }*/
            }
        });

        checkChatRoom();
    }



    void checkChatRoom() {

        FirebaseDatabase.getInstance().getReference().child("chatrooms").orderByChild("users/"+uid).equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() == null){
                    ChatModel newRoom = new ChatModel();
                    newRoom.users.put(uid, true);
                    newRoom.users.put(destinatonUid, true);
                    FirebaseDatabase.getInstance().getReference().child("chatrooms").push().setValue(newRoom).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            checkChatRoom();
                        }
                    });
                    return;
                }

                for (DataSnapshot item: dataSnapshot.getChildren()){
                    ChatModel chatModel =item.getValue(ChatModel.class);
                    if(chatModel.users.containsKey(destinatonUid) && chatModel.users.size() == 2){
                        chatRoomUid = item.getKey();
                        sendbutton.setEnabled(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ChattingRoomActivity.this));
                        recyclerView.setAdapter(new RecyclerViewAdapter());
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        List<ChatModel.Comment> comments;
        public RecyclerViewAdapter() {
            comments = new ArrayList<>();
            getMessageList();
        }
        void getMessageList(){
            FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chatRoomUid).child("comments").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    comments.clear();
                    for(DataSnapshot item : dataSnapshot.getChildren()){
                        comments.add(item.getValue(ChatModel.Comment.class));
                    }
                    notifyDataSetChanged();
                    recyclerView.scrollToPosition(comments.size() - 1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message,viewGroup,false);
            return new MessageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            MessageViewHolder messageViewHolder = ((MessageViewHolder)viewHolder);
            if(comments.get(i).uid.equals(uid)){
                ((MessageViewHolder)viewHolder).textView_message.setText(comments.get(i).message);
                messageViewHolder.textView_message.setBackgroundResource(R.drawable.rightbubble9);
                messageViewHolder.textView_message.setTextSize(20);
                messageViewHolder.linearLayout_destination.setVisibility(View.INVISIBLE);
                messageViewHolder.linearLayout_main.setGravity(Gravity.RIGHT);
            }else{
                messageViewHolder.textView_name.setText(destinatonUid);
                messageViewHolder.linearLayout_destination.setVisibility(View.VISIBLE);
                messageViewHolder.textView_message.setBackgroundResource(R.drawable.leftbubble9);
                messageViewHolder.textView_message.setText(comments.get(i).message);
                messageViewHolder.textView_message.setTextSize(20);
                messageViewHolder.linearLayout_main.setGravity(Gravity.LEFT);
            }
            long unixTime = (long) comments.get(i).timestamp;
            Date date = new Date(unixTime);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            String time = simpleDateFormat.format(date);
            messageViewHolder.textView_timestamp.setText(time);
        }

        @Override
        public int getItemCount() {
            return comments.size();
        }

        private class MessageViewHolder extends RecyclerView.ViewHolder {
            public TextView textView_message;
            public LinearLayout linearLayout_main;
            public TextView textView_name;
            public LinearLayout linearLayout_destination;
            public TextView textView_timestamp;
            public MessageViewHolder(View view) {
                super(view);
                textView_message = view.findViewById(R.id.messageItem_textView_message);
                textView_name = view.findViewById(R.id.messageItem_textview_name);
                linearLayout_destination = view.findViewById(R.id.messageItem_linearlayout_destination);
                linearLayout_main = view.findViewById(R.id.messageItem_linearlayout_main);
                textView_timestamp = view.findViewById(R.id.messageItem_textview_timestamp);
            }
        }
    }

    private void sendPostToFCM(String message) {
        final String messagetext = message;
        final String FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send";
        final String SERVER_KEY = "AAAAZV2Ds24:APA91bHgckOLjemH48bNzrzhHg0biW2uyCxFzEjXgCNL3Taf9buFtjquNbED2yaIQwjcuaRgTOfgG5lVmwruIO0LTydYBUfO8XmZrXXAsSYEFCrO1OnAMMDCm7J34l6JfJTi2nZSdXHP";
        FirebaseDatabase.getInstance().getReference().child("chatrooms").orderByChild("users/" + uid).equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                FirebaseDatabase.getInstance().getReference().child("users/"+destinatonUid).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot item: dataSnapshot.getChildren()) {
                            Token = item.getValue().toString().trim();
                            Log.d("body",Token);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // FMC 메시지 생성 start
                            JSONObject root = new JSONObject();
                            JSONObject notification = new JSONObject();
                            notification.put("body", messagetext);
                            notification.put("title", getString(R.string.app_name));
                            root.put("notification", notification);
                            root.put("to",Token);
                            // FMC 메시지 생성 end

                            URL Url = new URL(FCM_MESSAGE_URL);
                            HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
                            conn.setRequestMethod("POST");
                            conn.setDoOutput(true);
                            conn.setDoInput(true);
                            conn.addRequestProperty("Authorization", "key=" + SERVER_KEY);
                            conn.setRequestProperty("Accept", "application/json");
                            conn.setRequestProperty("Content-type", "application/json");
                            OutputStream os = conn.getOutputStream();
                            os.write(root.toString().getBytes("utf-8"));
                            os.flush();
                            conn.getResponseCode();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}