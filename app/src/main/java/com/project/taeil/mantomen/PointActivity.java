package com.project.taeil.mantomen;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.Constants;
import com.anjlab.android.iab.v3.SkuDetails;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.android.gms.common.internal.service.Common;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Integer.parseInt;

public class PointActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler {

    Variable variable;
    private Button btnPurchaseHeart;
    private MaterialDialog purchaseDialog;
    TextView Point;
    int MAX_PAGE = 3;
    Fragment cur_fragment = new Fragment();
    private PurchaseHeartsAdapter skusAdapter;




    private BillingProcessor bp;
    public static ArrayList<SkuDetails> products = new ArrayList<SkuDetails>();
    Button POINT;
    String productId = "p10000";

    String license = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlM8kG6jtaLKEXekt0ksubCwra/dsbYssKlCH+2mkQhv1IkOZYl3+6mEm5BhbwFaI2hRuTXyeNNbFERvCLERT5yEgJcUnYzsKv45vCBiqXdBq12gscPPhEnG1OVijCgHfS84HBkYBovAoW4zpUn5FxA7FTwOOVM1iom4W5vLCYSkyX8YwiYXYY2pfAdGO/wP4LvK9DAa7DAZDPKpqUKcNwe3hV52HiN3cKp9c+V/u5bXrkR9dZP2gQYXy/ikZFmJYe8XQzDv2MwiVV8iwGbAgJuuAdfQzdNvhON1ar4h5wJ904UqfyCjEE0JMXzONuvqsfudW6FBCZsa8Z74pShE25QIDAQAB";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);
        bp = new BillingProcessor(this, license, this);
        Point = findViewById(R.id.point2);

        Point.setText(variable.getUserPoint());
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new adapter(getSupportFragmentManager()));

//        init();

        //        POINT = findViewById(R.id.POINT);
//        POINT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                purchaseProduct(productId);
//            }
//        });


    }


    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        // 구매한 아이템 정보 //구매성공시 띄우는거
        SkuDetails sku = bp.getPurchaseListingDetails(productId);
        // 하트 100개 구매에 성공하였습니다! 메세지 띄우기
        String purchaseMessage = sku.title + "구매 성공!";
        //Common.showMessage(this, getCurrentFocus(), purchaseMessage);

        // 구매 처리
        int amount = 0;
        try {

            Toast.makeText(this, purchaseMessage, Toast.LENGTH_SHORT).show();

            JSONObject postDataParam = new JSONObject();

            postDataParam.put("userPoint", productId.substring(1));  // p1000에서 p를 지우고 1000만 보내는거

            int point2 = variable.getUserPoint();
            int point = Integer.parseInt(productId.substring(1));
            Log.d("포인트", String.valueOf(point));

            variable.setUserPoint(point+point2);   // 변수에 저장

            new PointBuyInsertData(PointActivity.this).execute(postDataParam);

            // 사용자의 하트 100개를 추가
//            Count += 10000;  // 여기에 포인트 추가하는거
//              variable.getUserPoint() += parseInt(productId.substring(1));
//            userStore.purchaseHearts(amount, tvNavigationHearts);


        } catch (Exception e) {
            //Toast.makeText(this, R.string.purchase_error, Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
        if (errorCode != Constants.BILLING_RESPONSE_RESULT_USER_CANCELED) {
            String errorMessage = "취소" + " (" + errorCode + ")";
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }


    private void init() {
        // 결제 아이템 다이얼로그

        // products = (ArrayList<SkuDetails>) bp.getPurchaseListingDetails(new InAppPurchaseItems().getIds());  // 내가볼땐 여기서
        SkuDetails sku1;
        SkuDetails sku2;
        SkuDetails sku3;
        SkuDetails sku4;

        SkuDetails sku = bp.getPurchaseListingDetails("p1000");  // 수동으로 추가
        products.add(sku);
        Log.e("스쿠1", sku.title);

//         sku2 = bp.getPurchaseListingDetails("p5000");  // 수동으로 추가
//         products.add(sku2);
//         Log.e("스쿠2", sku2.title);

        sku3 = bp.getPurchaseListingDetails("p10000");  // 수동으로 추가
        products.add(sku3);
        Log.e("스쿠3", sku3.title);

        sku4 = bp.getPurchaseListingDetails("p100000");  // 수동으로 추가
        products.add(sku4);
        Log.e("스쿠4", sku4.title);


        skusAdapter = new PurchaseHeartsAdapter(this);

        View purchaseView = getLayoutInflater().inflate(R.layout.layout_dialog_heartstore, null);
        ListView lvSkus = purchaseView.findViewById(R.id.lv_skus);

        skusAdapter.update(products);


        lvSkus.setAdapter(skusAdapter);


        purchaseDialog = new MaterialDialog.Builder(this)
                .customView(purchaseView, false)
                .negativeText("취소")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                }).build();


        btnPurchaseHeart = findViewById(R.id.POINT);
        btnPurchaseHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  // 이게 다이얼로그를 부르는 버튼클릭리스너
                purchaseDialog.show();
            }
        });


    }


    @Override
    public void onBillingInitialized() {   //구매준비가 되면 호출 다이얼로그를띄워야지

        init();

//        products = (ArrayList<SkuDetails>) bp.getPurchaseListingDetails(new InAppPurchaseItems().getIds());  // 내가볼땐 여기서
//        SkuDetails sku = bp.getPurchaseListingDetails(productId);
//        products.add(sku);
//
//        // 결제 아이템 다이얼로그 설정
//        skusAdapter = new PurchaseHeartsAdapter(this);
//
//        View purchaseView = getLayoutInflater().inflate(R.layout.layout_dialog_heartstore, null);
//        ListView lvSkus = purchaseView.findViewById(R.id.lv_skus);
//        lvSkus.setAdapter(skusAdapter);
//
//        skusAdapter.update(products);
//
//        purchaseDialog = new MaterialDialog.Builder(this)
//                .customView(purchaseView, false)
//                .negativeText("취소")
//                .onNegative(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        dialog.dismiss();
//                    }
//                })
//                .build();
//
//        skusAdapter.update(products);


    }


    public void purchaseProduct(final String productId) {
        if (bp.isPurchased(productId)) {
            bp.consumePurchase(productId);
        }
        bp.purchase(this, productId);  // 이게 그거랬음 띄우는거! 구매절차 띄우는거 그니까 저 프로덕트 아이디에 맞는거를 줘야함
    }

    private class InAppPurchaseItems {

        ArrayList<String> IDS = new ArrayList<String>();

        SkuDetails sku = bp.getPurchaseListingDetails(productId);   // 아이디를 주면 스쿠를 만들어줌


        public ArrayList<String> getIds() {
            Log.e("스쿠", sku.title);
            IDS.add(sku.title);
            return IDS;
        }

    }

    private class adapter extends FragmentPagerAdapter {
        public adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position < 0 || MAX_PAGE <= position)
                return null;
            switch (position) {
                case 0:
                    cur_fragment = new AdvPage_1();
                    break;
                case 1:
                    cur_fragment = new AdvPage_2();
                    break;
                case 2:
                    cur_fragment = new AdvPage_3();
                    break;
            }
            return cur_fragment;
        }

        @Override
        public int getCount() {
            return MAX_PAGE;
        }
    }
}
