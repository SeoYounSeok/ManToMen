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
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.Constants;
import com.anjlab.android.iab.v3.SkuDetails;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.android.gms.common.internal.service.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PointActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler {

    private View view;
    private ListView lvLog;
    private Button btnPurchaseHeart;
    private MaterialDialog purchaseDialog;

    int MAX_PAGE = 3;
    Fragment cur_fragment = new Fragment();

    private PurchaseHeartsAdapter skusAdapter;


    private BillingProcessor bp;
    public static ArrayList<SkuDetails> products = new ArrayList<SkuDetails>();
    Button POINT;
    String productId = "p10000";
    int Count = 0;
    String license = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlM8kG6jtaLKEXekt0ksubCwra/dsbYssKlCH+2mkQhv1IkOZYl3+6mEm5BhbwFaI2hRuTXyeNNbFERvCLERT5yEgJcUnYzsKv45vCBiqXdBq12gscPPhEnG1OVijCgHfS84HBkYBovAoW4zpUn5FxA7FTwOOVM1iom4W5vLCYSkyX8YwiYXYY2pfAdGO/wP4LvK9DAa7DAZDPKpqUKcNwe3hV52HiN3cKp9c+V/u5bXrkR9dZP2gQYXy/ikZFmJYe8XQzDv2MwiVV8iwGbAgJuuAdfQzdNvhON1ar4h5wJ904UqfyCjEE0JMXzONuvqsfudW6FBCZsa8Z74pShE25QIDAQAB";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);
        bp = new BillingProcessor(this, license, this);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new adapter(getSupportFragmentManager()));

        init();

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
            Count += 10000;
            Toast.makeText(this, Count, Toast.LENGTH_SHORT).show();
            // 사용자의 하트 100개를 추가
//            amount = Integer.parseInt(productId.substring(1));
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
        skusAdapter = new PurchaseHeartsAdapter(this);
        View purchaseView = getLayoutInflater().inflate(R.layout.layout_dialog_heartstore, null);
        ListView lvSkus = purchaseView.findViewById(R.id.lv_skus);
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

//        lvSkus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                purchaseProduct(productId);
//            }
//        });

//        RelativeLayout BUY = purchaseView.findViewById(R.id.BUY);
//
//        BUY.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        btnPurchaseHeart = findViewById(R.id.POINT);
        btnPurchaseHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  // 이게 다이얼로그를 부르는 버튼클릭리스너
                purchaseDialog.show();
            }
        });


        // skusAdapter.update(MainActivity.products);

    }


    @Override
    public void onBillingInitialized() {   //구매준비가 되면 호출 다이얼로그를띄워야지
        products = (ArrayList<SkuDetails>) bp.getPurchaseListingDetails(new InAppPurchaseItems().getIds());
        // Log.e("스쿠아이디",bp.getPurchaseListingDetails(new InAppPurchaseItems().getIds()).get(0).productId);
        // Sort ascending order
        Collections.sort(products, new Comparator<SkuDetails>() {   // 소트는 정렬하는거
            @Override
            public int compare(SkuDetails o1, SkuDetails o2) {
                if (o1.priceLong > o2.priceLong) {  // 이게 가격에 따라서 맥이는거같은데
                    return 1;
                } else if (o1.priceLong < o2.priceLong) {
                    return -1;
                } else return 0;
            }
        });

        // 결제 아이템 다이얼로그 설정
        skusAdapter = new PurchaseHeartsAdapter(this);
        View purchaseView = getLayoutInflater().inflate(R.layout.layout_dialog_heartstore, null);
        ListView lvSkus = purchaseView.findViewById(R.id.lv_skus);
        lvSkus.setAdapter(skusAdapter);

        purchaseDialog = new MaterialDialog.Builder(this)
                .customView(purchaseView, false)
                .negativeText("취소")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .build();

        skusAdapter.update(products);


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
            IDS.add(sku.title);
            Log.e("스쿠", sku.title);
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
