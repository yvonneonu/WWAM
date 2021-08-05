package com.example.waam;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;

import com.connectycube.auth.session.ConnectycubeSessionManager;
import com.connectycube.auth.session.ConnectycubeSessionParameters;
import com.connectycube.core.EntityCallback;
import com.connectycube.core.exception.ResponseException;
import com.connectycube.users.ConnectycubeUsers;
import com.connectycube.users.model.ConnectycubeUser;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import timber.log.Timber;

//import com.sinch.android.rtc.SinchError;

public class Login extends BaseActivity{
        //BaseActivity implements  SinchService.StartFailedListener{
    private TextView signup;
    private ImageView logm;
    private TextView text;
    private TextView pressback;
    private String loginToken;
    private EditText editPass;
    private EditText editEmail;
    private String Email;
    private String Password;
    private ProgressDialog mSpinner;

    private ArrayList users;
    private ArrayAdapter adapter;
    private HashMap _$_findViewCache;






    final String url_Login = "http://ec2-52-36-253-117.us-west-2.compute.amazonaws.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//I stopped here thank you
        this.makeLogin();

       // mChatManager = AGApplication.the().getChatManager();
        //mRtmClient = mChatManager.getRtmClient();



        signup = findViewById(R.id.again);
        pressback = findViewById(R.id.back);
        Log.d("TAG", "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");

        logm = findViewById(R.id.button);
        text = findViewById(R.id.dateofbirth);

        editEmail = findViewById(R.id.editText2);
        editPass = findViewById(R.id.editText4);


        pressback.setOnClickListener(v -> GoBack());
        text.setOnClickListener(v -> AnotherActivity());
        signup.setOnClickListener(v -> SignUnpage());

        Log.d("show", "show");


        logm.setOnClickListener(v -> {
            //user = editEmail.getText().toString();
            Email = editEmail.getText().toString();
            Password = editPass.getText().toString();

            new loginAut().execute(Email, Password);

            if (TextUtils.isEmpty(editEmail.getText().toString()) || TextUtils.isEmpty(editPass.getText().toString())) {
                String message = "All inputs required";
                Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show();


            }else {
                LoginRequest loginRequest = new LoginRequest("email", "password");
                loginRequest.setEmail(editEmail.getText().toString());
                loginRequest.setPassword(editPass.getText().toString());


                GeneralFactory.getGeneralFactory(Login.this).loginToFireBase(loginRequest.getEmail(),loginRequest.getPassword(),loginRequest);
                //GeneralFactory.getGeneralFactory(Login.this).loginToFireBase(loginRequest.getEmail(),loginRequest.getPassword(),loginRequest,mRtmClient);
                //loginUser(loginRequest);

            }

        });


