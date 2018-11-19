package com.example.taeil.mantomen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class ChatDataListAdapter extends BaseAdapter{

    private Context context;
    private List<ChatData> ChatData;
    private int count;

    public ChatDataListAdapter(Context context, List<ChatData> ChatData){
        // 메인에서 데이터 리스트를 넘겨받음
        notifyDataSetChanged();
        this.context = context;
        this.count = ChatData.size();
        this.ChatData = ChatData;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int i) {
        return ChatData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.chatlist,null);    // < 고쳐야한다

        // 클래스 확립될 동안 리스트뷰를 잠시꺼둠

        TextView chat = (TextView) v.findViewById(R.id.chatlist_text);


        chat.setText(ChatData.get(i).getChatuserID() + " : " + ChatData.get(i).getChatContents());

        v.setTag(ChatData.get(i).getChatuserID());

        return v;
    }



}
