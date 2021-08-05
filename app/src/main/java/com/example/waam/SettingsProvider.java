package com.example.waam;

import android.content.Context;

import com.connectycube.auth.session.ConnectycubeSettings;
import com.connectycube.chat.ConnectycubeChatService;
import com.connectycube.chat.connections.AbstractChatConnectionFabric;
import com.connectycube.chat.connections.tcp.TcpChatConnectionFabric;
import com.connectycube.chat.connections.tcp.TcpConfigurationBuilder;
import com.connectycube.core.LogLevel;
import com.connectycube.users.model.ConnectycubeUser;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public class SettingsProvider {
    private static String applicationID = "4663";
    private static String authKey = "RWV8dBeCsCh6g2a";
    private static String authSecret = "yhuExsebKPu8F8S";
    private static String accountKey = "BqZHeqx5VVn9myVe4FY1";
    public static SettingsProvider INSTANCE;

    public final void initConnectycubeCredentials(@NotNull Context applicationContext) {
        Intrinsics.checkParameterIsNotNull(applicationContext, "applicationContext");
        this.checkConfigJson(applicationContext);
        this.checkUserJson(applicationContext);
        this.initCredentials(applicationContext);
}
    private void initCredentials(Context applicationContext) {
        ConnectycubeSettings.getInstance().init(applicationContext, applicationID, authKey, authSecret);
        ConnectycubeSettings var10000 = ConnectycubeSettings.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(var10000, "ConnectycubeSettings.getInstance()");
        var10000.setAccountKey(accountKey);
    }

    private void checkConfigJson(Context applicationContext) {
        CharSequence var2 = (CharSequence)applicationID;
        boolean var3 = false;
        if (var2.length() != 0) {
            var2 = (CharSequence)authKey;
            var3 = false;
            if (var2.length() != 0) {
                var2 = (CharSequence)authSecret;
                var3 = false;
                if (var2.length() != 0) {
                }
            }
        }

       // throw (Throwable)(new AssertionError(applicationContext.getString(1900076)));
    }

    private void checkUserJson(Context applicationContext) {
        ArrayList users = UserConfigKt.getAllUsersFromFile("users_configuration.json", applicationContext);
        int var3 = users.size();
        if (2 <= var3) {
            if (5 >= var3 && !this.isUsersEmpty(users)) {
            }
        }

       // throw (Throwable)(new AssertionError(applicationContext.getString(1900089)));
    }

    private boolean isUsersEmpty(ArrayList users) {
        Iterable $this$forEach$iv = (Iterable)users;
        boolean $i$f$forEach = false;
        Iterator var4 = $this$forEach$iv.iterator();

        String var10000;
        do {
            if (!var4.hasNext()) {
                return false;
            }

            Object element$iv = var4.next();
            ConnectycubeUser user = (ConnectycubeUser)element$iv;
            boolean var7 = false;
            var10000 = user.getLogin();
            Intrinsics.checkExpressionValueIsNotNull(var10000, "user.login");
            if (StringsKt.isBlank((CharSequence)var10000)) {
                break;
            }

            var10000 = user.getPassword();
            Intrinsics.checkExpressionValueIsNotNull(var10000, "user.password");
        } while(!StringsKt.isBlank((CharSequence)var10000));

        return true;
    }

    public final void initChatConfiguration() {
        ConnectycubeSettings var10000 = ConnectycubeSettings.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(var10000, "ConnectycubeSettings.getInstance()");
        var10000.setLogLevel(LogLevel.DEBUG);
        ConnectycubeChatService.setDebugEnabled(true);
        ConnectycubeChatService.setDefaultPacketReplyTimeout(30000);
        ConnectycubeChatService.setDefaultConnectionTimeout(30000L);
        ConnectycubeChatService.getInstance().setUseStreamManagement(true);
        TcpConfigurationBuilder builder = ((TcpConfigurationBuilder)(new TcpConfigurationBuilder()).setAllowListenNetwork(true)).setUseStreamManagement(true).setSocketTimeout(0);
        ConnectycubeChatService.setConnectionFabric((AbstractChatConnectionFabric)(new TcpChatConnectionFabric(builder)));
    }

    private SettingsProvider() {
    }

    static {
        INSTANCE = new SettingsProvider();
        applicationID = "4663";
        authKey = "RWV8dBeCsCh6g2a";
        authSecret = "yhuExsebKPu8F8S";
        accountKey = "BqZHeqx5VVn9myVe4FY1";
    }
}