        ///mSpinner.dismiss();
    }

    private void makeLogin() {
        SharedPreferencesManager.Companion var10000 = (SharedPreferencesManager.Companion) SharedPreferencesManager.Companion;
        Context var10001 = this.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(var10001, "applicationContext");
        if (var10000.getInstance(var10001).currentUserExists()) {
            //ProgressBar var3 = (ProgressBar)this._$_findCachedViewById(R.id.progressbar);
          //  Intrinsics.checkExpressionValueIsNotNull("progressbar");
            //this.showProgress(var3);
            var10000 = (SharedPreferencesManager.Companion) SharedPreferencesManager.Companion;
            var10001 = this.getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(var10001, "applicationContext");
            ConnectycubeUser user = var10000.getInstance(var10001).getCurrentUser();
            TextView var2 = (TextView)this._$_findCachedViewById(id.text_view);
            Intrinsics.checkExpressionValueIsNotNull(var2, "text_view");
            Object[] var10003 = new Object[1];
            String var10006 = user.getFullName();
            if (var10006 == null) {
                var10006 = user.getLogin();
            }

            var10003[0] = var10006;
            var2.setText((CharSequence)this.getString(1900068, var10003));
            this.startDialogsScreen();
        } else {
            this.initUsers();
            this.initUserAdapter();
        }
    }

    private final void loginTo(ConnectycubeUser user) {
        ProgressBar var10001 = (ProgressBar)this._$_findCachedViewById(id.progressbar);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "progressbar");
        this.showProgress(var10001);
        Timber.d("called loginTo user = " + user, new Object[0]);
        final ArrayList usersLogins = new ArrayList();
        ArrayList var10000 = this.users;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("users");
        }

        Iterable $this$forEach$iv = (Iterable)var10000;
        int $i$f$forEach = false;
        Iterator var5 = $this$forEach$iv.iterator();

        ConnectycubeUser it;
        boolean $i$f$observe;
        while(var5.hasNext()) {
            Object element$iv = var5.next();
            it = (ConnectycubeUser)element$iv;
            $i$f$observe = false;
            usersLogins.add(it.getLogin());
        }

        Function0 factoryProducer$iv = (Function0)(new Function0() {
            // $FF: synthetic method
            // $FF: bridge method
            public Object invoke() {
                return this.invoke();
            }

            @NotNull
            public final UserListViewModelFactory invoke() {
                return InjectorUtils.INSTANCE.provideUserListViewModelFactory((Context)LoginActivity.this, usersLogins);
            }
        });
        int $i$f$viewModels = false;
        Lazy var17 = (Lazy)(new ViewModelLazy(Reflection.getOrCreateKotlinClass(UserListViewModel.class), (Function0)(new LoginActivity$loginTo$$inlined$viewModels$2(this)), factoryProducer$iv));
        Object var11 = null;
        Lazy userListViewModel = var17;
      <undefinedtype> $fun$errorProcessing$2 = new Function1() {
            // $FF: synthetic method
            // $FF: bridge method
            public Object invoke(Object var1) {
                this.invoke((String)var1);
                return Unit.INSTANCE;
            }

            public final void invoke(@NotNull String msg) {
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                LoginActivity var10000 = LoginActivity.this;
                ProgressBar var10001 = (ProgressBar)LoginActivity.this._$_findCachedViewById(id.progressbar);
                Intrinsics.checkExpressionValueIsNotNull(var10001, "progressbar");
                var10000.hideProgress(var10001);
                Toast.makeText(LoginActivity.this.getApplicationContext(), (CharSequence)msg, 0).show();
            }
        };
        it = null;
        $i$f$observe = false;
        LiveData $this$observe$iv = ((UserListViewModel)userListViewModel.getValue()).getUsers();
        LifecycleOwner owner$iv = (LifecycleOwner)this;
        $i$f$observe = false;
        Observer wrappedObserver$iv = (Observer)(new LoginActivity$loginTo$$inlined$observe$1(this, user, $fun$errorProcessing$2));
        $this$observe$iv.observe(owner$iv, wrappedObserver$iv);
    }

    private final boolean isSignedInREST(ConnectycubeUser user) {
        ConnectycubeSessionManager var10000 = ConnectycubeSessionManager.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(var10000, "ConnectycubeSessionManager.getInstance()");
        ConnectycubeSessionParameters var2 = var10000.getSessionParameters();
        Integer var3 = var2 != null ? var2.getUserId() : null;
        Object var10001 = user.getId();
        if (var10001 == null) {
            var10001 = false;
        }

        return Intrinsics.areEqual(var3, var10001);
    }

    private final void signInRestIdNeed(ConnectycubeUser user) {
        if (!this.isSignedInREST(user)) {
            this.signInRest(user);
        } else {
            this.startDialogsScreen();
        }

    }

    private final void signInRest(final ConnectycubeUser user) {
        ConnectycubeUsers.signIn(user).performAsync((EntityCallback)(new EntityCallback() {
            public void onSuccess(@NotNull ConnectycubeUser conUser, @NotNull Bundle args) {
                Intrinsics.checkParameterIsNotNull(conUser, "conUser");
                Intrinsics.checkParameterIsNotNull(args, "args");
                Companion var10000 = SharedPreferencesManager.Companion;
                Context var10001 = LoginActivity.this.getApplicationContext();
                Intrinsics.checkExpressionValueIsNotNull(var10001, "applicationContext");
                var10000.getInstance(var10001).saveCurrentUser(user);
                LoginActivity.this.startDialogsScreen();
            }

            // $FF: synthetic method
            // $FF: bridge method
            public void onSuccess(Object var1, Bundle var2) {
                this.onSuccess((ConnectycubeUser)var1, var2);
            }

            public void onError(@NotNull ResponseException ex) {
                Intrinsics.checkParameterIsNotNull(ex, "ex");
                LoginActivity var10000 = LoginActivity.this;
                ProgressBar var10001 = (ProgressBar)LoginActivity.this._$_findCachedViewById(id.progressbar);
                Intrinsics.checkExpressionValueIsNotNull(var10001, "progressbar");
                var10000.hideProgress(var10001);
                Toast.makeText(LoginActivity.this.getApplicationContext(), (CharSequence)LoginActivity.this.getString(1900032, new Object[]{ex.getMessage()}), 0).show();
            }
        }));
    }

    public final void startDialogsScreen() {
        ProgressBar var10001 = (ProgressBar)this._$_findCachedViewById(id.progressbar);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "progressbar");
        this.hideProgress(var10001);
        this.startDialogs();
    }

    public final void startDialogs() {
        Timber.d("ChatDialogActivity.start", new Object[0]);
        this.startChatDialogsActivity();
    }

    private final void startChatDialogsActivity() {
        Intent intent = new Intent((Context)this, ChatDialogActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    private final void initUsers() {
        this.users = UserConfigKt.getAllUsersFromFile("users_configuration.json", (Context)this);
    }

    private final void initUserAdapter() {
        ArrayList var10000 = new ArrayList;
        ArrayList var10002 = this.users;
        if (var10002 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("users");
        }

        var10000.<init>(var10002.size());
        ArrayList userList = var10000;
        var10000 = this.users;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("users");
        }

        Iterable $this$forEach$iv = (Iterable)var10000;
        int $i$f$forEach = false;
        Iterator var4 = $this$forEach$iv.iterator();

        while(var4.hasNext()) {
            Object element$iv = var4.next();
            ConnectycubeUser it = (ConnectycubeUser)element$iv;
            int var7 = false;
            userList.add(it.getLogin());
        }

        this.adapter = new ArrayAdapter((Context)this, 1300061, (List)userList);
        ListView var8 = (ListView)this._$_findCachedViewById(id.list_users);
        Intrinsics.checkExpressionValueIsNotNull(var8, "list_users");
        ArrayAdapter var10001 = this.adapter;
        if (var10001 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }

        var8.setAdapter((ListAdapter)var10001);
        var8 = (ListView)this._$_findCachedViewById(id.list_users);
        Intrinsics.checkExpressionValueIsNotNull(var8, "list_users");
        var8.setChoiceMode(1);
        var8 = (ListView)this._$_findCachedViewById(id.list_users);
        Intrinsics.checkExpressionValueIsNotNull(var8, "list_users");
        var8.setOnItemClickListener((AdapterView.OnItemClickListener)(new AdapterView.OnItemClickListener() {
            public final void onItemClick(AdapterView $noName_0, View $noName_1, int position, long $noName_3) {
                LoginActivity var10000 = LoginActivity.this;
                Object var10001 = LoginActivity.access$getUsers$p(LoginActivity.this).get(position);
                Intrinsics.checkExpressionValueIsNotNull(var10001, "users[position]");
                var10000.loginTo((ConnectycubeUser)var10001);
            }
        }));
    }

    // $FF: synthetic method
    public static final void access$signInRestIdNeed(LoginActivity $this, ConnectycubeUser user) {
        $this.signInRestIdNeed(user);
    }

    // $FF: synthetic method
    public static final ArrayList access$getUsers$p(LoginActivity $this) {
        ArrayList var10000 = $this.users;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("users");
        }

        return var10000;
    }

    // $FF: synthetic method
    public static final void access$setUsers$p(LoginActivity $this, ArrayList var1) {
        $this.users = var1;
    }

    public View _$_findCachedViewById(int var1) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }

        View var2 = (View)this._$_findViewCache.get(var1);
        if (var2 == null) {
            var2 = this.findViewById(var1);
            this._$_findViewCache.put(var1, var2);
        }

        return var2;
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }

    }
}
// LoginActivity$loginTo$$inlined$observe$1.java
package com.connectycube.messenger;

        import androidx.lifecycle.Observer;
        import com.connectycube.users.model.ConnectycubeUser;
        import kotlin.Metadata;

