package com.example.taeil.mantomen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Mypage2Fragment extends Fragment {

    static Context mContext = null;
    final static String TAG = "AndroidNodeJS";

    Variable variable = Variable.getInstance();

    Bitmap bitmap;   // 비트맵을 담기위한 비트맵 객체선언

    private OnFragmentInteractionListener mListener;

    public Mypage2Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        LinearLayout mypage2fragment = (LinearLayout) inflater.inflate(R.layout.fragment_mypage2, container, false);

        Button makeclass = (Button) mypage2fragment.findViewById(R.id.Mypage2_MakeClass); //클래스값받아오는버튼
        Button gotomypage = (Button) mypage2fragment.findViewById(R.id.Mypage2_Mypage); //마이페이지로가는버튼
        Button tutorregister = mypage2fragment.findViewById(R.id.Mypage2_TutorRegister); // 튜터등록 버튼


        // ImageView userPicture = getActivity().findViewById(R.id.Mypage2_userPicture); // 유저사진

        new DownloadImageTask((ImageView) mypage2fragment.findViewById(R.id.Mypage2_userPicture))
                .execute(variable.getUserPicture());

        tutorregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if문으로 튜티인지 튜터인지 확인 후 튜터이면 진행
                if (variable.getUserIdentity().equals("Tutee")) {
                    Main2Activity.mContext = getActivity();
                    Variable variable = Variable.getInstance();

                    Intent GoToTutorregisterintent = new Intent(((Main2Activity) Main2Activity.mContext), TutorRegisterActivity.class); // TutorRegisterActivity로 보내는 인텐트
                    ((Main2Activity) Main2Activity.mContext).startActivity(GoToTutorregisterintent);

                } else {
                    Toast.makeText(getActivity(), "이미 등록되셨습니다.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });


        gotomypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Main2Activity) getActivity()).switchFragment(4);

            }
        });

        makeclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main2Activity.mContext = getActivity();

                Intent GoToMakeClassintent = new Intent(((Main2Activity) Main2Activity.mContext), MakeClassActivity.class); //MakeClass액티비티로 보내는 인텐트
                ((Main2Activity) Main2Activity.mContext).startActivity(GoToMakeClassintent);
                GoToMakeClassintent.putExtra("userID", "ABC");
                ((Main2Activity) Main2Activity.mContext).overridePendingTransition(0, 0);  //화면전환효과 없애기
                //getActivity().finish();  // 액티비티 삭제

            }
        });


        // Inflate the layout for this fragment
        return mypage2fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
