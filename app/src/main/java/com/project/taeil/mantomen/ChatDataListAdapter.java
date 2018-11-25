package com.project.taeil.mantomen;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.taeil.mantomen.R;

import java.util.List;

public class ChatDataListAdapter extends BaseAdapter{

    private Context context;
    private List<ChatData> ChatData;
    private int count;

    public ChatDataListAdapter(Context context, List<ChatData> ChatData){
        // 메인에서 데이터 리스트를 넘겨받음
        // notifyDataSetChanged();
        this.context = context;
        this.count = ChatData.size();
        this.ChatData = ChatData;
    }


    public void upDateItemList(List<ChatData> chatData){
        this.ChatData = chatData;
        notifyDataSetChanged();
    }


//    public void setChatData(List<ChatData> chatData) {
//        this.ChatData = chatData;
//    }

    @Override
    public int getCount() {
        return ChatData.size();
    }

    @Override
    public Object getItem(int i) {
        return ChatData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
//
//    public void clearAllitems(){
//        ChatData.clear();
//    }

    public void addItems(ChatData chatData){
        this.ChatData.add(chatData);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.chatlist,null);

        // 클래스 확립될 동안 리스트뷰를 잠시꺼둠

        TextView chat = (TextView) v.findViewById(R.id.chatlist_text);

        chat.setText(ChatData.get(i).getChatuserID() + " : " + ChatData.get(i).getChatContents());

        v.setTag(ChatData.get(i).getChatuserID());

        return v;
    }



}
