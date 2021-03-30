package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.stripe.android.CustomerSession;
import com.stripe.android.PaymentSession;
import com.stripe.android.PaymentSessionConfig;
import com.stripe.android.PaymentSessionData;
import com.stripe.android.Stripe;
import com.stripe.android.model.ConfirmPaymentIntentParams;
import com.stripe.android.model.PaymentMethod;
import com.stripe.android.view.BillingAddressFields;
import com.stripe.android.view.CardInputWidget;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

//import com.stripe.android.model.Card;
//import com.stripe.android.model.Card;

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
        stripe = new Stripe(
                getApplicationContext(),
                Objects.requireNonNull(getString(R.string.publishablekey))
        );


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
        Log.d("Activity", "Sit down is called");
        if (data != null) {
            paymentSession.handlePaymentData(requestCode, resultCode, data);
            Log.d("Activity", "Sit down is called");
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

                       // Log.d("Tree",payMeth);
                    }
                }

                // Update your UI here with other data
                if (data.isPaymentReadyToCharge()) {
                    // Use the data to complete your charge - see below.

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