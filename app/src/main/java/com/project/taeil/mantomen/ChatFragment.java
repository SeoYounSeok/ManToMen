package com.project.taeil.mantomen;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.taeil.mantomen.R;
import com.project.taeil.mantomen.firebase.ChatModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;


public class ChatFragment extends Fragment {
    String destinationUid = null;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat,container,false);

        RecyclerView recyclerView = view.findViewById(R.id.chatfragment_recyclerview);
        recyclerView.setAdapter(new ChatRecyclerViewAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));

        return view;
    }
    class ChatRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private List<ChatModel> chatModels = new ArrayList<>();
        private String uid;
        private ArrayList<String> destinantionUsers = new ArrayList<>();
        public ChatRecyclerViewAdapter() {
            uid = Variable.getUserID();
            FirebaseDatabase.getInstance().getReference().child("chatrooms").orderByChild("users/"+uid).equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    chatModels.clear();
                    for (DataSnapshot item : dataSnapshot.getChildren()){
                        chatModels.add(item.getValue(ChatModel.class));
                    }
                    notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat,viewGroup,false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
            final CustomViewHolder customViewHolder = (CustomViewHolder)viewHolder;
            for(String user: chatModels.get(i).users.keySet()){
                if(!user.equals(uid)){
                    Log.d("노나",user);
                    destinationUid = user;
                    destinantionUsers.add(destinationUid);
                }
            }
            FirebaseDatabase.getInstance().getReference().child("users").child(destinationUid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    customViewHolder.textView_title.setText(destinantionUsers.get(i));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            Map<String,ChatModel.Comment> commentMap = new TreeMap<>(Collections.<String>reverseOrder());
            commentMap.putAll(chatModels.get(i).comments);
            if(commentMap.keySet().toArray().length > 0) {

                String lastMessageKey = (String) commentMap.keySet().toArray()[0];
                customViewHolder.textView_lase_message.setText(chatModels.get(i).comments.get(lastMessageKey).message);


                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
                long unixTime = (long) chatModels.get(i).comments.get(lastMessageKey).timestamp;
                Date date = new Date(unixTime);
                customViewHolder.textView_timestamp.setText(simpleDateFormat.format(date));
            }
            customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ChattingRoomActivity.class);
                    intent.putExtra("destinationUid", destinantionUsers.get(i));
                    startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount() {
            return chatModels.size();
        }

        private class CustomViewHolder extends RecyclerView.ViewHolder {
            public TextView textView_title;
            public TextView textView_lase_message;
            public TextView textView_timestamp;
            public CustomViewHolder(View view) {
                super(view);
                textView_title = view.findViewById(R.id.chatitem_textview_title);
                textView_lase_message = view.findViewById(R.id.chatitem_textview_lastMessange);
                textView_timestamp = view.findViewById(R.id.chatitem_textview_timestamp);
            }
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}