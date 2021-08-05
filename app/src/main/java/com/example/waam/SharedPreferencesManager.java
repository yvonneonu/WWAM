package com.example.waam;

import android.content.Context;
import android.content.SharedPreferences;

import com.connectycube.users.model.ConnectycubeUser;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.Intrinsics;

public class SharedPreferencesManager {
    @NotNull
    public static final String CUBE_SHARED_PREFERENCES_NAME = "connectycube_messenger";
    @NotNull
    public static final String CUBE_USER_LOGIN = "cube_user_login";
    @NotNull
    public static final String CUBE_USER_PASSWORD = "cube_user_password";
    @NotNull
    public static final String CUBE_USER_ID = "cube_user_id";
    @NotNull
    public static final String CUBE_USER_NAME = "cube_user_name";
    @NotNull
    public static final String CUBE_USER_AVATAR = "cube_user_avatar";
   // public static Object Companion;

    public static final SharedPreferencesManager.Companion COMPANION = new SharedPreferencesManager.Companion();
    public static Object Companion;
    private SharedPreferences sharedPreferences;
    @NotNull
    private final Context applicationContext;
    private static SharedPreferencesManager instance;

   // public static final SharedPreferencesManager.Companion Companion = new SharedPreferencesManager.Companion((DefaultConstructorMarker)null);

    public final void saveCurrentUser(@NotNull ConnectycubeUser user) {
        Intrinsics.checkParameterIsNotNull(user, "user");
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString("cube_user_login", user.getLogin());
        editor.putString("cube_user_password", user.getPassword());
        Integer var10002 = user.getId();
        Intrinsics.checkExpressionValueIsNotNull(var10002, "user.id");
        editor.putInt("cube_user_id", var10002);
        editor.putString("cube_user_name", user.getFullName());
        editor.putString("cube_user_avatar", user.getAvatar());
        editor.apply();
    }

    public final void deleteCurrentUser() {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.remove("cube_user_login");
        editor.remove("cube_user_password");
        editor.remove("cube_user_id");
        editor.remove("cube_user_name");
        editor.remove("cube_user_avatar");
        editor.apply();
    }

    public final void updateCurrentUserName(@NotNull ConnectycubeUser user) {
        Intrinsics.checkParameterIsNotNull(user, "user");
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString("cube_user_name", user.getFullName());
        editor.apply();
    }

    public final void updateCurrentUserAvatar(@NotNull ConnectycubeUser user) {
        Intrinsics.checkParameterIsNotNull(user, "user");
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString("cube_user_avatar", user.getAvatar());
        editor.apply();
    }

    public final boolean currentUserExists() {
        return this.sharedPreferences.contains("cube_user_login");
    }

    @NotNull
    public final ConnectycubeUser getCurrentUser() {
        ConnectycubeUser currentUser = new ConnectycubeUser();
        if (this.currentUserExists()) {
            boolean var3 = false;
            boolean var4 = false;
            boolean var6 = false;
            currentUser.setLogin(this.sharedPreferences.getString("cube_user_login", (String)null));
            currentUser.setPassword(this.sharedPreferences.getString("cube_user_password", (String)null));
            currentUser.setId(this.sharedPreferences.getInt("cube_user_id", 0));
            currentUser.setFullName(this.sharedPreferences.getString("cube_user_name", (String)null));
            currentUser.setAvatar(this.sharedPreferences.getString("cube_user_avatar", (String)null));
        }

        return currentUser;
    }

    @NotNull
    public final Context getApplicationContext() {
        return this.applicationContext;
    }

    private SharedPreferencesManager(Context applicationContext) {
        this.applicationContext = applicationContext;
        SharedPreferences var10001 = this.applicationContext.getSharedPreferences("connectycube_messenger", 0);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "applicationContext.getSh…ME, Context.MODE_PRIVATE)");
        this.sharedPreferences = var10001;
    }

    // $FF: synthetic method
   // public SharedPreferencesManager(Context applicationContext, DefaultConstructorMarker $constructor_marker) {
   //     this(applicationContext);
   // }


    public static final class Companion {
        @NotNull
        public final SharedPreferencesManager getInstance(@NotNull Context applicationContext) {
            Intrinsics.checkParameterIsNotNull(applicationContext, "applicationContext");
            if (SharedPreferencesManager.instance == null) {
                SharedPreferencesManager.instance = new SharedPreferencesManager(applicationContext);
            }

            SharedPreferencesManager var10000 = SharedPreferencesManager.instance;
            if (var10000 == null) {
                Intrinsics.throwNpe();
            }

            return var10000;
        }

        private Companion() {
        }

        // $FF: synthetic method
       // public Companion(DefaultConstructorMarker $constructor_marker) {
          //  this();
        //}
    }
}
