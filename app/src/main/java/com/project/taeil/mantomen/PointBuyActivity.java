package com.project.taeil.mantomen;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.SkuDetails;
import com.anjlab.android.iab.v3.TransactionDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.security.AccessController.getContext;

public class PointBuyActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler {

    private BillingProcessor mBillingProcessor;
    private String liceneseKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlM8kG6jtaLKEXekt0ksubCwra/dsbYssKlCH+2mkQhv1IkOZYl3+6mEm5BhbwFaI2hRuTXyeNNbFERvCLERT5yEgJcUnYzsKv45vCBiqXdBq12gscPPhEnG1OVijCgHfS84HBkYBovAoW4zpUn5FxA7FTwOOVM1iom4W5vLCYSkyX8YwiYXYY2pfAdGO/wP4LvK9DAa7DAZDPKpqUKcNwe3hV52HiN3cKp9c+V/u5bXrkR9dZP2gQYXy/ikZFmJYe8XQzDv2MwiVV8iwGbAgJuuAdfQzdNvhON1ar4h5wJ904UqfyCjEE0JMXzONuvqsfudW6FBCZsa8Z74pShE25QIDAQAB";
    private int point = 0;

    /**
     * InApp 초기화
     */
    private void initInApp() {
        mBillingProcessor = new BillingProcessor(this, liceneseKEY, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_buy);

        mBillingProcessor = new BillingProcessor(this, liceneseKEY, this);

        mBillingProcessor = BillingProcessor.newBillingProcessor(this, liceneseKEY, this); // doesn't bind
        mBillingProcessor.initialize(); // binds

        mBillingProcessor.purchase(PointBuyActivity.this, "p10000");
        Bundle extraParams = new Bundle();
        extraParams.putString("accountId", "MY_ACCOUNT_ID");

    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        // 구매한 아이템 정보
        SkuDetails skuDetails = mBillingProcessor.getPurchaseListingDetails(productId);
//        showOneButtonDialog(skuDetails.title + getString(R.string.success_purchase), new OnOkListener() {
//            @Override
//            public void notifyOk() {
//                // 사용자 구매 처리 처리
//                Log.d(com.kcs.android.test.common.Constants.LOG_TAG,"사용자 구매 처리 처리");
//                I.P.setPurchaseInAppForRemoveAD(InAppManageActivity.this, true);
//                setResult(Activity.RESULT_OK);
//                finish();
//            }
//        });

    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
        Log.e("에러", "onBillingError : " + error.getMessage() + ", Error Code : " + errorCode);


        //        showOneButtonDialog("에러", new OnOkListener() {
//            @Override
//            public void notifyOk() {
//
//            }
//        });
    }


    /* BillingProcessor 초기화 되며, 구매 준비가 되면 호출 된다.
     * - 구매할 아이템 리스트 구성 및 사용자에게 보여주기 코드 구현
     *
     * - 아이템 이름만 가져오는 경우 SkuDetails.title.replaceAll("\\(.*\\)", "")
     * - SkuDetails.priceText: 아이템 가격에 현지 화폐 단위를 붙인 String을 리턴한다. 예를들면 '1.99$'이런 식이다.
     * - SkuDetails.priceLong: 가격을 long 으로 리턴한다. 1.99 이런식이다.
     * - SkuDetails.productId: 제품ID(sku)를 가지고 온다. 이를 통해서 어떤 아이템을 구매했는지 판별 가능하다.
     * */
    @Override
    public void onBillingInitialized() {

//        mProduct = (SkuDetails) mBillingProcessor.getPurchaseListingDetails(mInAppPurchaseItemID);
//        String price = "";
//        if (mProduct == null){
//            mProductId = "0";
//        }else{
//            mProductId = mProduct.productId;
//            price = "\n(" + mProduct.priceText + ")";
//        }
//        btnPurchase.setText(getString(R.string.txt_in_app_btn_subtitle) + price);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //        super.onActivityResult(requestCode, resultCode, data);
        if (mBillingProcessor.handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
    }

//    /**
//     * 구매하기
//     */
//    private void purchaseProduct(final String productId) {
//
//        if (mBillingProcessor.isPurchased(productId)) {
//            // 구매하였으면 소비하여 없앤 후 다시 구매하게 하는 로직. 만약 1번 구매 후 계속 이어지게 할 것이면 아래 함수는 주석처리.
//            // mBillingProcessor.consumePurchase(productId);
//        }
//​
//        mBillingProcessor.purchase(this, productId);
//    }


    @Override
    public void onDestroy() {
        if (mBillingProcessor != null) {
            mBillingProcessor.release();
        }
        super.onDestroy();
    }

}
