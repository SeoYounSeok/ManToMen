package com.project.taeil.mantomen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.project.taeil.mantomen.R;


public class ChatFragment extends Fragment {

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout chatfragment = (LinearLayout) inflater.inflate(R.layout.fragment_chat, container, false);


        Button gotochat = (Button) chatfragment.findViewById(R.id.gotochat); //마이페이지로가는버튼

        gotochat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent GoToMainintent = new Intent(getContext(), ChattingRoomActivity.class); //메인액티비티로 보내는 인텐트
                (getContext()).startActivity(GoToMainintent);

            }
        });


        return chatfragment;
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
