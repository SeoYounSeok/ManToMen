package com.example.taeil.mantomen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;


public class ClassIntroFragment extends Fragment {

    VariableOfClass variableOfClass;

    public ClassIntroFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        variableOfClass.getInstance();
        LinearLayout classIntro = (LinearLayout) inflater.inflate(R.layout.fragment_class_intro, container, false); //밑에 주석처리는 리스트뷰 처리 이건 수정요망
        TextView ClassPlace = classIntro.findViewById(R.id.ClassIntro_ClassPlace);
        TextView ClassPlaceDetail = classIntro.findViewById(R.id.ClassIntro_ClassPlaceDetail);
        TextView ClassNumberOfTime = classIntro.findViewById(R.id.ClassIntro_ClassNumberOfTime);
        TextView ClassHour = classIntro.findViewById(R.id.ClassIntro_ClassHour);
        TextView ClassWeek = classIntro.findViewById(R.id.ClassIntro_ClassWeek);
        TextView ClassTotalPeople = classIntro.findViewById(R.id.ClassIntro_ClassTotalPeople);
        TextView ClassPrice = classIntro.findViewById(R.id.ClassIntro_ClassPrice);
        TextView ClassIntro = classIntro.findViewById(R.id.ClassIntro_ClassIntro);
        TextView ClassWhom = classIntro.findViewById(R.id.ClassIntro_ClassWhom);

        ImageView ClassPicture = classIntro.findViewById(R.id.ClassIntro_ClassPicture);


        ClassPlace.setText(variableOfClass.getClassPlace());
        ClassPlaceDetail.setText(variableOfClass.getClassPlaceDetail());
        ClassNumberOfTime.setText(variableOfClass.getClassNumberOfTime());
        ClassHour.setText(variableOfClass.getClassHour());
        ClassWeek.setText(variableOfClass.getClassWeek());
        ClassTotalPeople.setText(variableOfClass.getClassTotalPeople());
        ClassPrice.setText(variableOfClass.getClassPrice());
        ClassIntro.setText(variableOfClass.getClassIntro());
        ClassWhom.setText(variableOfClass.getClassWhom());


        new DownloadImageTask((ImageView) classIntro.findViewById(R.id.ClassIntro_ClassPicture))
                .execute(variableOfClass.getClassPicture());

        return classIntro;
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



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}