@Metadata(
        mv = {1, 1, 16},
        bv = {1, 0, 3},
        k = 3,
        d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
        d2 = {"<anonymous>", "", "T", "t", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Object;)V", "androidx/lifecycle/LiveDataKt$observe$wrappedObserver$1"}
)
public final class LoginActivity$loginTo$$inlined$observe$1 implements Observer {
    // $FF: synthetic field
    final LoginActivity this$0;
    // $FF: synthetic field
    final ConnectycubeUser $user$inlined;
    // $FF: synthetic field
    final <undefinedtype> $errorProcessing$2$inlined;

    public LoginActivity$loginTo$$inlined$observe$1(LoginActivity var1, ConnectycubeUser var2, Object var3) {
        this.this$0 = var1;
        this.$user$inlined = var2;
        this.$errorProcessing$2$inlined = var3;
    }

    public final void onChanged(Object t) {
        // $FF: Couldn't be decompiled
    }
}
// LoginActivity$loginTo$$inlined$viewModels$2.java
package com.connectycube.messenger;

        import androidx.activity.ComponentActivity;
        import androidx.lifecycle.ViewModelStore;
        import kotlin.Metadata;
        import kotlin.jvm.functions.Function0;
        import kotlin.jvm.internal.Intrinsics;
        import kotlin.jvm.internal.Lambda;
        import org.jetbrains.annotations.NotNull;

