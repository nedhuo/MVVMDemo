package com.goldze.base.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.goldze.base.R;

/**
 * 加載中Dialog
 */
public class LoadingDialog extends Dialog {
    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.LoadingDialog);
        setContentView(R.layout.dialog_loading);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

}
