package com.project.taeil.mantomen;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
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


    private BillingProcessor bp;
    public static ArrayList<SkuDetails> products;
    private MaterialDialog purchaseDialog;
    Button POINT;
    String productId = "p10000";
    int Count = 0;
    String license = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlM8kG6jtaLKEXekt0ksubCwra/dsbYssKlCH+2mkQhv1IkOZYl3+6mEm5BhbwFaI2hRuTXyeNNbFERvCLERT5yEgJcUnYzsKv45vCBiqXdBq12gscPPhEnG1OVijCgHfS84HBkYBovAoW4zpUn5FxA7FTwOOVM1iom4W5vLCYSkyX8YwiYXYY2pfAdGO/wP4LvK9DAa7DAZDPKpqUKcNwe3hV52HiN3cKp9c+V/u5bXrkR9dZP2gQYXy/ikZFmJYe8XQzDv2MwiVV8iwGbAgJuuAdfQzdNvhON1ar4h5wJ904UqfyCjEE0JMXzONuvqsfudW6FBCZsa8Z74pShE25QIDAQAB";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);
        bp = new BillingProcessor(this, license, this);
        POINT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchaseProduct(productId);
            }
        });
    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        // 구매한 아이템 정보
        SkuDetails sku = bp.getPurchaseListingDetails(productId);
        // 하트 100개 구매에 성공하였습니다! 메세지 띄우기
        String purchaseMessage = sku.title + "구매 성공!";
        //Common.showMessage(this, getCurrentFocus(), purchaseMessage);

        // 구매 처리
        int amount = 0;
        try {
            Count += 10000;
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


    @Override
    public void onBillingInitialized() {
        //products = (ArrayList<SkuDetails>) bp.getPurchaseListingDetails(new InAppPurchaseItems().getIds());
        // Sort ascending order
        Collections.sort(products, new Comparator<SkuDetails>() {
            @Override
            public int compare(SkuDetails o1, SkuDetails o2) {
                if (o1.priceLong > o2.priceLong) {
                    return 1;
                } else if (o1.priceLong < o2.priceLong) {
                    return -1;
                } else return 0;
            }
        });

        // 결제 아이템 다이얼로그 설정  // 아 이게 리스트뷰를 아 알았다 ㅋㅋㅋ 필요없죠 ㅅㅅㅅ

        View purchaseView = getLayoutInflater().inflate(R.layout.layout_dialog_heartstore, null);

        Button BTN = purchaseView.findViewById(R.id.BUY);

        purchaseDialog = new MaterialDialog.Builder(this)
                .customView(purchaseView, false)
                .negativeText("취소~")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .build();

        BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchaseProduct(productId);
            }
        });

    }


    public void purchaseProduct(final String productId) {
        if (bp.isPurchased(productId)) {
            bp.consumePurchase(productId);
        }
        bp.purchase(this, productId);  // 이게 그거랬음 띄우는거! 구매절차 띄우는거 그니까 저 프로덕트 아이디에 맞는거를 줘야함
    }


}
