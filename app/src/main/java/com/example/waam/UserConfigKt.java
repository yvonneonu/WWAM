package com.example.waam;

import android.content.Context;

import com.connectycube.users.model.ConnectycubeUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;

import kotlin.collections.CollectionsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

public class UserConfigKt {
    @NotNull
    public static ArrayList getAllUsersFromFile(@NotNull String filename, @NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(filename, "filename");
        Intrinsics.checkParameterIsNotNull(context, "context");
        InputStream jsonInputStream = null;
        try {
            jsonInputStream = context.getAssets().open(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intrinsics.checkExpressionValueIsNotNull(jsonInputStream, "jsonInputStream");
        Charset var5 = Charsets.UTF_8;
        boolean var6 = false;
        boolean var8 = false;
        Reader var7 = (Reader)(new InputStreamReader(jsonInputStream, var5));
        short var30 = 8192;
        boolean var9 = false;
        Closeable var4 = (Closeable)(var7 instanceof BufferedReader ? (BufferedReader)var7 : new BufferedReader(var7, var30));
        boolean var24 = false;
        Throwable var26 = (Throwable)null;

        String var29;
        try {
            BufferedReader p1 = (BufferedReader)var4;
            var8 = false;
            var29 = TextStreamsKt.readText((Reader)p1);
        } catch (Throwable var21) {
            var26 = var21;
            throw var21;
        }

        try {
            jsonInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type userType = (new TypeToken() {
        }).getType();
        Object var10000 = (new Gson()).fromJson(var29, userType);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "Gson().fromJson(jsonUsers, userType)");
        Map userMap = (Map)var10000;
        ArrayList users = new ArrayList();
        var8 = false;
        boolean var10 = false;

        for (Object o : userMap.entrySet()) {
            Map.Entry element$iv = (Map.Entry) o;
            boolean var14 = false;
            boolean var16 = false;
            String login = (String) element$iv.getKey();
            var16 = false;
            Map mapPassword = (Map) element$iv.getValue();
            users.add(new ConnectycubeUser(login, (String) CollectionsKt.elementAt((Iterable) mapPassword.keySet(), 0)));
        }

        return users;
    }
}
