package com.example.ordersapplication.utils;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.ordersapplication.R;

public class Utils {

    public static AlertDialog.Builder showDialog(Context context, String message, DialogInterface.OnClickListener onClickListener) {
        androidx.appcompat.app.AlertDialog.Builder dialogo1 = new androidx.appcompat.app.AlertDialog.Builder(context);
        dialogo1.setTitle(R.string.aviso);
        dialogo1.setMessage(message);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton(R.string.aceptar, onClickListener);
        dialogo1.setNegativeButton(R.string.cancelar, (dialogo11, id) -> dialogo11.dismiss());
        return dialogo1;
    }
}
