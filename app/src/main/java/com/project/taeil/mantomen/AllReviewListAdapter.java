package com.project.taeil.mantomen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.taeil.mantomen.R;

import java.io.InputStream;
import java.util.List;

public class AllReviewListAdapter extends BaseAdapter{

    private LayoutInflater inflate;
    private RecyclerView.ViewHolder viewHolder;
    private Context context;
    private List<AllReview> AllReview;
    private int count;

    public AllReviewListAdapter(Context context, List<AllReview> AllReview){
        // 메인에서 데이터 리스트를 넘겨받음
        notifyDataSetChanged();
        this.context = context;
        this.count = AllReview.size();
        this.AllReview = AllReview;
    }

    @Override
    public int getCount() {
        // return count;
        return AllReview.size();

    }

    @Override
    public Object getItem(int i) {
        return AllReview.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.reviewlist,null);


        TextView userName = (TextView) v.findViewById(R.id.Reviewlist_userID);
        TextView Contents = (TextView) v.findViewById(R.id.Reviewlist_Contents);
        TextView Date = (TextView) v.findViewById(R.id.Reviewlist_Date);


        userName.setText(AllReview.get(i).getReviewUserID());
        Contents.setText(AllReview.get(i).getReviewContents());
        Date.setText(AllReview.get(i).getReviewDate());

        // v.setTag(AllReview.get(i).getClassName()); 셋태그가 뭘까?


        return v;
    }

}
