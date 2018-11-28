package com.project.taeil.mantomen;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.vending.billing.IInAppBillingService;
import com.google.android.gms.common.internal.service.Common;
import com.project.taeil.mantomen.util.IabHelper;
import com.project.taeil.mantomen.util.IabResult;
import com.project.taeil.mantomen.util.Inventory;
import com.project.taeil.mantomen.util.Purchase;

import java.util.List;

public class PointBuyActivity extends AppCompatActivity {

    private static final String TAG = "InAppBilling";
    IabHelper mHelper;
    static final String ITEM_SKU = "android.test.purchased";

    private Button clickButton;
    private Button buyButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_buy);

        buyButton = findViewById(R.id.buyButton);
        clickButton = findViewById(R.id.clickButton);
        clickButton.setEnabled(false);
        String base64EncodedPublicKey = "내 라이센스 키";

        mHelper = new IabHelper(this, base64EncodedPublicKey);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            @Override
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    Log.d(TAG, "성공");
                } else {
                    Log.d(TAG, "실패");
                }
            }
        });


    }


    public void buttonClicked(View view) {
        clickButton.setEnabled(false);
        buyButton.setEnabled(true);
    }

    public void buyClick(View view) {
        mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001, mPurchaseFinishedListener, "mypurchasetoken");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {

            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        @Override
        public void onIabPurchaseFinished(IabResult result, Purchase info) {
            if (result.isFailure()) {
                return;
            } else if (info.getSku().equals(ITEM_SKU)) {
                consumeItem();
                buyButton.setEnabled(false);
            }
        }
    };

    public void consumeItem() {
        mHelper.queryInventoryAsync(mReceivedInventoryListener);
    }

    IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        @Override
        public void onQueryInventoryFinished(IabResult result, Inventory inv) {
            if (result.isFailure()) {

            } else {
                mHelper.consumeAsync(inv.getPurchase(ITEM_SKU), mConsumeFinishedListener);
            }
        }
    };
    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
        @Override
        public void onConsumeFinished(Purchase purchase, IabResult result) {
            if (result.isSuccess()) {
                clickButton.setEnabled(true);
            } else {
            }
        }



    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mHelper != null) mHelper.dispose();
        mHelper = null;
    }
}