@Metadata(
        mv = {1, 1, 16},
        bv = {1, 0, 3},
        k = 3,
        d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"},
        d2 = {"<anonymous>", "Landroidx/lifecycle/ViewModelStore;", "VM", "Landroidx/lifecycle/ViewModel;", "invoke", "androidx/activity/ActivityViewModelLazyKt$viewModels$1"}
)
public final class LoginActivity$loginTo$$inlined$viewModels$2 extends Lambda implements Function0 {
    // $FF: synthetic field
    final ComponentActivity $this_viewModels;

    public LoginActivity$loginTo$$inlined$viewModels$2(ComponentActivity var1) {
        super(0);
        this.$this_viewModels = var1;
    }

    // $FF: synthetic method
    // $FF: bridge method
    public Object invoke() {
        return this.invoke();
    }

    @NotNull
    public final ViewModelStore invoke() {
        ViewModelStore var10000 = this.$this_viewModels.getViewModelStore();
        Intrinsics.checkExpressionValueIsNotNull(var10000, "viewModelStore");
        return var10000;
    }
}
// LoginActivity$WhenMappings.java
package com.connectycube.messenger;

        import com.connectycube.messenger.vo.Status;
        import kotlin.Metadata;

// $FF: synthetic class
@Metadata(
        mv = {1, 1, 16},
        bv = {1, 0, 3},
        k = 3
)
public final class LoginActivity$WhenMappings {
    // $FF: synthetic field
    public static final int[] $EnumSwitchMapping$0 = new int[Status.values().length];

    static {
        $EnumSwitchMapping$0[Status.SUCCESS.ordinal()] = 1;
        $EnumSwitchMapping$0[Status.ERROR.ordinal()] = 2;
        $EnumSwitchMapping$0[Status.LOADING.ordinal()] = 3;
    }
}
// LoginActivity$loginTo$$inlined$viewModels$1.java
package com.connectycube.messenger;

        import androidx.activity.ComponentActivity;
        import androidx.lifecycle.ViewModelProvider.Factory;
        import kotlin.Metadata;
        import kotlin.jvm.functions.Function0;
        import kotlin.jvm.internal.Intrinsics;
        import kotlin.jvm.internal.Lambda;
        import org.jetbrains.annotations.NotNull;

@Metadata(
        mv = {1, 1, 16},
        bv = {1, 0, 3},
        k = 3,
        d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"},
        d2 = {"<anonymous>", "Landroidx/lifecycle/ViewModelProvider$Factory;", "VM", "Landroidx/lifecycle/ViewModel;", "invoke", "androidx/activity/ActivityViewModelLazyKt$viewModels$factoryPromise$1"}
)
public final class LoginActivity$loginTo$$inlined$viewModels$1 extends Lambda implements Function0 {
    // $FF: synthetic field
    final ComponentActivity $this_viewModels;

    public LoginActivity$loginTo$$inlined$viewModels$1(ComponentActivity var1) {
        super(0);
        this.$this_viewModels = var1;
    }

    // $FF: synthetic method
    // $FF: bridge method
    public Object invoke() {
        return this.invoke();
    }

    @NotNull
    public final Factory invoke() {
        Factory var10000 = this.$this_viewModels.getDefaultViewModelProviderFactory();
        Intrinsics.checkExpressionValueIsNotNull(var10000, "defaultViewModelProviderFactory");
        return var10000;
    }
    private void showSpinner() {
        mSpinner = new ProgressDialog(this);
        mSpinner.setTitle("Logging in");
        mSpinner.setMessage("Please wait...");
        mSpinner.show();
    }


    private void Themain() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        // finish();
    }
    private void AnotherActivity() {
        Intent intent = new Intent(Login.this, Another.class);
        startActivity(intent);
        // finish();
    }

    private void SignUnpage() {
        Intent intent = new Intent(Login.this, SignUp.class);
        startActivity(intent);
        // finish();
    }


    private void GoBack() {
        Intent intent = new Intent(Login.this, ViewPager.class);
        startActivity(intent);
    }



    @Override
    protected void onPause() {
        if (mSpinner != null) {
            mSpinner.dismiss();
        }
        super.onPause();
    }

    private void openPlaceCallActivity() {

    }

    private class loginAut extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String Email = strings[0];
            String Password = strings[1];

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("user_id", Email)
                    .add("user_password", Password)
                    .build();

            Request request = new Request.Builder()
                    .url(url_Login)
                    .post(formBody)
                    .build();

            okhttp3.Response response = null;

            try {
                response = okHttpClient.newCall(request).execute();
                if (response.isSuccessful()){
                    String result = response.body().string();
                    if (result.equalsIgnoreCase("login")){
                        Intent i = new Intent(Login.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    }else {
                        Toast.makeText(Login.this,"Email or Password mismatch!", Toast.LENGTH_LONG).show();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

    }



}