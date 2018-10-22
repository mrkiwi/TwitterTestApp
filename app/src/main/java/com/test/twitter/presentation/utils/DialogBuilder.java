package com.test.twitter.presentation.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class DialogBuilder {

    public static void build(Context context, String message, String positiveBtn, String negativeBtn, DialogInterface.OnClickListener positive, boolean cancelable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setPositiveButton(positiveBtn, positive);
        builder.setNegativeButton(negativeBtn, (dialog, which) -> {dialog.dismiss(); });
        builder.setCancelable(cancelable);
        builder.create().show();
    }
}
