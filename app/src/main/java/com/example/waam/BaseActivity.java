package com.example.waam;

import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

import kotlin.jvm.internal.Intrinsics;

public class BaseActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;

    public final void hideProgress(@NotNull ProgressBar progressbar) {
        Intrinsics.checkParameterIsNotNull(progressbar, "progressbar");
        this.getWindow().clearFlags(16);
        progressbar.setVisibility(View.INVISIBLE);
    }

    public final void showProgress(@NotNull ProgressBar progressbar) {
        Intrinsics.checkParameterIsNotNull(progressbar, "progressbar");
        this.getWindow().setFlags(16, 16);
        progressbar.setVisibility(View.VISIBLE);
    }

    public final void showProgressValueIfNotNull(@NotNull ProgressBar progressbar, @Nullable Integer progress) {
        Intrinsics.checkParameterIsNotNull(progressbar, "progressbar");
        this.showProgress(progressbar);
        if (progress != null) {
            if (progressbar.isIndeterminate()) {
                progressbar.setIndeterminate(false);
            }

            progressbar.setProgress(progress);
        } else if (!progressbar.isIndeterminate()) {
            progressbar.setIndeterminate(true);
        }

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
