package com.example.waam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.CustomerSession;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.PaymentIntentResult;
import com.stripe.android.PaymentSession;
import com.stripe.android.PaymentSessionConfig;
import com.stripe.android.PaymentSessionData;
import com.stripe.android.Stripe;
//import com.stripe.android.model.Card;
//import com.stripe.android.model.Card;
import com.stripe.android.model.ConfirmPaymentIntentParams;
import com.stripe.android.model.PaymentIntent;
import com.stripe.android.model.PaymentMethod;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.model.SourceTypeModel.*;
import com.stripe.android.view.BillingAddressFields;
import com.stripe.android.view.CardInputWidget;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaymentPage extends AppCompatActivity {
    String price;

    private static final String BACKEND_URL = "http://54.188.200.48/api/";
    //private OkHttpClient httpClient = new OkHttpClient();
    //private String paymentIntentClientSecret;
    CardInputWidget cardInputWidget;
    private PaymentSession paymentSession;
    private LinearLayout pay;
    private String payMeth;
    private String token;
    private Stripe stripe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment_page);
        Toolbar bar = findViewById(R.id.toolbar);
        setSupportActionBar(bar);
        price = getIntent().getStringExtra("price");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        token = getIntent().getStringExtra("");
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /*stripe = new Stripe(
                getApplicationContext(),
                Objects.requireNonNull(getString(R.string.publishablekey))
        );*/


        pay = findViewById(R.id.sub);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setUpPayment();
                //paymentSession.presentPaymentMethodSelection();
            }
        });




        //cardInputWidget = findViewById(R.id.cardInputWidget);
        //startCheckout();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            paymentSession.handlePaymentData(requestCode, resultCode, data);
        }
    }


    /*private void onPaymentSuccess(@NonNull final Response response) throws IOException {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> responseMap = gson.fromJson(
                Objects.requireNonNull(response.body()).string(),type
        );
        paymentIntentClientSecret = responseMap.get("clientSecret");
    }*/



    /*private void startCheckout() {
        // ...


        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        String json = "{"
                + "\"currency\":\"usd\","
                + "\"items\":["
                + "{\"id\":\"photo_subscription\"}"
                + "]"
                + "}";


        int amount = 2000;
        Map<String, Object> payMap = new HashMap<>();
        Map<String, Object> itemMap = new HashMap<>();

        List<Map<String, Object>> itemList = new ArrayList<>();
        payMap.put("currency", "usd");
        itemMap.put("id", "photo_subscription");
        itemMap.put("amount", amount);
        itemList.add(itemMap);
        payMap.put("items", itemList);
        String gsonTojson = new Gson().toJson(payMap);
        RequestBody body = RequestBody.create(gsonTojson,mediaType);
        
        Request request = new Request.Builder()
                .url(BACKEND_URL + "upgrademembership")
                .post(body)
                .build();
        httpClient.newCall(request)
                .enqueue(new PayCallback(this));

        // Hook up the pay button to the card widget and stripe instance
        LinearLayout payButton = findViewById(R.id.sub);
        payButton.setOnClickListener((View view) -> {
            PaymentMethodCreateParams params = cardInputWidget.getPaymentMethodCreateParams();
            if (params != null) {
                ConfirmPaymentIntentParams confirmParams = ConfirmPaymentIntentParams
                        .createWithPaymentMethodCreateParams(params, paymentIntentClientSecret);
                final Context context = getApplicationContext();
                stripe = new Stripe(
                        context,
                        PaymentConfiguration.getInstance(context).getPublishableKey()
                );



            }
        });
    }*/


    /*private void displayAlert(@NonNull String title,
                              @Nullable String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message);
        builder.setPositiveButton("Ok", null);
        builder.create().show();
    }*/

    /*private static final class PaymentResultCallback implements ApiResultCallback<PaymentIntentResult> {
        @NonNull
        private final WeakReference<PaymentPage> activityRef;

        PaymentResultCallback(@NonNull PaymentPage activity) {
            activityRef = new WeakReference<>(activity);
        }

        @Override
        public void onSuccess(@NonNull PaymentIntentResult result) {
            final PaymentPage activity = activityRef.get();
            if (activity == null) {
                return;
            }

            PaymentIntent paymentIntent = result.getIntent();
            PaymentIntent.Status status = paymentIntent.getStatus();
            if (status == PaymentIntent.Status.Succeeded) {
                // Payment completed successfully
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                /*activity.displayAlert(
                        "Payment completed",
                        gson.toJson(paymentIntent)
                );
            } else if (status == PaymentIntent.Status.RequiresPaymentMethod) {
                // Payment failed
                /*activity.displayAlert(
                        "Payment failed",
                        Objects.requireNonNull(paymentIntent.getLastPaymentError()).getMessage()
                );
            }
        }

        @Override
        public void onError(@NonNull Exception e) {
            final PaymentPage activity = activityRef.get();
            if (activity == null) {
                return;
            }

            // Payment request failed – allow retrying using the same payment method
            //activity.displayAlert("Error", e.toString());
        }
    }*/


    /*private static final class PayCallback implements Callback {
        @NonNull private final WeakReference<PaymentPage> activityRef;
        PayCallback(@NonNull PaymentPage activity) {
            activityRef = new WeakReference<>(activity);
        }
        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            final PaymentPage activity = activityRef.get();
            if (activity == null) {
                return;
            }
            activity.runOnUiThread(() ->
                    Toast.makeText(
                            activity, "Error: " + e.toString(), Toast.LENGTH_LONG
                    ).show()
            );
        }
        @Override
        public void onResponse(@NonNull Call call, @NonNull final Response response)
                throws IOException {
            final PaymentPage activity = activityRef.get();
            if (activity == null) {
                return;
            }
            if (!response.isSuccessful()) {
                activity.runOnUiThread(() ->
                        Toast.makeText(
                                activity, "Error: " + response.toString(), Toast.LENGTH_LONG
                        ).show()
                );
            } else {
                activity.onPaymentSuccess(response);
            }
        }
    }*/


    private void setUpPayment(){
        CustomerSession.initCustomerSession(this, new ExampleEphemeralKeyProvider(token));
        paymentSession = new PaymentSession(this, new PaymentSessionConfig.Builder()
                .setShippingInfoRequired(false)
                .setShippingMethodsRequired(false)
                .setBillingAddressFields(BillingAddressFields.None)
                .build());


        paymentSession.init(new PaymentSession.PaymentSessionListener() {

            @Override
            public void onCommunicatingStateChanged(boolean b) {

            }


            @Override
            public void onError(int i, @NotNull String s) {

            }

            @Override
            public void onPaymentSessionDataChanged(@NotNull PaymentSessionData data) {
                if (data.getUseGooglePay()) {
                    // customer intends to pay with Google Pay
                } else {
                    final PaymentMethod paymentMethod = data.getPaymentMethod();
                    if (paymentMethod != null) {
                        payMeth = paymentMethod.toString();
                    }
                }

                // Update your UI here with other data
                if (data.isPaymentReadyToCharge()) {
                    // Use the data to complete your charge - see below.

                    UpgradeMembership mem = new UpgradeMembership();
                    mem.setCurrency("USD");
                    mem.setPayment_method_id(payMeth);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    UserService upgrademem = retrofit.create(UserService.class);
                    Call<UpgradeMembershipResponse> membership = upgrademem.upgrade(mem,token);
                    membership.enqueue(new Callback<UpgradeMembershipResponse>() {
                        @Override
                        public void onResponse(Call<UpgradeMembershipResponse> call, retrofit2.Response<UpgradeMembershipResponse> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(PaymentPage.this,"An error occured",Toast.LENGTH_SHORT).show();
                            }

                            String clientSecret = response.body().getGetClientSecret();

                            confirmPayment(clientSecret,payMeth);

                            //UpgradeMembership members = response.body().getClientSecreet;
                            //confirmPayment("client",paymentIntentClientSecret);
                            Toast.makeText(PaymentPage.this,"Payment was succesful",Toast.LENGTH_SHORT).show();
                        }


                        @Override
                        public void onFailure(Call<UpgradeMembershipResponse> call, Throwable t) {

                        }
                    });
                }
            }
        });

    }


    private void confirmPayment(@NonNull String clientSecret, @NonNull String paymentMethodId) {
        stripe.confirmPayment(this,
                ConfirmPaymentIntentParams.createWithPaymentMethodId(
                        paymentMethodId,
                        clientSecret
                )
        );
    }

}