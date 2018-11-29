package com.project.taeil.mantomen;

import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.SkuDetails;

import java.util.ArrayList;


public class PurchaseHeartsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SkuDetails> products;
    // private InAppPurchaseItems items;
    private PointActivity activity;

    public PurchaseHeartsAdapter(Activity activity) {
        this.activity = (PointActivity) activity;
        this.context = activity;
        this.products = new ArrayList<>();
        // this.items = new InAppPurchaseItems();
    }
    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final SkuDetails sku = products.get(i);
        final ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.view_heartstore, viewGroup, false);
            holder = new ViewHolder();
           // holder.ivIcon = view.findViewById(R.id.iv_icon);
            holder.tvLabel = view.findViewById(R.id.tv_label);
            holder.tvPrice = view.findViewById(R.id.tv_price);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        // holder.ivIcon.setImageDrawable(context.getResources().getDrawable(items.getDrawable(sku.productId)));
        holder.tvLabel.setText(sku.title.replaceAll("\\(.*\\)", ""));
        holder.tvPrice.setText(sku.priceText);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.purchaseProduct(sku.productId);
            }
        });

        return view;
    }

    public void update(ArrayList<SkuDetails> products) {
        if (products != null) {
            this.products = products;
            notifyDataSetChanged();
        }
    }

    class ViewHolder {
        public ImageView ivIcon;
        public TextView tvLabel, tvPrice;
    }
}
